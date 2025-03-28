package com.stytch.java.consumer.models.webauthn

// !!!
// WARNING: This file is autogenerated
// Only modify code within MANUAL() sections
// or your changes may be overwritten later!
// !!!

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.stytch.java.consumer.models.sessions.Session
import com.stytch.java.consumer.models.users.User
import com.stytch.java.consumer.models.users.WebAuthnRegistration

@JsonClass(generateAdapter = true)
public data class WebAuthnCredential
    @JvmOverloads
    constructor(
        /**
         * The unique, public ID of the WebAuthn credential.
         */
        @Json(name = "credential_id")
        val credentialId: String,
        /**
         * The unique ID for the Passkey or WebAuthn registration.
         */
        @Json(name = "webauthn_registration_id")
        val webauthnRegistrationId: String,
        /**
         * The type of the WebAuthn credential. Examples include `public-key`.
         */
        @Json(name = "type")
        val type: String,
    )

/**
* Request type for `WebAuthn.authenticate`.
*/
@JsonClass(generateAdapter = true)
public data class AuthenticateRequest
    @JvmOverloads
    constructor(
        /**
         * The response of the [navigator.credentials.create()](https://www.w3.org/TR/webauthn-2/#sctn-createCredential).
         */
        @Json(name = "public_key_credential")
        val publicKeyCredential: String,
        /**
         * The `session_token` associated with a User's existing Session.
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
         *   If the `session_duration_minutes` parameter is not specified, a Stytch session will not be created.
         */
        @Json(name = "session_duration_minutes")
        val sessionDurationMinutes: Int? = null,
        /**
         * The `session_jwt` associated with a User's existing Session.
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
* Response type for `WebAuthn.authenticate`.
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
         * The unique ID of the affected User.
         */
        @Json(name = "user_id")
        val userId: String,
        /**
         * The unique ID for the Passkey or WebAuthn registration.
         */
        @Json(name = "webauthn_registration_id")
        val webauthnRegistrationId: String,
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
* Request type for `WebAuthn.authenticateStart`.
*/
@JsonClass(generateAdapter = true)
public data class AuthenticateStartRequest
    @JvmOverloads
    constructor(
        /**
         * The domain for Passkeys or WebAuthn. Defaults to `window.location.hostname`.
         */
        @Json(name = "domain")
        val domain: String,
        /**
         * The `user_id` of an active user the Passkey or WebAuthn registration should be tied to.
         */
        @Json(name = "user_id")
        val userId: String? = null,
        /**
         * If true, the `public_key_credential_creation_options` returned will be optimized for Passkeys with `userVerification`
         * set to `"preferred"`.
         *
         */
        @Json(name = "return_passkey_credential_options")
        val returnPasskeyCredentialOptions: Boolean? = null,
    )

/**
* Response type for `WebAuthn.authenticateStart`.
*/
@JsonClass(generateAdapter = true)
public data class AuthenticateStartResponse
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
         * Options used for Passkey or WebAuthn authentication.
         */
        @Json(name = "public_key_credential_request_options")
        val publicKeyCredentialRequestOptions: String,
        /**
         * The HTTP status code of the response. Stytch follows standard HTTP response status code patterns, e.g. 2XX values
         * equate to success, 3XX values are redirects, 4XX are client errors, and 5XX are server errors.
         */
        @Json(name = "status_code")
        val statusCode: Int,
    )

/**
* Request type for `WebAuthn.credentials`.
*/
@JsonClass(generateAdapter = true)
public data class CredentialsRequest
    @JvmOverloads
    constructor(
        /**
         * The `user_id` of an active user the Passkey or WebAuthn registration should be tied to.
         */
        @Json(name = "user_id")
        val userId: String,
        /**
         * The domain for Passkeys or WebAuthn. Defaults to `window.location.hostname`.
         */
        @Json(name = "domain")
        val domain: String,
    )

/**
* Response type for `WebAuthn.credentials`.
*/
@JsonClass(generateAdapter = true)
public data class CredentialsResponse
    @JvmOverloads
    constructor(
        /**
         * A list of WebAuthn credential objects.
         */
        @Json(name = "credentials")
        val credentials: List<WebAuthnCredential>,
        /**
         * The HTTP status code of the response. Stytch follows standard HTTP response status code patterns, e.g. 2XX values
         * equate to success, 3XX values are redirects, 4XX are client errors, and 5XX are server errors.
         */
        @Json(name = "status_code")
        val statusCode: Int,
    )

/**
* Request type for `WebAuthn.register`.
*/
@JsonClass(generateAdapter = true)
public data class RegisterRequest
    @JvmOverloads
    constructor(
        /**
         * The `user_id` of an active user the Passkey or WebAuthn registration should be tied to.
         */
        @Json(name = "user_id")
        val userId: String,
        /**
         * The response of the [navigator.credentials.create()](https://www.w3.org/TR/webauthn-2/#sctn-createCredential).
         */
        @Json(name = "public_key_credential")
        val publicKeyCredential: String,
        /**
         * The `session_token` associated with a User's existing Session.
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
         *   If the `session_duration_minutes` parameter is not specified, a Stytch session will not be created.
         */
        @Json(name = "session_duration_minutes")
        val sessionDurationMinutes: Int? = null,
        /**
         * The `session_jwt` associated with a User's existing Session.
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
* Response type for `WebAuthn.register`.
*/
@JsonClass(generateAdapter = true)
public data class RegisterResponse
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
         * The unique ID for the Passkey or WebAuthn registration.
         */
        @Json(name = "webauthn_registration_id")
        val webauthnRegistrationId: String,
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
        @Json(name = "user")
        val user: User,
        /**
         * The HTTP status code of the response. Stytch follows standard HTTP response status code patterns, e.g. 2XX values
         * equate to success, 3XX values are redirects, 4XX are client errors, and 5XX are server errors.
         */
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
* Request type for `WebAuthn.registerStart`.
*/
@JsonClass(generateAdapter = true)
public data class RegisterStartRequest
    @JvmOverloads
    constructor(
        /**
         * The `user_id` of an active user the Passkey or WebAuthn registration should be tied to.
         */
        @Json(name = "user_id")
        val userId: String,
        /**
         * The domain for Passkeys or WebAuthn. Defaults to `window.location.hostname`.
         */
        @Json(name = "domain")
        val domain: String,
        /**
         * The user agent of the User.
         */
        @Json(name = "user_agent")
        val userAgent: String? = null,
        /**
         * The requested authenticator type of the Passkey or WebAuthn device. The two valid values are platform and
         * cross-platform. If no value passed, we assume both values are allowed.
         */
        @Json(name = "authenticator_type")
        val authenticatorType: String? = null,
        /**
         * If true, the `public_key_credential_creation_options` returned will be optimized for Passkeys with `residentKey` set to
         * `"required"` and `userVerification` set to `"preferred"`.
         *
         */
        @Json(name = "return_passkey_credential_options")
        val returnPasskeyCredentialOptions: Boolean? = null,
        @Json(name = "override_id")
        val overrideId: String? = null,
        @Json(name = "override_name")
        val overrideName: String? = null,
        @Json(name = "override_display_name")
        val overrideDisplayName: String? = null,
    )

/**
* Response type for `WebAuthn.registerStart`.
*/
@JsonClass(generateAdapter = true)
public data class RegisterStartResponse
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
         * Options used for Passkey or WebAuthn registration.
         */
        @Json(name = "public_key_credential_creation_options")
        val publicKeyCredentialCreationOptions: String,
        /**
         * The HTTP status code of the response. Stytch follows standard HTTP response status code patterns, e.g. 2XX values
         * equate to success, 3XX values are redirects, 4XX are client errors, and 5XX are server errors.
         */
        @Json(name = "status_code")
        val statusCode: Int,
    )

/**
* Request type for `WebAuthn.update`.
*/
@JsonClass(generateAdapter = true)
public data class UpdateRequest
    @JvmOverloads
    constructor(
        /**
         * Globally unique UUID that identifies a Passkey or WebAuthn registration in the Stytch API. The
         * `webauthn_registration_id` is used when you need to operate on a specific User's WebAuthn registration.
         */
        @Json(name = "webauthn_registration_id")
        val webauthnRegistrationId: String,
        /**
         * The `name` of the WebAuthn registration or Passkey.
         */
        @Json(name = "name")
        val name: String,
    )

/**
* Response type for `WebAuthn.update`.
*/
@JsonClass(generateAdapter = true)
public data class UpdateResponse
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
        /**
         * A Passkey or WebAuthn registration.
         */
        @Json(name = "webauthn_registration")
        val webauthnRegistration: WebAuthnRegistration? = null,
    )
