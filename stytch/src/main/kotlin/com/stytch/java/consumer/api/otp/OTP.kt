package com.stytch.java.consumer.api.otp

// !!!
// WARNING: This file is autogenerated
// Only modify code within MANUAL() sections
// or your changes may be overwritten later!
// !!!

import com.squareup.moshi.Moshi
import com.stytch.java.common.InstantAdapter
import com.stytch.java.common.StytchResult
import com.stytch.java.consumer.api.otpemail.Email
import com.stytch.java.consumer.api.otpemail.EmailImpl
import com.stytch.java.consumer.api.otpsms.Sms
import com.stytch.java.consumer.api.otpsms.SmsImpl
import com.stytch.java.consumer.api.otpwhatsapp.Whatsapp
import com.stytch.java.consumer.api.otpwhatsapp.WhatsappImpl
import com.stytch.java.consumer.models.otp.AuthenticateRequest
import com.stytch.java.consumer.models.otp.AuthenticateResponse
import com.stytch.java.http.HttpClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.future.asCompletableFuture
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.CompletableFuture

public interface OTPs {
    public val sms: Sms

    public val whatsapp: Whatsapp

    public val email: Email

    /**
     * Authenticate a User given a `method_id` (the associated `email_id` or `phone_id`) and a `code`. This endpoint verifies
     * that the code is valid, hasn't expired or been previously used, and any optional security settings such as IP match or
     * user agent match are satisfied. A given `method_id` may only have a single active OTP code at any given time, if a User
     * requests another OTP code before the first one has expired, the first one will be invalidated.
     */
    public suspend fun authenticate(data: AuthenticateRequest): StytchResult<AuthenticateResponse>

    /**
     * Authenticate a User given a `method_id` (the associated `email_id` or `phone_id`) and a `code`. This endpoint verifies
     * that the code is valid, hasn't expired or been previously used, and any optional security settings such as IP match or
     * user agent match are satisfied. A given `method_id` may only have a single active OTP code at any given time, if a User
     * requests another OTP code before the first one has expired, the first one will be invalidated.
     */
    public fun authenticate(
        data: AuthenticateRequest,
        callback: (StytchResult<AuthenticateResponse>) -> Unit,
    )

    /**
     * Authenticate a User given a `method_id` (the associated `email_id` or `phone_id`) and a `code`. This endpoint verifies
     * that the code is valid, hasn't expired or been previously used, and any optional security settings such as IP match or
     * user agent match are satisfied. A given `method_id` may only have a single active OTP code at any given time, if a User
     * requests another OTP code before the first one has expired, the first one will be invalidated.
     */
    public fun authenticateCompletable(data: AuthenticateRequest): CompletableFuture<StytchResult<AuthenticateResponse>>
}

internal class OTPsImpl(private val httpClient: HttpClient, private val coroutineScope: CoroutineScope) : OTPs {
    private val moshi = Moshi.Builder().add(InstantAdapter()).build()

    override val sms: Sms = SmsImpl(httpClient, coroutineScope)
    override val whatsapp: Whatsapp = WhatsappImpl(httpClient, coroutineScope)
    override val email: Email = EmailImpl(httpClient, coroutineScope)

    override suspend fun authenticate(data: AuthenticateRequest): StytchResult<AuthenticateResponse> =
        withContext(Dispatchers.IO) {
            val asJson = moshi.adapter(AuthenticateRequest::class.java).toJson(data)
            httpClient.post("/v1/otps/authenticate", asJson)
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
