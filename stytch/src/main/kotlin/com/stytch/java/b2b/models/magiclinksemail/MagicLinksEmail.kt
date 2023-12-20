package com.stytch.java.b2b.models.magiclinksemail

// !!!
// WARNING: This file is autogenerated
// Only modify code within MANUAL() sections
// or your changes may be overwritten later!
// !!!

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.stytch.java.b2b.models.organizations.Member
import com.stytch.java.b2b.models.organizations.Organization
import com.stytch.java.common.methodoptions.Authorization

@JsonClass(generateAdapter = false)
public enum class InviteRequestLocale {
    @Json(name = "en")
    EN,

    @Json(name = "es")
    ES,

    @Json(name = "ptbr")
    PTBR,
}

@JsonClass(generateAdapter = false)
public enum class LoginOrSignupRequestLocale {
    @Json(name = "en")
    EN,

    @Json(name = "es")
    ES,

    @Json(name = "ptbr")
    PTBR,
}

public data class InviteRequestOptions
    @JvmOverloads
    constructor(
        /**
         * Optional authorization object.
         * Pass in an active Stytch Member session token or session JWT and the request
         * will be run using that member's permissions.
         */
        val authorization: Authorization? = null,
    ) {
        fun addHeaders(headers: Map<String, String> = emptyMap()): Map<String, String> {
            var res = mutableMapOf<String, String>()
            if (authorization != null) {
                res = authorization.addHeaders(res)
            }
            return res + headers
        }
    }

/**
* Request type for `Email.invite`.
*/
@JsonClass(generateAdapter = true)
public data class InviteRequest
    @JvmOverloads
    constructor(
        /**
         * Globally unique UUID that identifies a specific Organization. The `organization_id` is critical to perform operations
         * on an Organization, so be sure to preserve this value.
         */
        @Json(name = "organization_id")
        val organizationId: String,
        /**
         * The email address of the Member.
         */
        @Json(name = "email_address")
        val emailAddress: String,
        /**
         * The URL that the Member clicks from the invite Email Magic Link. This URL should be an endpoint in the backend server
         * that verifies
         *   the request by querying Stytch's authenticate endpoint and finishes the invite flow. If this value is not passed, the
         * default `invite_redirect_url`
         *   that you set in your Dashboard is used. If you have not set a default `invite_redirect_url`, an error is returned.
         */
        @Json(name = "invite_redirect_url")
        val inviteRedirectURL: String? = null,
        /**
         * The `member_id` of the Member who sends the invite.
         */
        @Json(name = "invited_by_member_id")
        val invitedByMemberId: String? = null,
        /**
         * The name of the Member.
         */
        @Json(name = "name")
        val name: String? = null,
        /**
         * An arbitrary JSON object for storing application-specific data or identity-provider-specific data.
         */
        @Json(name = "trusted_metadata")
        val trustedMetadata: Map<String, Any>? = null,
        /**
         * An arbitrary JSON object of application-specific data. These fields can be edited directly by the
         *   frontend SDK, and should not be used to store critical information. See the
         * [Metadata resource](https://stytch.com/docs/b2b/api/metadata)
         *   for complete field behavior details.
         */
        @Json(name = "untrusted_metadata")
        val untrustedMetadata: Map<String, Any>? = null,
        /**
         * Use a custom template for invite emails. By default, it will use your default email template. The template must be a
         * template
         *   using our built-in customizations or a custom HTML email for Magic Links - Invite.
         */
        @Json(name = "invite_template_id")
        val inviteTemplateId: String? = null,
        /**
         * Used to determine which language to use when sending the user this delivery method. Parameter is a
         * [IETF BCP 47 language tag](https://www.w3.org/International/articles/language-tags/), e.g. `"en"`.
         *
         * Currently supported languages are English (`"en"`), Spanish (`"es"`), and Brazilian Portuguese (`"pt-br"`); if no value
         * is provided, the copy defaults to English.
         *
         * Request support for additional languages
         * [here](https://docs.google.com/forms/d/e/1FAIpQLScZSpAu_m2AmLXRT3F3kap-s_mcV6UTBitYn6CdyWP0-o7YjQ/viewform?usp=sf_link")!
         *
         */
        @Json(name = "locale")
        val locale: InviteRequestLocale? = null,
        /**
         * Roles to explicitly assign to this Member. See the [RBAC guide](https://stytch.com/docs/b2b/guides/rbac/role-assignment)
         *    for more information about role assignment.
         */
        @Json(name = "roles")
        val roles: List<String>? = null,
    )

/**
* Response type for `Email.invite`.
*/
@JsonClass(generateAdapter = true)
public data class InviteResponse
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
         * The HTTP status code of the response. Stytch follows standard HTTP response status code patterns, e.g. 2XX values
         * equate to success, 3XX values are redirects, 4XX are client errors, and 5XX are server errors.
         */
        @Json(name = "status_code")
        val statusCode: Int,
    )

/**
* Request type for `Email.loginOrSignup`.
*/
@JsonClass(generateAdapter = true)
public data class LoginOrSignupRequest
    @JvmOverloads
    constructor(
        /**
         * Globally unique UUID that identifies a specific Organization. The `organization_id` is critical to perform operations
         * on an Organization, so be sure to preserve this value.
         */
        @Json(name = "organization_id")
        val organizationId: String,
        /**
         * The email address of the Member.
         */
        @Json(name = "email_address")
        val emailAddress: String,
        /**
         * The URL that the Member clicks from the login Email Magic Link. This URL should be an endpoint in the backend server
         * that
         *   verifies the request by querying Stytch's authenticate endpoint and finishes the login. If this value is not passed,
         * the default login
         *   redirect URL that you set in your Dashboard is used. If you have not set a default login redirect URL, an error is
         * returned.
         */
        @Json(name = "login_redirect_url")
        val loginRedirectURL: String? = null,
        /**
         * The URL the Member clicks from the signup Email Magic Link. This URL should be an endpoint in the backend server that
         * verifies
         *   the request by querying Stytch's authenticate endpoint and finishes the login. If this value is not passed, the
         * default sign-up redirect URL
         *   that you set in your Dashboard is used. If you have not set a default sign-up redirect URL, an error is returned.
         */
        @Json(name = "signup_redirect_url")
        val signupRedirectURL: String? = null,
        /**
         * A base64url encoded SHA256 hash of a one time secret used to validate that the request starts and ends on the same
         * device.
         */
        @Json(name = "pkce_code_challenge")
        val pkceCodeChallenge: String? = null,
        /**
         * Use a custom template for login emails. By default, it will use your default email template. The template must be from
         * Stytch's
         * built-in customizations or a custom HTML email for Magic Links - Login.
         */
        @Json(name = "login_template_id")
        val loginTemplateId: String? = null,
        /**
         * Use a custom template for signup emails. By default, it will use your default email template. The template must be from
         * Stytch's
         * built-in customizations or a custom HTML email for Magic Links - Signup.
         */
        @Json(name = "signup_template_id")
        val signupTemplateId: String? = null,
        /**
         * Used to determine which language to use when sending the user this delivery method. Parameter is a
         * [IETF BCP 47 language tag](https://www.w3.org/International/articles/language-tags/), e.g. `"en"`.
         *
         * Currently supported languages are English (`"en"`), Spanish (`"es"`), and Brazilian Portuguese (`"pt-br"`); if no value
         * is provided, the copy defaults to English.
         *
         * Request support for additional languages
         * [here](https://docs.google.com/forms/d/e/1FAIpQLScZSpAu_m2AmLXRT3F3kap-s_mcV6UTBitYn6CdyWP0-o7YjQ/viewform?usp=sf_link")!
         *
         */
        @Json(name = "locale")
        val locale: LoginOrSignupRequestLocale? = null,
    )

/**
* Response type for `Email.loginOrSignup`.
*/
@JsonClass(generateAdapter = true)
public data class LoginOrSignupResponse
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
         * A flag indicating `true` if a new Member object was created and `false` if the Member object already existed.
         */
        @Json(name = "member_created")
        val memberCreated: Boolean,
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
