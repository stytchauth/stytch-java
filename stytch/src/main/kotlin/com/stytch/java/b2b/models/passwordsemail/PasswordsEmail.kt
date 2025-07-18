package com.stytch.java.b2b.models.passwordsemail

// !!!
// WARNING: This file is autogenerated
// Only modify code within MANUAL() sections
// or your changes may be overwritten later!
// !!!

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.stytch.java.b2b.models.mfa.MfaRequired
import com.stytch.java.b2b.models.organizations.Member
import com.stytch.java.b2b.models.organizations.Organization
import com.stytch.java.b2b.models.sessions.MemberSession
import com.stytch.java.b2b.models.sessions.PrimaryRequired
import com.stytch.java.common.methodoptions.Authorization

@JsonClass(generateAdapter = false)
public enum class ResetRequestLocale {
    @Json(name = "en")
    EN,

    @Json(name = "es")
    ES,

    @Json(name = "ptbr")
    PTBR,

    @Json(name = "fr")
    FR,
}

@JsonClass(generateAdapter = false)
public enum class ResetStartRequestLocale {
    @Json(name = "en")
    EN,

    @Json(name = "es")
    ES,

    @Json(name = "ptbr")
    PTBR,

    @Json(name = "fr")
    FR,
}

public data class RequireResetRequestOptions
    @JvmOverloads
    constructor(
        /**
         * Optional authorization object.
         * Pass in an active Stytch Member session token or session JWT and the request
         * will be run using that member's permissions.
         */
        val authorization: Authorization? = null,
    ) {
        internal fun addHeaders(headers: Map<String, String> = emptyMap()): Map<String, String> {
            var res = mapOf<String, String>()
            if (authorization != null) {
                res = authorization.addHeaders(res)
            }
            return res + headers
        }
    }

/**
* Request type for `Email.requireReset`.
*/
@JsonClass(generateAdapter = true)
public data class RequireResetRequest
    @JvmOverloads
    constructor(
        /**
         * The email address of the Member to start the email reset process for.
         */
        @Json(name = "email_address")
        val emailAddress: String,
        /**
         * Globally unique UUID that identifies a specific Organization. The `organization_id` is critical to perform operations
         * on an Organization, so be sure to preserve this value. You may also use the organization_slug here as a convenience.
         */
        @Json(name = "organization_id")
        val organizationId: String? = null,
        /**
         * Globally unique UUID that identifies a specific Member. The `member_id` is critical to perform operations on a Member,
         * so be sure to preserve this value. You may use an external_id here if one is set for the member.
         */
        @Json(name = "member_id")
        val memberId: String? = null,
    )

/**
* Response type for `Email.requireReset`.
*/
@JsonClass(generateAdapter = true)
public data class RequireResetResponse
    @JvmOverloads
    constructor(
        /**
         * The HTTP status code of the response. Stytch follows standard HTTP response status code patterns, e.g. 2XX values
         * equate to success, 3XX values are redirects, 4XX are client errors, and 5XX are server errors.
         */
        @Json(name = "status_code")
        val statusCode: Int,
        /**
         * Globally unique UUID that identifies a specific Member.
         */
        @Json(name = "member_id")
        val memberId: String? = null,
        /**
         * The [Member object](https://stytch.com/docs/b2b/api/member-object)
         */
        @Json(name = "member")
        val member: Member? = null,
        /**
         * The [Organization object](https://stytch.com/docs/b2b/api/organization-object).
         */
        @Json(name = "organization")
        val organization: Organization? = null,
    )

/**
* Request type for `Email.reset`.
*/
@JsonClass(generateAdapter = true)
public data class ResetRequest
    @JvmOverloads
    constructor(
        /**
         * The password reset token to authenticate.
         */
        @Json(name = "password_reset_token")
        val passwordResetToken: String,
        /**
         * The password to authenticate, reset, or set for the first time. Any UTF8 character is allowed, e.g. spaces, emojis,
         * non-English characters, etc.
         */
        @Json(name = "password")
        val password: String,
        /**
         * Reuse an existing session instead of creating a new one. If you provide a `session_token`, Stytch will update the
         * session.
         *       If the `session_token` and `magic_links_token` belong to different Members, the `session_token` will be ignored.
         * This endpoint will error if
         *       both `session_token` and `session_jwt` are provided.
         */
        @Json(name = "session_token")
        val sessionToken: String? = null,
        /**
         * Set the session lifetime to be this many minutes from now. This will start a new session if one doesn't already exist,
         *   returning both an opaque `session_token` and `session_jwt` for this session. Remember that the `session_jwt` will
         * have a fixed lifetime of
         *   five minutes regardless of the underlying session duration, and will need to be refreshed over time.
         *
         *   This value must be a minimum of 5 and a maximum of 527040 minutes (366 days).
         *
         *   If a `session_token` or `session_jwt` is provided then a successful authentication will continue to extend the
         * session this many minutes.
         *
         *   If the `session_duration_minutes` parameter is not specified, a Stytch session will be created with a 60 minute
         * duration. If you don't want
         *   to use the Stytch session product, you can ignore the session fields in the response.
         */
        @Json(name = "session_duration_minutes")
        val sessionDurationMinutes: Int? = null,
        /**
         * Reuse an existing session instead of creating a new one. If you provide a `session_jwt`, Stytch will update the
         * session. If the `session_jwt`
         *       and `magic_links_token` belong to different Members, the `session_jwt` will be ignored. This endpoint will error
         * if both `session_token` and `session_jwt`
         *       are provided.
         */
        @Json(name = "session_jwt")
        val sessionJwt: String? = null,
        /**
         * A base64url encoded one time secret used to validate that the request starts and ends on the same device.
         */
        @Json(name = "code_verifier")
        val codeVerifier: String? = null,
        /**
         * Add a custom claims map to the Session being authenticated. Claims are only created if a Session is initialized by
         * providing a value in
         *   `session_duration_minutes`. Claims will be included on the Session object and in the JWT. To update a key in an
         * existing Session, supply a new value. To
         *   delete a key, supply a null value. Custom claims made with reserved claims (`iss`, `sub`, `aud`, `exp`, `nbf`, `iat`,
         * `jti`) will be ignored.
         *   Total custom claims size cannot exceed four kilobytes.
         */
        @Json(name = "session_custom_claims")
        val sessionCustomClaims: Map<String, Any?>? = emptyMap(),
        /**
         * If the Member needs to complete an MFA step, and the Member has a phone number, this endpoint will pre-emptively send a
         * one-time passcode (OTP) to the Member's phone number. The locale argument will be used to determine which language to
         * use when sending the passcode.
         *
         * Parameter is a [IETF BCP 47 language tag](https://www.w3.org/International/articles/language-tags/), e.g. `"en"`.
         *
         * Currently supported languages are English (`"en"`), Spanish (`"es"`), and Brazilian Portuguese (`"pt-br"`); if no value
         * is provided, the copy defaults to English.
         *
         * Request support for additional languages
         * [here](https://docs.google.com/forms/d/e/1FAIpQLScZSpAu_m2AmLXRT3F3kap-s_mcV6UTBitYn6CdyWP0-o7YjQ/viewform?usp=sf_link")!
         *
         */
        @Json(name = "locale")
        val locale: ResetRequestLocale? = null,
        /**
         * Adds this primary authentication factor to the intermediate session token. If the resulting set of factors satisfies
         * the organization's primary authentication requirements and MFA requirements, the intermediate session token will be
         * consumed and converted to a member session. If not, the same intermediate session token will be returned.
         */
        @Json(name = "intermediate_session_token")
        val intermediateSessionToken: String? = null,
    )

/**
* Response type for `Email.reset`.
*/
@JsonClass(generateAdapter = true)
public data class ResetResponse
    @JvmOverloads
    constructor(
        /**
         * Globally unique UUID that is returned with every API call. This value is important to log for debugging purposes; we
         * may ask for this value to help identify a specific API call when helping you debug an issue.
         */
        @Json(name = "request_id")
        val requestId: String,
        /**
         * Globally unique UUID that identifies a specific Member.
         */
        @Json(name = "member_id")
        val memberId: String,
        /**
         * Globally unique UUID that identifies a member's email
         */
        @Json(name = "member_email_id")
        val memberEmailId: String,
        /**
         * Globally unique UUID that identifies a specific Organization. The `organization_id` is critical to perform operations
         * on an Organization, so be sure to preserve this value.
         */
        @Json(name = "organization_id")
        val organizationId: String,
        /**
         * The [Member object](https://stytch.com/docs/b2b/api/member-object)
         */
        @Json(name = "member")
        val member: Member,
        /**
         * A secret token for a given Stytch Session.
         */
        @Json(name = "session_token")
        val sessionToken: String,
        /**
         * The JSON Web Token (JWT) for a given Stytch Session.
         */
        @Json(name = "session_jwt")
        val sessionJwt: String,
        /**
         * The [Organization object](https://stytch.com/docs/b2b/api/organization-object).
         */
        @Json(name = "organization")
        val organization: Organization,
        /**
         * The returned Intermediate Session Token contains a password factor associated with the Member. If this value is
         * non-empty, the member must complete an MFA step to finish logging in to the Organization. The token can be used with
         * the [OTP SMS Authenticate endpoint](https://stytch.com/docs/b2b/api/authenticate-otp-sms),
         * [TOTP Authenticate endpoint](https://stytch.com/docs/b2b/api/authenticate-totp), or
         * [Recovery Codes Recover endpoint](https://stytch.com/docs/b2b/api/recovery-codes-recover) to complete an MFA flow and
         * log in to the Organization. The token has a default expiry of 10 minutes. Password factors are not transferable between
         * Organizations, so the intermediate session token is not valid for use with discovery endpoints.
         */
        @Json(name = "intermediate_session_token")
        val intermediateSessionToken: String,
        /**
         * Indicates whether the Member is fully authenticated. If false, the Member needs to complete an MFA step to log in to
         * the Organization.
         */
        @Json(name = "member_authenticated")
        val memberAuthenticated: Boolean,
        /**
         * The HTTP status code of the response. Stytch follows standard HTTP response status code patterns, e.g. 2XX values
         * equate to success, 3XX values are redirects, 4XX are client errors, and 5XX are server errors.
         */
        @Json(name = "status_code")
        val statusCode: Int,
        /**
         * The [Session object](https://stytch.com/docs/b2b/api/session-object).
         */
        @Json(name = "member_session")
        val memberSession: MemberSession? = null,
        /**
         * Information about the MFA requirements of the Organization and the Member's options for fulfilling MFA.
         */
        @Json(name = "mfa_required")
        val mfaRequired: MfaRequired? = null,
        /**
         * Information about the primary authentication requirements of the Organization.
         */
        @Json(name = "primary_required")
        val primaryRequired: PrimaryRequired? = null,
    )

/**
* Request type for `Email.resetStart`.
*/
@JsonClass(generateAdapter = true)
public data class ResetStartRequest
    @JvmOverloads
    constructor(
        /**
         * Globally unique UUID that identifies a specific Organization. The `organization_id` is critical to perform operations
         * on an Organization, so be sure to preserve this value. You may also use the organization_slug here as a convenience.
         */
        @Json(name = "organization_id")
        val organizationId: String,
        /**
         * The email address of the Member to start the email reset process for.
         */
        @Json(name = "email_address")
        val emailAddress: String,
        /**
         * The URL that the Member clicks from the reset password link. This URL should be an endpoint in the backend server that
         * verifies the request by querying
         *   Stytch's authenticate endpoint and finishes the reset password flow. If this value is not passed, the default
         * `reset_password_redirect_url` that you set in your Dashboard is used.
         *   If you have not set a default `reset_password_redirect_url`, an error is returned.
         */
        @Json(name = "reset_password_redirect_url")
        val resetPasswordRedirectURL: String? = null,
        /**
         * Sets a time limit after which the email link to reset the member's password will no longer be valid. The minimum
         * allowed expiration is 5 minutes and the maximum is 10080 minutes (7 days). By default, the expiration is 30 minutes.
         */
        @Json(name = "reset_password_expiration_minutes")
        val resetPasswordExpirationMinutes: Int? = null,
        /**
         * A base64url encoded SHA256 hash of a one time secret used to validate that the request starts and ends on the same
         * device.
         */
        @Json(name = "code_challenge")
        val codeChallenge: String? = null,
        /**
         * The URL that the member clicks from the reset without password link. This URL should be an endpoint in the backend
         * server
         *       that verifies the request by querying Stytch's authenticate endpoint and finishes the magic link flow. If this
         * value is not passed, the
         *       default `login_redirect_url` that you set in your Dashboard is used. This value is only used if magic links are
         * enabled for the member. If
         *       you have not set a default `login_redirect_url` and magic links are not enabled for the member, an error is
         * returned.
         */
        @Json(name = "login_redirect_url")
        val loginRedirectURL: String? = null,
        /**
         * Used to determine which language to use when sending the user this delivery method. Parameter is a
         * [IETF BCP 47 language tag](https://www.w3.org/International/articles/language-tags/), e.g. `"en"`.
         *
         * Currently supported languages are English (`"en"`), Spanish (`"es"`), French (`"fr"`) and Brazilian Portuguese
         * (`"pt-br"`); if no value is provided, the copy defaults to English.
         *
         * Request support for additional languages
         * [here](https://docs.google.com/forms/d/e/1FAIpQLScZSpAu_m2AmLXRT3F3kap-s_mcV6UTBitYn6CdyWP0-o7YjQ/viewform?usp=sf_link")!
         *
         */
        @Json(name = "locale")
        val locale: ResetStartRequestLocale? = null,
        /**
         * Use a custom template for reset password emails. By default, it will use your default email template. The template must
         * be a template using our built-in customizations or a custom HTML email for Passwords - Reset Password.
         */
        @Json(name = "reset_password_template_id")
        val resetPasswordTemplateId: String? = null,
        /**
         * Use a custom template for verification emails sent during password reset flows. When cross-organization passwords are
         * enabled for your Project, this template will be used the first time a user sets a password via a
         *   password reset flow. By default, it will use your default email template. The template must be a template using our
         * built-in customizations or a custom HTML email for Passwords - Email Verification.
         */
        @Json(name = "verify_email_template_id")
        val verifyEmailTemplateId: String? = null,
    )

/**
* Response type for `Email.resetStart`.
*/
@JsonClass(generateAdapter = true)
public data class ResetStartResponse
    @JvmOverloads
    constructor(
        /**
         * Globally unique UUID that is returned with every API call. This value is important to log for debugging purposes; we
         * may ask for this value to help identify a specific API call when helping you debug an issue.
         */
        @Json(name = "request_id")
        val requestId: String,
        /**
         * Globally unique UUID that identifies a specific Member.
         */
        @Json(name = "member_id")
        val memberId: String,
        /**
         * Globally unique UUID that identifies a member's email
         */
        @Json(name = "member_email_id")
        val memberEmailId: String,
        /**
         * The [Member object](https://stytch.com/docs/b2b/api/member-object)
         */
        @Json(name = "member")
        val member: Member,
        /**
         * The HTTP status code of the response. Stytch follows standard HTTP response status code patterns, e.g. 2XX values
         * equate to success, 3XX values are redirects, 4XX are client errors, and 5XX are server errors.
         */
        @Json(name = "status_code")
        val statusCode: Int,
    )
