package com.stytch.java.b2b.api.otpsms

// !!!
// WARNING: This file is autogenerated
// Only modify code within MANUAL() sections
// or your changes may be overwritten later!
// !!!

import com.squareup.moshi.Moshi
import com.stytch.java.b2b.models.otpsms.AuthenticateRequest
import com.stytch.java.b2b.models.otpsms.AuthenticateResponse
import com.stytch.java.b2b.models.otpsms.SendRequest
import com.stytch.java.b2b.models.otpsms.SendResponse
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

public interface Sms {
    /**
     * Send a One-Time Passcode (OTP) to a Member's phone number.
     *
     * If the Member already has a phone number, the `mfa_phone_number` field is not needed; the endpoint will send an OTP to
     * the number associated with the Member.
     * If the Member does not have a phone number, the endpoint will send an OTP to the `mfa_phone_number` provided and link
     * the `mfa_phone_number` with the Member.
     *
     * An error will be thrown if the Member already has a phone number and the provided `mfa_phone_number` does not match the
     * existing one.
     *
     * Note that sending another OTP code before the first has expired will invalidate the first code.
     *
     * If a Member has a phone number and is enrolled in MFA, then after a successful primary authentication event (e.g.
     * [email magic link](https://stytch.com/docs/b2b/api/authenticate-magic-link) or
     * [SSO](https://stytch.com/docs/b2b/api/sso-authenticate) login is complete), an SMS OTP will automatically be sent to
     * their phone number. In that case, this endpoint should only be used for subsequent authentication events, such as
     * prompting a Member for an OTP again after a period of inactivity.
     *
     * Passing an intermediate session token, session token, or session JWT is not required, but if passed must match the
     * Member ID passed.
     *
     * ### Cost to send SMS OTP
     * Before configuring SMS or WhatsApp OTPs, please review how Stytch
     * [bills the costs of international OTPs](https://stytch.com/pricing) and understand how to protect your app against
     * [toll fraud](https://stytch.com/docs/guides/passcodes/toll-fraud/overview).
     *
     * Even when international SMS is enabled, we do not support sending SMS to countries on our
     * [Unsupported countries list](https://stytch.com/docs/guides/passcodes/unsupported-countries).
     *
     * __Note:__ SMS to phone numbers outside of the US and Canada is disabled by default for customers who did not use SMS
     * prior to October 2023. If you're interested in sending international SMS, please reach out to
     * [support@stytch.com](mailto:support@stytch.com?subject=Enable%20international%20SMS).
     */
    public suspend fun send(data: SendRequest): StytchResult<SendResponse>

    /**
     * Send a One-Time Passcode (OTP) to a Member's phone number.
     *
     * If the Member already has a phone number, the `mfa_phone_number` field is not needed; the endpoint will send an OTP to
     * the number associated with the Member.
     * If the Member does not have a phone number, the endpoint will send an OTP to the `mfa_phone_number` provided and link
     * the `mfa_phone_number` with the Member.
     *
     * An error will be thrown if the Member already has a phone number and the provided `mfa_phone_number` does not match the
     * existing one.
     *
     * Note that sending another OTP code before the first has expired will invalidate the first code.
     *
     * If a Member has a phone number and is enrolled in MFA, then after a successful primary authentication event (e.g.
     * [email magic link](https://stytch.com/docs/b2b/api/authenticate-magic-link) or
     * [SSO](https://stytch.com/docs/b2b/api/sso-authenticate) login is complete), an SMS OTP will automatically be sent to
     * their phone number. In that case, this endpoint should only be used for subsequent authentication events, such as
     * prompting a Member for an OTP again after a period of inactivity.
     *
     * Passing an intermediate session token, session token, or session JWT is not required, but if passed must match the
     * Member ID passed.
     *
     * ### Cost to send SMS OTP
     * Before configuring SMS or WhatsApp OTPs, please review how Stytch
     * [bills the costs of international OTPs](https://stytch.com/pricing) and understand how to protect your app against
     * [toll fraud](https://stytch.com/docs/guides/passcodes/toll-fraud/overview).
     *
     * Even when international SMS is enabled, we do not support sending SMS to countries on our
     * [Unsupported countries list](https://stytch.com/docs/guides/passcodes/unsupported-countries).
     *
     * __Note:__ SMS to phone numbers outside of the US and Canada is disabled by default for customers who did not use SMS
     * prior to October 2023. If you're interested in sending international SMS, please reach out to
     * [support@stytch.com](mailto:support@stytch.com?subject=Enable%20international%20SMS).
     */
    public fun send(
        data: SendRequest,
        callback: (StytchResult<SendResponse>) -> Unit,
    )

    /**
     * Send a One-Time Passcode (OTP) to a Member's phone number.
     *
     * If the Member already has a phone number, the `mfa_phone_number` field is not needed; the endpoint will send an OTP to
     * the number associated with the Member.
     * If the Member does not have a phone number, the endpoint will send an OTP to the `mfa_phone_number` provided and link
     * the `mfa_phone_number` with the Member.
     *
     * An error will be thrown if the Member already has a phone number and the provided `mfa_phone_number` does not match the
     * existing one.
     *
     * Note that sending another OTP code before the first has expired will invalidate the first code.
     *
     * If a Member has a phone number and is enrolled in MFA, then after a successful primary authentication event (e.g.
     * [email magic link](https://stytch.com/docs/b2b/api/authenticate-magic-link) or
     * [SSO](https://stytch.com/docs/b2b/api/sso-authenticate) login is complete), an SMS OTP will automatically be sent to
     * their phone number. In that case, this endpoint should only be used for subsequent authentication events, such as
     * prompting a Member for an OTP again after a period of inactivity.
     *
     * Passing an intermediate session token, session token, or session JWT is not required, but if passed must match the
     * Member ID passed.
     *
     * ### Cost to send SMS OTP
     * Before configuring SMS or WhatsApp OTPs, please review how Stytch
     * [bills the costs of international OTPs](https://stytch.com/pricing) and understand how to protect your app against
     * [toll fraud](https://stytch.com/docs/guides/passcodes/toll-fraud/overview).
     *
     * Even when international SMS is enabled, we do not support sending SMS to countries on our
     * [Unsupported countries list](https://stytch.com/docs/guides/passcodes/unsupported-countries).
     *
     * __Note:__ SMS to phone numbers outside of the US and Canada is disabled by default for customers who did not use SMS
     * prior to October 2023. If you're interested in sending international SMS, please reach out to
     * [support@stytch.com](mailto:support@stytch.com?subject=Enable%20international%20SMS).
     */
    public fun sendCompletable(data: SendRequest): CompletableFuture<StytchResult<SendResponse>>

    /**
     * SMS OTPs may not be used as a primary authentication mechanism. They can be used to complete an MFA requirement, or
     * they can be used as a step-up factor to be added to an existing session.
     *
     * This endpoint verifies that the one-time passcode (OTP) is valid and hasn't expired or been previously used. A given
     * Member may only have a single active OTP code at any given time. If a Member requests another OTP code before the first
     * one has expired, the first one will be invalidated.
     *
     * Exactly one of `intermediate_session_token`, `session_token`, or `session_jwt` must be provided in the request.
     * If an intermediate session token is provided, this operation will consume it.
     *
     * Intermediate session tokens are generated upon successful calls to primary authenticate methods in the case where MFA
     * is required,
     * such as [email magic link authenticate](https://stytch.com/docs/b2b/api/authenticate-magic-link),
     * or upon successful calls to discovery authenticate methods, such as
     * [email magic link discovery authenticate](https://stytch.com/docs/b2b/api/authenticate-discovery-magic-link).
     *
     * If the Organization's MFA policy is `REQUIRED_FOR_ALL`, a successful OTP authentication will change the Member's
     * `mfa_enrolled` status to `true` if it is not already `true`.
     * If the Organization's MFA policy is `OPTIONAL`, the Member's MFA enrollment can be toggled by passing in a value for
     * the `set_mfa_enrollment` field.
     * The Member's MFA enrollment can also be toggled through the
     * [Update Member](https://stytch.com/docs/b2b/api/update-member) endpoint.
     *
     * Provide the `session_duration_minutes` parameter to set the lifetime of the session. If the `session_duration_minutes`
     * parameter is not specified, a Stytch session will be created with a duration of 60 minutes.
     */
    public suspend fun authenticate(data: AuthenticateRequest): StytchResult<AuthenticateResponse>

    /**
     * SMS OTPs may not be used as a primary authentication mechanism. They can be used to complete an MFA requirement, or
     * they can be used as a step-up factor to be added to an existing session.
     *
     * This endpoint verifies that the one-time passcode (OTP) is valid and hasn't expired or been previously used. A given
     * Member may only have a single active OTP code at any given time. If a Member requests another OTP code before the first
     * one has expired, the first one will be invalidated.
     *
     * Exactly one of `intermediate_session_token`, `session_token`, or `session_jwt` must be provided in the request.
     * If an intermediate session token is provided, this operation will consume it.
     *
     * Intermediate session tokens are generated upon successful calls to primary authenticate methods in the case where MFA
     * is required,
     * such as [email magic link authenticate](https://stytch.com/docs/b2b/api/authenticate-magic-link),
     * or upon successful calls to discovery authenticate methods, such as
     * [email magic link discovery authenticate](https://stytch.com/docs/b2b/api/authenticate-discovery-magic-link).
     *
     * If the Organization's MFA policy is `REQUIRED_FOR_ALL`, a successful OTP authentication will change the Member's
     * `mfa_enrolled` status to `true` if it is not already `true`.
     * If the Organization's MFA policy is `OPTIONAL`, the Member's MFA enrollment can be toggled by passing in a value for
     * the `set_mfa_enrollment` field.
     * The Member's MFA enrollment can also be toggled through the
     * [Update Member](https://stytch.com/docs/b2b/api/update-member) endpoint.
     *
     * Provide the `session_duration_minutes` parameter to set the lifetime of the session. If the `session_duration_minutes`
     * parameter is not specified, a Stytch session will be created with a duration of 60 minutes.
     */
    public fun authenticate(
        data: AuthenticateRequest,
        callback: (StytchResult<AuthenticateResponse>) -> Unit,
    )

    /**
     * SMS OTPs may not be used as a primary authentication mechanism. They can be used to complete an MFA requirement, or
     * they can be used as a step-up factor to be added to an existing session.
     *
     * This endpoint verifies that the one-time passcode (OTP) is valid and hasn't expired or been previously used. A given
     * Member may only have a single active OTP code at any given time. If a Member requests another OTP code before the first
     * one has expired, the first one will be invalidated.
     *
     * Exactly one of `intermediate_session_token`, `session_token`, or `session_jwt` must be provided in the request.
     * If an intermediate session token is provided, this operation will consume it.
     *
     * Intermediate session tokens are generated upon successful calls to primary authenticate methods in the case where MFA
     * is required,
     * such as [email magic link authenticate](https://stytch.com/docs/b2b/api/authenticate-magic-link),
     * or upon successful calls to discovery authenticate methods, such as
     * [email magic link discovery authenticate](https://stytch.com/docs/b2b/api/authenticate-discovery-magic-link).
     *
     * If the Organization's MFA policy is `REQUIRED_FOR_ALL`, a successful OTP authentication will change the Member's
     * `mfa_enrolled` status to `true` if it is not already `true`.
     * If the Organization's MFA policy is `OPTIONAL`, the Member's MFA enrollment can be toggled by passing in a value for
     * the `set_mfa_enrollment` field.
     * The Member's MFA enrollment can also be toggled through the
     * [Update Member](https://stytch.com/docs/b2b/api/update-member) endpoint.
     *
     * Provide the `session_duration_minutes` parameter to set the lifetime of the session. If the `session_duration_minutes`
     * parameter is not specified, a Stytch session will be created with a duration of 60 minutes.
     */
    public fun authenticateCompletable(data: AuthenticateRequest): CompletableFuture<StytchResult<AuthenticateResponse>>
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
            httpClient.post("/v1/b2b/otps/sms/send", asJson, headers)
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

    override suspend fun authenticate(data: AuthenticateRequest): StytchResult<AuthenticateResponse> =
        withContext(Dispatchers.IO) {
            var headers = emptyMap<String, String>()

            val asJson = moshi.adapter(AuthenticateRequest::class.java).toJson(data)
            httpClient.post("/v1/b2b/otps/sms/authenticate", asJson, headers)
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
