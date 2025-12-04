package com.stytch.java.consumer.api.m2m

import com.squareup.moshi.Moshi
import com.stytch.java.common.InstantAdapter
import com.stytch.java.common.JWTException
import com.stytch.java.common.JwtOptions
import com.stytch.java.common.StytchResult
import com.stytch.java.consumer.models.m2m.AuthenticateTokenRequest
import com.stytch.java.consumer.models.m2m.TokenRequest
import com.stytch.java.http.HttpClient
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.jose4j.jwk.HttpsJwks
import org.jose4j.jwk.JsonWebKey
import org.jose4j.jwk.RsaJwkGenerator
import org.jose4j.jws.AlgorithmIdentifiers
import org.jose4j.jws.JsonWebSignature
import org.jose4j.jwt.JwtClaims
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
internal class M2MTest {
    private lateinit var m2m: M2M
    private lateinit var jwksClient: HttpsJwks
    private lateinit var jwtNoScopes: String
    private lateinit var jwtWithScopes: String
    private val projectId = "project-test-00000000-0000-0000-0000-000000000000"
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @MockK
    private lateinit var mockOkhttpClient: OkHttpClient

    @Before
    fun before() {
        MockKAnnotations.init(this, true, true)
        Dispatchers.setMain(mainThreadSurrogate)
        val rsaJsonWebKey = RsaJwkGenerator.generateJwk(2048)
        val jsonWebKey = JsonWebKey.Factory.newJwk(rsaJsonWebKey.publicKey)
        jsonWebKey.keyId = "k1"
        jwksClient =
            mockk {
                every { location } returns ""
                every { jsonWebKeys } returns listOf(jsonWebKey)
            }
        jwtNoScopes =
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
        jwtWithScopes =
            JsonWebSignature()
                .apply {
                    payload =
                        JwtClaims()
                            .apply {
                                issuer = "stytch.com/$projectId"
                                audience = listOf(projectId)
                                subject = "user-live-fde03dd1-fff7-4b3c-9b31-ead3fbc224de"
                                setClaim("scope", "read:user write:user")
                                setExpirationTimeMinutesInTheFuture(5F)
                                setGeneratedJwtId()
                                setIssuedAtToNow()
                                setNotBeforeMinutesInThePast(0F)
                            }.toJson()
                    key = rsaJsonWebKey.privateKey
                    keyIdHeaderValue = rsaJsonWebKey.keyId
                    algorithmHeaderValue = AlgorithmIdentifiers.RSA_USING_SHA256
                }.compactSerialization
        m2m =
            M2MImpl(
                httpClient =
                    HttpClient(
                        baseUrl = "http://test",
                        projectId = "",
                        secret = "",
                        client = mockOkhttpClient,
                    ),
                coroutineScope = CoroutineScope(mainThreadSurrogate),
                jwksClient = jwksClient,
                jwtOptions =
                    JwtOptions(
                        audience = projectId,
                        issuers = listOf("stytch.com/$projectId", "http://test"),
                        type = "JWT",
                    ),
            )
    }

    @Test
    fun `authenticateToken returns JwtMissingAction exception when required action is missing`() =
        runTest {
            val result =
                m2m.authenticateToken(
                    AuthenticateTokenRequest(
                        accessToken = jwtWithScopes,
                        requiredScopes = listOf("delete:user"),
                        maxTokenAgeSeconds = 5000,
                    ),
                )
            require(result is StytchResult.Error)
            assert(result.exception.reason is JWTException.JwtMissingAction)
        }

    @Test
    fun `authenticateToken returns JwtMissingAction exception when required scopes are missing`() =
        runTest {
            val result =
                m2m.authenticateToken(
                    AuthenticateTokenRequest(
                        accessToken = jwtNoScopes,
                        requiredScopes = listOf("read:user", "write:user"),
                        maxTokenAgeSeconds = 5000,
                    ),
                )
            require(result is StytchResult.Error)
            assert(result.exception.reason is JWTException.JwtMissingAction)
        }

    @Test
    fun `authenticateToken returns expected AuthenticateTokenResponse data when successful`() =
        runTest {
            val result =
                m2m.authenticateToken(
                    AuthenticateTokenRequest(
                        accessToken = jwtWithScopes,
                        requiredScopes = listOf("read:user", "write:user"),
                        maxTokenAgeSeconds = 5000,
                    ),
                )
            require(result is StytchResult.Success)
        }

    @Test
    fun `token makes correct call to httpClient`() =
        runTest {
            val moshi = Moshi.Builder().add(InstantAdapter()).build()
            val callbackSlot = slot<Callback>()
            val requestSlot = slot<Request>()
            val mockCall: Call =
                mockk {
                    every { enqueue(capture(callbackSlot)) } answers {
                        callbackSlot.captured.onResponse(mockk(), mockk())
                    }
                }
            every { mockOkhttpClient.newCall(capture(requestSlot)) } returns mockCall
            val params =
                TokenRequest(
                    clientId = "client-id",
                    clientSecret = "client-secret",
                    scopes = listOf("read:user", "write:user"),
                )
            val expectedMediaType = "application/x-www-form-urlencoded; charset=utf-8".toMediaType()
            val expectedPayload =
                mapOf(
                    "client_id" to params.clientId,
                    "client_secret" to params.clientSecret,
                    "grant_type" to "client_credentials",
                    "scope" to params.scopes?.joinToString(" "),
                )
            val payload = moshi.adapter(Map::class.java).toJson(expectedPayload)
            val expectedRequest =
                Request
                    .Builder()
                    .url("http://test/v1/public/$projectId/oauth2/token")
                    .post(payload.toRequestBody(expectedMediaType))
                    .build()
            m2m.token(params)
            assert(requestSlot.captured.toString() == expectedRequest.toString())
        }
}
