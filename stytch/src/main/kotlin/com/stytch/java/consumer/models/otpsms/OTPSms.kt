package com.stytch.java.consumer.models.otpsms

// !!!
// WARNING: This file is autogenerated
// Only modify code within MANUAL() sections
// or your changes may be overwritten later!
// !!!

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.stytch.java.consumer.models.attribute.Attributes

@JsonClass(generateAdapter = false)
public enum class LoginOrCreateRequestLocale {
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

    @Json(name = "deDE")
    DEDE,

    @Json(name = "zhHans")
    ZHHANS,

    @Json(name = "caES")
    CAES,
}

@JsonClass(generateAdapter = false)
public enum class SendRequestLocale {
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

    @Json(name = "deDE")
    DEDE,

    @Json(name = "zhHans")
    ZHHANS,

    @Json(name = "caES")
    CAES,
}

/**
* Request type for `Sms.loginOrCreate`.
*/
@JsonClass(generateAdapter = true)
public data class LoginOrCreateRequest
    @JvmOverloads
    constructor(
        /**
         * The phone number to use for one-time passcodes. The phone number should be in E.164 format (i.e. +1XXXXXXXXXX). You may
         * use +10000000000 to test this endpoint, see [Testing](https://stytch.com/docs/home#resources_testing) for more detail.
         */
        @Json(name = "phone_number")
        val phoneNumber: String,
        /**
         * Set the expiration for the one-time passcode, in minutes. The minimum expiration is 1 minute and the maximum is 10
         * minutes. The default expiration is 2 minutes.
         */
        @Json(name = "expiration_minutes")
        val expirationMinutes: Int? = null,
        /**
         * Provided attributes help with fraud detection.
         */
        @Json(name = "attributes")
        val attributes: Attributes? = null,
        /**
         * Flag for whether or not to save a user as pending vs active in Stytch. Defaults to false.
         *         If true, users will be saved with status pending in Stytch's backend until authenticated.
         *         If false, users will be created as active. An example usage of
         *         a true flag would be to require users to verify their phone by entering the OTP code before creating
         *         an account for them.
         */
        @Json(name = "create_user_as_pending")
        val createUserAsPending: Boolean? = null,
        /**
         * Used to determine which language to use when sending the user this delivery method. Parameter is a
         * [IETF BCP 47 language tag](https://www.w3.org/International/articles/language-tags/), e.g. `"en"`.
         *
         * Currently supported languages are English (`"en"`), Spanish (`"es"`), French (`"fr"`) and Brazilian Portuguese
         * (`"pt-br"`); if no value is provided, the copy defaults to English.
         *
         * Request support for additional languages
         * [here](https://docs.google.com/forms/d/e/1FAIpQLScZSpAu_m2AmLXRT3F3kap-s_mcV6UTBitYn6CdyWP0-o7YjQ/viewform?usp=sf_link")!
         *
         */
        @Json(name = "locale")
        val locale: LoginOrCreateRequestLocale? = null,
    )

/**
* Response type for `Sms.loginOrCreate`.
*/
@JsonClass(generateAdapter = true)
public data class LoginOrCreateResponse
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
         * The unique ID for the phone number.
         */
        @Json(name = "phone_id")
        val phoneId: String,
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

/**
* Request type for `Sms.send`.
*/
@JsonClass(generateAdapter = true)
public data class SendRequest
    @JvmOverloads
    constructor(
        /**
         * The phone number to use for one-time passcodes. The phone number should be in E.164 format (i.e. +1XXXXXXXXXX). You may
         * use +10000000000 to test this endpoint, see [Testing](https://stytch.com/docs/home#resources_testing) for more detail.
         */
        @Json(name = "phone_number")
        val phoneNumber: String,
        /**
         * Set the expiration for the one-time passcode, in minutes. The minimum expiration is 1 minute and the maximum is 10
         * minutes. The default expiration is 2 minutes.
         */
        @Json(name = "expiration_minutes")
        val expirationMinutes: Int? = null,
        /**
         * Provided attributes help with fraud detection.
         */
        @Json(name = "attributes")
        val attributes: Attributes? = null,
        /**
         * Used to determine which language to use when sending the user this delivery method. Parameter is a
         * [IETF BCP 47 language tag](https://www.w3.org/International/articles/language-tags/), e.g. `"en"`.
         *
         * Currently supported languages are English (`"en"`), Spanish (`"es"`), French (`"fr"`) and Brazilian Portuguese
         * (`"pt-br"`); if no value is provided, the copy defaults to English.
         *
         * Request support for additional languages
         * [here](https://docs.google.com/forms/d/e/1FAIpQLScZSpAu_m2AmLXRT3F3kap-s_mcV6UTBitYn6CdyWP0-o7YjQ/viewform?usp=sf_link")!
         *
         */
        @Json(name = "locale")
        val locale: SendRequestLocale? = null,
        /**
         * The unique ID of a specific User. You may use an external_id here if one is set for the user.
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
* Response type for `Sms.send`.
*/
@JsonClass(generateAdapter = true)
public data class SendResponse
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
         * The unique ID for the phone number.
         */
        @Json(name = "phone_id")
        val phoneId: String,
        /**
         * The HTTP status code of the response. Stytch follows standard HTTP response status code patterns, e.g. 2XX values
         * equate to success, 3XX values are redirects, 4XX are client errors, and 5XX are server errors.
         */
        @Json(name = "status_code")
        val statusCode: Int,
    )
