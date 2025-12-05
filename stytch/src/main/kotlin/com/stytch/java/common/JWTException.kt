package com.stytch.java.common

public sealed class JWTException(
    public open val exception: Exception? = null,
    public open val reason: String? = null,
) : IllegalStateException() {
    public data class JwtError(
        override val exception: Exception,
    ) : JWTException(exception = exception)

    public data class JwtTooOld(
        val iat: Long,
        val maxTokenAgeSeconds: Int,
    ) : JWTException(
            reason = "JWT was issued at $iat, more than $maxTokenAgeSeconds seconds ago",
        )

    public object JwtMissingClaims : JWTException()

    public object MissingRolesClaim : JWTException(reason = "Missing roles claim field")

    public data class JwtMissingAction(
        val action: String,
    ) : JWTException(
            reason = "Missing required action: $action",
        )

    public data class JwtMissingScope(
        val missingScope: String,
    ) : JWTException(
            reason = "Missing required scope: $missingScope",
        )
}
