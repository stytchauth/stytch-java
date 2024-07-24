package com.stytch.java.b2b.models.sessions

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
import com.stytch.java.common.methodoptions.Authorization
import com.stytch.java.consumer.models.sessions.AuthenticationFactor
import com.stytch.java.consumer.models.sessions.JWK
import java.time.Instant

@JsonClass(generateAdapter = false)
public enum class ExchangeRequestLocale {
    @Json(name = "en")
    EN,

    @Json(name = "es")
    ES,

    @Json(name = "ptbr")
    PTBR,
}

@JsonClass(generateAdapter = true)
public data class AuthorizationCheck
    @JvmOverloads
    constructor(
        /**
         * Globally unique UUID that identifies a specific Organization. The `organization_id` is critical to perform operations
         * on an Organization, so be sure to preserve this value.
         */
        @Json(name = "organization_id")
        val organizationId: String,
        /**
         * A unique identifier of the RBAC Resource, provided by the developer and intended to be human-readable.
         *
         *   A `resource_id` is not allowed to start with `stytch`, which is a special prefix used for Stytch default Resources
         * with reserved  `resource_id`s. These include:
         *
         *   * `stytch.organization`
         *   * `stytch.member`
         *   * `stytch.sso`
         *   * `stytch.self`
         *
         *   Check out the [guide on Stytch default Resources](https://stytch.com/docs/b2b/guides/rbac/stytch-default) for a more
         * detailed explanation.
         *
         *
         */
        @Json(name = "resource_id")
        val resourceId: String,
        /**
         * An action to take on a Resource.
         */
        @Json(name = "action")
        val action: String,
    )

@JsonClass(generateAdapter = true)
public data class AuthorizationVerdict
    @JvmOverloads
    constructor(
        @Json(name = "authorized")
        val authorized: Boolean,
        @Json(name = "granting_roles")
        val grantingRoles: List<String>,
    )

@JsonClass(generateAdapter = true)
public data class MemberSession
    @JvmOverloads
    constructor(
        /**
         * Globally unique UUID that identifies a specific Session.
         */
        @Json(name = "member_session_id")
        val memberSessionId: String,
        /**
         * Globally unique UUID that identifies a specific Member.
         */
        @Json(name = "member_id")
        val memberId: String,
        /**
         * The timestamp when the Session was created. Values conform to the RFC 3339 standard and are expressed in UTC, e.g.
         * `2021-12-29T12:33:09Z`.
         */
        @Json(name = "started_at")
        val startedAt: Instant,
        /**
         * The timestamp when the Session was last accessed. Values conform to the RFC 3339 standard and are expressed in UTC,
         * e.g. `2021-12-29T12:33:09Z`.
         */
        @Json(name = "last_accessed_at")
        val lastAccessedAt: Instant,
        /**
         * The timestamp when the Session expires. Values conform to the RFC 3339 standard and are expressed in UTC, e.g.
         * `2021-12-29T12:33:09Z`.
         */
        @Json(name = "expires_at")
        val expiresAt: Instant,
        /**
         * An array of different authentication factors that comprise a Session.
         */
        @Json(name = "authentication_factors")
        val authenticationFactors: List<AuthenticationFactor>,
        /**
         * Globally unique UUID that identifies a specific Organization. The `organization_id` is critical to perform operations
         * on an Organization, so be sure to preserve this value.
         */
        @Json(name = "organization_id")
        val organizationId: String,
        @Json(name = "roles")
        val roles: List<String>,
        /**
         * The custom claims map for a Session. Claims can be added to a session during a Sessions authenticate call.
         */
        @Json(name = "custom_claims")
        val customClaims: Map<String, Any?>? = emptyMap(),
    )

@JsonClass(generateAdapter = true)
public data class PrimaryRequired
    @JvmOverloads
    constructor(
        /**
         * If non-empty, indicates that the Organization restricts the authentication methods it allows for login (such as `sso`
         * or `password`), and the end user must complete one of those authentication methods to log in. If empty, indicates that
         * the Organization does not restrict the authentication method it allows for login, but the end user does not have any
         * transferrable primary factors. Only email magic link and OAuth factors can be transferred between Organizations.
         */
        @Json(name = "allowed_auth_methods")
        val allowedAuthMethods: List<String>,
    )

public data class RevokeRequestOptions
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
* Request type for `Sessions.authenticate`.
*/
@JsonClass(generateAdapter = true)
public data class AuthenticateRequest
    @JvmOverloads
    constructor(
        /**
         * A secret token for a given Stytch Session.
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
         * The JSON Web Token (JWT) for a given Stytch Session.
         */
        @Json(name = "session_jwt")
        val sessionJwt: String? = null,
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
         * If an `authorization_check` object is passed in, this endpoint will also check if the Member is
         *   authorized to perform the given action on the given Resource in the specified Organization. A Member is authorized if
         *   their Member Session contains a Role, assigned
         *   [explicitly or implicitly](https://stytch.com/docs/b2b/guides/rbac/role-assignment), with adequate permissions.
         *   In addition, the `organization_id` passed in the authorization check must match the Member's Organization.
         *
         *   The Roles on the Member Session may differ from the Roles you see on the Member object - Roles that are implicitly
         *   assigned by SSO connection or SSO group will only be valid for a Member Session if there is at least one
         * authentication
         *   factor on the Member Session from the specified SSO connection.
         *
         *   If the Member is not authorized to perform the specified action on the specified Resource, or if the
         *   `organization_id` does not match the Member's Organization, a 403 error will be thrown.
         *   Otherwise, the response will contain a list of Roles that satisfied the authorization check.
         */
        @Json(name = "authorization_check")
        val authorizationCheck: AuthorizationCheck? = null,
    )

/**
* Response type for `Sessions.authenticate`.
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
         * The [Session object](https://stytch.com/docs/b2b/api/session-object).
         */
        @Json(name = "member_session")
        val memberSession: MemberSession,
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
        /**
         * If an `authorization_check` is provided in the request and the check succeeds, this field will return
         *   the complete list of Roles that gave the Member permission to perform the specified action on the specified Resource.
         */
        @Json(name = "verdict")
        val verdict: AuthorizationVerdict? = null,
    )

/**
* Request type for `Sessions.exchange`.
*/
@JsonClass(generateAdapter = true)
public data class ExchangeRequest
    @JvmOverloads
    constructor(
        /**
         * Globally unique UUID that identifies a specific Organization. The `organization_id` is critical to perform operations
         * on an Organization, so be sure to preserve this value.
         */
        @Json(name = "organization_id")
        val organizationId: String,
        /**
         * The `session_token` belonging to the member that you wish to associate the email with.
         */
        @Json(name = "session_token")
        val sessionToken: String? = null,
        /**
         * The `session_jwt` belonging to the member that you wish to associate the email with.
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
        val locale: ExchangeRequestLocale? = null,
    )

/**
* Response type for `Sessions.exchange`.
*/
@JsonClass(generateAdapter = true)
public data class ExchangeResponse
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
         * The [Session object](https://stytch.com/docs/b2b/api/session-object).
         */
        @Json(name = "member_session")
        val memberSession: MemberSession,
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
         * Indicates whether the Member is fully authenticated. If false, the Member needs to complete an MFA step to log in to
         * the Organization.
         */
        @Json(name = "member_authenticated")
        val memberAuthenticated: Boolean,
        /**
         * The returned Intermediate Session Token contains any Email Magic Link or OAuth factors from the original member session
         * that are valid for the target Organization. If this value is non-empty, the member must complete an MFA step to finish
         * logging in to the Organization. The token can be used with the
         * [OTP SMS Authenticate endpoint](https://stytch.com/docs/b2b/api/authenticate-otp-sms),
         * [TOTP Authenticate endpoint](https://stytch.com/docs/b2b/api/authenticate-totp), or
         * [Recovery Codes Recover endpoint](https://stytch.com/docs/b2b/api/recovery-codes-recover) to complete an MFA flow and
         * log in to the Organization. It can also be used with the
         * [Exchange Intermediate Session endpoint](https://stytch.com/docs/b2b/api/exchange-intermediate-session) to join a
         * specific Organization that allows the factors represented by the intermediate session token; or the
         * [Create Organization via Discovery endpoint](https://stytch.com/docs/b2b/api/create-organization-via-discovery) to
         * create a new Organization and Member.
         */
        @Json(name = "intermediate_session_token")
        val intermediateSessionToken: String,
        /**
         * The HTTP status code of the response. Stytch follows standard HTTP response status code patterns, e.g. 2XX values
         * equate to success, 3XX values are redirects, 4XX are client errors, and 5XX are server errors.
         */
        @Json(name = "status_code")
        val statusCode: Int,
        /**
         * Information about the MFA requirements of the Organization and the Member's options for fulfilling MFA.
         */
        @Json(name = "mfa_required")
        val mfaRequired: MfaRequired? = null,
        @Json(name = "primary_required")
        val primaryRequired: PrimaryRequired? = null,
    )

/**
* Request type for `Sessions.getJWKS`.
*/
@JsonClass(generateAdapter = true)
public data class GetJWKSRequest
    @JvmOverloads
    constructor(
        /**
         * The `project_id` to get the JWKS for.
         */
        @Json(name = "project_id")
        val projectId: String,
    )

/**
* Response type for `Sessions.getJWKS`.
*/
@JsonClass(generateAdapter = true)
public data class GetJWKSResponse
    @JvmOverloads
    constructor(
        /**
         * The JWK
         */
        @Json(name = "keys")
        val keys: List<JWK>,
        /**
         * Globally unique UUID that is returned with every API call. This value is important to log for debugging purposes; we
         * may ask for this value to help identify a specific API call when helping you debug an issue.
         */
        @Json(name = "request_id")
        val requestId: String,
        /**
         * The HTTP status code of the response. Stytch follows standard HTTP response status code patterns, e.g. 2XX values
         * equate to success, 3XX values are redirects, 4XX are client errors, and 5XX are server errors.
         */
        @Json(name = "status_code")
        val statusCode: Int,
    )

/**
* Request type for `Sessions.get`.
*/
@JsonClass(generateAdapter = true)
public data class GetRequest
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
    )

/**
* Response type for `Sessions.get`.
*/
@JsonClass(generateAdapter = true)
public data class GetResponse
    @JvmOverloads
    constructor(
        /**
         * Globally unique UUID that is returned with every API call. This value is important to log for debugging purposes; we
         * may ask for this value to help identify a specific API call when helping you debug an issue.
         */
        @Json(name = "request_id")
        val requestId: String,
        /**
         * An array of [Session objects](https://stytch.com/docs/b2b/api/session-object).
         */
        @Json(name = "member_sessions")
        val memberSessions: List<MemberSession>,
        /**
         * The HTTP status code of the response. Stytch follows standard HTTP response status code patterns, e.g. 2XX values
         * equate to success, 3XX values are redirects, 4XX are client errors, and 5XX are server errors.
         */
        @Json(name = "status_code")
        val statusCode: Int,
    )

/**
* Request type for `Sessions.migrate`.
*/
@JsonClass(generateAdapter = true)
public data class MigrateRequest
    @JvmOverloads
    constructor(
        /**
         * The authorization token Stytch will pass in to the external userinfo endpoint.
         */
        @Json(name = "session_token")
        val sessionToken: String,
        /**
         * Globally unique UUID that identifies a specific Organization. The `organization_id` is critical to perform operations
         * on an Organization, so be sure to preserve this value.
         */
        @Json(name = "organization_id")
        val organizationId: String,
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
    )

/**
* Response type for `Sessions.migrate`.
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
         * The [Member object](https://stytch.com/docs/b2b/api/member-object)
         */
        @Json(name = "member")
        val member: Member,
        /**
         * The [Organization object](https://stytch.com/docs/b2b/api/organization-object).
         */
        @Json(name = "organization")
        val organization: Organization,
        @Json(name = "status_code")
        val statusCode: Int,
        /**
         * The [Session object](https://stytch.com/docs/b2b/api/session-object).
         */
        @Json(name = "member_session")
        val memberSession: MemberSession? = null,
    )

/**
* Request type for `Sessions.revoke`.
*/
@JsonClass(generateAdapter = true)
public data class RevokeRequest
    @JvmOverloads
    constructor(
        /**
         * Globally unique UUID that identifies a specific Session in the Stytch API. The `member_session_id` is critical to
         * perform operations on an Session, so be sure to preserve this value.
         */
        @Json(name = "member_session_id")
        val memberSessionId: String? = null,
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
         * Globally unique UUID that identifies a specific Member. The `member_id` is critical to perform operations on a Member,
         * so be sure to preserve this value.
         */
        @Json(name = "member_id")
        val memberId: String? = null,
    )

/**
* Response type for `Sessions.revoke`.
*/
@JsonClass(generateAdapter = true)
public data class RevokeResponse
    @JvmOverloads
    constructor(
        /**
         * Globally unique UUID that is returned with every API call. This value is important to log for debugging purposes; we
         * may ask for this value to help identify a specific API call when helping you debug an issue.
         */
        @Json(name = "request_id")
        val requestId: String,
        /**
         * The HTTP status code of the response. Stytch follows standard HTTP response status code patterns, e.g. 2XX values
         * equate to success, 3XX values are redirects, 4XX are client errors, and 5XX are server errors.
         */
        @Json(name = "status_code")
        val statusCode: Int,
    )
