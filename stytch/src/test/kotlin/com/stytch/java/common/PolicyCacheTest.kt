package com.stytch.java.common

import com.stytch.java.b2b.api.rbac.RBAC
import com.stytch.java.b2b.models.rbac.Policy
import com.stytch.java.b2b.models.rbac.PolicyResource
import com.stytch.java.b2b.models.rbac.PolicyResponse
import com.stytch.java.b2b.models.rbac.PolicyRole
import com.stytch.java.b2b.models.rbac.PolicyRolePermission
import com.stytch.java.b2b.models.sessions.AuthorizationCheck
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test

private val policy =
    Policy(
        resources =
            listOf(
                PolicyResource(
                    resourceId = "foo",
                    description = "Foo Resource",
                    actions = listOf("read", "write", "delete"),
                ),
                PolicyResource(
                    resourceId = "bar",
                    description = "Bar Resource",
                    actions = listOf("read", "write", "delete"),
                ),
            ),
        roles =
            listOf(
                PolicyRole(
                    roleId = "admin",
                    description = "Admin",
                    permissions =
                        listOf(
                            PolicyRolePermission(
                                resourceId = "foo",
                                actions = listOf("*"),
                            ),
                            PolicyRolePermission(
                                resourceId = "bar",
                                actions = listOf("*"),
                            ),
                        ),
                ),
                PolicyRole(
                    roleId = "global_writer",
                    description = "Writer for all services",
                    permissions =
                        listOf(
                            PolicyRolePermission(
                                resourceId = "foo",
                                actions = listOf("read", "write"),
                            ),
                            PolicyRolePermission(
                                resourceId = "bar",
                                actions = listOf("read", "write"),
                            ),
                        ),
                ),
                PolicyRole(
                    roleId = "global_reader",
                    description = "Reader for all services",
                    permissions =
                        listOf(
                            PolicyRolePermission(
                                resourceId = "foo",
                                actions = listOf("read"),
                            ),
                            PolicyRolePermission(
                                resourceId = "bar",
                                actions = listOf("read"),
                            ),
                        ),
                ),
                PolicyRole(
                    roleId = "bar_writer",
                    description = "Writer for bar service",
                    permissions =
                        listOf(
                            PolicyRolePermission(
                                resourceId = "bar",
                                actions = listOf("read", "write"),
                            ),
                        ),
                ),
            ),
        scopes = listOf(
            PolicyScope(
                scopeId = "global",
                description = "Global scope",
                roles = listOf(
                    PolicyRolePermission(
                        resourceId = "bar",
                        actions = listOf("read", "write"),
                    ),
                ),
            ),
        ),
    )

internal class PolicyCacheTest {
    private lateinit var rbac: RBAC

    @Before
    fun before() {
        rbac =
            mockk(relaxed = true, relaxUnitFun = true) {
                every { policyCompletable(any()).get() } returns
                    StytchResult.Success(
                        PolicyResponse(
                            statusCode = 200,
                            requestId = "",
                            policy = policy,
                        ),
                    )
            }
    }

    @Test(expected = TenancyException::class)
    fun `throws TenancyException when subjectOrgId does not match authZ request organizationId`() {
        val policyCache = PolicyCache(rbac)
        policyCache.performAuthorizationCheck(
            subjectRoles = listOf("admin"),
            subjectOrgId = "foo",
            authorizationCheck =
                AuthorizationCheck(
                    organizationId = "bar",
                    resourceId = "foo",
                    action = "read",
                ),
        )
    }

    @Test(expected = PermissionException::class)
    fun `throws PermissionException when subject does not have matching resource`() {
        val policyCache = PolicyCache(rbac)
        policyCache.performAuthorizationCheck(
            subjectRoles = listOf("bar_writer"),
            subjectOrgId = "my-org",
            authorizationCheck =
                AuthorizationCheck(
                    organizationId = "my-org",
                    resourceId = "foo",
                    action = "write",
                ),
        )
    }

    @Test(expected = PermissionException::class)
    fun `throws PermissionException when subject does not have matching action`() {
        val policyCache = PolicyCache(rbac)
        policyCache.performAuthorizationCheck(
            subjectRoles = listOf("global_writer"),
            subjectOrgId = "my-org",
            authorizationCheck =
                AuthorizationCheck(
                    organizationId = "my-org",
                    resourceId = "foo",
                    action = "delete",
                ),
        )
    }

    @Test
    fun `succeeds when subject has matching resource and action`() {
        val policyCache = PolicyCache(rbac)
        policyCache.performAuthorizationCheck(
            subjectRoles = listOf("global_writer"),
            subjectOrgId = "my-org",
            authorizationCheck =
                AuthorizationCheck(
                    organizationId = "my-org",
                    resourceId = "foo",
                    action = "write",
                ),
        )
    }

    @Test
    fun `succeeds when subject has matching resource and star action`() {
        val policyCache = PolicyCache(rbac)
        policyCache.performAuthorizationCheck(
            subjectRoles = listOf("admin"),
            subjectOrgId = "my-org",
            authorizationCheck =
                AuthorizationCheck(
                    organizationId = "my-org",
                    resourceId = "foo",
                    action = "delete",
                ),
        )
    }
}
