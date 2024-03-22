package com.stytch.java.consumer.models.passwords

// !!!
// WARNING: This file is autogenerated
// Only modify code within MANUAL() sections
// or your changes may be overwritten later!
// !!!

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.stytch.java.consumer.models.sessions.Session
import com.stytch.java.consumer.models.users.Name
import com.stytch.java.consumer.models.users.User

@JsonClass(generateAdapter = false)
public enum class MigrateRequestHashType {
    @Json(name = "bcrypt")
    BCRYPT,

    @Json(name = "md_5")
    MD_5,

    @Json(name = "argon_2i")
    ARGON_2I,

    @Json(name = "argon_2id")
    ARGON_2ID,

    @Json(name = "sha_1")
    SHA_1,

    @Json(name = "scrypt")
    SCRYPT,

    @Json(name = "phpass")
    PHPASS,

    @Json(name = "pbkdf_2")
    PBKDF_2,
}

@JsonClass(generateAdapter = true)
public data class Argon2Config
    @JvmOverloads
    constructor(
        /**
         * The salt value.
         */
        @Json(name = "salt")
        val salt: String,
        /**
         * The iteration amount.
         */
        @Json(name = "iteration_amount")
        val iterationAmount: Int,
        /**
         * The memory in kibibytes.
         */
        @Json(name = "memory")
        val memory: Int,
        /**
         * The thread value, also known as the parallelism factor.
         */
        @Json(name = "threads")
        val threads: Int,
        /**
         * The key length, also known as the hash length.
         */
        @Json(name = "key_length")
        val keyLength: Int,
    )

@JsonClass(generateAdapter = true)
public data class Feedback
    @JvmOverloads
    constructor(
        /**
         * For `zxcvbn` validation, contains an end user consumable warning if the password is valid but not strong enough.
         */
        @Json(name = "warning")
        val warning: String,
        /**
         * For `zxcvbn` validation, contains end user consumable suggestions on how to improve the strength of the password.
         */
        @Json(name = "suggestions")
        val suggestions: List<String>,
        /**
         * Contains which LUDS properties are fulfilled by the password and which are missing to convert an invalid password into
         * a valid one. You'll use these fields to provide feedback to the user on how to improve the password.
         */
        @Json(name = "luds_requirements")
        val ludsRequirements: LUDSRequirements? = null,
    )

@JsonClass(generateAdapter = true)
public data class LUDSRequirements
    @JvmOverloads
    constructor(
        /**
         * For LUDS validation, whether the password contains at least one lowercase letter.
         */
        @Json(name = "has_lower_case")
        val hasLowerCase: Boolean,
        /**
         * For LUDS validation, whether the password contains at least one uppercase letter.
         */
        @Json(name = "has_upper_case")
        val hasUpperCase: Boolean,
        /**
         * For LUDS validation, whether the password contains at least one digit.
         */
        @Json(name = "has_digit")
        val hasDigit: Boolean,
        /**
         * For LUDS validation, whether the password contains at least one symbol. Any UTF8 character outside of a-z or A-Z may
         * count as a valid symbol.
         */
        @Json(name = "has_symbol")
        val hasSymbol: Boolean,
        /**
         * For LUDS validation, the number of complexity requirements that are missing from the password. Check the complexity
         * fields to see which requirements are missing.
         */
        @Json(name = "missing_complexity")
        val missingComplexity: Int,
        /**
         * For LUDS validation, this is the required length of the password that you've set minus the length of the password being
         * checked. The user will need to add this many characters to the password to make it valid.
         */
        @Json(name = "missing_characters")
        val missingCharacters: Int,
    )

@JsonClass(generateAdapter = true)
public data class MD5Config
    @JvmOverloads
    constructor(
        /**
         * The salt that should be prepended to the migrated password.
         */
        @Json(name = "prepend_salt")
        val prependSalt: String,
        /**
         * The salt that should be appended to the migrated password.
         */
        @Json(name = "append_salt")
        val appendSalt: String,
    )

@JsonClass(generateAdapter = true)
public data class PBKDF2Config
    @JvmOverloads
    constructor(
        /**
         * The salt value, which should be in a base64 encoded string form.
         */
        @Json(name = "salt")
        val salt: String,
        /**
         * The iteration amount.
         */
        @Json(name = "iteration_amount")
        val iterationAmount: Int,
        /**
         * The key length, also known as the hash length.
         */
        @Json(name = "key_length")
        val keyLength: Int,
    )

@JsonClass(generateAdapter = true)
public data class SHA1Config
    @JvmOverloads
    constructor(
        /**
         * The salt that should be prepended to the migrated password.
         */
        @Json(name = "prepend_salt")
        val prependSalt: String,
        /**
         * The salt that should be appended to the migrated password.
         */
        @Json(name = "append_salt")
        val appendSalt: String,
    )

@JsonClass(generateAdapter = true)
public data class ScryptConfig
    @JvmOverloads
    constructor(
        /**
         * The salt value, which should be in a base64 encoded string form.
         */
        @Json(name = "salt")
        val salt: String,
        /**
         * The N value, also known as the iterations count. It must be a power of two greater than 1 and less than 262,145.
         *       If your applicaiton's N parameter is larger than 262,144, please reach out to
         * [support@stytch.com](mailto:support@stytch.com)
         */
        @Json(name = "n_parameter")
        val nParameter: Int,
        /**
         * The r parameter, also known as the block size.
         */
        @Json(name = "r_parameter")
        val rParameter: Int,
        /**
         * The p parameter, also known as the parallelism factor.
         */
        @Json(name = "p_parameter")
        val pParameter: Int,
        /**
         * The key length, also known as the hash length.
         */
        @Json(name = "key_length")
        val keyLength: Int,
    )

/**
* Request type for `Passwords.authenticate`.
*/
@JsonClass(generateAdapter = true)
public data class AuthenticateRequest
    @JvmOverloads
    constructor(
        /**
         * The email address of the end user.
         */
        @Json(name = "email")
        val email: String,
        /**
         * The password of the user
         */
        @Json(name = "password")
        val password: String,
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
* Response type for `Passwords.authenticate`.
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
* Request type for `Passwords.create`.
*/
@JsonClass(generateAdapter = true)
public data class CreateRequest
    @JvmOverloads
    constructor(
        /**
         * The email address of the end user.
         */
        @Json(name = "email")
        val email: String,
        /**
         * The password of the user
         */
        @Json(name = "password")
        val password: String,
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
        /**
         * The `trusted_metadata` field contains an arbitrary JSON object of application-specific data. See the
         * [Metadata](https://stytch.com/docs/api/metadata) reference for complete field behavior details.
         */
        @Json(name = "trusted_metadata")
        val trustedMetadata: Map<String, Any?>? = emptyMap(),
        /**
         * The `untrusted_metadata` field contains an arbitrary JSON object of application-specific data. Untrusted metadata can
         * be edited by end users directly via the SDK, and **cannot be used to store critical information.** See the
         * [Metadata](https://stytch.com/docs/api/metadata) reference for complete field behavior details.
         */
        @Json(name = "untrusted_metadata")
        val untrustedMetadata: Map<String, Any?>? = emptyMap(),
        /**
         * The name of the user. Each field in the name object is optional.
         */
        @Json(name = "name")
        val name: Name? = null,
    )

/**
* Response type for `Passwords.create`.
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
         * The unique ID of the affected User.
         */
        @Json(name = "user_id")
        val userId: String,
        /**
         * The unique ID of a specific email address.
         */
        @Json(name = "email_id")
        val emailId: String,
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
* Request type for `Passwords.migrate`.
*/
@JsonClass(generateAdapter = true)
public data class MigrateRequest
    @JvmOverloads
    constructor(
        /**
         * The email address of the end user.
         */
        @Json(name = "email")
        val email: String,
        /**
         * The password hash. For a Scrypt or PBKDF2 hash, the hash needs to be a base64 encoded string.
         */
        @Json(name = "hash")
        val hash: String,
        /**
         * The password hash used. Currently `bcrypt`, `scrypt`, `argon_2i`, `argon_2id`, `md_5`, `sha_1`, and `pbkdf_2` are
         * supported.
         */
        @Json(name = "hash_type")
        val hashType: MigrateRequestHashType,
        /**
         * Optional parameters for MD-5 hash types.
         */
        @Json(name = "md_5_config")
        val md5Config: MD5Config? = null,
        /**
         * Required parameters if the argon2 hex form, as opposed to the encoded form, is supplied.
         */
        @Json(name = "argon_2_config")
        val argon2Config: Argon2Config? = null,
        /**
         * Optional parameters for SHA-1 hash types.
         */
        @Json(name = "sha_1_config")
        val sha1Config: SHA1Config? = null,
        /**
         * Required parameters if the scrypt is not provided in a
         * [PHC encoded form](https://github.com/P-H-C/phc-string-format/blob/master/phc-sf-spec.md#phc-string-format).
         */
        @Json(name = "scrypt_config")
        val scryptConfig: ScryptConfig? = null,
        /**
         * Required additional parameters for PBKDF2 hash keys.
         */
        @Json(name = "pbkdf_2_config")
        val pbkdf2Config: PBKDF2Config? = null,
        /**
         * The `trusted_metadata` field contains an arbitrary JSON object of application-specific data. See the
         * [Metadata](https://stytch.com/docs/api/metadata) reference for complete field behavior details.
         */
        @Json(name = "trusted_metadata")
        val trustedMetadata: Map<String, Any?>? = emptyMap(),
        /**
         * The `untrusted_metadata` field contains an arbitrary JSON object of application-specific data. Untrusted metadata can
         * be edited by end users directly via the SDK, and **cannot be used to store critical information.** See the
         * [Metadata](https://stytch.com/docs/api/metadata) reference for complete field behavior details.
         */
        @Json(name = "untrusted_metadata")
        val untrustedMetadata: Map<String, Any?>? = emptyMap(),
        /**
         * Whether to set the user's email as verified. This is a dangerous field. Incorrect use may lead to users getting
         * erroneously
         *                 deduplicated into one user object. This flag should only be set if you can attest that the user owns
         * the email address in question.
         *                 Access to this field is restricted. To enable it, please send us a note at support@stytch.com.
         */
        @Json(name = "set_email_verified")
        val setEmailVerified: Boolean? = null,
        /**
         * The name of the user. Each field in the name object is optional.
         */
        @Json(name = "name")
        val name: Name? = null,
    )

/**
* Response type for `Passwords.migrate`.
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
         * The unique ID of a specific email address.
         */
        @Json(name = "email_id")
        val emailId: String,
        /**
         * In `login_or_create` endpoints, this field indicates whether or not a User was just created.
         */
        @Json(name = "user_created")
        val userCreated: Boolean,
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

/**
* Request type for `Passwords.strengthCheck`.
*/
@JsonClass(generateAdapter = true)
public data class StrengthCheckRequest
    @JvmOverloads
    constructor(
        /**
         * The password of the user
         */
        @Json(name = "password")
        val password: String,
        /**
         * The email address of the end user.
         */
        @Json(name = "email")
        val email: String? = null,
    )

/**
* Response type for `Passwords.strengthCheck`.
*/
@JsonClass(generateAdapter = true)
public data class StrengthCheckResponse
    @JvmOverloads
    constructor(
        /**
         * Globally unique UUID that is returned with every API call. This value is important to log for debugging purposes; we
         * may ask for this value to help identify a specific API call when helping you debug an issue.
         */
        @Json(name = "request_id")
        val requestId: String,
        /**
         * Returns `true` if the password passes our password validation. We offer two validation options,
         * [zxcvbn](https://stytch.com/docs/passwords#strength-requirements) is the default option which offers a high level of
         * sophistication. We also offer [LUDS](https://stytch.com/docs/passwords#strength-requirements). If an email address is
         * included in the call we also require that the password hasn't been compromised using built-in breach detection powered
         * by [HaveIBeenPwned](https://haveibeenpwned.com/).
         */
        @Json(name = "valid_password")
        val validPassword: Boolean,
        /**
         * The score of the password determined by [zxcvbn](https://github.com/dropbox/zxcvbn). Values will be between 1 and 4, a
         * 3 or greater is required to pass validation.
         */
        @Json(name = "score")
        val score: Int,
        /**
         * Returns `true` if the password has been breached. Powered by [HaveIBeenPwned](https://haveibeenpwned.com/).
         */
        @Json(name = "breached_password")
        val breachedPassword: Boolean,
        /**
         * The strength policy type enforced, either `zxcvbn` or `luds`.
         */
        @Json(name = "strength_policy")
        val strengthPolicy: String,
        /**
         * Will return `true` if breach detection will be evaluated. By default this option is enabled. This option can be
         * disabled by contacting [support@stytch.com](mailto:support@stytch.com?subject=Password%20strength%20configuration). If
         * this value is `false` then `breached_password` will always be `false` as well.
         */
        @Json(name = "breach_detection_on_create")
        val breachDetectionOnCreate: Boolean,
        /**
         * The HTTP status code of the response. Stytch follows standard HTTP response status code patterns, e.g. 2XX values
         * equate to success, 3XX values are redirects, 4XX are client errors, and 5XX are server errors.
         */
        @Json(name = "status_code")
        val statusCode: Int,
        /**
         * Feedback for how to improve the password's strength [HaveIBeenPwned](https://haveibeenpwned.com/).
         */
        @Json(name = "feedback")
        val feedback: Feedback? = null,
    )
