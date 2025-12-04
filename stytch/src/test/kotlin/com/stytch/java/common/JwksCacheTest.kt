package com.stytch.java.common

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNotSame
import org.junit.Test

internal class JwksCacheTest {
    private val testCaches = mutableListOf<JwksCache>()
    private val testScopes = mutableListOf<CoroutineScope>()

    @After
    fun cleanup() {
        testCaches.forEach { it.cancelBackgroundRefresh() }
        testScopes.forEach { it.cancel() }
    }

    @Test
    fun `creates HttpsJwks instance with correct location`() {
        val location = "https://test.stytch.com/v1/sessions/jwks/project-test-123"
        val scope = CoroutineScope(SupervisorJob())
        testScopes.add(scope)

        val cache = JwksCache(location, scope)
        testCaches.add(cache)
        val httpsJwks = cache.getHttpsJwks()

        assertNotNull(httpsJwks)
        // Verify the location is set correctly
        assertEquals(location, httpsJwks.location)
    }

    @Test
    fun `creates HttpsJwks instance for consumer endpoints`() {
        val location = "https://test.stytch.com/v1/sessions/jwks/project-test-123"
        val scope = CoroutineScope(SupervisorJob())
        testScopes.add(scope)

        val cache = JwksCache(location, scope)
        testCaches.add(cache)
        val httpsJwks = cache.getHttpsJwks()

        assertNotNull(httpsJwks)
        assertEquals(location, httpsJwks.location)
    }

    @Test
    fun `creates HttpsJwks instance for B2B endpoints`() {
        val location = "https://test.stytch.com/v1/b2b/sessions/jwks/project-test-456"
        val scope = CoroutineScope(SupervisorJob())
        testScopes.add(scope)

        val cache = JwksCache(location, scope)
        testCaches.add(cache)
        val httpsJwks = cache.getHttpsJwks()

        assertNotNull(httpsJwks)
        assertEquals(location, httpsJwks.location)
    }

    @Test
    fun `creates separate instances for different locations`() {
        val location1 = "https://test.stytch.com/v1/sessions/jwks/project-test-123"
        val location2 = "https://test.stytch.com/v1/b2b/sessions/jwks/project-test-456"
        val scope = CoroutineScope(SupervisorJob())
        testScopes.add(scope)

        val cache1 = JwksCache(location1, scope)
        val cache2 = JwksCache(location2, scope)
        testCaches.add(cache1)
        testCaches.add(cache2)

        val httpsJwks1 = cache1.getHttpsJwks()
        val httpsJwks2 = cache2.getHttpsJwks()

        assertNotNull(httpsJwks1)
        assertNotNull(httpsJwks2)
        assertNotSame(httpsJwks1, httpsJwks2)
        assertEquals(location1, httpsJwks1.location)
        assertEquals(location2, httpsJwks2.location)
    }

    @Test
    fun `getHttpsJwks returns same instance on multiple calls`() {
        val location = "https://test.stytch.com/v1/sessions/jwks/project-test-123"
        val scope = CoroutineScope(SupervisorJob())
        testScopes.add(scope)

        val cache = JwksCache(location, scope)
        testCaches.add(cache)

        val httpsJwks1 = cache.getHttpsJwks()
        val httpsJwks2 = cache.getHttpsJwks()

        // Should return the same instance
        assertEquals(httpsJwks1, httpsJwks2)
    }
}
