package com.stytch.java.common

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.jose4j.jwk.HttpsJwks

/**
 * Manages JWKS caching with proactive background refresh to prevent latency spikes.
 *
 * The Stytch JWKS endpoint returns cache-control: public, max-age=900 (15 minutes).
 * This class proactively refreshes the JWKS every 10 minutes to ensure the cache
 * stays fresh and avoid blocking requests when the cache expires.
 */
internal class JwksCache(
    location: String,
    coroutineScope: CoroutineScope,
) {
    private val job = SupervisorJob(coroutineScope.coroutineContext[Job])
    private val scope = CoroutineScope(coroutineScope.coroutineContext + job)
    private val httpsJwks = HttpsJwks(location)
    private var backgroundRefreshStarted = false

    companion object {
        private const val DEFAULT_CACHE_DURATION_SECONDS = 3600L // 1 hour fallback
        private const val REFRESH_REPRIEVE_THRESHOLD_SECONDS = 300L // 5 min throttle for manual refresh calls
        private const val BACKGROUND_REFRESH_INTERVAL_MS = 600000L // 10 minutes (more frequent than 15 min cache expiration)
    }

    init {
        httpsJwks.apply {
            // Fallback cache duration if no cache-control headers (Stytch returns max-age=900)
            setDefaultCacheDuration(DEFAULT_CACHE_DURATION_SECONDS)
            // Prevent rapid consecutive refresh() calls within 5 minutes
            setRefreshReprieveThreshold(REFRESH_REPRIEVE_THRESHOLD_SECONDS)
        }
    }

    /**
     * Gets the HttpsJwks instance, starting background refresh on first access.
     */
    fun getHttpsJwks(): HttpsJwks {
        if (!backgroundRefreshStarted) {
            startBackgroundRefresh()
            backgroundRefreshStarted = true
        }
        return httpsJwks
    }

    private fun startBackgroundRefresh() {
        scope.launch {
            while (isActive) {
                delay(BACKGROUND_REFRESH_INTERVAL_MS)
                try {
                    httpsJwks.refresh()
                } catch (_: Exception) {
                    // Ignore refresh failures - the cache will still work with stale data
                }
            }
        }
    }

    /**
     * Cancels the background refresh job.
     * This allows the refresh job to be stopped independently of the parent scope.
     */
    fun cancelBackgroundRefresh() {
        job.cancel()
    }
}
