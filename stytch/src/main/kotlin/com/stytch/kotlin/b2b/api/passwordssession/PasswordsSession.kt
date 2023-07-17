package com.stytch.kotlin.b2b.api.passwordssession

// !!!
// WARNING: This file is autogenerated
// Only modify code within MANUAL() sections
// or your changes may be overwritten later!
// !!!

import com.squareup.moshi.Moshi
import com.stytch.kotlin.b2b.models.passwordssession.ResetRequest
import com.stytch.kotlin.b2b.models.passwordssession.ResetResponse
import com.stytch.kotlin.common.StytchResult
import com.stytch.kotlin.http.HttpClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.future.asCompletableFuture
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.CompletableFuture

public interface Sessions {
    /**
     * Reset the Member's password using their existing session. The endpoint will error if the session does not contain an
     * authentication factor that has been issued within the last 5 minutes. Either `session_token` or `session_jwt` should be
     * provided.
     */
    public suspend fun reset(data: ResetRequest): StytchResult<ResetResponse>

    /**
     * Reset the Member's password using their existing session. The endpoint will error if the session does not contain an
     * authentication factor that has been issued within the last 5 minutes. Either `session_token` or `session_jwt` should be
     * provided.
     */
    public fun reset(data: ResetRequest, callback: (StytchResult<ResetResponse>) -> Unit)

    /**
     * Reset the Member's password using their existing session. The endpoint will error if the session does not contain an
     * authentication factor that has been issued within the last 5 minutes. Either `session_token` or `session_jwt` should be
     * provided.
     */
    public fun resetCompletable(data: ResetRequest): CompletableFuture<StytchResult<ResetResponse>>
}

internal class SessionsImpl(
    private val httpClient: HttpClient,
    private val coroutineScope: CoroutineScope,
) : Sessions {

    private val moshi = Moshi.Builder().build()

    override suspend fun reset(data: ResetRequest): StytchResult<ResetResponse> = withContext(Dispatchers.IO) {
        val asJson = moshi.adapter(ResetRequest::class.java).toJson(data)
        httpClient.post("/v1/b2b/passwords/session/reset", asJson)
    }

    override fun reset(data: ResetRequest, callback: (StytchResult<ResetResponse>) -> Unit) {
        coroutineScope.launch {
            callback(reset(data))
        }
    }

    override fun resetCompletable(data: ResetRequest): CompletableFuture<StytchResult<ResetResponse>> =
        coroutineScope.async {
            reset(data)
        }.asCompletableFuture()
}
