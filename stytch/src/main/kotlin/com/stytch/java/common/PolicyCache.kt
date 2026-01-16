package com.stytch.java.common

import com.stytch.java.b2b.api.rbac.RBAC
import com.stytch.java.b2b.api.rbacorganizations.Organizations
import com.stytch.java.b2b.models.rbac.OrgPolicy
import com.stytch.java.b2b.models.rbac.Policy
import com.stytch.java.b2b.models.rbac.PolicyRequest
import com.stytch.java.b2b.models.rbac.PolicyRole
import com.stytch.java.b2b.models.rbacorganizations.GetOrgPolicyRequest
import com.stytch.java.b2b.models.sessions.AuthorizationCheck
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.Instant

public class TenancyException(
    subjectOrgId: String,
    authCheckOrgId: String,
) : RuntimeException("Subject organizationId $subjectOrgId does not match authZ request organizationId $authCheckOrgId")

public class PermissionException(
    authorizationCheck: AuthorizationCheck,
) : RuntimeException("Permission denied for request $authorizationCheck")

private data class CachedOrgPolicy(
    val orgPolicy: OrgPolicy,
    val lastUpdate: Instant,
)

internal class PolicyCache(
    private val client: RBAC,
    coroutineScope: CoroutineScope,
    private val organizations: Organizations? = null,
) {
    private val job = SupervisorJob(coroutineScope.coroutineContext[Job])
    private val scope = CoroutineScope(coroutineScope.coroutineContext + job)
    private var cachedPolicy: Policy? = null
    private val cachedOrgPolicies: MutableMap<String, CachedOrgPolicy> = mutableMapOf()
    private var policyLastUpdate: Instant? = null
    private var backgroundRefreshStarted = false

    companion object {
        private const val CACHE_TTL_SECONDS = 3600L // 1 hour
        private const val REFRESH_INTERVAL_MS = 3600000L // 1 hour in milliseconds
    }

    private fun getPolicy(invalidate: Boolean = false): Policy {
        val isMissing = cachedPolicy == null || policyLastUpdate == null
        val isStale = policyLastUpdate == null || Duration.between(policyLastUpdate, Instant.now()).seconds > CACHE_TTL_SECONDS
        if (invalidate || isMissing || isStale) {
            refreshPolicy()
        }

        // Start background refresh after first successful fetch
        if (!backgroundRefreshStarted && cachedPolicy != null) {
            startBackgroundRefresh()
            backgroundRefreshStarted = true
        }

        return cachedPolicy ?: throw Exception("Error fetching the policy")
    }

    private fun getOrgPolicy(
        orgId: String,
        invalidate: Boolean = false,
    ): OrgPolicy? {
        val cached = cachedOrgPolicies[orgId]
        val isMissing = cached == null
        val isStale = cached != null && Duration.between(cached.lastUpdate, Instant.now()).seconds > CACHE_TTL_SECONDS

        if (invalidate || isMissing || isStale) {
            refreshOrgPolicy(orgId)
        }

        return cachedOrgPolicies[orgId]?.orgPolicy
    }

    private fun refreshOrgPolicy(orgId: String) {
        val orgs = organizations ?: client.organizations
        when (val result = orgs.getOrgPolicyCompletable(GetOrgPolicyRequest(orgId)).get()) {
            is StytchResult.Success -> {
                result.value.orgPolicy?.let { orgPolicy ->
                    cachedOrgPolicies[orgId] = CachedOrgPolicy(orgPolicy, Instant.now())
                }
            }

            else -> {}
        }
    }

    private fun refreshPolicy() {
        when (val result = client.policyCompletable(PolicyRequest()).get()) {
            is StytchResult.Success -> {
                cachedPolicy = result.value.policy
                policyLastUpdate = Instant.now()
            }

            else -> {}
        }
    }

    private fun startBackgroundRefresh() {
        scope.launch {
            while (isActive) {
                delay(REFRESH_INTERVAL_MS)
                refreshPolicy()
                // Refresh all cached org policies
                cachedOrgPolicies.keys.toList().forEach { orgId ->
                    refreshOrgPolicy(orgId)
                }
            }
        }
    }

    /**
     * Cancels the background refresh job.
     * This allows the refresh job to be stopped independently of the parent scope.
     */
    fun cancelBackgroundRefresh() {
        job.cancel()
    }

    fun performAuthorizationCheck(
        subjectRoles: List<String>,
        subjectOrgId: String,
        authorizationCheck: AuthorizationCheck,
    ) {
        if (authorizationCheck.organizationId != subjectOrgId) {
            throw TenancyException(subjectOrgId, authorizationCheck.organizationId)
        }
        val policy = getPolicy()
        val orgPolicy = getOrgPolicy(subjectOrgId)

        // Combine roles from both global policy and org-specific policy
        val allRoles: List<PolicyRole> =
            buildList {
                addAll(policy.roles)
                orgPolicy?.roles?.let { addAll(it) }
            }

        val hasMatchingActionAndResource =
            allRoles
                .filter { it.roleId in subjectRoles }
                .flatMap { it.permissions }
                .filter {
                    val hasMatchingAction = it.actions.contains("*") || it.actions.contains(authorizationCheck.action)
                    val hasMatchingResource = it.resourceId == authorizationCheck.resourceId
                    return@filter hasMatchingAction && hasMatchingResource
                }.isNotEmpty()
        hasMatchingActionAndResource && return
        throw PermissionException(authorizationCheck)
    }
}
