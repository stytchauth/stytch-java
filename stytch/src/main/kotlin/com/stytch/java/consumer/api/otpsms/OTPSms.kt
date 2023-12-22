package com.stytch.java.consumer.api.otpsms

// !!!
// WARNING: This file is autogenerated
// Only modify code within MANUAL() sections
// or your changes may be overwritten later!
// !!!

import com.squareup.moshi.Moshi
import com.stytch.java.common.InstantAdapter
import com.stytch.java.common.StytchResult
import com.stytch.java.consumer.models.otpsms.LoginOrCreateRequest
import com.stytch.java.consumer.models.otpsms.LoginOrCreateResponse
import com.stytch.java.consumer.models.otpsms.SendRequest
import com.stytch.java.consumer.models.otpsms.SendResponse
import com.stytch.java.http.HttpClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.future.asCompletableFuture
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.CompletableFuture

public interface Sms {
    /**
     * Send a one-time passcode (OTP) to a user's phone number. If you'd like to create a user and send them a passcode with
     * one request, use our [log in or create](https://stytch.com/docs/api/log-in-or-create-user-by-sms) endpoint.
     *
     * Note that sending another OTP code before the first has expired will invalidate the first code.
     *
     * ### Cost to send SMS OTP
     * Before configuring SMS or WhatsApp OTPs, please review how Stytch
     * [bills the costs of international OTPs](https://stytch.com/pricing) and understand how to protect your app against
     * [toll fraud](https://stytch.com/docs/guides/passcodes/toll-fraud/overview).
     *
     * __Note:__ SMS to phone numbers outside of the US and Canada is disabled by default for customers who did not use SMS
     * prior to October 2023. If you're interested in sending international SMS, please reach out to
     * [support@stytch.com](mailto:support@stytch.com?subject=Enable%20international%20SMS).
     *
     * ### Add a phone number to an existing user
     *
     * This endpoint also allows you to add a new phone number to an existing Stytch User. Including a `user_id`,
     * `session_token`, or `session_jwt` in your Send one-time passcode by SMS request will add the new, unverified phone
     * number to the existing Stytch User. If the user successfully authenticates within 5 minutes, the new phone number will
     * be marked as verified and remain permanently on the existing Stytch User. Otherwise, it will be removed from the User
     * object, and any subsequent login requests using that phone number will create a new User.
     *
     * ### Next steps
     *
     * Collect the OTP which was delivered to the user. Call [Authenticate OTP](https://stytch.com/docs/api/authenticate-otp)
     * using the OTP `code` along with the `phone_id` found in the response as the `method_id`.
     */
    public suspend fun send(data: SendRequest): StytchResult<SendResponse>

    /**
     * Send a one-time passcode (OTP) to a user's phone number. If you'd like to create a user and send them a passcode with
     * one request, use our [log in or create](https://stytch.com/docs/api/log-in-or-create-user-by-sms) endpoint.
     *
     * Note that sending another OTP code before the first has expired will invalidate the first code.
     *
     * ### Cost to send SMS OTP
     * Before configuring SMS or WhatsApp OTPs, please review how Stytch
     * [bills the costs of international OTPs](https://stytch.com/pricing) and understand how to protect your app against
     * [toll fraud](https://stytch.com/docs/guides/passcodes/toll-fraud/overview).
     *
     * __Note:__ SMS to phone numbers outside of the US and Canada is disabled by default for customers who did not use SMS
     * prior to October 2023. If you're interested in sending international SMS, please reach out to
     * [support@stytch.com](mailto:support@stytch.com?subject=Enable%20international%20SMS).
     *
     * ### Add a phone number to an existing user
     *
     * This endpoint also allows you to add a new phone number to an existing Stytch User. Including a `user_id`,
     * `session_token`, or `session_jwt` in your Send one-time passcode by SMS request will add the new, unverified phone
     * number to the existing Stytch User. If the user successfully authenticates within 5 minutes, the new phone number will
     * be marked as verified and remain permanently on the existing Stytch User. Otherwise, it will be removed from the User
     * object, and any subsequent login requests using that phone number will create a new User.
     *
     * ### Next steps
     *
     * Collect the OTP which was delivered to the user. Call [Authenticate OTP](https://stytch.com/docs/api/authenticate-otp)
     * using the OTP `code` along with the `phone_id` found in the response as the `method_id`.
     */
    public fun send(
        data: SendRequest,
        callback: (StytchResult<SendResponse>) -> Unit,
    )

    /**
     * Send a one-time passcode (OTP) to a user's phone number. If you'd like to create a user and send them a passcode with
     * one request, use our [log in or create](https://stytch.com/docs/api/log-in-or-create-user-by-sms) endpoint.
     *
     * Note that sending another OTP code before the first has expired will invalidate the first code.
     *
     * ### Cost to send SMS OTP
     * Before configuring SMS or WhatsApp OTPs, please review how Stytch
     * [bills the costs of international OTPs](https://stytch.com/pricing) and understand how to protect your app against
     * [toll fraud](https://stytch.com/docs/guides/passcodes/toll-fraud/overview).
     *
     * __Note:__ SMS to phone numbers outside of the US and Canada is disabled by default for customers who did not use SMS
     * prior to October 2023. If you're interested in sending international SMS, please reach out to
     * [support@stytch.com](mailto:support@stytch.com?subject=Enable%20international%20SMS).
     *
     * ### Add a phone number to an existing user
     *
     * This endpoint also allows you to add a new phone number to an existing Stytch User. Including a `user_id`,
     * `session_token`, or `session_jwt` in your Send one-time passcode by SMS request will add the new, unverified phone
     * number to the existing Stytch User. If the user successfully authenticates within 5 minutes, the new phone number will
     * be marked as verified and remain permanently on the existing Stytch User. Otherwise, it will be removed from the User
     * object, and any subsequent login requests using that phone number will create a new User.
     *
     * ### Next steps
     *
     * Collect the OTP which was delivered to the user. Call [Authenticate OTP](https://stytch.com/docs/api/authenticate-otp)
     * using the OTP `code` along with the `phone_id` found in the response as the `method_id`.
     */
    public fun sendCompletable(data: SendRequest): CompletableFuture<StytchResult<SendResponse>>

    /**
     * Send a One-Time Passcode (OTP) to a User using their phone number. If the phone number is not associated with a user
     * already, a user will be created.
     *
     * ### Cost to send SMS OTP
     * Before configuring SMS or WhatsApp OTPs, please review how Stytch
     * [bills the costs of international OTPs](https://stytch.com/pricing) and understand how to protect your app against
     * [toll fraud](https://stytch.com/docs/guides/passcodes/toll-fraud/overview).
     *
     * __Note:__ SMS to phone numbers outside of the US and Canada is disabled by default for customers who did not use SMS
     * prior to October 2023. If you're interested in sending international SMS, please reach out to
     * [support@stytch.com](mailto:support@stytch.com?subject=Enable%20international%20SMS).
     *
     * ### Next steps
     *
     * Collect the OTP which was delivered to the User. Call [Authenticate OTP](https://stytch.com/docs/api/authenticate-otp)
     * using the OTP `code` along with the `phone_id` found in the response as the `method_id`.
     */
    public suspend fun loginOrCreate(data: LoginOrCreateRequest): StytchResult<LoginOrCreateResponse>

    /**
     * Send a One-Time Passcode (OTP) to a User using their phone number. If the phone number is not associated with a user
     * already, a user will be created.
     *
     * ### Cost to send SMS OTP
     * Before configuring SMS or WhatsApp OTPs, please review how Stytch
     * [bills the costs of international OTPs](https://stytch.com/pricing) and understand how to protect your app against
     * [toll fraud](https://stytch.com/docs/guides/passcodes/toll-fraud/overview).
     *
     * __Note:__ SMS to phone numbers outside of the US and Canada is disabled by default for customers who did not use SMS
     * prior to October 2023. If you're interested in sending international SMS, please reach out to
     * [support@stytch.com](mailto:support@stytch.com?subject=Enable%20international%20SMS).
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
     * Send a One-Time Passcode (OTP) to a User using their phone number. If the phone number is not associated with a user
     * already, a user will be created.
     *
     * ### Cost to send SMS OTP
     * Before configuring SMS or WhatsApp OTPs, please review how Stytch
     * [bills the costs of international OTPs](https://stytch.com/pricing) and understand how to protect your app against
     * [toll fraud](https://stytch.com/docs/guides/passcodes/toll-fraud/overview).
     *
     * __Note:__ SMS to phone numbers outside of the US and Canada is disabled by default for customers who did not use SMS
     * prior to October 2023. If you're interested in sending international SMS, please reach out to
     * [support@stytch.com](mailto:support@stytch.com?subject=Enable%20international%20SMS).
     *
     * ### Next steps
     *
     * Collect the OTP which was delivered to the User. Call [Authenticate OTP](https://stytch.com/docs/api/authenticate-otp)
     * using the OTP `code` along with the `phone_id` found in the response as the `method_id`.
     */
    public fun loginOrCreateCompletable(data: LoginOrCreateRequest): CompletableFuture<StytchResult<LoginOrCreateResponse>>
}

internal class SmsImpl(
    private val httpClient: HttpClient,
    private val coroutineScope: CoroutineScope,
) : Sms {
    private val moshi = Moshi.Builder().add(InstantAdapter()).build()

    override suspend fun send(data: SendRequest): StytchResult<SendResponse> =
        withContext(Dispatchers.IO) {
            var headers = emptyMap<String, String>()

            val asJson = moshi.adapter(SendRequest::class.java).toJson(data)
            httpClient.post("/v1/otps/sms/send", asJson, headers)
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
            httpClient.post("/v1/otps/sms/login_or_create", asJson, headers)
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
