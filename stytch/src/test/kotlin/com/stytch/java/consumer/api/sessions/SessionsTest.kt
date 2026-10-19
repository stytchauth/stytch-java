package com.stytch.java.consumer.api.sessions

import com.stytch.java.common.JWTException
import com.stytch.java.common.JwtOptions
import com.stytch.java.common.StytchException
import com.stytch.java.common.StytchResult
import com.stytch.java.http.HttpClient
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.jose4j.jwk.HttpsJwks
import org.jose4j.jwk.JsonWebKey
import org.jose4j.jwk.RsaJwkGenerator
import org.jose4j.jws.AlgorithmIdentifiers
import org.jose4j.jws.JsonWebSignature
import org.jose4j.jwt.JwtClaims
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.time.Instant
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
internal class SessionsTest {
    private lateinit var sessions: Sessions
    private lateinit var jwksClient: HttpsJwks
    private lateinit var jwtNoClaims: String
    private lateinit var jwtWithClaims: String
    private val projectId = "project-test-00000000-0000-0000-0000-000000000000"
    private lateinit var server: MockWebServer
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun before() {
        server = MockWebServer().apply { start() }
        Dispatchers.setMain(mainThreadSurrogate)
        val rsaJsonWebKey = RsaJwkGenerator.generateJwk(2048)
        val jsonWebKey = JsonWebKey.Factory.newJwk(rsaJsonWebKey.publicKey)
        jsonWebKey.keyId = "k1"
        jwksClient =
            mockk {
                every { location } returns ""
                every { jsonWebKeys } returns listOf(jsonWebKey)
            }
        val now = Instant.now()
        val startedAt = DateTimeFormatter.ISO_INSTANT.format(now)
        val expiresAt = DateTimeFormatter.ISO_INSTANT.format(now.plusSeconds(5 * 60))
        val sessionClaim =
            mapOf(
                "started_at" to startedAt,
                "last_accessed_at" to startedAt,
                "expires_at" to expiresAt,
                "attributes" to
                    mapOf(
                        "user_agent" to "",
                        "ip_address" to "",
                    ),
                "id" to "session-live-e26a0ccb-0dc0-4edb-a4bb-e70210f43555",
                "authentication_factors" to
                    listOf(
                        mapOf(
                            "delivery_method" to "email",
                            "email_factor" to
                                mapOf(
                                    "email_address" to "sandbox@stytch.com",
                                    "email_id" to "email-live-cca9d7d0-11b6-4167-9385-d7e0c9a77418",
                                ),
                            "last_authenticated_at" to startedAt,
                            "type" to "magic_link",
                        ),
                    ),
            )
        jwtNoClaims =
            JsonWebSignature()
                .apply {
                    payload =
                        JwtClaims()
                            .apply {
                                issuer = "stytch.com/$projectId"
                                audience = listOf(projectId)
                                subject = "user-live-fde03dd1-fff7-4b3c-9b31-ead3fbc224de"
                                setExpirationTimeMinutesInTheFuture(5F)
                                setGeneratedJwtId()
                                setIssuedAtToNow()
                                setNotBeforeMinutesInThePast(0F)
                            }.toJson()
                    key = rsaJsonWebKey.privateKey
                    keyIdHeaderValue = rsaJsonWebKey.keyId
                    algorithmHeaderValue = AlgorithmIdentifiers.RSA_USING_SHA256
                }.compactSerialization
        jwtWithClaims =
            JsonWebSignature()
                .apply {
                    payload =
                        JwtClaims()
                            .apply {
                                issuer = "stytch.com/$projectId"
                                audience = listOf(projectId)
                                subject = "user-live-fde03dd1-fff7-4b3c-9b31-ead3fbc224de"
                                setClaim("https://stytch.com/session", sessionClaim)
                                setExpirationTimeMinutesInTheFuture(5F)
                                setGeneratedJwtId()
                                setIssuedAtToNow()
                                setNotBeforeMinutesInThePast(0F)
                            }.toJson()
                    key = rsaJsonWebKey.privateKey
                    keyIdHeaderValue = rsaJsonWebKey.keyId
                    algorithmHeaderValue = AlgorithmIdentifiers.RSA_USING_SHA256
                }.compactSerialization
        sessions =
            SessionsImpl(
                httpClient = HttpClient(server.url("").toString().trimEnd('/'), projectId, "secret"),
                coroutineScope = CoroutineScope(mainThreadSurrogate),
                jwksClient = jwksClient,
                jwtOptions =
                    JwtOptions(
                        audience = projectId,
                        issuers = listOf("stytch.com/$projectId"),
                        type = "JWT",
                    ),
                policyCache = mockk(relaxed = true, relaxUnitFun = true),
            )
    }

    @Test
    fun `authenticateJwtLocal returns JwtMissingClaims exception when session claim is missing`() =
        runTest {
            val result = sessions.authenticateJwtLocal(jwtNoClaims, 50000)
            require(result is StytchResult.Error)
            assert(result.exception.reason is JWTException.JwtMissingClaims)
        }

    @Test
    fun `authenticateJwtLocal returns expected Session data when successful`() =
        runTest {
            val result = sessions.authenticateJwtLocal(jwtWithClaims, 50000)
            require(result is StytchResult.Success)
        }

    @After
    fun after() {
        server.shutdown()
    }

    @Test
    fun `authenticateJwt returns Error when the API rejects the JWT`() =
        runTest {
            server.enqueue(
                MockResponse().setResponseCode(401).setBody(
                    """{"status_code":401,"request_id":"req","error_type":"session_not_found","error_message":"nope","error_url":"url"}""",
                ),
            )
            val result = sessions.authenticateJwt("aaa.bbb.ccc", null)
            require(result is StytchResult.Error)
            assert(result.exception is StytchException.Response)
        }

    @Test
    fun `authenticateJwt returns Error when the API error response cannot be parsed`() =
        runTest {
            server.enqueue(MockResponse().setResponseCode(500).setBody("upstream unavailable"))
            val result = sessions.authenticateJwt("aaa.bbb.ccc", null)
            require(result is StytchResult.Error)
            assert(result.exception is StytchException.Critical)
        }
}
