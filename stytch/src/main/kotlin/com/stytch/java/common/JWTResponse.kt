package com.stytch.java.common

import com.stytch.java.consumer.models.sessions.AuthenticateResponse
import com.stytch.java.consumer.models.sessions.Session

public sealed interface JWTResponse

public object JWTNullResponse : JWTResponse

public data class JWTSessionResponse(val response: Session?) : JWTResponse

public data class JWTAuthResponse(val response: AuthenticateResponse) : JWTResponse

public data class JWTErrorResponse(
    val statusCode: Int,
    val requestId: String?,
    val errorType: String,
    val errorMessage: String,
    val errorUrl: String,
) : JWTResponse
