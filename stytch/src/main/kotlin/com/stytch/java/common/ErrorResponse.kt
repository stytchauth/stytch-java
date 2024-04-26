package com.stytch.java.common

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
public data class ErrorResponse(
    @Json(name = "status_code")
    val statusCode: Int,
    @Json(name = "request_id")
    val requestId: String?,
    @Json(name = "error_type")
    public val errorType: String,
    @Json(name = "error_message")
    public val errorMessage: String,
    @Json(name = "error_url")
    public val errorUrl: String,
)

@JsonClass(generateAdapter = true)
public data class OAuth2ErrorResponse(
    @Json(name = "status_code")
    val statusCode: Int,
    @Json(name = "request_id")
    val requestId: String?,
    @Json(name = "error")
    public val error: String,
    @Json(name = "error_description")
    public val errorDescription: String,
    @Json(name = "error_uri")
    public val errorUri: String,
) {
    internal fun toErrorResponse(): ErrorResponse =
        ErrorResponse(
            statusCode = statusCode,
            requestId = requestId,
            errorType = error,
            errorMessage = errorDescription,
            errorUrl = errorUri,
        )
}
