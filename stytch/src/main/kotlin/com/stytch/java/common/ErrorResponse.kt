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
