package com.stytch.java.consumer.api.passwordssession

// !!!
// WARNING: This file is autogenerated
// Only modify code within MANUAL() sections
// or your changes may be overwritten later!
// !!!

import com.squareup.moshi.Moshi
import com.stytch.java.common.InstantAdapter
import com.stytch.java.common.StytchResult
import com.stytch.java.consumer.models.passwordssession.ResetRequest
import com.stytch.java.consumer.models.passwordssession.ResetResponse
import com.stytch.java.http.HttpClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.future.asCompletableFuture
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.CompletableFuture

public interface Sessions {
    /**
     * Reset the user’s password using their existing session. The endpoint will error if the session does not have a
     * password, email magic link, or email OTP authentication factor that has been issued within the last 5 minutes. This
     * endpoint requires either a `session_jwt` or `session_token` be included in the request.
     *
     * Note that a successful password reset via an existing session will revoke all active sessions for the `user_id`, except
     * for the one used during the reset flow.
     */
    public suspend fun reset(data: ResetRequest): StytchResult<ResetResponse>

    /**
     * Reset the user’s password using their existing session. The endpoint will error if the session does not have a
     * password, email magic link, or email OTP authentication factor that has been issued within the last 5 minutes. This
     * endpoint requires either a `session_jwt` or `session_token` be included in the request.
     *
     * Note that a successful password reset via an existing session will revoke all active sessions for the `user_id`, except
     * for the one used during the reset flow.
     */
    public fun reset(
        data: ResetRequest,
        callback: (StytchResult<ResetResponse>) -> Unit,
    )

    /**
     * Reset the user’s password using their existing session. The endpoint will error if the session does not have a
     * password, email magic link, or email OTP authentication factor that has been issued within the last 5 minutes. This
     * endpoint requires either a `session_jwt` or `session_token` be included in the request.
     *
     * Note that a successful password reset via an existing session will revoke all active sessions for the `user_id`, except
     * for the one used during the reset flow.
     */
    public fun resetCompletable(data: ResetRequest): CompletableFuture<StytchResult<ResetResponse>>
}

internal class SessionsImpl(private val httpClient: HttpClient, private val coroutineScope: CoroutineScope) : Sessions {
    private val moshi = Moshi.Builder().add(InstantAdapter()).build()

    override suspend fun reset(data: ResetRequest): StytchResult<ResetResponse> =
        withContext(Dispatchers.IO) {
            val asJson = moshi.adapter(ResetRequest::class.java).toJson(data)
            httpClient.post("/v1/passwords/session/reset", asJson)
        }

    override fun reset(
        data: ResetRequest,
        callback: (StytchResult<ResetResponse>) -> Unit,
    ) {
        coroutineScope.launch {
            callback(reset(data))
        }
    }

    override fun resetCompletable(data: ResetRequest): CompletableFuture<StytchResult<ResetResponse>> =
        coroutineScope.async {
            reset(data)
        }.asCompletableFuture()
}
