package com.stytch.java.b2b.models.sso

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
import com.stytch.java.common.methodoptions.Authorization
import java.time.Instant

@JsonClass(generateAdapter = false)
public enum class AuthenticateRequestLocale {
    @Json(name = "en")
    EN,

    @Json(name = "es")
    ES,

    @Json(name = "ptbr")
    PTBR,

    @Json(name = "fr")
    FR,

    @Json(name = "it")
    IT,
}

@JsonClass(generateAdapter = true)
public data class Connection
    @JvmOverloads
    constructor(
        @Json(name = "organization_id")
        val organizationId: String,
        @Json(name = "connection_id")
        val connectionId: String,
        @Json(name = "external_organization_id")
        val externalOrganizationId: String,
        @Json(name = "external_connection_id")
        val externalConnectionId: String,
        @Json(name = "display_name")
        val displayName: String,
        @Json(name = "status")
        val status: String,
        @Json(name = "external_connection_implicit_role_assignments")
        val externalConnectionImplicitRoleAssignments: List<ConnectionImplicitRoleAssignment>,
        @Json(name = "external_group_implicit_role_assignments")
        val externalGroupImplicitRoleAssignments: List<GroupImplicitRoleAssignment>,
    )

@JsonClass(generateAdapter = true)
public data class ConnectionImplicitRoleAssignment
    @JvmOverloads
    constructor(
        /**
         * The unique identifier of the RBAC Role, provided by the developer and intended to be human-readable.
         *
         *   Reserved `role_id`s that are predefined by Stytch include:
         *
         *   * `stytch_member`
         *   * `stytch_admin`
         *
         *   Check out the [guide on Stytch default Roles](https://stytch.com/docs/b2b/guides/rbac/stytch-default) for a more
         * detailed explanation.
         *
         *
         */
        @Json(name = "role_id")
        val roleId: String,
    )

public data class DeleteConnectionRequestOptions
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

public data class GetConnectionsRequestOptions
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

@JsonClass(generateAdapter = true)
public data class GroupImplicitRoleAssignment
    @JvmOverloads
    constructor(
        /**
         * The unique identifier of the RBAC Role, provided by the developer and intended to be human-readable.
         *
         *   Reserved `role_id`s that are predefined by Stytch include:
         *
         *   * `stytch_member`
         *   * `stytch_admin`
         *
         *   Check out the [guide on Stytch default Roles](https://stytch.com/docs/b2b/guides/rbac/stytch-default) for a more
         * detailed explanation.
         *
         *
         */
        @Json(name = "role_id")
        val roleId: String,
        /**
         * The name of the group that grants the specified role assignment.
         */
        @Json(name = "group")
        val group: String,
    )

@JsonClass(generateAdapter = true)
public data class OIDCConnection
    @JvmOverloads
    constructor(
        @Json(name = "organization_id")
        val organizationId: String,
        @Json(name = "connection_id")
        val connectionId: String,
        @Json(name = "status")
        val status: String,
        @Json(name = "display_name")
        val displayName: String,
        @Json(name = "redirect_url")
        val redirectURL: String,
        @Json(name = "client_id")
        val clientId: String,
        @Json(name = "client_secret")
        val clientSecret: String,
        @Json(name = "issuer")
        val issuer: String,
        @Json(name = "authorization_url")
        val authorizationURL: String,
        @Json(name = "token_url")
        val tokenURL: String,
        @Json(name = "userinfo_url")
        val userinfoURL: String,
        @Json(name = "jwks_url")
        val jwksURL: String,
        @Json(name = "identity_provider")
        val identityProvider: String,
        @Json(name = "custom_scopes")
        val customScopes: String,
        @Json(name = "attribute_mapping")
        val attributeMapping: Map<String, Any?>? = emptyMap(),
    )

@JsonClass(generateAdapter = true)
public data class SAMLConnection
    @JvmOverloads
    constructor(
        @Json(name = "organization_id")
        val organizationId: String,
        @Json(name = "connection_id")
        val connectionId: String,
        @Json(name = "status")
        val status: String,
        @Json(name = "idp_entity_id")
        val idpEntityId: String,
        @Json(name = "display_name")
        val displayName: String,
        @Json(name = "idp_sso_url")
        val idpSSOURL: String,
        @Json(name = "acs_url")
        val acsURL: String,
        @Json(name = "audience_uri")
        val audienceUri: String,
        @Json(name = "signing_certificates")
        val signingCertificates: List<X509Certificate>,
        @Json(name = "verification_certificates")
        val verificationCertificates: List<X509Certificate>,
        @Json(name = "saml_connection_implicit_role_assignments")
        val samlConnectionImplicitRoleAssignments: List<SAMLConnectionImplicitRoleAssignment>,
        @Json(name = "saml_group_implicit_role_assignments")
        val samlGroupImplicitRoleAssignments: List<SAMLGroupImplicitRoleAssignment>,
        @Json(name = "alternative_audience_uri")
        val alternativeAudienceUri: String,
        @Json(name = "identity_provider")
        val identityProvider: String,
        @Json(name = "nameid_format")
        val nameidFormat: String,
        @Json(name = "alternative_acs_url")
        val alternativeAcsURL: String,
        @Json(name = "attribute_mapping")
        val attributeMapping: Map<String, Any?>? = emptyMap(),
    )

@JsonClass(generateAdapter = true)
public data class SAMLConnectionImplicitRoleAssignment
    @JvmOverloads
    constructor(
        /**
         * The unique identifier of the RBAC Role, provided by the developer and intended to be human-readable.
         *
         *   Reserved `role_id`s that are predefined by Stytch include:
         *
         *   * `stytch_member`
         *   * `stytch_admin`
         *
         *   Check out the [guide on Stytch default Roles](https://stytch.com/docs/b2b/guides/rbac/stytch-default) for a more
         * detailed explanation.
         *
         *
         */
        @Json(name = "role_id")
        val roleId: String,
    )

@JsonClass(generateAdapter = true)
public data class SAMLGroupImplicitRoleAssignment
    @JvmOverloads
    constructor(
        /**
         * The unique identifier of the RBAC Role, provided by the developer and intended to be human-readable.
         *
         *   Reserved `role_id`s that are predefined by Stytch include:
         *
         *   * `stytch_member`
         *   * `stytch_admin`
         *
         *   Check out the [guide on Stytch default Roles](https://stytch.com/docs/b2b/guides/rbac/stytch-default) for a more
         * detailed explanation.
         *
         *
         */
        @Json(name = "role_id")
        val roleId: String,
        /**
         * The name of the group that grants the specified role assignment.
         */
        @Json(name = "group")
        val group: String,
    )

@JsonClass(generateAdapter = true)
public data class X509Certificate
    @JvmOverloads
    constructor(
        @Json(name = "certificate_id")
        val certificateId: String,
        @Json(name = "certificate")
        val certificate: String,
        @Json(name = "issuer")
        val issuer: String,
        @Json(name = "created_at")
        val createdAt: Instant? = null,
        @Json(name = "expires_at")
        val expiresAt: Instant? = null,
        @Json(name = "updated_at")
        val updatedAt: Instant? = null,
    )

/**
* Request type for `SSO.authenticate`.
*/
@JsonClass(generateAdapter = true)
public data class AuthenticateRequest
    @JvmOverloads
    constructor(
        /**
         * The token to authenticate.
         */
        @Json(name = "sso_token")
        val ssoToken: String,
        /**
         * A base64url encoded one time secret used to validate that the request starts and ends on the same device.
         */
        @Json(name = "pkce_code_verifier")
        val pkceCodeVerifier: String? = null,
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
         * If the needs to complete an MFA step, and the Member has a phone number, this endpoint will pre-emptively send a
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
        val locale: AuthenticateRequestLocale? = null,
        /**
         * Adds this primary authentication factor to the intermediate session token. If the resulting set of factors satisfies
         * the organization's primary authentication requirements and MFA requirements, the intermediate session token will be
         * consumed and converted to a member session. If not, the same intermediate session token will be returned.
         */
        @Json(name = "intermediate_session_token")
        val intermediateSessionToken: String? = null,
    )

/**
* Response type for `SSO.authenticate`.
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
         * This field is deprecated.
         */
        @Json(name = "reset_session")
        val resetSession: Boolean,
        /**
         * The [Organization object](https://stytch.com/docs/b2b/api/organization-object).
         */
        @Json(name = "organization")
        val organization: Organization,
        /**
         * The returned Intermediate Session Token contains an SSO factor associated with the Member. If this value is non-empty,
         * the member must complete an MFA step to finish logging in to the Organization. The token can be used with the
         * [OTP SMS Authenticate endpoint](https://stytch.com/docs/b2b/api/authenticate-otp-sms),
         * [TOTP Authenticate endpoint](https://stytch.com/docs/b2b/api/authenticate-totp), or
         * [Recovery Codes Recover endpoint](https://stytch.com/docs/b2b/api/recovery-codes-recover) to complete an MFA flow and
         * log in to the Organization. SSO factors are not transferable between Organizations, so the intermediate session token
         * is not valid for use with discovery endpoints.
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
    )

/**
* Request type for `SSO.deleteConnection`.
*/
@JsonClass(generateAdapter = true)
public data class DeleteConnectionRequest
    @JvmOverloads
    constructor(
        /**
         * The organization ID that the SSO connection belongs to.
         */
        @Json(name = "organization_id")
        val organizationId: String,
        /**
         * The ID of the SSO connection. SAML, OIDC, and External connection IDs can be provided.
         */
        @Json(name = "connection_id")
        val connectionId: String,
    )

/**
* Response type for `SSO.deleteConnection`.
*/
@JsonClass(generateAdapter = true)
public data class DeleteConnectionResponse
    @JvmOverloads
    constructor(
        /**
         * Globally unique UUID that is returned with every API call. This value is important to log for debugging purposes; we
         * may ask for this value to help identify a specific API call when helping you debug an issue.
         */
        @Json(name = "request_id")
        val requestId: String,
        /**
         * The `connection_id` that was deleted as part of the delete request.
         */
        @Json(name = "connection_id")
        val connectionId: String,
        /**
         * The HTTP status code of the response. Stytch follows standard HTTP response status code patterns, e.g. 2XX values
         * equate to success, 3XX values are redirects, 4XX are client errors, and 5XX are server errors.
         */
        @Json(name = "status_code")
        val statusCode: Int,
    )

/**
* Request type for `SSO.getConnections`.
*/
@JsonClass(generateAdapter = true)
public data class GetConnectionsRequest
    @JvmOverloads
    constructor(
        /**
         * Globally unique UUID that identifies a specific Organization. The `organization_id` is critical to perform operations
         * on an Organization, so be sure to preserve this value.
         */
        @Json(name = "organization_id")
        val organizationId: String,
    )

/**
* Response type for `SSO.getConnections`.
*/
@JsonClass(generateAdapter = true)
public data class GetConnectionsResponse
    @JvmOverloads
    constructor(
        /**
         * Globally unique UUID that is returned with every API call. This value is important to log for debugging purposes; we
         * may ask for this value to help identify a specific API call when helping you debug an issue.
         */
        @Json(name = "request_id")
        val requestId: String,
        /**
         * The list of [SAML Connections](https://stytch.com/docs/b2b/api/saml-connection-object) owned by this organization.
         */
        @Json(name = "saml_connections")
        val samlConnections: List<SAMLConnection>,
        /**
         * The list of [OIDC Connections](https://stytch.com/docs/b2b/api/oidc-connection-object) owned by this organization.
         */
        @Json(name = "oidc_connections")
        val oidcConnections: List<OIDCConnection>,
        /**
         * The list of [External Connections](https://stytch.com/docs/b2b/api/external-connection-object) owned by this
         * organization.
         */
        @Json(name = "external_connections")
        val externalConnections: List<Connection>,
        /**
         * The HTTP status code of the response. Stytch follows standard HTTP response status code patterns, e.g. 2XX values
         * equate to success, 3XX values are redirects, 4XX are client errors, and 5XX are server errors.
         */
        @Json(name = "status_code")
        val statusCode: Int,
    )
