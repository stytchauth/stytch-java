package com.stytch.java.consumer.api.otpemail

// !!!
// WARNING: This file is autogenerated
// Only modify code within MANUAL() sections
// or your changes may be overwritten later!
// !!!

import com.squareup.moshi.Moshi
import com.stytch.java.common.InstantAdapter
import com.stytch.java.common.StytchResult
import com.stytch.java.consumer.models.otpemail.LoginOrCreateRequest
import com.stytch.java.consumer.models.otpemail.LoginOrCreateResponse
import com.stytch.java.consumer.models.otpemail.SendRequest
import com.stytch.java.consumer.models.otpemail.SendResponse
import com.stytch.java.http.HttpClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.future.asCompletableFuture
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.CompletableFuture

public interface Email {
    /**
     * Send a One-Time Passcode (OTP) to a User using their email. If you'd like to create a user and send them a passcode
     * with one request, use our [log in or create endpoint](https://stytch.com/docs/api/log-in-or-create-user-by-email-otp).
     *
     * ### Add an email to an existing user
     * This endpoint also allows you to add a new email address to an existing Stytch User. Including a `user_id`,
     * `session_token`, or `session_jwt` in your Send one-time passcode by email request will add the new, unverified email
     * address to the existing Stytch User. If the user successfully authenticates within 5 minutes, the new email address
     * will be marked as verified and remain permanently on the existing Stytch User. Otherwise, it will be removed from the
     * User object, and any subsequent login requests using that email address will create a new User.
     *
     * ### Next steps
     * Collect the OTP which was delivered to the user. Call [Authenticate OTP](https://stytch.com/docs/api/authenticate-otp)
     * using the OTP `code` along with the `phone_id` found in the response as the `method_id`.
     */
    public suspend fun send(data: SendRequest): StytchResult<SendResponse>

    /**
     * Send a One-Time Passcode (OTP) to a User using their email. If you'd like to create a user and send them a passcode
     * with one request, use our [log in or create endpoint](https://stytch.com/docs/api/log-in-or-create-user-by-email-otp).
     *
     * ### Add an email to an existing user
     * This endpoint also allows you to add a new email address to an existing Stytch User. Including a `user_id`,
     * `session_token`, or `session_jwt` in your Send one-time passcode by email request will add the new, unverified email
     * address to the existing Stytch User. If the user successfully authenticates within 5 minutes, the new email address
     * will be marked as verified and remain permanently on the existing Stytch User. Otherwise, it will be removed from the
     * User object, and any subsequent login requests using that email address will create a new User.
     *
     * ### Next steps
     * Collect the OTP which was delivered to the user. Call [Authenticate OTP](https://stytch.com/docs/api/authenticate-otp)
     * using the OTP `code` along with the `phone_id` found in the response as the `method_id`.
     */
    public fun send(
        data: SendRequest,
        callback: (StytchResult<SendResponse>) -> Unit,
    )

    /**
     * Send a One-Time Passcode (OTP) to a User using their email. If you'd like to create a user and send them a passcode
     * with one request, use our [log in or create endpoint](https://stytch.com/docs/api/log-in-or-create-user-by-email-otp).
     *
     * ### Add an email to an existing user
     * This endpoint also allows you to add a new email address to an existing Stytch User. Including a `user_id`,
     * `session_token`, or `session_jwt` in your Send one-time passcode by email request will add the new, unverified email
     * address to the existing Stytch User. If the user successfully authenticates within 5 minutes, the new email address
     * will be marked as verified and remain permanently on the existing Stytch User. Otherwise, it will be removed from the
     * User object, and any subsequent login requests using that email address will create a new User.
     *
     * ### Next steps
     * Collect the OTP which was delivered to the user. Call [Authenticate OTP](https://stytch.com/docs/api/authenticate-otp)
     * using the OTP `code` along with the `phone_id` found in the response as the `method_id`.
     */
    public fun sendCompletable(data: SendRequest): CompletableFuture<StytchResult<SendResponse>>

    /**
     * Send a one-time passcode (OTP) to a User using their email. If the email is not associated with a User already, a User
     * will be created.
     *
     * ### Next steps
     *
     * Collect the OTP which was delivered to the User. Call [Authenticate OTP](https://stytch.com/docs/api/authenticate-otp)
     * using the OTP `code` along with the `phone_id` found in the response as the `method_id`.
     */
    public suspend fun loginOrCreate(data: LoginOrCreateRequest): StytchResult<LoginOrCreateResponse>

    /**
     * Send a one-time passcode (OTP) to a User using their email. If the email is not associated with a User already, a User
     * will be created.
     *
     * ### Next steps
     *
     * Collect the OTP which was delivered to the User. Call [Authenticate OTP](https://stytch.com/docs/api/authenticate-otp)
     * using the OTP `code` along with the `phone_id` found in the response as the `method_id`.
     */
    public fun loginOrCreate(
        data: LoginOrCreateRequest,
        callback: (StytchResult<LoginOrCreateResponse>) -> Unit,
    )

    /**
     * Send a one-time passcode (OTP) to a User using their email. If the email is not associated with a User already, a User
     * will be created.
     *
     * ### Next steps
     *
     * Collect the OTP which was delivered to the User. Call [Authenticate OTP](https://stytch.com/docs/api/authenticate-otp)
     * using the OTP `code` along with the `phone_id` found in the response as the `method_id`.
     */
    public fun loginOrCreateCompletable(data: LoginOrCreateRequest): CompletableFuture<StytchResult<LoginOrCreateResponse>>
}

internal class EmailImpl(private val httpClient: HttpClient, private val coroutineScope: CoroutineScope) : Email {
    private val moshi = Moshi.Builder().add(InstantAdapter()).build()

    override suspend fun send(data: SendRequest): StytchResult<SendResponse> =
        withContext(Dispatchers.IO) {
            var headers = emptyMap<String, String>()

            val asJson = moshi.adapter(SendRequest::class.java).toJson(data)
            httpClient.post("/v1/otps/email/send", asJson, headers)
        }

    override fun send(
        data: SendRequest,
        callback: (StytchResult<SendResponse>) -> Unit,
    ) {
        coroutineScope.launch {
            callback(send(data))
        }
    }

    override fun sendCompletable(data: SendRequest): CompletableFuture<StytchResult<SendResponse>> =
        coroutineScope.async {
            send(data)
        }.asCompletableFuture()

    override suspend fun loginOrCreate(data: LoginOrCreateRequest): StytchResult<LoginOrCreateResponse> =
        withContext(Dispatchers.IO) {
            var headers = emptyMap<String, String>()

            val asJson = moshi.adapter(LoginOrCreateRequest::class.java).toJson(data)
            httpClient.post("/v1/otps/email/login_or_create", asJson, headers)
        }

    override fun loginOrCreate(
        data: LoginOrCreateRequest,
        callback: (StytchResult<LoginOrCreateResponse>) -> Unit,
    ) {
        coroutineScope.launch {
            callback(loginOrCreate(data))
        }
    }

    override fun loginOrCreateCompletable(data: LoginOrCreateRequest): CompletableFuture<StytchResult<LoginOrCreateResponse>> =
        coroutineScope.async {
            loginOrCreate(data)
        }.asCompletableFuture()
}
