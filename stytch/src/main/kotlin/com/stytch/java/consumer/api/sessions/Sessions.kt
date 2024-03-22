package com.stytch.java.consumer.api.sessions

// !!!
// WARNING: This file is autogenerated
// Only modify code within MANUAL() sections
// or your changes may be overwritten later!
// !!!

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.stytch.java.common.InstantAdapter
import com.stytch.java.common.JWTAuthResponse
import com.stytch.java.common.JWTException
import com.stytch.java.common.JWTNullResponse
import com.stytch.java.common.JWTResponse
import com.stytch.java.common.JWTSessionResponse
import com.stytch.java.common.JwtOptions
import com.stytch.java.common.ParseJWTClaimsOptions
import com.stytch.java.common.StytchException
import com.stytch.java.common.StytchResult
import com.stytch.java.common.StytchSessionClaim
import com.stytch.java.common.parseJWTClaims
import com.stytch.java.consumer.models.sessions.AuthenticateRequest
import com.stytch.java.consumer.models.sessions.AuthenticateResponse
import com.stytch.java.consumer.models.sessions.GetJWKSRequest
import com.stytch.java.consumer.models.sessions.GetJWKSResponse
import com.stytch.java.consumer.models.sessions.GetRequest
import com.stytch.java.consumer.models.sessions.GetResponse
import com.stytch.java.consumer.models.sessions.RevokeRequest
import com.stytch.java.consumer.models.sessions.RevokeResponse
import com.stytch.java.consumer.models.sessions.Session
import com.stytch.java.http.HttpClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.future.asCompletableFuture
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jose4j.jwk.HttpsJwks
import java.time.Instant
import java.util.concurrent.CompletableFuture

public interface Sessions {
    /**
     * List all active Sessions for a given `user_id`. All timestamps are formatted according to the RFC 3339 standard and are
     * expressed in UTC, e.g. `2021-12-29T12:33:09Z`.
     */
    public suspend fun get(data: GetRequest): StytchResult<GetResponse>

    /**
     * List all active Sessions for a given `user_id`. All timestamps are formatted according to the RFC 3339 standard and are
     * expressed in UTC, e.g. `2021-12-29T12:33:09Z`.
     */
    public fun get(
        data: GetRequest,
        callback: (StytchResult<GetResponse>) -> Unit,
    )

    /**
     * List all active Sessions for a given `user_id`. All timestamps are formatted according to the RFC 3339 standard and are
     * expressed in UTC, e.g. `2021-12-29T12:33:09Z`.
     */
    public fun getCompletable(data: GetRequest): CompletableFuture<StytchResult<GetResponse>>

    /**
     * Authenticate a session token and retrieve associated session data. If `session_duration_minutes` is included, update
     * the lifetime of the session to be that many minutes from now. All timestamps are formatted according to the RFC 3339
     * standard and are expressed in UTC, e.g. `2021-12-29T12:33:09Z`. This endpoint requires exactly one `session_jwt` or
     * `session_token` as part of the request. If both are included you will receive a `too_many_session_arguments` error.
     */
    public suspend fun authenticate(data: AuthenticateRequest): StytchResult<AuthenticateResponse>

    /**
     * Authenticate a session token and retrieve associated session data. If `session_duration_minutes` is included, update
     * the lifetime of the session to be that many minutes from now. All timestamps are formatted according to the RFC 3339
     * standard and are expressed in UTC, e.g. `2021-12-29T12:33:09Z`. This endpoint requires exactly one `session_jwt` or
     * `session_token` as part of the request. If both are included you will receive a `too_many_session_arguments` error.
     */
    public fun authenticate(
        data: AuthenticateRequest,
        callback: (StytchResult<AuthenticateResponse>) -> Unit,
    )

    /**
     * Authenticate a session token and retrieve associated session data. If `session_duration_minutes` is included, update
     * the lifetime of the session to be that many minutes from now. All timestamps are formatted according to the RFC 3339
     * standard and are expressed in UTC, e.g. `2021-12-29T12:33:09Z`. This endpoint requires exactly one `session_jwt` or
     * `session_token` as part of the request. If both are included you will receive a `too_many_session_arguments` error.
     */
    public fun authenticateCompletable(data: AuthenticateRequest): CompletableFuture<StytchResult<AuthenticateResponse>>

    /**
     * Revoke a Session, immediately invalidating all of its session tokens. You can revoke a session in three ways: using its
     * ID, or using one of its session tokens, or one of its JWTs. This endpoint requires exactly one of those to be included
     * in the request. It will return an error if multiple are present.
     */
    public suspend fun revoke(data: RevokeRequest): StytchResult<RevokeResponse>

    /**
     * Revoke a Session, immediately invalidating all of its session tokens. You can revoke a session in three ways: using its
     * ID, or using one of its session tokens, or one of its JWTs. This endpoint requires exactly one of those to be included
     * in the request. It will return an error if multiple are present.
     */
    public fun revoke(
        data: RevokeRequest,
        callback: (StytchResult<RevokeResponse>) -> Unit,
    )

    /**
     * Revoke a Session, immediately invalidating all of its session tokens. You can revoke a session in three ways: using its
     * ID, or using one of its session tokens, or one of its JWTs. This endpoint requires exactly one of those to be included
     * in the request. It will return an error if multiple are present.
     */
    public fun revokeCompletable(data: RevokeRequest): CompletableFuture<StytchResult<RevokeResponse>>

    /**
     * Get the JSON Web Key Set (JWKS) for a project.
     *
     * JWKS are rotated every ~6 months. Upon rotation, new JWTs will be signed using the new key set, and both key sets will
     * be returned by this endpoint for a period of 1 month.
     *
     * JWTs have a set lifetime of 5 minutes, so there will be a 5 minute period where some JWTs will be signed by the old
     * JWKS, and some JWTs will be signed by the new JWKS. The correct JWKS to use for validation is determined by matching
     * the `kid` value of the JWT and JWKS.
     *
     * If you're using one of our [backend SDKs](https://stytch.com/docs/sdks), the JWKS roll will be handled for you.
     *
     * If you're using your own JWT validation library, many have built-in support for JWKS rotation, and you'll just need to
     * supply this API endpoint. If not, your application should decide which JWKS to use for validation by inspecting the
     * `kid` value.
     */
    public suspend fun getJWKS(data: GetJWKSRequest): StytchResult<GetJWKSResponse>

    /**
     * Get the JSON Web Key Set (JWKS) for a project.
     *
     * JWKS are rotated every ~6 months. Upon rotation, new JWTs will be signed using the new key set, and both key sets will
     * be returned by this endpoint for a period of 1 month.
     *
     * JWTs have a set lifetime of 5 minutes, so there will be a 5 minute period where some JWTs will be signed by the old
     * JWKS, and some JWTs will be signed by the new JWKS. The correct JWKS to use for validation is determined by matching
     * the `kid` value of the JWT and JWKS.
     *
     * If you're using one of our [backend SDKs](https://stytch.com/docs/sdks), the JWKS roll will be handled for you.
     *
     * If you're using your own JWT validation library, many have built-in support for JWKS rotation, and you'll just need to
     * supply this API endpoint. If not, your application should decide which JWKS to use for validation by inspecting the
     * `kid` value.
     */
    public fun getJWKS(
        data: GetJWKSRequest,
        callback: (StytchResult<GetJWKSResponse>) -> Unit,
    )

    /**
     * Get the JSON Web Key Set (JWKS) for a project.
     *
     * JWKS are rotated every ~6 months. Upon rotation, new JWTs will be signed using the new key set, and both key sets will
     * be returned by this endpoint for a period of 1 month.
     *
     * JWTs have a set lifetime of 5 minutes, so there will be a 5 minute period where some JWTs will be signed by the old
     * JWKS, and some JWTs will be signed by the new JWKS. The correct JWKS to use for validation is determined by matching
     * the `kid` value of the JWT and JWKS.
     *
     * If you're using one of our [backend SDKs](https://stytch.com/docs/sdks), the JWKS roll will be handled for you.
     *
     * If you're using your own JWT validation library, many have built-in support for JWKS rotation, and you'll just need to
     * supply this API endpoint. If not, your application should decide which JWKS to use for validation by inspecting the
     * `kid` value.
     */
    public fun getJWKSCompletable(data: GetJWKSRequest): CompletableFuture<StytchResult<GetJWKSResponse>>

    // MANUAL(authenticateJWT_interface)(INTERFACE_METHOD)
    // ADDIMPORT: import com.stytch.java.consumer.models.sessions.Session
    // ADDIMPORT: import com.stytch.java.common.JWTException
    // ADDIMPORT: import com.stytch.java.common.ParseJWTClaimsOptions
    // ADDIMPORT: import com.stytch.java.common.StytchSessionClaim
    // ADDIMPORT: import com.stytch.java.common.parseJWTClaims
    // ADDIMPORT: import com.stytch.java.common.ParsedJWTClaims
    // ADDIMPORT: import com.stytch.java.common.JWTResponse
    // ADDIMPORT: import com.stytch.java.common.JWTAuthResponse
    // ADDIMPORT: import com.stytch.java.common.JWTNullResponse
    // ADDIMPORT: import com.stytch.java.common.JWTSessionResponse

    /** Parse a JWT and verify the signature, preferring local verification over remote.
     *
     * If maxTokenAgeSeconds is set, remote verification will be forced if the JWT was issued at
     * (based on the "iat" claim) more than that many seconds ago.
     *
     * To force remote validation for all tokens, set maxTokenAgeSeconds to zero or use the
     * authenticate method instead.
     */
    public suspend fun authenticateJwt(
        jwt: String,
        maxTokenAgeSeconds: Int?,
    ): StytchResult<JWTResponse>

    /** Parse a JWT and verify the signature, preferring local verification over remote.
     *
     * If maxTokenAgeSeconds is set, remote verification will be forced if the JWT was issued at
     * (based on the "iat" claim) more than that many seconds ago.
     *
     * To force remote validation for all tokens, set maxTokenAgeSeconds to zero or use the
     * authenticate method instead.
     */
    public fun authenticateJwt(
        jwt: String,
        maxTokenAgeSeconds: Int?,
        callback: (StytchResult<JWTResponse>) -> Unit,
    )

    /** Parse a JWT and verify the signature, preferring local verification over remote.
     *
     * If maxTokenAgeSeconds is set, remote verification will be forced if the JWT was issued at
     * (based on the "iat" claim) more than that many seconds ago.
     *
     * To force remote validation for all tokens, set maxTokenAgeSeconds to zero or use the
     * authenticate method instead.
     */
    public fun authenticateJwtCompletable(
        jwt: String,
        maxTokenAgeSeconds: Int?,
    ): CompletableFuture<StytchResult<JWTResponse>>

    /** Parse a JWT and verify the signature locally (without calling /authenticate in the API).
     *
     * If maxTokenAgeSeconds is set, this will return an error if the JWT was issued (based on the "iat"
     * claim) more than maxTokenAge seconds ago.
     *
     * If maxTokenAgeSeconds is explicitly set to zero, all tokens will be considered too old,
     * even if they are otherwise valid.
     *
     * The value for leeway is the maximum allowable difference when comparing
     * timestamps. It defaults to zero.
     */
    public suspend fun authenticateJwtLocal(
        jwt: String,
        maxTokenAgeSeconds: Int?,
        leeway: Int = 0,
    ): StytchResult<Session?>

    /** Parse a JWT and verify the signature locally (without calling /authenticate in the API).
     *
     * If maxTokenAgeSeconds is set, this will return an error if the JWT was issued (based on the "iat"
     * claim) more than maxTokenAge seconds ago.
     *
     * If maxTokenAgeSeconds is explicitly set to zero, all tokens will be considered too old,
     * even if they are otherwise valid.
     *
     * The value for leeway is the maximum allowable difference when comparing
     * timestamps. It defaults to zero.
     */
    public fun authenticateJwtLocal(
        jwt: String,
        maxTokenAgeSeconds: Int?,
        leeway: Int = 0,
        callback: (StytchResult<Session?>) -> Unit,
    )

    /** Parse a JWT and verify the signature locally (without calling /authenticate in the API).
     *
     * If maxTokenAgeSeconds is set, this will return an error if the JWT was issued (based on the "iat"
     * claim) more than maxTokenAge seconds ago.
     *
     * If maxTokenAgeSeconds is explicitly set to zero, all tokens will be considered too old,
     * even if they are otherwise valid.
     *
     * The value for leeway is the maximum allowable difference when comparing
     * timestamps. It defaults to zero.
     */
    public suspend fun authenticateJwtLocalCompletable(
        jwt: String,
        maxTokenAgeSeconds: Int?,
        leeway: Int = 0,
    ): CompletableFuture<StytchResult<Session?>>
    // ENDMANUAL(authenticateJWT_interface)
}

internal class SessionsImpl(
    private val httpClient: HttpClient,
    private val coroutineScope: CoroutineScope,
    private val jwksClient: HttpsJwks,
    private val jwtOptions: JwtOptions,
) : Sessions {
    private val moshi = Moshi.Builder().add(InstantAdapter()).build()

    override suspend fun get(data: GetRequest): StytchResult<GetResponse> =
        withContext(Dispatchers.IO) {
            var headers = emptyMap<String, String>()

            val asJson = moshi.adapter(GetRequest::class.java).toJson(data)
            val type = Types.newParameterizedType(Map::class.java, String::class.java, Any::class.java)
            val adapter: JsonAdapter<Map<String, Any>> = moshi.adapter(type)
            val asMap = adapter.fromJson(asJson) ?: emptyMap()
            httpClient.get("/v1/sessions", asMap, headers)
        }

    override fun get(
        data: GetRequest,
        callback: (StytchResult<GetResponse>) -> Unit,
    ) {
        coroutineScope.launch {
            callback(get(data))
        }
    }

    override fun getCompletable(data: GetRequest): CompletableFuture<StytchResult<GetResponse>> =
        coroutineScope.async {
            get(data)
        }.asCompletableFuture()

    override suspend fun authenticate(data: AuthenticateRequest): StytchResult<AuthenticateResponse> =
        withContext(Dispatchers.IO) {
            var headers = emptyMap<String, String>()

            val asJson = moshi.adapter(AuthenticateRequest::class.java).toJson(data)
            httpClient.post("/v1/sessions/authenticate", asJson, headers)
        }

    override fun authenticate(
        data: AuthenticateRequest,
        callback: (StytchResult<AuthenticateResponse>) -> Unit,
    ) {
        coroutineScope.launch {
            callback(authenticate(data))
        }
    }

    override fun authenticateCompletable(data: AuthenticateRequest): CompletableFuture<StytchResult<AuthenticateResponse>> =
        coroutineScope.async {
            authenticate(data)
        }.asCompletableFuture()

    override suspend fun revoke(data: RevokeRequest): StytchResult<RevokeResponse> =
        withContext(Dispatchers.IO) {
            var headers = emptyMap<String, String>()

            val asJson = moshi.adapter(RevokeRequest::class.java).toJson(data)
            httpClient.post("/v1/sessions/revoke", asJson, headers)
        }

    override fun revoke(
        data: RevokeRequest,
        callback: (StytchResult<RevokeResponse>) -> Unit,
    ) {
        coroutineScope.launch {
            callback(revoke(data))
        }
    }

    override fun revokeCompletable(data: RevokeRequest): CompletableFuture<StytchResult<RevokeResponse>> =
        coroutineScope.async {
            revoke(data)
        }.asCompletableFuture()

    override suspend fun getJWKS(data: GetJWKSRequest): StytchResult<GetJWKSResponse> =
        withContext(Dispatchers.IO) {
            var headers = emptyMap<String, String>()

            val asJson = moshi.adapter(GetJWKSRequest::class.java).toJson(data)
            val type = Types.newParameterizedType(Map::class.java, String::class.java, Any::class.java)
            val adapter: JsonAdapter<Map<String, Any>> = moshi.adapter(type)
            val asMap = adapter.fromJson(asJson) ?: emptyMap()
            httpClient.get("/v1/sessions/jwks/${data.projectId}", asMap, headers)
        }

    override fun getJWKS(
        data: GetJWKSRequest,
        callback: (StytchResult<GetJWKSResponse>) -> Unit,
    ) {
        coroutineScope.launch {
            callback(getJWKS(data))
        }
    }

    override fun getJWKSCompletable(data: GetJWKSRequest): CompletableFuture<StytchResult<GetJWKSResponse>> =
        coroutineScope.async {
            getJWKS(data)
        }.asCompletableFuture()

    // MANUAL(authenticateJWT_impl)(SERVICE_METHOD)
    override suspend fun authenticateJwt(
        jwt: String,
        maxTokenAgeSeconds: Int?,
    ): StytchResult<JWTResponse> =
        withContext(Dispatchers.IO) {
            when (val localResult = authenticateJwtLocal(jwt = jwt, maxTokenAgeSeconds = maxTokenAgeSeconds)) {
                is StytchResult.Success -> StytchResult.Success(JWTSessionResponse(localResult.value))
                else ->
                    when (val netResult = authenticate(AuthenticateRequest(sessionJwt = jwt))) {
                        is StytchResult.Success -> StytchResult.Success(JWTAuthResponse(netResult.value))
                        else -> StytchResult.Success(JWTNullResponse)
                    }
            }
        }

    override fun authenticateJwt(
        jwt: String,
        maxTokenAgeSeconds: Int?,
        callback: (StytchResult<JWTResponse>) -> Unit,
    ) {
        coroutineScope.launch {
            callback(authenticateJwt(jwt, maxTokenAgeSeconds))
        }
    }

    override fun authenticateJwtCompletable(
        jwt: String,
        maxTokenAgeSeconds: Int?,
    ): CompletableFuture<StytchResult<JWTResponse>> =
        coroutineScope.async {
            authenticateJwt(jwt, maxTokenAgeSeconds)
        }.asCompletableFuture()

    override suspend fun authenticateJwtLocal(
        jwt: String,
        maxTokenAgeSeconds: Int?,
        leeway: Int,
    ): StytchResult<Session?> {
        return try {
            val jwtClaims =
                parseJWTClaims(
                    jwt = jwt,
                    jwtOptions = jwtOptions,
                    jwksClient = jwksClient,
                    options =
                        ParseJWTClaimsOptions(
                            leeway = leeway,
                            maxTokenAgeSeconds = maxTokenAgeSeconds,
                        ),
                )
            val stytchSessionClaims = jwtClaims.payload.claimsMap["https://stytch.com/session"] as? Map<*, *>
            val stytchSessionClaim =
                stytchSessionClaims?.let {
                    val type = Types.newParameterizedType(Map::class.java, String::class.java, Any::class.java)
                    val adapter: JsonAdapter<Map<*, *>> = moshi.adapter(type)
                    moshi.adapter(StytchSessionClaim::class.java).fromJson(adapter.toJson(it))
                } ?: throw JWTException.JwtMissingClaims
            return StytchResult.Success(
                Session(
                    sessionId = stytchSessionClaim.id,
                    attributes = stytchSessionClaim.attributes,
                    authenticationFactors = stytchSessionClaim.authenticationFactors,
                    userId = jwtClaims.payload.subject,
                    startedAt = Instant.parse(stytchSessionClaim.startedAt),
                    lastAccessedAt = Instant.parse(stytchSessionClaim.lastAccessedAt),
                    expiresAt = Instant.parse(stytchSessionClaim.expiresAt),
                    customClaims = jwtClaims.customClaims,
                ),
            )
        } catch (e: JWTException.JwtTooOld) {
            StytchResult.Error(StytchException.Critical(e))
        } catch (e: JWTException.JwtMissingClaims) {
            StytchResult.Error(StytchException.Critical(e))
        } catch (e: Exception) {
            StytchResult.Error(StytchException.Critical(JWTException.JwtError(e)))
        }
    }

    override fun authenticateJwtLocal(
        jwt: String,
        maxTokenAgeSeconds: Int?,
        leeway: Int,
        callback: (StytchResult<Session?>) -> Unit,
    ) {
        coroutineScope.launch {
            callback(authenticateJwtLocal(jwt, maxTokenAgeSeconds, leeway))
        }
    }

    override suspend fun authenticateJwtLocalCompletable(
        jwt: String,
        maxTokenAgeSeconds: Int?,
        leeway: Int,
    ): CompletableFuture<StytchResult<Session?>> =
        coroutineScope.async {
            authenticateJwtLocal(jwt, maxTokenAgeSeconds, leeway)
        }.asCompletableFuture()
    // ENDMANUAL(authenticateJWT_impl)
}
