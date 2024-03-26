package com.stytch.java.b2b.models.totps

// !!!
// WARNING: This file is autogenerated
// Only modify code within MANUAL() sections
// or your changes may be overwritten later!
// !!!

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.stytch.java.b2b.models.organizations.Member
import com.stytch.java.b2b.models.organizations.Organization
import com.stytch.java.b2b.models.sessions.MemberSession

/**
* Request type for `TOTPs.authenticate`.
*/
@JsonClass(generateAdapter = true)
public data class AuthenticateRequest
    @JvmOverloads
    constructor(
        /**
         * Globally unique UUID that identifies a specific Organization. The `organization_id` is critical to perform operations
         * on an Organization, so be sure to preserve this value.
         */
        @Json(name = "organization_id")
        val organizationId: String,
        /**
         * Globally unique UUID that identifies a specific Member. The `member_id` is critical to perform operations on a Member,
         * so be sure to preserve this value.
         */
        @Json(name = "member_id")
        val memberId: String,
        /**
         * The code to authenticate.
         */
        @Json(name = "code")
        val code: String,
        /**
         * The Intermediate Session Token. This token does not necessarily belong to a specific instance of a Member, but
         * represents a bag of factors that may be converted to a member session. The token can be used with the
         * [OTP SMS Authenticate endpoint](https://stytch.com/docs/b2b/api/authenticate-otp-sms),
         * [TOTP Authenticate endpoint](https://stytch.com/docs/b2b/api/authenticate-totp),
         *     or [Recovery Codes Recover endpoint](https://stytch.com/docs/b2b/api/recovery-codes-recover) to complete an MFA
         * flow and log in to the Organization. It can also be used with the
         * [Exchange Intermediate Session endpoint](https://stytch.com/docs/b2b/api/exchange-intermediate-session) to join a
         * specific Organization that allows the factors represented by the intermediate session token;
         *     or the
         * [Create Organization via Discovery endpoint](https://stytch.com/docs/b2b/api/create-organization-via-discovery) to
         * create a new Organization and Member.
         */
        @Json(name = "intermediate_session_token")
        val intermediateSessionToken: String? = null,
        /**
         * A secret token for a given Stytch Session.
         */
        @Json(name = "session_token")
        val sessionToken: String? = null,
        /**
         * The JSON Web Token (JWT) for a given Stytch Session.
         */
        @Json(name = "session_jwt")
        val sessionJwt: String? = null,
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
         * Optionally sets the Member’s MFA enrollment status upon a successful authentication. If the Organization’s MFA policy
         * is `REQUIRED_FOR_ALL`, this field will be ignored. If this field is not passed in, the Member’s `mfa_enrolled` boolean
         * will not be affected. The options are:
         *
         *   `enroll` – sets the Member's `mfa_enrolled` boolean to `true`. The Member will be required to complete an MFA step
         * upon subsequent logins to the Organization.
         *
         *   `unenroll` –  sets the Member's `mfa_enrolled` boolean to `false`. The Member will no longer be required to complete
         * MFA steps when logging in to the Organization.
         *
         */
        @Json(name = "set_mfa_enrollment")
        val setMfaEnrollment: String? = null,
        /**
         * If passed will set the authenticated method to the default MFA method. Completing an MFA authentication flow for the
         * first time for a Member will implicitly set the method to the default MFA method. This option can be used to update the
         * default MFA method if multiple are being used.
         */
        @Json(name = "set_default_mfa")
        val setDefaultMfa: Boolean? = null,
    )

/**
* Response type for `TOTPs.authenticate`.
*/
@JsonClass(generateAdapter = true)
public data class AuthenticateResponse
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
         * The [Member object](https://stytch.com/docs/b2b/api/member-object)
         */
        @Json(name = "member")
        val member: Member,
        /**
         * The [Organization object](https://stytch.com/docs/b2b/api/organization-object).
         */
        @Json(name = "organization")
        val organization: Organization,
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
    )

/**
* Request type for `TOTPs.create`.
*/
@JsonClass(generateAdapter = true)
public data class CreateRequest
    @JvmOverloads
    constructor(
        /**
         * Globally unique UUID that identifies a specific Organization. The `organization_id` is critical to perform operations
         * on an Organization, so be sure to preserve this value.
         */
        @Json(name = "organization_id")
        val organizationId: String,
        /**
         * Globally unique UUID that identifies a specific Member. The `member_id` is critical to perform operations on a Member,
         * so be sure to preserve this value.
         */
        @Json(name = "member_id")
        val memberId: String,
        /**
         * The expiration for the TOTP registration. If the newly created TOTP registration is not authenticated within this time
         * frame the member will have to restart the registration flow. Defaults to 60 (1 hour) with a minimum of 5 and a maximum
         * of 1440.
         */
        @Json(name = "expiration_minutes")
        val expirationMinutes: Int? = null,
        /**
         * The Intermediate Session Token. This token does not necessarily belong to a specific instance of a Member, but
         * represents a bag of factors that may be converted to a member session. The token can be used with the
         * [OTP SMS Authenticate endpoint](https://stytch.com/docs/b2b/api/authenticate-otp-sms),
         * [TOTP Authenticate endpoint](https://stytch.com/docs/b2b/api/authenticate-totp),
         *     or [Recovery Codes Recover endpoint](https://stytch.com/docs/b2b/api/recovery-codes-recover) to complete an MFA
         * flow and log in to the Organization. It can also be used with the
         * [Exchange Intermediate Session endpoint](https://stytch.com/docs/b2b/api/exchange-intermediate-session) to join a
         * specific Organization that allows the factors represented by the intermediate session token;
         *     or the
         * [Create Organization via Discovery endpoint](https://stytch.com/docs/b2b/api/create-organization-via-discovery) to
         * create a new Organization and Member.
         */
        @Json(name = "intermediate_session_token")
        val intermediateSessionToken: String? = null,
        /**
         * A secret token for a given Stytch Session.
         */
        @Json(name = "session_token")
        val sessionToken: String? = null,
        /**
         * The JSON Web Token (JWT) for a given Stytch Session.
         */
        @Json(name = "session_jwt")
        val sessionJwt: String? = null,
    )

/**
* Response type for `TOTPs.create`.
*/
@JsonClass(generateAdapter = true)
public data class CreateResponse
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
         * The unique ID for a TOTP instance.
         */
        @Json(name = "totp_registration_id")
        val totpRegistrationId: String,
        /**
         * The TOTP secret key shared between the authenticator app and the server used to generate TOTP codes.
         */
        @Json(name = "secret")
        val secret: String,
        /**
         * The QR code image encoded in base64.
         */
        @Json(name = "qr_code")
        val qrCode: String,
        /**
         * An array of recovery codes that can be used to recover a Member's account.
         */
        @Json(name = "recovery_codes")
        val recoveryCodes: List<String>,
        /**
         * The [Member object](https://stytch.com/docs/b2b/api/member-object)
         */
        @Json(name = "member")
        val member: Member,
        /**
         * The [Organization object](https://stytch.com/docs/b2b/api/organization-object).
         */
        @Json(name = "organization")
        val organization: Organization,
        /**
         * The HTTP status code of the response. Stytch follows standard HTTP response status code patterns, e.g. 2XX values
         * equate to success, 3XX values are redirects, 4XX are client errors, and 5XX are server errors.
         */
        @Json(name = "status_code")
        val statusCode: Int,
    )

/**
* Request type for `TOTPs.migrate`.
*/
@JsonClass(generateAdapter = true)
public data class MigrateRequest
    @JvmOverloads
    constructor(
        /**
         * Globally unique UUID that identifies a specific Organization. The `organization_id` is critical to perform operations
         * on an Organization, so be sure to preserve this value.
         */
        @Json(name = "organization_id")
        val organizationId: String,
        /**
         * Globally unique UUID that identifies a specific Member. The `member_id` is critical to perform operations on a Member,
         * so be sure to preserve this value.
         */
        @Json(name = "member_id")
        val memberId: String,
        /**
         * The TOTP secret key shared between the authenticator app and the server used to generate TOTP codes.
         */
        @Json(name = "secret")
        val secret: String,
        /**
         * An existing set of recovery codes to be imported into Stytch to be used to authenticate in place of the secondary MFA
         * method.
         */
        @Json(name = "recovery_codes")
        val recoveryCodes: List<String>,
    )

/**
* Response type for `TOTPs.migrate`.
*/
@JsonClass(generateAdapter = true)
public data class MigrateResponse
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
         * The [Member object](https://stytch.com/docs/b2b/api/member-object)
         */
        @Json(name = "member")
        val member: Member,
        /**
         * The [Organization object](https://stytch.com/docs/b2b/api/organization-object).
         */
        @Json(name = "organization")
        val organization: Organization,
        /**
         * The unique ID for a TOTP instance.
         */
        @Json(name = "totp_registration_id")
        val totpRegistrationId: String,
        /**
         * An array of recovery codes that can be used to recover a Member's account.
         */
        @Json(name = "recovery_codes")
        val recoveryCodes: List<String>,
        /**
         * The HTTP status code of the response. Stytch follows standard HTTP response status code patterns, e.g. 2XX values
         * equate to success, 3XX values are redirects, 4XX are client errors, and 5XX are server errors.
         */
        @Json(name = "status_code")
        val statusCode: Int,
    )
