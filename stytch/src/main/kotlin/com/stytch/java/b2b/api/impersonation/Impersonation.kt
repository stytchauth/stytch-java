package com.stytch.java.b2b.api.impersonation

// !!!
// WARNING: This file is autogenerated
// Only modify code within MANUAL() sections
// or your changes may be overwritten later!
// !!!

import com.squareup.moshi.Moshi
import com.stytch.java.b2b.models.impersonation.AuthenticateRequest
import com.stytch.java.b2b.models.impersonation.AuthenticateResponse
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

public interface Impersonation {
    /**
     * Authenticate an impersonation token to impersonate a Member. This endpoint requires an impersonation token that is not
     * expired or previously used.
     * A Stytch session will be created for the impersonated member with a 60 minute duration. Impersonated sessions cannot be
     * extended.
     *
     * Prior to this step, you can generate an impersonation token by visiting the Stytch Dashboard, viewing a member, and
     * clicking the `Impersonate Member` button.
     */
    public suspend fun authenticate(data: AuthenticateRequest): StytchResult<AuthenticateResponse>

    /**
     * Authenticate an impersonation token to impersonate a Member. This endpoint requires an impersonation token that is not
     * expired or previously used.
     * A Stytch session will be created for the impersonated member with a 60 minute duration. Impersonated sessions cannot be
     * extended.
     *
     * Prior to this step, you can generate an impersonation token by visiting the Stytch Dashboard, viewing a member, and
     * clicking the `Impersonate Member` button.
     */
    public fun authenticate(
        data: AuthenticateRequest,
        callback: (StytchResult<AuthenticateResponse>) -> Unit,
    )

    /**
     * Authenticate an impersonation token to impersonate a Member. This endpoint requires an impersonation token that is not
     * expired or previously used.
     * A Stytch session will be created for the impersonated member with a 60 minute duration. Impersonated sessions cannot be
     * extended.
     *
     * Prior to this step, you can generate an impersonation token by visiting the Stytch Dashboard, viewing a member, and
     * clicking the `Impersonate Member` button.
     */
    public fun authenticateCompletable(data: AuthenticateRequest): CompletableFuture<StytchResult<AuthenticateResponse>>
}

internal class ImpersonationImpl(
    private val httpClient: HttpClient,
    private val coroutineScope: CoroutineScope,
) : Impersonation {
    private val moshi = Moshi.Builder().add(InstantAdapter()).build()

    override suspend fun authenticate(data: AuthenticateRequest): StytchResult<AuthenticateResponse> =
        withContext(Dispatchers.IO) {
            var headers = emptyMap<String, String>()

            val asJson = moshi.adapter(AuthenticateRequest::class.java).toJson(data)
            httpClient.post("/v1/b2b/impersonation/authenticate", asJson, headers)
        }

    override fun authenticate(
        data: AuthenticateRequest,
        callback: (StytchResult<AuthenticateResponse>) -> Unit,
    ) {
        coroutineScope.launch {
            callback(authenticate(data))
        }
    }

    override fun authenticateCompletable(data: AuthenticateRequest): CompletableFuture<StytchResult<AuthenticateResponse>> =
        coroutineScope.async {
            authenticate(data)
        }.asCompletableFuture()
}
