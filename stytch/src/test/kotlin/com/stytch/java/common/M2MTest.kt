package com.stytch.java.common

import com.stytch.java.consumer.api.m2m.M2MImpl
import org.junit.Test
import org.junit.Assert.assertThrows

class M2MTest {
    @Test
    fun `test basic`() {
        val has = listOf("read:users", "write:users")
        val needs = listOf("read:users")

        // Assertion is just that no exception is raised
        M2MImpl.performAuthorizationCheck(has, needs)
    }

    @Test
    fun `test multiple required scopes`() {
        val has = listOf("read:users", "write:users", "read:books")
        val needs = listOf("read:users", "read:books")

        // Assertion is just that no exception is raised
        M2MImpl.performAuthorizationCheck(has, needs)
    }

    @Test
    fun `test simple scopes`() {
        val has = listOf("read_users", "write_users")
        val needs = listOf("read_users")

        // Assertion is just that no exception is raised
        M2MImpl.performAuthorizationCheck(has, needs)
    }

    @Test
    fun `test wildcard resource`() {
        val has = listOf("read:*", "write:*")
        val needs = listOf("read:users")

        // Assertion is just that no exception is raised
        M2MImpl.performAuthorizationCheck(has, needs)
    }

    @Test
    fun `test missing required scope`() {
        val has = listOf("read:users")
        val needs = listOf("write:users")

        assertThrows(JWTException::class.java) { M2MImpl.performAuthorizationCheck(has, needs) }
    }

    @Test
    fun `test missing required scope with wildcard`() {
        val has = listOf("read:users", "write:*")
        val needs = listOf("delete:books")

        assertThrows(JWTException::class.java) { M2MImpl.performAuthorizationCheck(has, needs) }
    }
}
