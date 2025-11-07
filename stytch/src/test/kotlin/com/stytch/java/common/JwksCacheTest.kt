package com.stytch.java.common

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

internal class JwksCacheTest {
    @Test
    fun `creates HttpsJwks instance with correct location`() {
        val location = "https://test.stytch.com/v1/sessions/jwks/project-test-123"
        val httpsJwks = JwksCache.create(location)

        assertNotNull(httpsJwks)
        // Verify the location is set correctly
        assertEquals(location, httpsJwks.location)
    }

    @Test
    fun `creates HttpsJwks instance for consumer endpoints`() {
        val location = "https://test.stytch.com/v1/sessions/jwks/project-test-123"
        val httpsJwks = JwksCache.create(location)

        assertNotNull(httpsJwks)
        assertEquals(location, httpsJwks.location)
    }

    @Test
    fun `creates HttpsJwks instance for B2B endpoints`() {
        val location = "https://test.stytch.com/v1/b2b/sessions/jwks/project-test-456"
        val httpsJwks = JwksCache.create(location)

        assertNotNull(httpsJwks)
        assertEquals(location, httpsJwks.location)
    }

    @Test
    fun `creates separate instances for different locations`() {
        val location1 = "https://test.stytch.com/v1/sessions/jwks/project-test-123"
        val location2 = "https://test.stytch.com/v1/b2b/sessions/jwks/project-test-456"

        val httpsJwks1 = JwksCache.create(location1)
        val httpsJwks2 = JwksCache.create(location2)

        assertNotNull(httpsJwks1)
        assertNotNull(httpsJwks2)
        assertEquals(location1, httpsJwks1.location)
        assertEquals(location2, httpsJwks2.location)
    }
}
