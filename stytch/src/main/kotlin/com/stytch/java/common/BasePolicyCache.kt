package com.stytch.java.common

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.Instant

public class PermissionException(
    // Accepts either the B2B or consumer AuthorizationCheck; rendered via toString for the message.
    authorizationCheck: Any,
) : RuntimeException("Permission denied for request $authorizationCheck")

/**
 * A normalized view of an RBAC Role used for local authorization checks. Both the B2B and consumer
 * policy caches map their (distinct) generated Role types into this shape so the matching logic can
 * be shared.
 */
internal data class RoleView(
    val roleId: String,
    val permissions: List<PermissionView>,
)

internal data class PermissionView(
    val resourceId: String,
    val actions: List<String>,
)

/**
 * Returns true if any of the subject's roles grants the given action on the given resource.
 * A permission matches when its resource matches and its actions contain the requested action or
 * the "*" wildcard.
 */
internal fun hasRolePermission(
    subjectRoles: List<String>,
    roles: List<RoleView>,
    resourceId: String,
    action: String,
): Boolean =
    roles
        .filter { it.roleId in subjectRoles }
        .flatMap { it.permissions }
        .any {
            val hasMatchingAction = it.actions.contains("*") || it.actions.contains(action)
            val hasMatchingResource = it.resourceId == resourceId
            hasMatchingAction && hasMatchingResource
        }

/**
 * Shared caching machinery for a project-level RBAC policy. Subclasses supply how to fetch the
 * policy and may refresh additional caches (e.g. org policies) during the background refresh loop.
 *
 * The policy is fetched on first use and refreshed both lazily (when stale) and by a background
 * coroutine on a fixed interval.
 */
internal abstract class BasePolicyCache<P>(
    coroutineScope: CoroutineScope,
) {
    private val job = SupervisorJob(coroutineScope.coroutineContext[Job])
    protected val scope: CoroutineScope = CoroutineScope(coroutineScope.coroutineContext + job)
    private var cachedPolicy: P? = null
    private var policyLastUpdate: Instant? = null
    private var backgroundRefreshStarted = false

    companion object {
        private const val CACHE_TTL_SECONDS = 3600L // 1 hour
        private const val REFRESH_INTERVAL_MS = 3600000L // 1 hour in milliseconds
    }

    /** Fetch the latest policy from the API, or null if the request failed. */
    protected abstract fun fetchPolicy(): P?

    /** Hook for subclasses to refresh any additional caches during the background refresh loop. */
    protected open fun refreshAdditionalCaches() {}

    protected fun getPolicy(invalidate: Boolean = false): P {
        val isMissing = cachedPolicy == null || policyLastUpdate == null
        val isStale = policyLastUpdate == null || Duration.between(policyLastUpdate, Instant.now()).seconds > CACHE_TTL_SECONDS
        if (invalidate || isMissing || isStale) {
            refreshPolicy()
        }

        // Start background refresh after first successful fetch
        if (!backgroundRefreshStarted && cachedPolicy != null) {
            startBackgroundRefresh()
            backgroundRefreshStarted = true
        }

        return cachedPolicy ?: throw Exception("Error fetching the policy")
    }

    private fun refreshPolicy() {
        fetchPolicy()?.let {
            cachedPolicy = it
            policyLastUpdate = Instant.now()
        }
    }

    private fun startBackgroundRefresh() {
        scope.launch {
            while (isActive) {
                delay(REFRESH_INTERVAL_MS)
                refreshPolicy()
                refreshAdditionalCaches()
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
