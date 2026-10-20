package com.stytch.java.common

import com.stytch.java.consumer.models.sessions.AuthenticateResponse
import com.stytch.java.consumer.models.sessions.Session

public sealed interface JWTResponse

@Deprecated("No longer returned; authenticateJwt reports failures as StytchResult.Error.")
public object JWTNullResponse : JWTResponse

public data class JWTSessionResponse(
    val response: Session?,
) : JWTResponse

public data class JWTAuthResponse(
    val response: AuthenticateResponse,
) : JWTResponse

@Deprecated("No longer returned; authenticateJwt reports failures as StytchResult.Error.")
public data class JWTErrorResponse(
    val statusCode: Int,
    val requestId: String?,
    val errorType: String,
    val errorMessage: String,
    val errorUrl: String,
) : JWTResponse
