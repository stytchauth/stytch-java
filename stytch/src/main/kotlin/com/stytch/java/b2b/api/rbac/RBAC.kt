package com.stytch.java.b2b.api.rbac

// !!!
// WARNING: This file is autogenerated
// Only modify code within MANUAL() sections
// or your changes may be overwritten later!
// !!!

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.stytch.java.b2b.models.rbac.PolicyRequest
import com.stytch.java.b2b.models.rbac.PolicyResponse
import com.stytch.java.common.InstantAdapter
import com.stytch.java.common.StytchResult
import com.stytch.java.http.HttpClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.future.asCompletableFuture
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.CompletableFuture

public interface RBAC {
    /**
     * Get the active RBAC Policy for your current Stytch Project. An RBAC Policy is the canonical document that stores all
     * defined Resources and Roles within your RBAC permissioning model.
     *
     * When using the backend SDKs, the RBAC Policy will automatically be loaded and refreshed in the background to allow for
     * local evaluations, eliminating the need for an extra request to Stytch.
     *
     * Resources and Roles can be created and managed within the [Dashboard](/dashboard). Additionally,
     * [Role assignment](https://stytch.com/docs/b2b/guides/rbac/role-assignment) can be programmatically managed through
     * certain Stytch API endpoints.
     *
     *
     * Check out the [RBAC overview](https://stytch.com/docs/b2b/guides/rbac/overview) to learn more about Stytch's RBAC
     * permissioning model.
     */
    public suspend fun policy(data: PolicyRequest): StytchResult<PolicyResponse>

    /**
     * Get the active RBAC Policy for your current Stytch Project. An RBAC Policy is the canonical document that stores all
     * defined Resources and Roles within your RBAC permissioning model.
     *
     * When using the backend SDKs, the RBAC Policy will automatically be loaded and refreshed in the background to allow for
     * local evaluations, eliminating the need for an extra request to Stytch.
     *
     * Resources and Roles can be created and managed within the [Dashboard](/dashboard). Additionally,
     * [Role assignment](https://stytch.com/docs/b2b/guides/rbac/role-assignment) can be programmatically managed through
     * certain Stytch API endpoints.
     *
     *
     * Check out the [RBAC overview](https://stytch.com/docs/b2b/guides/rbac/overview) to learn more about Stytch's RBAC
     * permissioning model.
     */
    public fun policy(
        data: PolicyRequest,
        callback: (StytchResult<PolicyResponse>) -> Unit,
    )

    /**
     * Get the active RBAC Policy for your current Stytch Project. An RBAC Policy is the canonical document that stores all
     * defined Resources and Roles within your RBAC permissioning model.
     *
     * When using the backend SDKs, the RBAC Policy will automatically be loaded and refreshed in the background to allow for
     * local evaluations, eliminating the need for an extra request to Stytch.
     *
     * Resources and Roles can be created and managed within the [Dashboard](/dashboard). Additionally,
     * [Role assignment](https://stytch.com/docs/b2b/guides/rbac/role-assignment) can be programmatically managed through
     * certain Stytch API endpoints.
     *
     *
     * Check out the [RBAC overview](https://stytch.com/docs/b2b/guides/rbac/overview) to learn more about Stytch's RBAC
     * permissioning model.
     */
    public fun policyCompletable(data: PolicyRequest): CompletableFuture<StytchResult<PolicyResponse>>
}

internal class RBACImpl(private val httpClient: HttpClient, private val coroutineScope: CoroutineScope) : RBAC {
    private val moshi = Moshi.Builder().add(InstantAdapter()).build()

    override suspend fun policy(data: PolicyRequest): StytchResult<PolicyResponse> =
        withContext(Dispatchers.IO) {
            val asJson = moshi.adapter(PolicyRequest::class.java).toJson(data)
            val type = Types.newParameterizedType(Map::class.java, String::class.java, Any::class.java)
            val adapter: JsonAdapter<Map<String, Any>> = moshi.adapter(type)
            val asMap = adapter.fromJson(asJson) ?: emptyMap()
            httpClient.get("/v1/b2b/rbac/policy", asMap)
        }

    override fun policy(
        data: PolicyRequest,
        callback: (StytchResult<PolicyResponse>) -> Unit,
    ) {
        coroutineScope.launch {
            callback(policy(data))
        }
    }

    override fun policyCompletable(data: PolicyRequest): CompletableFuture<StytchResult<PolicyResponse>> =
        coroutineScope.async {
            policy(data)
        }.asCompletableFuture()
}
