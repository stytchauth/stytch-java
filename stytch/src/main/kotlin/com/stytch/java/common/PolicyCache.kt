package com.stytch.java.common

import com.stytch.java.b2b.api.rbac.RBAC
import com.stytch.java.b2b.models.rbac.Policy
import com.stytch.java.b2b.models.rbac.PolicyRequest
import com.stytch.java.b2b.models.sessions.AuthorizationCheck
import java.time.Duration
import java.time.Instant

public class TenancyException(subjectOrgId: String, authCheckOrgId: String) :
    RuntimeException("Subject organizationId $subjectOrgId does not match authZ request organizationId $authCheckOrgId")

public class PermissionException(authorizationCheck: AuthorizationCheck) :
    RuntimeException("Permission denied for request $authorizationCheck")

internal class PolicyCache(
    private val client: RBAC,
) {
    private var cachedPolicy: Policy? = null
    private var policyLastUpdate: Instant? = null

    private fun getPolicy(invalidate: Boolean = false): Policy {
        val isMissing = cachedPolicy == null || policyLastUpdate == null
        val isStale = policyLastUpdate == null || Duration.between(policyLastUpdate, Instant.now()).seconds > 300
        if (invalidate || isMissing || isStale) {
            when (val result = client.policyCompletable(PolicyRequest()).get()) {
                is StytchResult.Success -> {
                    cachedPolicy = result.value.policy
                    policyLastUpdate = Instant.now()
                }
                else -> {}
            }
        }
        return cachedPolicy ?: throw Exception("Error fetching the policy")
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
                }
                .isNotEmpty()
        hasMatchingActionAndResource && return
        throw PermissionException(authorizationCheck)
    }
}
