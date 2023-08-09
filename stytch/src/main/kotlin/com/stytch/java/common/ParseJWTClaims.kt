package com.stytch.java.common

import org.jose4j.jwk.HttpsJwks
import org.jose4j.jwt.JwtClaims
import org.jose4j.jwt.consumer.JwtConsumerBuilder
import java.util.Date

internal data class ParsedJWTClaims(
    val payload: JwtClaims,
    val customClaims: Map<String, Any> = emptyMap(),
)

private val IGNORED_CLAIMS = listOf("aud", "exp", "iat", "iss", "jti", "nbf", "sub")

internal fun parseJWTClaims(
    jwt: String,
    jwtOptions: JwtOptions,
    jwksClient: HttpsJwks,
    options: ParseJWTClaimsOptions,
): ParsedJWTClaims {
    val now = Date().time
    val jwtConsumer = JwtConsumerBuilder().apply {
        setRequireExpirationTime()
        setAllowedClockSkewInSeconds(options.leeway)
        setRequireSubject()
        setExpectedIssuer(jwtOptions.issuer)
        setExpectedSubject(jwtOptions.audience)
        setVerificationKey(jwksClient.jsonWebKeys[0].key)
    }.build()
    val jwtClaims = jwtConsumer.processToClaims(jwt)
    if (options.maxTokenAgeSeconds != null) {
        val iat = jwtClaims.issuedAt
        if ((now - iat.valueInMillis) / 1000 >= options.maxTokenAgeSeconds) {
            throw JWTException.JwtTooOld(
                iat = iat.valueInMillis / 1000,
                maxTokenAgeSeconds = options.maxTokenAgeSeconds,
            )
        }
    }
    val customClaims = jwtClaims.claimsMap.filterNot {
        IGNORED_CLAIMS.contains(it.key)
    }
    return ParsedJWTClaims(
        payload = jwtClaims,
        customClaims = customClaims,
    )
}
