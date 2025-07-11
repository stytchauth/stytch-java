package com.stytch.java.b2b.models.discoveryorganizations

// !!!
// WARNING: This file is autogenerated
// Only modify code within MANUAL() sections
// or your changes may be overwritten later!
// !!!

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.stytch.java.b2b.models.discovery.DiscoveredOrganization
import com.stytch.java.b2b.models.mfa.MfaRequired
import com.stytch.java.b2b.models.organizations.EmailImplicitRoleAssignment
import com.stytch.java.b2b.models.organizations.Member
import com.stytch.java.b2b.models.organizations.Organization
import com.stytch.java.b2b.models.sessions.MemberSession
import com.stytch.java.b2b.models.sessions.PrimaryRequired

@JsonClass(generateAdapter = false)
public enum class CreateRequestFirstPartyConnectedAppsAllowedType {
    @Json(name = "ALL_ALLOWED")
    ALL_ALLOWED,

    @Json(name = "RESTRICTED")
    RESTRICTED,

    @Json(name = "NOT_ALLOWED")
    NOT_ALLOWED,
}

@JsonClass(generateAdapter = false)
public enum class CreateRequestThirdPartyConnectedAppsAllowedType {
    @Json(name = "ALL_ALLOWED")
    ALL_ALLOWED,

    @Json(name = "RESTRICTED")
    RESTRICTED,

    @Json(name = "NOT_ALLOWED")
    NOT_ALLOWED,
}

/**
* Request type for `Organizations.create`.
*/
@JsonClass(generateAdapter = true)
public data class CreateRequest
    @JvmOverloads
    constructor(
        /**
         * The Intermediate Session Token. This token does not necessarily belong to a specific instance of a Member, but
         * represents a bag of factors that may be converted to a member session. The token can be used with the
         * [OTP SMS Authenticate endpoint](https://stytch.com/docs/b2b/api/authenticate-otp-sms),
         * [TOTP Authenticate endpoint](https://stytch.com/docs/b2b/api/authenticate-totp), or
         * [Recovery Codes Recover endpoint](https://stytch.com/docs/b2b/api/recovery-codes-recover) to complete an MFA flow and
         * log in to the Organization. The token has a default expiry of 10 minutes. It can also be used with the
         * [Exchange Intermediate Session endpoint](https://stytch.com/docs/b2b/api/exchange-intermediate-session) to join a
         * specific Organization that allows the factors represented by the intermediate session token; or the
         * [Create Organization via Discovery endpoint](https://stytch.com/docs/b2b/api/create-organization-via-discovery) to
         * create a new Organization and Member. Intermediate Session Tokens have a default expiry of 10 minutes.
         */
        @Json(name = "intermediate_session_token")
        val intermediateSessionToken: String,
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
         * The name of the Organization. If the name is not specified, a default name will be created based on the email used to
         * initiate the discovery flow. If the email domain is a common email provider such as gmail.com, or if the email is a
         * .edu email, the organization name will be generated based on the name portion of the email. Otherwise, the organization
         * name will be generated based on the email domain.
         */
        @Json(name = "organization_name")
        val organizationName: String? = null,
        /**
         * The unique URL slug of the Organization. A minimum of two characters is required. The slug only accepts alphanumeric
         * characters and the following reserved characters: `-` `.` `_` `~`. If the slug is not specified, a default slug will be
         * created based on the email used to initiate the discovery flow. If the email domain is a common email provider such as
         * gmail.com, or if the email is a .edu email, the organization slug will be generated based on the name portion of the
         * email. Otherwise, the organization slug will be generated based on the email domain.
         */
        @Json(name = "organization_slug")
        val organizationSlug: String? = null,
        /**
         * The image URL of the Organization logo.
         */
        @Json(name = "organization_logo_url")
        val organizationLogoURL: String? = null,
        /**
         * An arbitrary JSON object for storing application-specific data or identity-provider-specific data.
         */
        @Json(name = "trusted_metadata")
        val trustedMetadata: Map<String, Any?>? = emptyMap(),
        /**
         * The authentication setting that controls the JIT provisioning of Members when authenticating via SSO. The accepted
         * values are:
         *
         *   `ALL_ALLOWED` – new Members will be automatically provisioned upon successful authentication via any of the
         * Organization's `sso_active_connections`.
         *
         *   `RESTRICTED` – only new Members with SSO logins that comply with `sso_jit_provisioning_allowed_connections` can be
         * provisioned upon authentication.
         *
         *   `NOT_ALLOWED` – disable JIT provisioning via SSO.
         *
         */
        @Json(name = "sso_jit_provisioning")
        val ssoJITProvisioning: String? = null,
        /**
         * An array of email domains that allow invites or JIT provisioning for new Members. This list is enforced when either
         * `email_invites` or `email_jit_provisioning` is set to `RESTRICTED`.
         *
         *
         *     Common domains such as `gmail.com` are not allowed. See the
         * [common email domains resource](https://stytch.com/docs/b2b/api/common-email-domains) for the full list.
         */
        @Json(name = "email_allowed_domains")
        val emailAllowedDomains: List<String>? = emptyList(),
        /**
         * The authentication setting that controls how a new Member can be provisioned by authenticating via Email Magic Link or
         * OAuth. The accepted values are:
         *
         *   `RESTRICTED` – only new Members with verified emails that comply with `email_allowed_domains` can be provisioned upon
         * authentication via Email Magic Link or OAuth.
         *
         *   `NOT_ALLOWED` – disable JIT provisioning via Email Magic Link and OAuth.
         *
         */
        @Json(name = "email_jit_provisioning")
        val emailJITProvisioning: String? = null,
        /**
         * The authentication setting that controls how a new Member can be invited to an organization by email. The accepted
         * values are:
         *
         *   `ALL_ALLOWED` – any new Member can be invited to join via email.
         *
         *   `RESTRICTED` – only new Members with verified emails that comply with `email_allowed_domains` can be invited via
         * email.
         *
         *   `NOT_ALLOWED` – disable email invites.
         *
         */
        @Json(name = "email_invites")
        val emailInvites: String? = null,
        /**
         * The setting that controls which authentication methods can be used by Members of an Organization. The accepted values
         * are:
         *
         *   `ALL_ALLOWED` – the default setting which allows all authentication methods to be used.
         *
         *   `RESTRICTED` – only methods that comply with `allowed_auth_methods` can be used for authentication. This setting does
         * not apply to Members with `is_breakglass` set to `true`.
         *
         */
        @Json(name = "auth_methods")
        val authMethods: String? = null,
        /**
         * An array of allowed authentication methods. This list is enforced when `auth_methods` is set to `RESTRICTED`.
         *   The list's accepted values are: `sso`, `magic_link`, `email_otp`, `password`, `google_oauth`, `microsoft_oauth`,
         * `slack_oauth`, `github_oauth`, and `hubspot_oauth`.
         *
         */
        @Json(name = "allowed_auth_methods")
        val allowedAuthMethods: List<String>? = emptyList(),
        /**
         * The setting that controls the MFA policy for all Members in the Organization. The accepted values are:
         *
         *   `REQUIRED_FOR_ALL` – All Members within the Organization will be required to complete MFA every time they wish to log
         * in. However, any active Session that existed prior to this setting change will remain valid.
         *
         *   `OPTIONAL` – The default value. The Organization does not require MFA by default for all Members. Members will be
         * required to complete MFA only if their `mfa_enrolled` status is set to true.
         *
         */
        @Json(name = "mfa_policy")
        val mfaPolicy: String? = null,
        /**
         * Implicit role assignments based off of email domains.
         *   For each domain-Role pair, all Members whose email addresses have the specified email domain will be granted the
         *   associated Role, regardless of their login method. See the
         * [RBAC guide](https://stytch.com/docs/b2b/guides/rbac/role-assignment)
         *   for more information about role assignment.
         */
        @Json(name = "rbac_email_implicit_role_assignments")
        val rbacEmailImplicitRoleAssignments: List<EmailImplicitRoleAssignment>? = emptyList(),
        /**
         * The setting that controls which MFA methods can be used by Members of an Organization. The accepted values are:
         *
         *   `ALL_ALLOWED` – the default setting which allows all authentication methods to be used.
         *
         *   `RESTRICTED` – only methods that comply with `allowed_mfa_methods` can be used for authentication. This setting does
         * not apply to Members with `is_breakglass` set to `true`.
         *
         */
        @Json(name = "mfa_methods")
        val mfaMethods: String? = null,
        /**
         * An array of allowed MFA authentication methods. This list is enforced when `mfa_methods` is set to `RESTRICTED`.
         *   The list's accepted values are: `sms_otp` and `totp`.
         *
         */
        @Json(name = "allowed_mfa_methods")
        val allowedMfaMethods: List<String>? = emptyList(),
        /**
         * The authentication setting that controls how a new Member can JIT provision into an organization by tenant. The
         * accepted values are:
         *
         *   `RESTRICTED` – only new Members with tenants in `allowed_oauth_tenants` can JIT provision via tenant.
         *
         *   `NOT_ALLOWED` – disable JIT provisioning by OAuth Tenant.
         *
         */
        @Json(name = "oauth_tenant_jit_provisioning")
        val oauthTenantJITProvisioning: String? = null,
        /**
         * A map of allowed OAuth tenants. If this field is not passed in, the Organization will not allow JIT provisioning by
         * OAuth Tenant. Allowed keys are "slack", "hubspot", and "github".
         */
        @Json(name = "allowed_oauth_tenants")
        val allowedOAuthTenants: Map<String, Any?>? = emptyMap(),
        /**
         * The authentication setting that sets the Organization's policy towards first party Connected Apps. The accepted values
         * are:
         *
         *   `ALL_ALLOWED` – any first party Connected App in the Project is permitted for use by Members.
         *
         *   `RESTRICTED` – only first party Connected Apps with IDs in `allowed_first_party_connected_apps` can be used by
         * Members.
         *
         *   `NOT_ALLOWED` – no first party Connected Apps are permitted.
         *
         */
        @Json(name = "first_party_connected_apps_allowed_type")
        val firstPartyConnectedAppsAllowedType: CreateRequestFirstPartyConnectedAppsAllowedType? = null,
        /**
         * An array of first party Connected App IDs that are allowed for the Organization. Only used when the Organization's
         * `first_party_connected_apps_allowed_type` is `RESTRICTED`.
         */
        @Json(name = "allowed_first_party_connected_apps")
        val allowedFirstPartyConnectedApps: List<String>? = emptyList(),
        /**
         * The authentication setting that sets the Organization's policy towards third party Connected Apps. The accepted values
         * are:
         *
         *   `ALL_ALLOWED` – any third party Connected App in the Project is permitted for use by Members.
         *
         *   `RESTRICTED` – only third party Connected Apps with IDs in `allowed_first_party_connected_apps` can be used by
         * Members.
         *
         *   `NOT_ALLOWED` – no third party Connected Apps are permitted.
         *
         */
        @Json(name = "third_party_connected_apps_allowed_type")
        val thirdPartyConnectedAppsAllowedType: CreateRequestThirdPartyConnectedAppsAllowedType? = null,
        /**
         * An array of third party Connected App IDs that are allowed for the Organization. Only used when the Organization's
         * `third_party_connected_apps_allowed_type` is `RESTRICTED`.
         */
        @Json(name = "allowed_third_party_connected_apps")
        val allowedThirdPartyConnectedApps: List<String>? = emptyList(),
    )

/**
* Response type for `Organizations.create`.
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
         * Indicates whether the Member is fully authenticated. If false, the Member needs to complete an MFA step to log in to
         * the Organization.
         */
        @Json(name = "member_authenticated")
        val memberAuthenticated: Boolean,
        /**
         * The returned Intermediate Session Token is identical to the one that was originally passed in to the request. If this
         * value is non-empty, the member must complete an MFA step to finish logging in to the Organization. The token can be
         * used with the [OTP SMS Authenticate endpoint](https://stytch.com/docs/b2b/api/authenticate-otp-sms),
         * [TOTP Authenticate endpoint](https://stytch.com/docs/b2b/api/authenticate-totp), or
         * [Recovery Codes Recover endpoint](https://stytch.com/docs/b2b/api/recovery-codes-recover) to complete an MFA flow and
         * log in to the Organization. The token has a default expiry of 10 minutes. It can also be used with the
         * [Exchange Intermediate Session endpoint](https://stytch.com/docs/b2b/api/exchange-intermediate-session) to join a
         * specific Organization that allows the factors represented by the intermediate session token; or the
         * [Create Organization via Discovery endpoint](https://stytch.com/docs/b2b/api/create-organization-via-discovery) to
         * create a new Organization and Member. Intermediate Session Tokens have a default expiry of 10 minutes.
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
         * The [Session object](https://stytch.com/docs/b2b/api/session-object).
         */
        @Json(name = "member_session")
        val memberSession: MemberSession? = null,
        /**
         * The [Organization object](https://stytch.com/docs/b2b/api/organization-object).
         */
        @Json(name = "organization")
        val organization: Organization? = null,
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
* Request type for `Organizations.list`.
*/
@JsonClass(generateAdapter = true)
public data class ListRequest
    @JvmOverloads
    constructor(
        /**
         * The Intermediate Session Token. This token does not necessarily belong to a specific instance of a Member, but
         * represents a bag of factors that may be converted to a member session. The token can be used with the
         * [OTP SMS Authenticate endpoint](https://stytch.com/docs/b2b/api/authenticate-otp-sms),
         * [TOTP Authenticate endpoint](https://stytch.com/docs/b2b/api/authenticate-totp), or
         * [Recovery Codes Recover endpoint](https://stytch.com/docs/b2b/api/recovery-codes-recover) to complete an MFA flow and
         * log in to the Organization. The token has a default expiry of 10 minutes. It can also be used with the
         * [Exchange Intermediate Session endpoint](https://stytch.com/docs/b2b/api/exchange-intermediate-session) to join a
         * specific Organization that allows the factors represented by the intermediate session token; or the
         * [Create Organization via Discovery endpoint](https://stytch.com/docs/b2b/api/create-organization-via-discovery) to
         * create a new Organization and Member. Intermediate Session Tokens have a default expiry of 10 minutes.
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
* Response type for `Organizations.list`.
*/
@JsonClass(generateAdapter = true)
public data class ListResponse
    @JvmOverloads
    constructor(
        /**
         * Globally unique UUID that is returned with every API call. This value is important to log for debugging purposes; we
         * may ask for this value to help identify a specific API call when helping you debug an issue.
         */
        @Json(name = "request_id")
        val requestId: String,
        /**
         * The email address.
         */
        @Json(name = "email_address")
        val emailAddress: String,
        /**
         * An array of `discovered_organization` objects tied to the `intermediate_session_token`, `session_token`, or
         * `session_jwt`. See the [Discovered Organization Object](https://stytch.com/docs/b2b/api/discovered-organization-object)
         * for complete details.
         *
         *   Note that Organizations will only appear here under any of the following conditions:
         *   1. The end user is already a Member of the Organization.
         *   2. The end user is invited to the Organization.
         *   3. The end user can join the Organization because:
         *
         *       a) The Organization allows JIT provisioning.
         *
         *       b) The Organizations' allowed domains list contains the Member's email domain.
         *
         *       c) The Organization has at least one other Member with a verified email address with the same domain as the end
         * user (to prevent phishing attacks).
         */
        @Json(name = "discovered_organizations")
        val discoveredOrganizations: List<DiscoveredOrganization>,
        /**
         * The HTTP status code of the response. Stytch follows standard HTTP response status code patterns, e.g. 2XX values
         * equate to success, 3XX values are redirects, 4XX are client errors, and 5XX are server errors.
         */
        @Json(name = "status_code")
        val statusCode: Int,
        /**
         * If the intermediate session token is associated with a specific Organization, that Organization ID will be returned
         * here. The Organization ID will be null if the intermediate session token was generated by a email magic link discovery
         * or OAuth discovery flow. If a session token or session JWT is provided, the Organization ID hint will be null.
         */
        @Json(name = "organization_id_hint")
        val organizationIdHint: String? = null,
    )
