package com.stytch.java.common

import io.mockk.every
import io.mockk.mockk
import org.jose4j.jwk.HttpsJwks
import org.jose4j.jwk.JsonWebKey
import org.jose4j.jwk.RsaJwkGenerator
import org.jose4j.jws.AlgorithmIdentifiers
import org.jose4j.jws.JsonWebSignature
import org.jose4j.jwt.JwtClaims
import org.junit.Before
import org.junit.Test
import java.time.Instant
import java.time.format.DateTimeFormatter.ISO_INSTANT

internal class ParseJWTClaimsTest {
    private lateinit var jwksClient: HttpsJwks
    private lateinit var jwt: String
    private val projectId = "project-test-00000000-0000-0000-0000-000000000000"

    @Before
    fun before() {
        val rsaJsonWebKey = RsaJwkGenerator.generateJwk(2048)
        val jsonWebKey = JsonWebKey.Factory.newJwk(rsaJsonWebKey.publicKey)
        jsonWebKey.keyId = "k1"
        jwksClient = mockk(relaxed=true, relaxUnitFun = true) {
            every { jsonWebKeys } returns listOf(jsonWebKey)
        }
        val now = Instant.now()
        val startedAt = ISO_INSTANT.format(now)
        val expiresAt = ISO_INSTANT.format(now.plusSeconds(5 * 60))
        val sessionClaim = mapOf(
            "started_at" to startedAt,
            "last_accessed_at" to startedAt,
            "expires_at" to expiresAt,
            "attributes" to mapOf(
                "user_agent" to "",
                "ip_address" to "",
            ),
            "id" to "session-live-e26a0ccb-0dc0-4edb-a4bb-e70210f43555",
            "authentication_factors" to listOf(
                mapOf(
                    "delivery_method" to "email",
                    "email_factor" to mapOf(
                        "email_address" to "sandbox@stytch.com",
                        "email_id" to "email-live-cca9d7d0-11b6-4167-9385-d7e0c9a77418",
                    ),
                    "last_authenticated_at" to startedAt,
                    "type" to "magic_link",
                ),
            ),
        )
        val claims = JwtClaims().apply {
            issuer = "stytch.com/$projectId"
            audience = listOf(projectId)
            subject = "user-live-fde03dd1-fff7-4b3c-9b31-ead3fbc224de"
            setClaim("https://stytch.com/session", sessionClaim)
            setExpirationTimeMinutesInTheFuture(5F)
            setGeneratedJwtId()
            setIssuedAtToNow()
            setNotBeforeMinutesInThePast(0F)
        }
        val jws = JsonWebSignature().apply {
            payload = claims.toJson()
            key = rsaJsonWebKey.privateKey
            keyIdHeaderValue = rsaJsonWebKey.keyId
            algorithmHeaderValue = AlgorithmIdentifiers.RSA_USING_SHA256
        }
        jwt = jws.compactSerialization
    }

    @Test(expected = JWTException.JwtTooOld::class)
    fun `throws JwtTooOld when age is greater than maxTokenAgeSeconds`() {
        parseJWTClaims(
            jwt = jwt,
            jwtOptions = JwtOptions(
                audience = projectId,
                issuer = "stytch.com/$projectId",
                type = "JWT",
            ),
            jwksClient = jwksClient,
            options = ParseJWTClaimsOptions(leeway = 0, maxTokenAgeSeconds = 0),
        )
    }

    @Test
    fun `strips out ignored claims when returning successfully`() {
        val claims = parseJWTClaims(
            jwt = jwt,
            jwtOptions = JwtOptions(
                audience = projectId,
                issuer = "stytch.com/$projectId",
                type = "JWT",
            ),
            jwksClient = jwksClient,
            options = ParseJWTClaimsOptions(leeway = 0, maxTokenAgeSeconds = 10000),
        )
        val hasStrippedClaims = claims.customClaims.keys.any {
            IGNORED_CLAIMS.contains(it)
        }
        assert(!hasStrippedClaims)
    }
}
