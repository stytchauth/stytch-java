package com.stytch.java.b2b.models.oauthdiscovery

// !!!
// WARNING: This file is autogenerated
// Only modify code within MANUAL() sections
// or your changes may be overwritten later!
// !!!

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.stytch.java.b2b.models.discovery.DiscoveredOrganization

/**
* Request type for `Discovery.authenticate`.
*/
@JsonClass(generateAdapter = true)
public data class AuthenticateRequest
    @JvmOverloads
    constructor(
        /**
         * The Discovery OAuth token to authenticate.
         */
        @Json(name = "discovery_oauth_token")
        val discoveryOAuthToken: String,
        @Json(name = "session_token")
        val sessionToken: String? = null,
        @Json(name = "session_duration_minutes")
        val sessionDurationMinutes: Int? = null,
        @Json(name = "session_jwt")
        val sessionJwt: String? = null,
        @Json(name = "session_custom_claims")
        val sessionCustomClaims: Map<String, Any?>? = emptyMap(),
        /**
         * A base64url encoded one time secret used to validate that the request starts and ends on the same device.
         */
        @Json(name = "pkce_code_verifier")
        val pkceCodeVerifier: String? = null,
    )

/**
* Response type for `Discovery.authenticate`.
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
         * The Intermediate Session Token. This token does not necessarily belong to a specific instance of a Member, but
         * represents a bag of factors that may be converted to a member session. The token can be used with the
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
         * Denotes the OAuth identity provider that the user has authenticated with, e.g. Google, Microsoft, GitHub etc.
         */
        @Json(name = "provider_type")
        val providerType: String,
        /**
         * The tenant ID returned by the OAuth provider. This is typically used to identify the organization. For example, for
         * HubSpot this is the Hub ID, for Slack, this is the Workspace ID, and for GitHub this is an organization ID.
         */
        @Json(name = "provider_tenant_id")
        val providerTenantId: String,
        /**
         * The IDs of tenants returned from a completed OAuth authentication. Some providers do not return tenants.
         */
        @Json(name = "provider_tenant_ids")
        val providerTenantIds: List<String>,
        /**
         * The HTTP status code of the response. Stytch follows standard HTTP response status code patterns, e.g. 2XX values
         * equate to success, 3XX values are redirects, 4XX are client errors, and 5XX are server errors.
         */
        @Json(name = "status_code")
        val statusCode: Int,
    )
