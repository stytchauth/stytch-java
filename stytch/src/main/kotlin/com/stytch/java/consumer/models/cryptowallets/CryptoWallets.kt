package com.stytch.java.consumer.models.cryptowallets

// !!!
// WARNING: This file is autogenerated
// Only modify code within MANUAL() sections
// or your changes may be overwritten later!
// !!!

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.stytch.java.consumer.models.sessions.Session
import com.stytch.java.consumer.models.users.User

/**
* Request type for `CryptoWallets.authenticate`.
*/
@JsonClass(generateAdapter = true)
public data class AuthenticateRequest
    @JvmOverloads
    constructor(
        /**
         * The type of wallet to authenticate. Currently `ethereum` and `solana` are supported. Wallets for any EVM-compatible
         * chains (such as Polygon or BSC) are also supported and are grouped under the `ethereum` type.
         */
        @Json(name = "crypto_wallet_type")
        val cryptoWalletType: String,
        /**
         * The crypto wallet address to authenticate.
         */
        @Json(name = "crypto_wallet_address")
        val cryptoWalletAddress: String,
        /**
         * The signature from the message challenge.
         */
        @Json(name = "signature")
        val signature: String,
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
* Response type for `CryptoWallets.authenticate`.
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
* Request type for `CryptoWallets.authenticateStart`.
*/
@JsonClass(generateAdapter = true)
public data class AuthenticateStartRequest
    @JvmOverloads
    constructor(
        /**
         * The type of wallet to authenticate. Currently `ethereum` and `solana` are supported. Wallets for any EVM-compatible
         * chains (such as Polygon or BSC) are also supported and are grouped under the `ethereum` type.
         */
        @Json(name = "crypto_wallet_type")
        val cryptoWalletType: String,
        /**
         * The crypto wallet address to authenticate.
         */
        @Json(name = "crypto_wallet_address")
        val cryptoWalletAddress: String,
        /**
         * The unique ID of a specific User.
         */
        @Json(name = "user_id")
        val userId: String? = null,
        /**
         * The `session_token` associated with a User's existing Session.
         */
        @Json(name = "session_token")
        val sessionToken: String? = null,
        /**
         * The `session_jwt` associated with a User's existing Session.
         */
        @Json(name = "session_jwt")
        val sessionJwt: String? = null,
    )

/**
* Response type for `CryptoWallets.authenticateStart`.
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
         * A challenge string to be signed by the wallet in order to prove ownership.
         */
        @Json(name = "challenge")
        val challenge: String,
        /**
         * In `login_or_create` endpoints, this field indicates whether or not a User was just created.
         */
        @Json(name = "user_created")
        val userCreated: Boolean,
        /**
         * The HTTP status code of the response. Stytch follows standard HTTP response status code patterns, e.g. 2XX values
         * equate to success, 3XX values are redirects, 4XX are client errors, and 5XX are server errors.
         */
        @Json(name = "status_code")
        val statusCode: Int,
    )
