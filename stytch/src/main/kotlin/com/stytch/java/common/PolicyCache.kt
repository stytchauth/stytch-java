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
import java.time.Duration
import java.time.Instant

public class TenancyException(
    subjectOrgId: String,
    authCheckOrgId: String,
) : RuntimeException("Subject organizationId $subjectOrgId does not match authZ request organizationId $authCheckOrgId")

private data class CachedOrgPolicy(
    val orgPolicy: OrgPolicy,
    val lastUpdate: Instant,
)

internal class PolicyCache(
    private val client: RBAC,
    coroutineScope: CoroutineScope,
    private val organizations: Organizations? = null,
) : BasePolicyCache<Policy>(coroutineScope) {
    private val cachedOrgPolicies: MutableMap<String, CachedOrgPolicy> = mutableMapOf()

    companion object {
        private const val CACHE_TTL_SECONDS = 3600L // 1 hour
    }

    override fun fetchPolicy(): Policy? =
        when (val result = client.policyCompletable(PolicyRequest()).get()) {
            is StytchResult.Success -> result.value.policy
            else -> null
        }

    override fun refreshAdditionalCaches() {
        // Refresh all cached org policies
        cachedOrgPolicies.keys.toList().forEach { orgId ->
            refreshOrgPolicy(orgId)
        }
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

        val roleViews =
            allRoles.map { role ->
                RoleView(
                    roleId = role.roleId,
                    permissions = role.permissions.map { PermissionView(it.resourceId, it.actions) },
                )
            }

        if (hasRolePermission(subjectRoles, roleViews, authorizationCheck.resourceId, authorizationCheck.action)) {
            return
        }
        throw PermissionException(authorizationCheck)
    }
}
