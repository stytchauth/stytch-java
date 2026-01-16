package com.stytch.java.common

import com.stytch.java.b2b.api.rbac.RBAC
import com.stytch.java.b2b.api.rbacorganizations.Organizations
import com.stytch.java.b2b.models.rbac.OrgPolicy
import com.stytch.java.b2b.models.rbac.Policy
import com.stytch.java.b2b.models.rbac.PolicyResource
import com.stytch.java.b2b.models.rbac.PolicyResponse
import com.stytch.java.b2b.models.rbac.PolicyRole
import com.stytch.java.b2b.models.rbac.PolicyRolePermission
import com.stytch.java.b2b.models.rbac.PolicyScope
import com.stytch.java.b2b.models.rbac.PolicyScopePermission
import com.stytch.java.b2b.models.rbacorganizations.GetOrgPolicyResponse
import com.stytch.java.b2b.models.sessions.AuthorizationCheck
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.util.concurrent.atomic.AtomicInteger

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
        scopes =
            listOf(
                PolicyScope(
                    scope = "global",
                    description = "Global scope",
                    permissions =
                        listOf(
                            PolicyScopePermission(
                                resourceId = "bar",
                                actions = listOf("read", "write"),
                            ),
                        ),
                ),
            ),
    )

private val orgPolicy =
    OrgPolicy(
        roles =
            listOf(
                PolicyRole(
                    roleId = "org_admin",
                    description = "Organization-specific admin",
                    permissions =
                        listOf(
                            PolicyRolePermission(
                                resourceId = "baz",
                                actions = listOf("*"),
                            ),
                        ),
                ),
                PolicyRole(
                    roleId = "org_reader",
                    description = "Organization-specific reader",
                    permissions =
                        listOf(
                            PolicyRolePermission(
                                resourceId = "baz",
                                actions = listOf("read"),
                            ),
                        ),
                ),
            ),
    )

internal class PolicyCacheTest {
    private lateinit var rbac: RBAC
    private val testScope = CoroutineScope(Dispatchers.Unconfined)

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
                every { organizations.getOrgPolicyCompletable(any()).get() } returns
                    StytchResult.Success(
                        GetOrgPolicyResponse(
                            statusCode = 200,
                            requestId = "",
                            orgPolicy = orgPolicy,
                        ),
                    )
            }
    }

    @Test(expected = TenancyException::class)
    fun `throws TenancyException when subjectOrgId does not match authZ request organizationId`() {
        val policyCache = PolicyCache(rbac, testScope)
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
        val policyCache = PolicyCache(rbac, testScope)
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
        val policyCache = PolicyCache(rbac, testScope)
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
        val policyCache = PolicyCache(rbac, testScope)
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
        val policyCache = PolicyCache(rbac, testScope)
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

    @Test
    fun `fetches policy on first authorization check`() {
        val rbacMock =
            mockk<RBAC>(relaxed = true, relaxUnitFun = true) {
                every { policyCompletable(any()).get() } returns
                    StytchResult.Success(
                        PolicyResponse(
                            statusCode = 200,
                            requestId = "",
                            policy = policy,
                        ),
                    )
                every { organizations.getOrgPolicyCompletable(any()).get() } returns
                    StytchResult.Success(
                        GetOrgPolicyResponse(
                            statusCode = 200,
                            requestId = "",
                            orgPolicy = OrgPolicy(roles = emptyList()),
                        ),
                    )
            }

        val policyCache = PolicyCache(rbacMock, testScope)

        // First call should fetch the policy
        policyCache.performAuthorizationCheck(
            subjectRoles = listOf("admin"),
            subjectOrgId = "my-org",
            authorizationCheck =
                AuthorizationCheck(
                    organizationId = "my-org",
                    resourceId = "foo",
                    action = "read",
                ),
        )

        verify(exactly = 1) { rbacMock.policyCompletable(any()) }
    }

    @Test
    fun `uses cached policy on subsequent authorization checks`() {
        val callCount = AtomicInteger(0)
        val rbacMock =
            mockk<RBAC>(relaxed = true, relaxUnitFun = true) {
                every { policyCompletable(any()).get() } answers {
                    callCount.incrementAndGet()
                    StytchResult.Success(
                        PolicyResponse(
                            statusCode = 200,
                            requestId = "",
                            policy = policy,
                        ),
                    )
                }
                every { organizations.getOrgPolicyCompletable(any()).get() } returns
                    StytchResult.Success(
                        GetOrgPolicyResponse(
                            statusCode = 200,
                            requestId = "",
                            orgPolicy = OrgPolicy(roles = emptyList()),
                        ),
                    )
            }

        val policyCache = PolicyCache(rbacMock, testScope)

        // First call fetches
        policyCache.performAuthorizationCheck(
            subjectRoles = listOf("admin"),
            subjectOrgId = "my-org",
            authorizationCheck =
                AuthorizationCheck(
                    organizationId = "my-org",
                    resourceId = "foo",
                    action = "read",
                ),
        )

        // Second call should use cache
        policyCache.performAuthorizationCheck(
            subjectRoles = listOf("admin"),
            subjectOrgId = "my-org",
            authorizationCheck =
                AuthorizationCheck(
                    organizationId = "my-org",
                    resourceId = "bar",
                    action = "read",
                ),
        )

        // Should only have called the API once (second call used cache)
        assertEquals(1, callCount.get())
    }

    @Test
    fun `cancelBackgroundRefresh stops background refresh job`() {
        val rbacMock =
            mockk<RBAC>(relaxed = true, relaxUnitFun = true) {
                every { policyCompletable(any()).get() } returns
                    StytchResult.Success(
                        PolicyResponse(
                            statusCode = 200,
                            requestId = "",
                            policy = policy,
                        ),
                    )
                every { organizations.getOrgPolicyCompletable(any()).get() } returns
                    StytchResult.Success(
                        GetOrgPolicyResponse(
                            statusCode = 200,
                            requestId = "",
                            orgPolicy = OrgPolicy(roles = emptyList()),
                        ),
                    )
            }

        val policyCache = PolicyCache(rbacMock, testScope)

        // Trigger initial fetch and start background refresh
        policyCache.performAuthorizationCheck(
            subjectRoles = listOf("admin"),
            subjectOrgId = "my-org",
            authorizationCheck =
                AuthorizationCheck(
                    organizationId = "my-org",
                    resourceId = "foo",
                    action = "read",
                ),
        )

        // Cancel the background refresh job
        policyCache.cancelBackgroundRefresh()
    }

    @Test
    fun `succeeds when subject has matching org policy role`() {
        val policyCache = PolicyCache(rbac, testScope)
        policyCache.performAuthorizationCheck(
            subjectRoles = listOf("org_admin"),
            subjectOrgId = "my-org",
            authorizationCheck =
                AuthorizationCheck(
                    organizationId = "my-org",
                    resourceId = "baz",
                    action = "write",
                ),
        )
    }

    @Test
    fun `succeeds when subject has org-specific role with read permission`() {
        val policyCache = PolicyCache(rbac, testScope)
        policyCache.performAuthorizationCheck(
            subjectRoles = listOf("org_reader"),
            subjectOrgId = "my-org",
            authorizationCheck =
                AuthorizationCheck(
                    organizationId = "my-org",
                    resourceId = "baz",
                    action = "read",
                ),
        )
    }

    @Test(expected = PermissionException::class)
    fun `throws PermissionException when org role does not have matching action`() {
        val policyCache = PolicyCache(rbac, testScope)
        policyCache.performAuthorizationCheck(
            subjectRoles = listOf("org_reader"),
            subjectOrgId = "my-org",
            authorizationCheck =
                AuthorizationCheck(
                    organizationId = "my-org",
                    resourceId = "baz",
                    action = "write",
                ),
        )
    }

    @Test
    fun `fetches org policy on first authorization check for an org`() {
        val rbacOrgMock =
            mockk<Organizations>(relaxed = true, relaxUnitFun = true) {
                every { getOrgPolicyCompletable(any()).get() } returns
                    StytchResult.Success(
                        GetOrgPolicyResponse(
                            statusCode = 200,
                            requestId = "",
                            orgPolicy = orgPolicy,
                        ),
                    )
            }

        val policyCache = PolicyCache(rbac, testScope, rbacOrgMock)

        // First call should fetch the org policy
        policyCache.performAuthorizationCheck(
            subjectRoles = listOf("org_admin"),
            subjectOrgId = "my-org",
            authorizationCheck =
                AuthorizationCheck(
                    organizationId = "my-org",
                    resourceId = "baz",
                    action = "read",
                ),
        )

        verify(exactly = 1) { rbacOrgMock.getOrgPolicyCompletable(any()) }
    }

    @Test
    fun `uses cached org policy on subsequent authorization checks for same org`() {
        val callCount = AtomicInteger(0)
        val rbacOrgMock =
            mockk<Organizations>(relaxed = true, relaxUnitFun = true) {
                every { getOrgPolicyCompletable(any()).get() } answers {
                    callCount.incrementAndGet()
                    StytchResult.Success(
                        GetOrgPolicyResponse(
                            statusCode = 200,
                            requestId = "",
                            orgPolicy = orgPolicy,
                        ),
                    )
                }
            }

        val policyCache = PolicyCache(rbac, testScope, rbacOrgMock)

        // First call fetches
        policyCache.performAuthorizationCheck(
            subjectRoles = listOf("org_admin"),
            subjectOrgId = "my-org",
            authorizationCheck =
                AuthorizationCheck(
                    organizationId = "my-org",
                    resourceId = "baz",
                    action = "read",
                ),
        )

        // Second call should use cache
        policyCache.performAuthorizationCheck(
            subjectRoles = listOf("org_admin"),
            subjectOrgId = "my-org",
            authorizationCheck =
                AuthorizationCheck(
                    organizationId = "my-org",
                    resourceId = "baz",
                    action = "write",
                ),
        )

        // Should only have called the API once (second call used cache)
        assertEquals(1, callCount.get())
    }

    @Test
    fun `fetches separate org policy for different organizations`() {
        val callCount = AtomicInteger(0)
        val rbacOrgMock =
            mockk<Organizations>(relaxed = true, relaxUnitFun = true) {
                every { getOrgPolicyCompletable(any()).get() } answers {
                    callCount.incrementAndGet()
                    StytchResult.Success(
                        GetOrgPolicyResponse(
                            statusCode = 200,
                            requestId = "",
                            orgPolicy = orgPolicy,
                        ),
                    )
                }
            }

        val policyCache = PolicyCache(rbac, testScope, rbacOrgMock)

        // First call for org1
        policyCache.performAuthorizationCheck(
            subjectRoles = listOf("org_admin"),
            subjectOrgId = "org1",
            authorizationCheck =
                AuthorizationCheck(
                    organizationId = "org1",
                    resourceId = "baz",
                    action = "read",
                ),
        )

        // Second call for org2 - should fetch again
        policyCache.performAuthorizationCheck(
            subjectRoles = listOf("org_admin"),
            subjectOrgId = "org2",
            authorizationCheck =
                AuthorizationCheck(
                    organizationId = "org2",
                    resourceId = "baz",
                    action = "read",
                ),
        )

        // Should have called the API twice (once per org)
        assertEquals(2, callCount.get())
    }

    @Test
    fun `succeeds when combining global and org policy permissions`() {
        // Subject has global_reader (can read foo) and org_admin (can do anything on baz)
        val policyCache = PolicyCache(rbac, testScope)

        // Should succeed using global policy
        policyCache.performAuthorizationCheck(
            subjectRoles = listOf("global_reader", "org_admin"),
            subjectOrgId = "my-org",
            authorizationCheck =
                AuthorizationCheck(
                    organizationId = "my-org",
                    resourceId = "foo",
                    action = "read",
                ),
        )

        // Should succeed using org policy
        policyCache.performAuthorizationCheck(
            subjectRoles = listOf("global_reader", "org_admin"),
            subjectOrgId = "my-org",
            authorizationCheck =
                AuthorizationCheck(
                    organizationId = "my-org",
                    resourceId = "baz",
                    action = "delete",
                ),
        )
    }
}
