package com.stytch.java.common

import com.stytch.java.consumer.api.rbac.RBAC
import com.stytch.java.consumer.models.rbac.Policy
import com.stytch.java.consumer.models.rbac.PolicyResource
import com.stytch.java.consumer.models.rbac.PolicyResponse
import com.stytch.java.consumer.models.rbac.PolicyRole
import com.stytch.java.consumer.models.rbac.PolicyRolePermission
import com.stytch.java.consumer.models.rbac.PolicyScope
import com.stytch.java.consumer.models.rbac.PolicyScopePermission
import com.stytch.java.consumer.models.sessions.AuthorizationCheck
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

internal class ConsumerPolicyCacheTest {
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
            }
    }

    @Test(expected = PermissionException::class)
    fun `throws PermissionException when subject does not have matching resource`() {
        val policyCache = ConsumerPolicyCache(rbac, testScope)
        policyCache.performAuthorizationCheck(
            subjectRoles = listOf("bar_writer"),
            authorizationCheck =
                AuthorizationCheck(
                    resourceId = "foo",
                    action = "write",
                ),
        )
    }

    @Test(expected = PermissionException::class)
    fun `throws PermissionException when subject does not have matching action`() {
        val policyCache = ConsumerPolicyCache(rbac, testScope)
        policyCache.performAuthorizationCheck(
            subjectRoles = listOf("global_writer"),
            authorizationCheck =
                AuthorizationCheck(
                    resourceId = "foo",
                    action = "delete",
                ),
        )
    }

    @Test
    fun `succeeds when subject has matching resource and action`() {
        val policyCache = ConsumerPolicyCache(rbac, testScope)
        policyCache.performAuthorizationCheck(
            subjectRoles = listOf("global_writer"),
            authorizationCheck =
                AuthorizationCheck(
                    resourceId = "foo",
                    action = "write",
                ),
        )
    }

    @Test
    fun `succeeds when subject has matching resource and star action`() {
        val policyCache = ConsumerPolicyCache(rbac, testScope)
        policyCache.performAuthorizationCheck(
            subjectRoles = listOf("admin"),
            authorizationCheck =
                AuthorizationCheck(
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
            }

        val policyCache = ConsumerPolicyCache(rbacMock, testScope)

        // First call should fetch the policy
        policyCache.performAuthorizationCheck(
            subjectRoles = listOf("admin"),
            authorizationCheck =
                AuthorizationCheck(
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
            }

        val policyCache = ConsumerPolicyCache(rbacMock, testScope)

        // First call fetches
        policyCache.performAuthorizationCheck(
            subjectRoles = listOf("admin"),
            authorizationCheck =
                AuthorizationCheck(
                    resourceId = "foo",
                    action = "read",
                ),
        )

        // Second call should use cache
        policyCache.performAuthorizationCheck(
            subjectRoles = listOf("admin"),
            authorizationCheck =
                AuthorizationCheck(
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
            }

        val policyCache = ConsumerPolicyCache(rbacMock, testScope)

        // Trigger initial fetch and start background refresh
        policyCache.performAuthorizationCheck(
            subjectRoles = listOf("admin"),
            authorizationCheck =
                AuthorizationCheck(
                    resourceId = "foo",
                    action = "read",
                ),
        )

        // Cancel the background refresh job
        policyCache.cancelBackgroundRefresh()
    }
}
