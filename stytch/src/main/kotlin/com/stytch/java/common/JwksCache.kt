package com.stytch.java.common

import org.jose4j.jwk.HttpsJwks

/**
 * Factory for creating configured HttpsJwks instances with optimal caching settings.
 */
internal object JwksCache {
    private const val CACHE_DURATION_SECONDS = 3600L // 1 hour
    private const val REFRESH_THRESHOLD_SECONDS = 300L // 5 minutes before expiration

    /**
     * Creates an HttpsJwks instance configured with 1-hour cache duration
     * and background refresh 5 minutes before expiration.
     *
     * @param location The JWKS endpoint URL
     * @return Configured HttpsJwks instance
     */
    fun create(location: String): HttpsJwks {
        return HttpsJwks(location).apply {
            // Set cache duration to 1 hour (3600 seconds)
            setDefaultCacheDuration(CACHE_DURATION_SECONDS)
            // Refresh 5 minutes before expiration to ensure cache stays fresh
            setRefreshReprieveThreshold(REFRESH_THRESHOLD_SECONDS)
        }
    }
}
