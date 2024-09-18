package com.stytch.java.b2b.api.passwords

// !!!
// WARNING: This file is autogenerated
// Only modify code within MANUAL() sections
// or your changes may be overwritten later!
// !!!

import com.squareup.moshi.Moshi
import com.stytch.java.b2b.api.passwordsemail.Email
import com.stytch.java.b2b.api.passwordsemail.EmailImpl
import com.stytch.java.b2b.api.passwordsexistingpassword.ExistingPassword
import com.stytch.java.b2b.api.passwordsexistingpassword.ExistingPasswordImpl
import com.stytch.java.b2b.api.passwordssession.Sessions
import com.stytch.java.b2b.api.passwordssession.SessionsImpl
import com.stytch.java.b2b.models.passwords.AuthenticateRequest
import com.stytch.java.b2b.models.passwords.AuthenticateResponse
import com.stytch.java.b2b.models.passwords.MigrateRequest
import com.stytch.java.b2b.models.passwords.MigrateResponse
import com.stytch.java.b2b.models.passwords.StrengthCheckRequest
import com.stytch.java.b2b.models.passwords.StrengthCheckResponse
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

public interface Passwords {
    public val email: Email

    public val sessions: Sessions

    public val existingPassword: ExistingPassword

    /**
     * This API allows you to check whether the user’s provided password is valid, and to provide feedback to the user on how
     * to increase the strength of their password.
     *
     * This endpoint adapts to your Project's password strength configuration. If you're using
     * [zxcvbn](https://stytch.com/docs/guides/passwords/strength-policy), the default, your passwords are considered valid if
     * the strength score is >= 3. If you're using [LUDS](https://stytch.com/docs/guides/passwords/strength-policy), your
     * passwords are considered valid if they meet the requirements that you've set with Stytch. You may update your password
     * strength configuration in the [stytch dashboard](https://stytch.com/dashboard/password-strength-config).
     *
     * ## Password feedback
     * The zxcvbn_feedback and luds_feedback objects contains relevant fields for you to relay feedback to users that failed
     * to create a strong enough password.
     *
     * If you're using [zxcvbn](https://stytch.com/docs/guides/passwords/strength-policy), the feedback object will contain
     * warning and suggestions for any password that does not meet the
     * [zxcvbn](https://stytch.com/docs/guides/passwords/strength-policy) strength requirements. You can return these strings
     * directly to the user to help them craft a strong password.
     *
     * If you're using [LUDS](https://stytch.com/docs/guides/passwords/strength-policy), the feedback object will contain a
     * collection of fields that the user failed or passed. You'll want to prompt the user to create a password that meets all
     * requirements that they failed.
     */
    public suspend fun strengthCheck(data: StrengthCheckRequest): StytchResult<StrengthCheckResponse>

    /**
     * This API allows you to check whether the user’s provided password is valid, and to provide feedback to the user on how
     * to increase the strength of their password.
     *
     * This endpoint adapts to your Project's password strength configuration. If you're using
     * [zxcvbn](https://stytch.com/docs/guides/passwords/strength-policy), the default, your passwords are considered valid if
     * the strength score is >= 3. If you're using [LUDS](https://stytch.com/docs/guides/passwords/strength-policy), your
     * passwords are considered valid if they meet the requirements that you've set with Stytch. You may update your password
     * strength configuration in the [stytch dashboard](https://stytch.com/dashboard/password-strength-config).
     *
     * ## Password feedback
     * The zxcvbn_feedback and luds_feedback objects contains relevant fields for you to relay feedback to users that failed
     * to create a strong enough password.
     *
     * If you're using [zxcvbn](https://stytch.com/docs/guides/passwords/strength-policy), the feedback object will contain
     * warning and suggestions for any password that does not meet the
     * [zxcvbn](https://stytch.com/docs/guides/passwords/strength-policy) strength requirements. You can return these strings
     * directly to the user to help them craft a strong password.
     *
     * If you're using [LUDS](https://stytch.com/docs/guides/passwords/strength-policy), the feedback object will contain a
     * collection of fields that the user failed or passed. You'll want to prompt the user to create a password that meets all
     * requirements that they failed.
     */
    public fun strengthCheck(
        data: StrengthCheckRequest,
        callback: (StytchResult<StrengthCheckResponse>) -> Unit,
    )

    /**
     * This API allows you to check whether the user’s provided password is valid, and to provide feedback to the user on how
     * to increase the strength of their password.
     *
     * This endpoint adapts to your Project's password strength configuration. If you're using
     * [zxcvbn](https://stytch.com/docs/guides/passwords/strength-policy), the default, your passwords are considered valid if
     * the strength score is >= 3. If you're using [LUDS](https://stytch.com/docs/guides/passwords/strength-policy), your
     * passwords are considered valid if they meet the requirements that you've set with Stytch. You may update your password
     * strength configuration in the [stytch dashboard](https://stytch.com/dashboard/password-strength-config).
     *
     * ## Password feedback
     * The zxcvbn_feedback and luds_feedback objects contains relevant fields for you to relay feedback to users that failed
     * to create a strong enough password.
     *
     * If you're using [zxcvbn](https://stytch.com/docs/guides/passwords/strength-policy), the feedback object will contain
     * warning and suggestions for any password that does not meet the
     * [zxcvbn](https://stytch.com/docs/guides/passwords/strength-policy) strength requirements. You can return these strings
     * directly to the user to help them craft a strong password.
     *
     * If you're using [LUDS](https://stytch.com/docs/guides/passwords/strength-policy), the feedback object will contain a
     * collection of fields that the user failed or passed. You'll want to prompt the user to create a password that meets all
     * requirements that they failed.
     */
    public fun strengthCheckCompletable(data: StrengthCheckRequest): CompletableFuture<StytchResult<StrengthCheckResponse>>

    /**
     * Adds an existing password to a member's email that doesn't have a password yet. We support migrating members from
     * passwords stored with bcrypt, scrypt, argon2, MD-5, SHA-1, and PBKDF2. This endpoint has a rate limit of 100 requests
     * per second.
     *
     * The member's email will be marked as verified when you use this endpoint.
     */
    public suspend fun migrate(data: MigrateRequest): StytchResult<MigrateResponse>

    /**
     * Adds an existing password to a member's email that doesn't have a password yet. We support migrating members from
     * passwords stored with bcrypt, scrypt, argon2, MD-5, SHA-1, and PBKDF2. This endpoint has a rate limit of 100 requests
     * per second.
     *
     * The member's email will be marked as verified when you use this endpoint.
     */
    public fun migrate(
        data: MigrateRequest,
        callback: (StytchResult<MigrateResponse>) -> Unit,
    )

    /**
     * Adds an existing password to a member's email that doesn't have a password yet. We support migrating members from
     * passwords stored with bcrypt, scrypt, argon2, MD-5, SHA-1, and PBKDF2. This endpoint has a rate limit of 100 requests
     * per second.
     *
     * The member's email will be marked as verified when you use this endpoint.
     */
    public fun migrateCompletable(data: MigrateRequest): CompletableFuture<StytchResult<MigrateResponse>>

    /**
     * Authenticate a member with their email address and password. This endpoint verifies that the member has a password
     * currently set, and that the entered password is correct.
     *
     * If you have breach detection during authentication enabled in your
     * [password strength policy](https://stytch.com/docs/b2b/guides/passwords/strength-policies) and the member's credentials
     * have appeared in the HaveIBeenPwned dataset, this endpoint will return a `member_reset_password` error even if the
     * member enters a correct password. We force a password reset in this case to ensure that the member is the legitimate
     * owner of the email address and not a malicious actor abusing the compromised credentials.
     *
     * If the is required to complete MFA to log in to the, the returned value of `member_authenticated` will be `false`, and
     * an `intermediate_session_token` will be returned.
     * The `intermediate_session_token` can be passed into the
     * [OTP SMS Authenticate endpoint](https://stytch.com/docs/b2b/api/authenticate-otp-sms) to complete the MFA step and
     * acquire a full member session.
     * The `session_duration_minutes` and `session_custom_claims` parameters will be ignored.
     *
     * If a valid `session_token` or `session_jwt` is passed in, the Member will not be required to complete an MFA step.
     */
    public suspend fun authenticate(data: AuthenticateRequest): StytchResult<AuthenticateResponse>

    /**
     * Authenticate a member with their email address and password. This endpoint verifies that the member has a password
     * currently set, and that the entered password is correct.
     *
     * If you have breach detection during authentication enabled in your
     * [password strength policy](https://stytch.com/docs/b2b/guides/passwords/strength-policies) and the member's credentials
     * have appeared in the HaveIBeenPwned dataset, this endpoint will return a `member_reset_password` error even if the
     * member enters a correct password. We force a password reset in this case to ensure that the member is the legitimate
     * owner of the email address and not a malicious actor abusing the compromised credentials.
     *
     * If the is required to complete MFA to log in to the, the returned value of `member_authenticated` will be `false`, and
     * an `intermediate_session_token` will be returned.
     * The `intermediate_session_token` can be passed into the
     * [OTP SMS Authenticate endpoint](https://stytch.com/docs/b2b/api/authenticate-otp-sms) to complete the MFA step and
     * acquire a full member session.
     * The `session_duration_minutes` and `session_custom_claims` parameters will be ignored.
     *
     * If a valid `session_token` or `session_jwt` is passed in, the Member will not be required to complete an MFA step.
     */
    public fun authenticate(
        data: AuthenticateRequest,
        callback: (StytchResult<AuthenticateResponse>) -> Unit,
    )

    /**
     * Authenticate a member with their email address and password. This endpoint verifies that the member has a password
     * currently set, and that the entered password is correct.
     *
     * If you have breach detection during authentication enabled in your
     * [password strength policy](https://stytch.com/docs/b2b/guides/passwords/strength-policies) and the member's credentials
     * have appeared in the HaveIBeenPwned dataset, this endpoint will return a `member_reset_password` error even if the
     * member enters a correct password. We force a password reset in this case to ensure that the member is the legitimate
     * owner of the email address and not a malicious actor abusing the compromised credentials.
     *
     * If the is required to complete MFA to log in to the, the returned value of `member_authenticated` will be `false`, and
     * an `intermediate_session_token` will be returned.
     * The `intermediate_session_token` can be passed into the
     * [OTP SMS Authenticate endpoint](https://stytch.com/docs/b2b/api/authenticate-otp-sms) to complete the MFA step and
     * acquire a full member session.
     * The `session_duration_minutes` and `session_custom_claims` parameters will be ignored.
     *
     * If a valid `session_token` or `session_jwt` is passed in, the Member will not be required to complete an MFA step.
     */
    public fun authenticateCompletable(data: AuthenticateRequest): CompletableFuture<StytchResult<AuthenticateResponse>>
}

internal class PasswordsImpl(
    private val httpClient: HttpClient,
    private val coroutineScope: CoroutineScope,
) : Passwords {
    private val moshi = Moshi.Builder().add(InstantAdapter()).build()

    override val email: Email = EmailImpl(httpClient, coroutineScope)
    override val sessions: Sessions = SessionsImpl(httpClient, coroutineScope)
    override val existingPassword: ExistingPassword = ExistingPasswordImpl(httpClient, coroutineScope)

    override suspend fun strengthCheck(data: StrengthCheckRequest): StytchResult<StrengthCheckResponse> =
        withContext(Dispatchers.IO) {
            var headers = emptyMap<String, String>()

            val asJson = moshi.adapter(StrengthCheckRequest::class.java).toJson(data)
            httpClient.post("/v1/b2b/passwords/strength_check", asJson, headers)
        }

    override fun strengthCheck(
        data: StrengthCheckRequest,
        callback: (StytchResult<StrengthCheckResponse>) -> Unit,
    ) {
        coroutineScope.launch {
            callback(strengthCheck(data))
        }
    }

    override fun strengthCheckCompletable(data: StrengthCheckRequest): CompletableFuture<StytchResult<StrengthCheckResponse>> =
        coroutineScope.async {
            strengthCheck(data)
        }.asCompletableFuture()

    override suspend fun migrate(data: MigrateRequest): StytchResult<MigrateResponse> =
        withContext(Dispatchers.IO) {
            var headers = emptyMap<String, String>()

            val asJson = moshi.adapter(MigrateRequest::class.java).toJson(data)
            httpClient.post("/v1/b2b/passwords/migrate", asJson, headers)
        }

    override fun migrate(
        data: MigrateRequest,
        callback: (StytchResult<MigrateResponse>) -> Unit,
    ) {
        coroutineScope.launch {
            callback(migrate(data))
        }
    }

    override fun migrateCompletable(data: MigrateRequest): CompletableFuture<StytchResult<MigrateResponse>> =
        coroutineScope.async {
            migrate(data)
        }.asCompletableFuture()

    override suspend fun authenticate(data: AuthenticateRequest): StytchResult<AuthenticateResponse> =
        withContext(Dispatchers.IO) {
            var headers = emptyMap<String, String>()

            val asJson = moshi.adapter(AuthenticateRequest::class.java).toJson(data)
            httpClient.post("/v1/b2b/passwords/authenticate", asJson, headers)
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
