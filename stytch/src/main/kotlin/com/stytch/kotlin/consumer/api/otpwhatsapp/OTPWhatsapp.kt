package com.stytch.kotlin.consumer.api.otpwhatsapp

// !!!
// WARNING: This file is autogenerated
// Only modify code within MANUAL() sections
// or your changes may be overwritten later!
// !!!

import com.squareup.moshi.Moshi
import com.stytch.kotlin.common.StytchResult
import com.stytch.kotlin.consumer.models.otpwhatsapp.LoginOrCreateRequest
import com.stytch.kotlin.consumer.models.otpwhatsapp.LoginOrCreateResponse
import com.stytch.kotlin.consumer.models.otpwhatsapp.SendRequest
import com.stytch.kotlin.consumer.models.otpwhatsapp.SendResponse
import com.stytch.kotlin.http.HttpClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.future.asCompletableFuture
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.CompletableFuture

public interface Whatsapp {
    /**
     * Send a one-time passcode (OTP) to a User's WhatsApp. If you'd like to create a user and send them a passcode with one
     * request, use our [log in or create](https://stytch.com/docs/api/whatsapp-login-or-create) endpoint.
     *
     * Note that sending another OTP code before the first has expired will invalidate the first code.
     *
     * ### Add a phone number to an existing user
     *
     * This endpoint also allows you to add a new phone number to an existing Stytch User. Including a `user_id`,
     * `session_token`, or `session_jwt` in the request will add the phone number to the pre-existing Stytch User upon
     * successful authentication.
     *
     * Adding a new phone number to an existing Stytch User requires the user to be present and validate the phone number via
     * OTP. This requirement is in place to prevent account takeover attacks.
     *
     * ### Next steps
     *
     * Collect the OTP which was delivered to the user. Call [Authenticate OTP](https://stytch.com/docs/api/authenticate-otp)
     * using the OTP `code` along with the `phone_id` found in the response as the `method_id`.
     */
    public suspend fun send(data: SendRequest): StytchResult<SendResponse>

    /**
     * Send a one-time passcode (OTP) to a User's WhatsApp. If you'd like to create a user and send them a passcode with one
     * request, use our [log in or create](https://stytch.com/docs/api/whatsapp-login-or-create) endpoint.
     *
     * Note that sending another OTP code before the first has expired will invalidate the first code.
     *
     * ### Add a phone number to an existing user
     *
     * This endpoint also allows you to add a new phone number to an existing Stytch User. Including a `user_id`,
     * `session_token`, or `session_jwt` in the request will add the phone number to the pre-existing Stytch User upon
     * successful authentication.
     *
     * Adding a new phone number to an existing Stytch User requires the user to be present and validate the phone number via
     * OTP. This requirement is in place to prevent account takeover attacks.
     *
     * ### Next steps
     *
     * Collect the OTP which was delivered to the user. Call [Authenticate OTP](https://stytch.com/docs/api/authenticate-otp)
     * using the OTP `code` along with the `phone_id` found in the response as the `method_id`.
     */
    public fun send(data: SendRequest, callback: (StytchResult<SendResponse>) -> Unit)

    /**
     * Send a one-time passcode (OTP) to a User's WhatsApp. If you'd like to create a user and send them a passcode with one
     * request, use our [log in or create](https://stytch.com/docs/api/whatsapp-login-or-create) endpoint.
     *
     * Note that sending another OTP code before the first has expired will invalidate the first code.
     *
     * ### Add a phone number to an existing user
     *
     * This endpoint also allows you to add a new phone number to an existing Stytch User. Including a `user_id`,
     * `session_token`, or `session_jwt` in the request will add the phone number to the pre-existing Stytch User upon
     * successful authentication.
     *
     * Adding a new phone number to an existing Stytch User requires the user to be present and validate the phone number via
     * OTP. This requirement is in place to prevent account takeover attacks.
     *
     * ### Next steps
     *
     * Collect the OTP which was delivered to the user. Call [Authenticate OTP](https://stytch.com/docs/api/authenticate-otp)
     * using the OTP `code` along with the `phone_id` found in the response as the `method_id`.
     */
    public fun sendCompletable(data: SendRequest): CompletableFuture<StytchResult<SendResponse>>

    /**
     * Send a one-time passcode (OTP) to a User's WhatsApp using their phone number. If the phone number is not associated
     * with a User already, a User will be created.
     *
     * ### Next steps
     *
     * Collect the OTP which was delivered to the User. Call [Authenticate OTP](https://stytch.com/docs/api/authenticate-otp)
     * using the OTP `code` along with the `phone_id` found in the response as the `method_id`.
     */
    public suspend fun loginOrCreate(data: LoginOrCreateRequest): StytchResult<LoginOrCreateResponse>

    /**
     * Send a one-time passcode (OTP) to a User's WhatsApp using their phone number. If the phone number is not associated
     * with a User already, a User will be created.
     *
     * ### Next steps
     *
     * Collect the OTP which was delivered to the User. Call [Authenticate OTP](https://stytch.com/docs/api/authenticate-otp)
     * using the OTP `code` along with the `phone_id` found in the response as the `method_id`.
     */
    public fun loginOrCreate(data: LoginOrCreateRequest, callback: (StytchResult<LoginOrCreateResponse>) -> Unit)

    /**
     * Send a one-time passcode (OTP) to a User's WhatsApp using their phone number. If the phone number is not associated
     * with a User already, a User will be created.
     *
     * ### Next steps
     *
     * Collect the OTP which was delivered to the User. Call [Authenticate OTP](https://stytch.com/docs/api/authenticate-otp)
     * using the OTP `code` along with the `phone_id` found in the response as the `method_id`.
     */
    public fun loginOrCreateCompletable(data: LoginOrCreateRequest): CompletableFuture<StytchResult<LoginOrCreateResponse>>
}

internal class WhatsappImpl(
    private val httpClient: HttpClient,
    private val coroutineScope: CoroutineScope,
) : Whatsapp {

    private val moshi = Moshi.Builder().build()

    override suspend fun send(data: SendRequest): StytchResult<SendResponse> = withContext(Dispatchers.IO) {
        val asJson = moshi.adapter(SendRequest::class.java).toJson(data)
        httpClient.post("/v1/otps/whatsapp/send", asJson)
    }

    override fun send(data: SendRequest, callback: (StytchResult<SendResponse>) -> Unit) {
        coroutineScope.launch {
            callback(send(data))
        }
    }

    override fun sendCompletable(data: SendRequest): CompletableFuture<StytchResult<SendResponse>> =
        coroutineScope.async {
            send(data)
        }.asCompletableFuture()
    override suspend fun loginOrCreate(data: LoginOrCreateRequest): StytchResult<LoginOrCreateResponse> = withContext(Dispatchers.IO) {
        val asJson = moshi.adapter(LoginOrCreateRequest::class.java).toJson(data)
        httpClient.post("/v1/otps/whatsapp/login_or_create", asJson)
    }

    override fun loginOrCreate(data: LoginOrCreateRequest, callback: (StytchResult<LoginOrCreateResponse>) -> Unit) {
        coroutineScope.launch {
            callback(loginOrCreate(data))
        }
    }

    override fun loginOrCreateCompletable(data: LoginOrCreateRequest): CompletableFuture<StytchResult<LoginOrCreateResponse>> =
        coroutineScope.async {
            loginOrCreate(data)
        }.asCompletableFuture()
}
