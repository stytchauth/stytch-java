package com.stytch.java.common
public sealed class JWTException(
    public open val exception: Exception? = null,
    public open val reason: String? = null,
) : IllegalStateException() {
    public data class JwtError(override val exception: Exception) : JWTException(exception = exception)

    public data class JwtTooOld(val iat: Long, val maxTokenAgeSeconds: Int) : JWTException(
        reason = "JWT was issued at $iat, more than $maxTokenAgeSeconds seconds ago",
    )

    public object JwtMissingClaims : JWTException()

    public data class JwtMissingScopes(val missingScopes: List<String>) : JWTException(
        reason = "Missing required scopes: $missingScopes",
    )
}
