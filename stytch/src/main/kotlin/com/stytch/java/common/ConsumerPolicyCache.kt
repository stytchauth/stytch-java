package com.stytch.java.common

import com.stytch.java.consumer.api.rbac.RBAC
import com.stytch.java.consumer.models.rbac.Policy
import com.stytch.java.consumer.models.rbac.PolicyRequest
import com.stytch.java.consumer.models.sessions.AuthorizationCheck
import kotlinx.coroutines.CoroutineScope

/**
 * Consumer (B2C) counterpart to [PolicyCache]. Unlike the B2B cache, consumer RBAC is not tenanted,
 * so there is no org-specific policy and no tenancy check — authorization is evaluated purely
 * against the project-level policy's roles.
 */
internal class ConsumerPolicyCache(
    private val client: RBAC,
    coroutineScope: CoroutineScope,
) : BasePolicyCache<Policy>(coroutineScope) {
    override fun fetchPolicy(): Policy? =
        when (val result = client.policyCompletable(PolicyRequest()).get()) {
            is StytchResult.Success -> result.value.policy
            else -> null
        }

    fun performAuthorizationCheck(
        subjectRoles: List<String>,
        authorizationCheck: AuthorizationCheck,
    ) {
        val policy = getPolicy()
        val roleViews =
            policy.roles.map { role ->
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
