package com.stytch.java.consumer.models.sessions

// !!!
// WARNING: This file is autogenerated
// Only modify code within MANUAL() sections
// or your changes may be overwritten later!
// !!!

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.stytch.java.consumer.models.attribute.Attributes
import com.stytch.java.consumer.models.users.User
import java.time.Instant

@JsonClass(generateAdapter = false)
public enum class AuthenticationFactorDeliveryMethod {
    @Json(name = "email")
    EMAIL,

    @Json(name = "sms")
    SMS,

    @Json(name = "whatsapp")
    WHATSAPP,

    @Json(name = "embedded")
    EMBEDDED,

    @Json(name = "oauth_google")
    OAUTH_GOOGLE,

    @Json(name = "oauth_microsoft")
    OAUTH_MICROSOFT,

    @Json(name = "oauth_apple")
    OAUTH_APPLE,

    @Json(name = "webauthn_registration")
    WEBAUTHN_REGISTRATION,

    @Json(name = "authenticator_app")
    AUTHENTICATOR_APP,

    @Json(name = "oauth_github")
    OAUTH_GITHUB,

    @Json(name = "recovery_code")
    RECOVERY_CODE,

    @Json(name = "oauth_facebook")
    OAUTH_FACEBOOK,

    @Json(name = "crypto_wallet")
    CRYPTO_WALLET,

    @Json(name = "oauth_amazon")
    OAUTH_AMAZON,

    @Json(name = "oauth_bitbucket")
    OAUTH_BITBUCKET,

    @Json(name = "oauth_coinbase")
    OAUTH_COINBASE,

    @Json(name = "oauth_discord")
    OAUTH_DISCORD,

    @Json(name = "oauth_figma")
    OAUTH_FIGMA,

    @Json(name = "oauth_gitlab")
    OAUTH_GITLAB,

    @Json(name = "oauth_instagram")
    OAUTH_INSTAGRAM,

    @Json(name = "oauth_linkedin")
    OAUTH_LINKEDIN,

    @Json(name = "oauth_shopify")
    OAUTH_SHOPIFY,

    @Json(name = "oauth_slack")
    OAUTH_SLACK,

    @Json(name = "oauth_snapchat")
    OAUTH_SNAPCHAT,

    @Json(name = "oauth_spotify")
    OAUTH_SPOTIFY,

    @Json(name = "oauth_steam")
    OAUTH_STEAM,

    @Json(name = "oauth_tiktok")
    OAUTH_TIKTOK,

    @Json(name = "oauth_twitch")
    OAUTH_TWITCH,

    @Json(name = "oauth_twitter")
    OAUTH_TWITTER,

    @Json(name = "knowledge")
    KNOWLEDGE,

    @Json(name = "biometric")
    BIOMETRIC,

    @Json(name = "sso_saml")
    SSO_SAML,

    @Json(name = "sso_oidc")
    SSO_OIDC,

    @Json(name = "oauth_salesforce")
    OAUTH_SALESFORCE,

    @Json(name = "oauth_yahoo")
    OAUTH_YAHOO,

    @Json(name = "oauth_hubspot")
    OAUTH_HUBSPOT,

    @Json(name = "imported_auth0")
    IMPORTED_AUTH0,

    @Json(name = "oauth_exchange_slack")
    OAUTH_EXCHANGE_SLACK,

    @Json(name = "oauth_exchange_hubspot")
    OAUTH_EXCHANGE_HUBSPOT,

    @Json(name = "oauth_exchange_github")
    OAUTH_EXCHANGE_GITHUB,

    @Json(name = "oauth_exchange_google")
    OAUTH_EXCHANGE_GOOGLE,

    @Json(name = "impersonation")
    IMPERSONATION,

    @Json(name = "oauth_access_token_exchange")
    OAUTH_ACCESS_TOKEN_EXCHANGE,
}

@JsonClass(generateAdapter = false)
public enum class AuthenticationFactorType {
    @Json(name = "magic_link")
    MAGIC_LINK,

    @Json(name = "otp")
    OTP,

    @Json(name = "oauth")
    OAUTH,

    @Json(name = "webauthn")
    WEBAUTHN,

    @Json(name = "totp")
    TOTP,

    @Json(name = "crypto")
    CRYPTO,

    @Json(name = "password")
    PASSWORD,

    @Json(name = "signature_challenge")
    SIGNATURE_CHALLENGE,

    @Json(name = "sso")
    SSO,

    @Json(name = "imported")
    IMPORTED,

    @Json(name = "recovery_codes")
    RECOVERY_CODES,

    @Json(name = "email_otp")
    EMAIL_OTP,

    @Json(name = "impersonated")
    IMPERSONATED,
}

@JsonClass(generateAdapter = true)
public data class AmazonOAuthFactor
    @JvmOverloads
    constructor(
        @Json(name = "id")
        val id: String,
        @Json(name = "provider_subject")
        val providerSubject: String,
        @Json(name = "email_id")
        val emailId: String? = null,
    )

@JsonClass(generateAdapter = true)
public data class AppleOAuthFactor
    @JvmOverloads
    constructor(
        @Json(name = "id")
        val id: String,
        @Json(name = "provider_subject")
        val providerSubject: String,
        @Json(name = "email_id")
        val emailId: String? = null,
    )

@JsonClass(generateAdapter = true)
public data class AuthenticationFactor
    @JvmOverloads
    constructor(
        /**
         * The type of authentication factor. The possible values are: `magic_link`, `otp`,
         *        `oauth`, `password`, `email_otp`, or `sso` .
         */
        @Json(name = "type")
        val type: AuthenticationFactorType,
        /**
         * The method that was used to deliver the authentication factor. The possible values depend on the `type`:
         *
         *       `magic_link` – Only `email`.
         *
         *       `otp` –  Either `sms` or `email` .
         *
         *       `oauth` – Either `oauth_google` or `oauth_microsoft`.
         *
         *       `password` – Only `knowledge`.
         *
         *       `sso` – Either `sso_saml` or `sso_oidc`.
         *
         */
        @Json(name = "delivery_method")
        val deliveryMethod: AuthenticationFactorDeliveryMethod,
        /**
         * The timestamp when the factor was last authenticated.
         */
        @Json(name = "last_authenticated_at")
        val lastAuthenticatedAt: Instant? = null,
        /**
         * The timestamp when the factor was initially authenticated.
         */
        @Json(name = "created_at")
        val createdAt: Instant? = null,
        /**
         * The timestamp when the factor was last updated.
         */
        @Json(name = "updated_at")
        val updatedAt: Instant? = null,
        /**
         * Information about the email factor, if one is present.
         */
        @Json(name = "email_factor")
        val emailFactor: EmailFactor? = null,
        /**
         * Information about the phone number factor, if one is present.
         */
        @Json(name = "phone_number_factor")
        val phoneNumberFactor: PhoneNumberFactor? = null,
        /**
         * Information about the Google OAuth factor, if one is present.
         */
        @Json(name = "google_oauth_factor")
        val googleOAuthFactor: GoogleOAuthFactor? = null,
        /**
         * Information about the Microsoft OAuth factor, if one is present.
         */
        @Json(name = "microsoft_oauth_factor")
        val microsoftOAuthFactor: MicrosoftOAuthFactor? = null,
        @Json(name = "apple_oauth_factor")
        val appleOAuthFactor: AppleOAuthFactor? = null,
        @Json(name = "webauthn_factor")
        val webauthnFactor: WebAuthnFactor? = null,
        /**
         * Information about the TOTP-backed Authenticator App factor, if one is present.
         */
        @Json(name = "authenticator_app_factor")
        val authenticatorAppFactor: AuthenticatorAppFactor? = null,
        @Json(name = "github_oauth_factor")
        val githubOAuthFactor: GithubOAuthFactor? = null,
        @Json(name = "recovery_code_factor")
        val recoveryCodeFactor: RecoveryCodeFactor? = null,
        @Json(name = "facebook_oauth_factor")
        val facebookOAuthFactor: FacebookOAuthFactor? = null,
        @Json(name = "crypto_wallet_factor")
        val cryptoWalletFactor: CryptoWalletFactor? = null,
        @Json(name = "amazon_oauth_factor")
        val amazonOAuthFactor: AmazonOAuthFactor? = null,
        @Json(name = "bitbucket_oauth_factor")
        val bitbucketOAuthFactor: BitbucketOAuthFactor? = null,
        @Json(name = "coinbase_oauth_factor")
        val coinbaseOAuthFactor: CoinbaseOAuthFactor? = null,
        @Json(name = "discord_oauth_factor")
        val discordOAuthFactor: DiscordOAuthFactor? = null,
        @Json(name = "figma_oauth_factor")
        val figmaOAuthFactor: FigmaOAuthFactor? = null,
        @Json(name = "git_lab_oauth_factor")
        val gitLabOAuthFactor: GitLabOAuthFactor? = null,
        @Json(name = "instagram_oauth_factor")
        val instagramOAuthFactor: InstagramOAuthFactor? = null,
        @Json(name = "linked_in_oauth_factor")
        val linkedInOAuthFactor: LinkedInOAuthFactor? = null,
        @Json(name = "shopify_oauth_factor")
        val shopifyOAuthFactor: ShopifyOAuthFactor? = null,
        @Json(name = "slack_oauth_factor")
        val slackOAuthFactor: SlackOAuthFactor? = null,
        @Json(name = "snapchat_oauth_factor")
        val snapchatOAuthFactor: SnapchatOAuthFactor? = null,
        @Json(name = "spotify_oauth_factor")
        val spotifyOAuthFactor: SpotifyOAuthFactor? = null,
        @Json(name = "steam_oauth_factor")
        val steamOAuthFactor: SteamOAuthFactor? = null,
        @Json(name = "tik_tok_oauth_factor")
        val tikTokOAuthFactor: TikTokOAuthFactor? = null,
        @Json(name = "twitch_oauth_factor")
        val twitchOAuthFactor: TwitchOAuthFactor? = null,
        @Json(name = "twitter_oauth_factor")
        val twitterOAuthFactor: TwitterOAuthFactor? = null,
        @Json(name = "embeddable_magic_link_factor")
        val embeddableMagicLinkFactor: EmbeddableMagicLinkFactor? = null,
        @Json(name = "biometric_factor")
        val biometricFactor: BiometricFactor? = null,
        /**
         * Information about the SAML SSO factor, if one is present.
         */
        @Json(name = "saml_sso_factor")
        val samlSSOFactor: SAMLSSOFactor? = null,
        /**
         * Information about the OIDC SSO factor, if one is present.
         */
        @Json(name = "oidc_sso_factor")
        val oidcSSOFactor: OIDCSSOFactor? = null,
        @Json(name = "salesforce_oauth_factor")
        val salesforceOAuthFactor: SalesforceOAuthFactor? = null,
        @Json(name = "yahoo_oauth_factor")
        val yahooOAuthFactor: YahooOAuthFactor? = null,
        @Json(name = "hubspot_oauth_factor")
        val hubspotOAuthFactor: HubspotOAuthFactor? = null,
        @Json(name = "slack_oauth_exchange_factor")
        val slackOAuthExchangeFactor: SlackOAuthExchangeFactor? = null,
        @Json(name = "hubspot_oauth_exchange_factor")
        val hubspotOAuthExchangeFactor: HubspotOAuthExchangeFactor? = null,
        @Json(name = "github_oauth_exchange_factor")
        val githubOAuthExchangeFactor: GithubOAuthExchangeFactor? = null,
        @Json(name = "google_oauth_exchange_factor")
        val googleOAuthExchangeFactor: GoogleOAuthExchangeFactor? = null,
        /**
         * Information about the impersonated factor, if one is present.
         */
        @Json(name = "impersonated_factor")
        val impersonatedFactor: ImpersonatedFactor? = null,
        @Json(name = "oauth_access_token_exchange_factor")
        val oauthAccessTokenExchangeFactor: OAuthAccessTokenExchangeFactor? = null,
    )

@JsonClass(generateAdapter = true)
public data class AuthenticatorAppFactor
    @JvmOverloads
    constructor(
        /**
         * Globally unique UUID that identifies a TOTP instance.
         */
        @Json(name = "totp_id")
        val totpId: String,
    )

@JsonClass(generateAdapter = true)
public data class BiometricFactor
    @JvmOverloads
    constructor(
        @Json(name = "biometric_registration_id")
        val biometricRegistrationId: String,
    )

@JsonClass(generateAdapter = true)
public data class BitbucketOAuthFactor
    @JvmOverloads
    constructor(
        @Json(name = "id")
        val id: String,
        @Json(name = "provider_subject")
        val providerSubject: String,
        @Json(name = "email_id")
        val emailId: String? = null,
    )

@JsonClass(generateAdapter = true)
public data class CoinbaseOAuthFactor
    @JvmOverloads
    constructor(
        @Json(name = "id")
        val id: String,
        @Json(name = "provider_subject")
        val providerSubject: String,
        @Json(name = "email_id")
        val emailId: String? = null,
    )

@JsonClass(generateAdapter = true)
public data class CryptoWalletFactor
    @JvmOverloads
    constructor(
        @Json(name = "crypto_wallet_id")
        val cryptoWalletId: String,
        @Json(name = "crypto_wallet_address")
        val cryptoWalletAddress: String,
        @Json(name = "crypto_wallet_type")
        val cryptoWalletType: String,
    )

@JsonClass(generateAdapter = true)
public data class DiscordOAuthFactor
    @JvmOverloads
    constructor(
        @Json(name = "id")
        val id: String,
        @Json(name = "provider_subject")
        val providerSubject: String,
        @Json(name = "email_id")
        val emailId: String? = null,
    )

@JsonClass(generateAdapter = true)
public data class EmailFactor
    @JvmOverloads
    constructor(
        /**
         * The globally unique UUID of the Member's email.
         */
        @Json(name = "email_id")
        val emailId: String,
        /**
         * The email address of the Member.
         */
        @Json(name = "email_address")
        val emailAddress: String,
    )

@JsonClass(generateAdapter = true)
public data class EmbeddableMagicLinkFactor
    @JvmOverloads
    constructor(
        @Json(name = "embedded_id")
        val embeddedId: String,
    )

@JsonClass(generateAdapter = true)
public data class FacebookOAuthFactor
    @JvmOverloads
    constructor(
        @Json(name = "id")
        val id: String,
        @Json(name = "provider_subject")
        val providerSubject: String,
        @Json(name = "email_id")
        val emailId: String? = null,
    )

@JsonClass(generateAdapter = true)
public data class FigmaOAuthFactor
    @JvmOverloads
    constructor(
        @Json(name = "id")
        val id: String,
        @Json(name = "provider_subject")
        val providerSubject: String,
        @Json(name = "email_id")
        val emailId: String? = null,
    )

@JsonClass(generateAdapter = true)
public data class GitLabOAuthFactor
    @JvmOverloads
    constructor(
        @Json(name = "id")
        val id: String,
        @Json(name = "provider_subject")
        val providerSubject: String,
        @Json(name = "email_id")
        val emailId: String? = null,
    )

@JsonClass(generateAdapter = true)
public data class GithubOAuthExchangeFactor
    @JvmOverloads
    constructor(
        @Json(name = "email_id")
        val emailId: String,
    )

@JsonClass(generateAdapter = true)
public data class GithubOAuthFactor
    @JvmOverloads
    constructor(
        @Json(name = "id")
        val id: String,
        @Json(name = "provider_subject")
        val providerSubject: String,
        @Json(name = "email_id")
        val emailId: String? = null,
    )

@JsonClass(generateAdapter = true)
public data class GoogleOAuthExchangeFactor
    @JvmOverloads
    constructor(
        @Json(name = "email_id")
        val emailId: String,
    )

@JsonClass(generateAdapter = true)
public data class GoogleOAuthFactor
    @JvmOverloads
    constructor(
        /**
         * The unique ID of an OAuth registration.
         */
        @Json(name = "id")
        val id: String,
        /**
         * The unique identifier for the User within a given OAuth provider. Also commonly called the `sub` or "Subject field" in
         * OAuth protocols.
         */
        @Json(name = "provider_subject")
        val providerSubject: String,
        /**
         * The globally unique UUID of the Member's email.
         */
        @Json(name = "email_id")
        val emailId: String? = null,
    )

@JsonClass(generateAdapter = true)
public data class HubspotOAuthExchangeFactor
    @JvmOverloads
    constructor(
        @Json(name = "email_id")
        val emailId: String,
    )

@JsonClass(generateAdapter = true)
public data class HubspotOAuthFactor
    @JvmOverloads
    constructor(
        @Json(name = "id")
        val id: String,
        @Json(name = "provider_subject")
        val providerSubject: String,
        @Json(name = "email_id")
        val emailId: String? = null,
    )

@JsonClass(generateAdapter = true)
public data class ImpersonatedFactor
    @JvmOverloads
    constructor(
        /**
         * The unique UUID of the impersonator. For impersonation sessions initiated via the Stytch dashboard, the
         * `impersonator_id` will be the impersonator's Stytch workspace id.
         */
        @Json(name = "impersonator_id")
        val impersonatorId: String,
        /**
         * The email address of the impersonator.
         */
        @Json(name = "impersonator_email_address")
        val impersonatorEmailAddress: String,
    )

@JsonClass(generateAdapter = true)
public data class InstagramOAuthFactor
    @JvmOverloads
    constructor(
        @Json(name = "id")
        val id: String,
        @Json(name = "provider_subject")
        val providerSubject: String,
        @Json(name = "email_id")
        val emailId: String? = null,
    )

@JsonClass(generateAdapter = true)
public data class JWK
    @JvmOverloads
    constructor(
        @Json(name = "kty")
        val kty: String,
        @Json(name = "use")
        val use: String,
        @Json(name = "key_ops")
        val keyOps: List<String>,
        @Json(name = "alg")
        val alg: String,
        @Json(name = "kid")
        val kid: String,
        @Json(name = "x5c")
        val x5C: List<String>,
        @Json(name = "x5tS256")
        val x5TS256: String,
        @Json(name = "n")
        val n: String,
        @Json(name = "e")
        val e: String,
    )

@JsonClass(generateAdapter = true)
public data class LinkedInOAuthFactor
    @JvmOverloads
    constructor(
        @Json(name = "id")
        val id: String,
        @Json(name = "provider_subject")
        val providerSubject: String,
        @Json(name = "email_id")
        val emailId: String? = null,
    )

@JsonClass(generateAdapter = true)
public data class MicrosoftOAuthFactor
    @JvmOverloads
    constructor(
        /**
         * The unique ID of an OAuth registration.
         */
        @Json(name = "id")
        val id: String,
        /**
         * The unique identifier for the User within a given OAuth provider. Also commonly called the `sub` or "Subject field" in
         * OAuth protocols.
         */
        @Json(name = "provider_subject")
        val providerSubject: String,
        /**
         * The globally unique UUID of the Member's email.
         */
        @Json(name = "email_id")
        val emailId: String? = null,
    )

@JsonClass(generateAdapter = true)
public data class OAuthAccessTokenExchangeFactor
    @JvmOverloads
    constructor(
        @Json(name = "client_id")
        val clientId: String,
    )

@JsonClass(generateAdapter = true)
public data class OIDCSSOFactor
    @JvmOverloads
    constructor(
        /**
         * The unique ID of an SSO Registration.
         */
        @Json(name = "id")
        val id: String,
        /**
         * Globally unique UUID that identifies a specific OIDC Connection.
         */
        @Json(name = "provider_id")
        val providerId: String,
        /**
         * The ID of the member given by the identity provider.
         */
        @Json(name = "external_id")
        val externalId: String,
    )

@JsonClass(generateAdapter = true)
public data class PhoneNumberFactor
    @JvmOverloads
    constructor(
        /**
         * The globally unique UUID of the Member's phone number.
         */
        @Json(name = "phone_id")
        val phoneId: String,
        /**
         * The phone number of the Member.
         */
        @Json(name = "phone_number")
        val phoneNumber: String,
    )

@JsonClass(generateAdapter = true)
public data class RecoveryCodeFactor
    @JvmOverloads
    constructor(
        @Json(name = "totp_recovery_code_id")
        val totpRecoveryCodeId: String,
    )

@JsonClass(generateAdapter = true)
public data class SAMLSSOFactor
    @JvmOverloads
    constructor(
        /**
         * The unique ID of an SSO Registration.
         */
        @Json(name = "id")
        val id: String,
        /**
         * Globally unique UUID that identifies a specific SAML Connection.
         */
        @Json(name = "provider_id")
        val providerId: String,
        /**
         * The ID of the member given by the identity provider.
         */
        @Json(name = "external_id")
        val externalId: String,
    )

@JsonClass(generateAdapter = true)
public data class SalesforceOAuthFactor
    @JvmOverloads
    constructor(
        @Json(name = "id")
        val id: String,
        @Json(name = "provider_subject")
        val providerSubject: String,
        @Json(name = "email_id")
        val emailId: String? = null,
    )

@JsonClass(generateAdapter = true)
public data class Session
    @JvmOverloads
    constructor(
        /**
         * A unique identifier for a specific Session.
         */
        @Json(name = "session_id")
        val sessionId: String,
        /**
         * The unique ID of the affected User.
         */
        @Json(name = "user_id")
        val userId: String,
        /**
         * An array of different authentication factors that comprise a Session.
         */
        @Json(name = "authentication_factors")
        val authenticationFactors: List<AuthenticationFactor>,
        /**
         * The timestamp when the Session was created. Values conform to the RFC 3339 standard and are expressed in UTC, e.g.
         * `2021-12-29T12:33:09Z`.
         */
        @Json(name = "started_at")
        val startedAt: Instant? = null,
        /**
         * The timestamp when the Session was last accessed. Values conform to the RFC 3339 standard and are expressed in UTC,
         * e.g. `2021-12-29T12:33:09Z`.
         */
        @Json(name = "last_accessed_at")
        val lastAccessedAt: Instant? = null,
        /**
         * The timestamp when the Session expires. Values conform to the RFC 3339 standard and are expressed in UTC, e.g.
         * `2021-12-29T12:33:09Z`.
         */
        @Json(name = "expires_at")
        val expiresAt: Instant? = null,
        /**
         * Provided attributes help with fraud detection.
         */
        @Json(name = "attributes")
        val attributes: Attributes? = null,
        /**
         * The custom claims map for a Session. Claims can be added to a session during a Sessions authenticate call.
         */
        @Json(name = "custom_claims")
        val customClaims: Map<String, Any?>? = emptyMap(),
    )

@JsonClass(generateAdapter = true)
public data class ShopifyOAuthFactor
    @JvmOverloads
    constructor(
        @Json(name = "id")
        val id: String,
        @Json(name = "provider_subject")
        val providerSubject: String,
        @Json(name = "email_id")
        val emailId: String? = null,
    )

@JsonClass(generateAdapter = true)
public data class SlackOAuthExchangeFactor
    @JvmOverloads
    constructor(
        @Json(name = "email_id")
        val emailId: String,
    )

@JsonClass(generateAdapter = true)
public data class SlackOAuthFactor
    @JvmOverloads
    constructor(
        @Json(name = "id")
        val id: String,
        @Json(name = "provider_subject")
        val providerSubject: String,
        @Json(name = "email_id")
        val emailId: String? = null,
    )

@JsonClass(generateAdapter = true)
public data class SnapchatOAuthFactor
    @JvmOverloads
    constructor(
        @Json(name = "id")
        val id: String,
        @Json(name = "provider_subject")
        val providerSubject: String,
        @Json(name = "email_id")
        val emailId: String? = null,
    )

@JsonClass(generateAdapter = true)
public data class SpotifyOAuthFactor
    @JvmOverloads
    constructor(
        @Json(name = "id")
        val id: String,
        @Json(name = "provider_subject")
        val providerSubject: String,
        @Json(name = "email_id")
        val emailId: String? = null,
    )

@JsonClass(generateAdapter = true)
public data class SteamOAuthFactor
    @JvmOverloads
    constructor(
        @Json(name = "id")
        val id: String,
        @Json(name = "provider_subject")
        val providerSubject: String,
        @Json(name = "email_id")
        val emailId: String? = null,
    )

@JsonClass(generateAdapter = true)
public data class TikTokOAuthFactor
    @JvmOverloads
    constructor(
        @Json(name = "id")
        val id: String,
        @Json(name = "provider_subject")
        val providerSubject: String,
        @Json(name = "email_id")
        val emailId: String? = null,
    )

@JsonClass(generateAdapter = true)
public data class TwitchOAuthFactor
    @JvmOverloads
    constructor(
        @Json(name = "id")
        val id: String,
        @Json(name = "provider_subject")
        val providerSubject: String,
        @Json(name = "email_id")
        val emailId: String? = null,
    )

@JsonClass(generateAdapter = true)
public data class TwitterOAuthFactor
    @JvmOverloads
    constructor(
        @Json(name = "id")
        val id: String,
        @Json(name = "provider_subject")
        val providerSubject: String,
        @Json(name = "email_id")
        val emailId: String? = null,
    )

@JsonClass(generateAdapter = true)
public data class WebAuthnFactor
    @JvmOverloads
    constructor(
        @Json(name = "webauthn_registration_id")
        val webauthnRegistrationId: String,
        @Json(name = "domain")
        val domain: String,
        @Json(name = "user_agent")
        val userAgent: String? = null,
    )

@JsonClass(generateAdapter = true)
public data class YahooOAuthFactor
    @JvmOverloads
    constructor(
        @Json(name = "id")
        val id: String,
        @Json(name = "provider_subject")
        val providerSubject: String,
        @Json(name = "email_id")
        val emailId: String? = null,
    )

/**
* Request type for `Sessions.authenticate`.
*/
@JsonClass(generateAdapter = true)
public data class AuthenticateRequest
    @JvmOverloads
    constructor(
        /**
         * The session token to authenticate.
         */
        @Json(name = "session_token")
        val sessionToken: String? = null,
        /**
         * Set the session lifetime to be this many minutes from now; minimum of 5 and a maximum of 527040 minutes (366 days).
         * Note that a successful authentication will continue to extend the session this many minutes.
         */
        @Json(name = "session_duration_minutes")
        val sessionDurationMinutes: Int? = null,
        /**
         * The JWT to authenticate. You may provide a JWT that has expired according to its `exp` claim and needs to be refreshed.
         * If the signature is valid and the underlying session is still active then Stytch will return a new JWT.
         */
        @Json(name = "session_jwt")
        val sessionJwt: String? = null,
        /**
         * Add a custom claims map to the Session being authenticated. Claims are only created if a Session is initialized by
         * providing a value in `session_duration_minutes`. Claims will be included on the Session object and in the JWT. To
         * update a key in an existing Session, supply a new value. To delete a key, supply a null value.
         *
         *   Custom claims made with reserved claims ("iss", "sub", "aud", "exp", "nbf", "iat", "jti") will be ignored. Total
         * custom claims size cannot exceed four kilobytes.
         */
        @Json(name = "session_custom_claims")
        val sessionCustomClaims: Map<String, Any?>? = emptyMap(),
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
         * If you initiate a Session, by including `session_duration_minutes` in your authenticate call, you'll receive a full
         * Session object in the response.
         *
         *   See [GET sessions](https://stytch.com/docs/api/session-get) for complete response fields.
         *
         */
        @Json(name = "session")
        val session: Session,
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
         * The `user` object affected by this API call. See the [Get user endpoint](https://stytch.com/docs/api/get-user) for
         * complete response field details.
         */
        @Json(name = "user")
        val user: User,
        /**
         * The HTTP status code of the response. Stytch follows standard HTTP response status code patterns, e.g. 2XX values
         * equate to success, 3XX values are redirects, 4XX are client errors, and 5XX are server errors.
         */
        @Json(name = "status_code")
        val statusCode: Int,
    )

@JsonClass(generateAdapter = true)
public data class ExchangeAccessTokenRequest
    @JvmOverloads
    constructor(
        @Json(name = "access_token")
        val accessToken: String,
        @Json(name = "session_duration_minutes")
        val sessionDurationMinutes: Int? = null,
        @Json(name = "session_custom_claims")
        val sessionCustomClaims: Map<String, Any?>? = emptyMap(),
    )

@JsonClass(generateAdapter = true)
public data class ExchangeAccessTokenResponse
    @JvmOverloads
    constructor(
        @Json(name = "request_id")
        val requestId: String,
        @Json(name = "user_id")
        val userId: String,
        @Json(name = "session_token")
        val sessionToken: String,
        @Json(name = "session_jwt")
        val sessionJwt: String,
        @Json(name = "user")
        val user: User,
        @Json(name = "status_code")
        val statusCode: Int,
        @Json(name = "session")
        val session: Session? = null,
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
         * The list of JWKs associated with the project.
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
         * The `user_id` to get active Sessions for. You may use an external_id here if one is set for the user.
         */
        @Json(name = "user_id")
        val userId: String,
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
         * An array of Session objects.
         */
        @Json(name = "sessions")
        val sessions: List<Session>,
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
         *   If the `session_duration_minutes` parameter is not specified, a Stytch session will not be created.
         */
        @Json(name = "session_duration_minutes")
        val sessionDurationMinutes: Int? = null,
        /**
         * Add a custom claims map to the Session being authenticated. Claims are only created if a Session is initialized by
         * providing a value in `session_duration_minutes`. Claims will be included on the Session object and in the JWT. To
         * update a key in an existing Session, supply a new value. To delete a key, supply a null value.
         *
         *   Custom claims made with reserved claims ("iss", "sub", "aud", "exp", "nbf", "iat", "jti") will be ignored. Total
         * custom claims size cannot exceed four kilobytes.
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
         * The unique ID of the affected User.
         */
        @Json(name = "user_id")
        val userId: String,
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
         * The `user` object affected by this API call. See the [Get user endpoint](https://stytch.com/docs/api/get-user) for
         * complete response field details.
         */
        @Json(name = "user")
        val user: User,
        @Json(name = "status_code")
        val statusCode: Int,
        /**
         * If you initiate a Session, by including `session_duration_minutes` in your authenticate call, you'll receive a full
         * Session object in the response.
         *
         *   See [GET sessions](https://stytch.com/docs/api/session-get) for complete response fields.
         *
         */
        @Json(name = "session")
        val session: Session? = null,
    )

/**
* Request type for `Sessions.revoke`.
*/
@JsonClass(generateAdapter = true)
public data class RevokeRequest
    @JvmOverloads
    constructor(
        /**
         * The `session_id` to revoke.
         */
        @Json(name = "session_id")
        val sessionId: String? = null,
        /**
         * The session token to revoke.
         */
        @Json(name = "session_token")
        val sessionToken: String? = null,
        /**
         * A JWT for the session to revoke.
         */
        @Json(name = "session_jwt")
        val sessionJwt: String? = null,
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
