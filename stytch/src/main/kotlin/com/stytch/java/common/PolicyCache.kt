import com.stytch.java.b2b.api.rbac.RBAC
import com.stytch.java.b2b.models.rbac.Policy
import com.stytch.java.b2b.models.sessions.AuthorizationCheck
import java.time.Duration
import java.time.Instant

class TenancyException(subjectOrgId: String, authCheckOrgId: String) :
    RuntimeException("Subject organizationId $subjectOrgId does not match authZ request organizationId $actualOrgId")

class PermissionException(authorizationCheck: AuthorizationCheck) :
    RuntimeException("Permission denied for request $authorizationCheck")

class PolicyCache(private val client: RBAC) {
    private var cachedPolicy: Policy? = null
    private var policyLastUpdate: Instant? = null

    fun getPolicy(invalidate: bool = false): Policy {
        if (invalidate || policyLastUpdate == null || Duration.between(policyLastUpdate, Instant.now()).seconds > 300) {
            cachedPolicy = client.policy()
            policyLastUpdate = Instant.now()
        }
        return cachedPolicy!!
    }

    fun performAuthorizationCheck(
        subjectRoles: List<String>,
        subjectOrgId: String,
        authorizationCheck: AuthorizationCheck,
    ) {
        requestOrgId = authorizationCheck.orgId
        if (authorizationCheck.orgId != subjectOrgId) {
            throw TenancyException(subjectOrgId, authorizationCheck.orgId)
        }

        policy = getPolicy()

        for (role in policy.roles) {
            if (role.name in subjectRoles) {
                for (permission in role.permissions) {
                    hasMatchingAction = permission.actions.contains("*") || permission.actions.contains(action)
                    hasMatchingResource = permission.resource_id == authorizationCheck.resourceId
                    if (hasMatchingAction && hasMatchingResource) {
                        // All good, we found a match
                        return
                    }
                }
            }
        }

        throw PermissionException(authorizationCheck)
    }
}
