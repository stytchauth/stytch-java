package com.stytch.java.b2b.models.passwordsdiscovery

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
         * The email address of the Member.
         */
        @Json(name = "email_address")
        val emailAddress: String,
        /**
         * The password to authenticate, reset, or set for the first time. Any UTF8 character is allowed, e.g. spaces, emojis,
         * non-English characters, etc.
         */
        @Json(name = "password")
        val password: String,
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
         * The email address.
         */
        @Json(name = "email_address")
        val emailAddress: String,
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
    )
