package com.stytch.java.common

import com.stytch.java.b2b.api.rbac.RBAC
import com.stytch.java.b2b.models.rbac.Policy
import com.stytch.java.b2b.models.rbac.PolicyRequest
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

internal class PolicyCache(
    private val client: RBAC,
    coroutineScope: CoroutineScope,
) {
    private val job = SupervisorJob(coroutineScope.coroutineContext[Job])
    private val scope = CoroutineScope(coroutineScope.coroutineContext + job)
    private var cachedPolicy: Policy? = null
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
        val hasMatchingActionAndResource =
            policy.roles
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
