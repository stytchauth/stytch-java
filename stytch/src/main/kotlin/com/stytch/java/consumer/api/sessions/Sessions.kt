package com.stytch.java.consumer.api.sessions

// !!!
// WARNING: This file is autogenerated
// Only modify code within MANUAL() sections
// or your changes may be overwritten later!
// !!!

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.stytch.java.common.JWTException
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
    public fun get(data: GetRequest, callback: (StytchResult<GetResponse>) -> Unit)

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
    public fun authenticate(data: AuthenticateRequest, callback: (StytchResult<AuthenticateResponse>) -> Unit)

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
    public fun revoke(data: RevokeRequest, callback: (StytchResult<RevokeResponse>) -> Unit)

    /**
     * Revoke a Session, immediately invalidating all of its session tokens. You can revoke a session in three ways: using its
     * ID, or using one of its session tokens, or one of its JWTs. This endpoint requires exactly one of those to be included
     * in the request. It will return an error if multiple are present.
     */
    public fun revokeCompletable(data: RevokeRequest): CompletableFuture<StytchResult<RevokeResponse>>

    /**
     * Get the JSON Web Key Set (JWKS) for a Stytch Project.
     */
    public suspend fun getJWKS(data: GetJWKSRequest): StytchResult<GetJWKSResponse>

    /**
     * Get the JSON Web Key Set (JWKS) for a Stytch Project.
     */
    public fun getJWKS(data: GetJWKSRequest, callback: (StytchResult<GetJWKSResponse>) -> Unit)

    /**
     * Get the JSON Web Key Set (JWKS) for a Stytch Project.
     */
    public fun getJWKSCompletable(data: GetJWKSRequest): CompletableFuture<StytchResult<GetJWKSResponse>>

    // MANUAL(authenticateJWT_interface)(INTERFACE_METHOD)
    // ADDIMPORT: import com.stytch.java.consumer.models.sessions.Session
    // ADDIMPORT: import com.stytch.java.common.JWTException
    // ADDIMPORT: import com.stytch.java.common.ParseJWTClaimsOptions
    // ADDIMPORT: import com.stytch.java.common.parseJWTClaims
    // ADDIMPORT: import com.stytch.java.common.ParsedJWTClaims
    /** Parse a JWT and verify the signature, preferring local verification over remote.
     *
     * If maxTokenAgeSeconds is set, remote verification will be forced if the JWT was issued at
     * (based on the "iat" claim) more than that many seconds ago.
     *
     * To force remote validation for all tokens, set max_token_age_seconds to zero or use the
     * authenticate method instead.
     */
    public suspend fun authenticateJwt(jwt: String, maxTokenAgeSeconds: Int?): StytchResult<Session?>

    /** Parse a JWT and verify the signature, preferring local verification over remote.
     *
     * If maxTokenAgeSeconds is set, remote verification will be forced if the JWT was issued at
     * (based on the "iat" claim) more than that many seconds ago.
     *
     * To force remote validation for all tokens, set max_token_age_seconds to zero or use the
     * authenticate method instead.
     */
    public fun authenticateJwt(jwt: String, maxTokenAgeSeconds: Int?, callback: (StytchResult<Session?>) -> Unit)

    /** Parse a JWT and verify the signature, preferring local verification over remote.
     *
     * If maxTokenAgeSeconds is set, remote verification will be forced if the JWT was issued at
     * (based on the "iat" claim) more than that many seconds ago.
     *
     * To force remote validation for all tokens, set max_token_age_seconds to zero or use the
     * authenticate method instead.
     */
    public fun authenticateJwtCompletable(jwt: String, maxTokenAgeSeconds: Int?): CompletableFuture<StytchResult<Session?>>

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
    public suspend fun authenticateJwtLocal(jwt: String, maxTokenAgeSeconds: Int?, leeway: Int = 0): StytchResult<Session?>

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
    public fun authenticateJwtLocal(jwt: String, maxTokenAgeSeconds: Int?, leeway: Int = 0, callback: (StytchResult<Session?>) -> Unit)

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
    public suspend fun authenticateJwtLocalCompletable(jwt: String, maxTokenAgeSeconds: Int?, leeway: Int = 0): CompletableFuture<StytchResult<Session?>>
    // ENDMANUAL(authenticateJWT_interface)
}

internal class SessionsImpl(
    private val httpClient: HttpClient,
    private val coroutineScope: CoroutineScope,
    private val jwksClient: HttpsJwks,
    private val jwtOptions: JwtOptions,
) : Sessions {

    private val moshi = Moshi.Builder().build()

    override suspend fun get(data: GetRequest): StytchResult<GetResponse> = withContext(Dispatchers.IO) {
        val asJson = moshi.adapter(GetRequest::class.java).toJson(data)
        val type = Types.newParameterizedType(Map::class.java, String::class.java, Any::class.java)
        val adapter: JsonAdapter<Map<String, Any>> = moshi.adapter(type)
        val asMap = adapter.fromJson(asJson) ?: emptyMap()
        httpClient.get("/v1/sessions", asMap)
    }

    override fun get(data: GetRequest, callback: (StytchResult<GetResponse>) -> Unit) {
        coroutineScope.launch {
            callback(get(data))
        }
    }

    override fun getCompletable(data: GetRequest): CompletableFuture<StytchResult<GetResponse>> =
        coroutineScope.async {
            get(data)
        }.asCompletableFuture()
    override suspend fun authenticate(data: AuthenticateRequest): StytchResult<AuthenticateResponse> = withContext(Dispatchers.IO) {
        val asJson = moshi.adapter(AuthenticateRequest::class.java).toJson(data)
        httpClient.post("/v1/sessions/authenticate", asJson)
    }

    override fun authenticate(data: AuthenticateRequest, callback: (StytchResult<AuthenticateResponse>) -> Unit) {
        coroutineScope.launch {
            callback(authenticate(data))
        }
    }

    override fun authenticateCompletable(data: AuthenticateRequest): CompletableFuture<StytchResult<AuthenticateResponse>> =
        coroutineScope.async {
            authenticate(data)
        }.asCompletableFuture()
    override suspend fun revoke(data: RevokeRequest): StytchResult<RevokeResponse> = withContext(Dispatchers.IO) {
        val asJson = moshi.adapter(RevokeRequest::class.java).toJson(data)
        httpClient.post("/v1/sessions/revoke", asJson)
    }

    override fun revoke(data: RevokeRequest, callback: (StytchResult<RevokeResponse>) -> Unit) {
        coroutineScope.launch {
            callback(revoke(data))
        }
    }

    override fun revokeCompletable(data: RevokeRequest): CompletableFuture<StytchResult<RevokeResponse>> =
        coroutineScope.async {
            revoke(data)
        }.asCompletableFuture()
    override suspend fun getJWKS(data: GetJWKSRequest): StytchResult<GetJWKSResponse> = withContext(Dispatchers.IO) {
        val asJson = moshi.adapter(GetJWKSRequest::class.java).toJson(data)
        val type = Types.newParameterizedType(Map::class.java, String::class.java, Any::class.java)
        val adapter: JsonAdapter<Map<String, Any>> = moshi.adapter(type)
        val asMap = adapter.fromJson(asJson) ?: emptyMap()
        httpClient.get("/v1/sessions/jwks/${data.projectId}", asMap)
    }

    override fun getJWKS(data: GetJWKSRequest, callback: (StytchResult<GetJWKSResponse>) -> Unit) {
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
    ): StytchResult<Session?> = withContext(Dispatchers.IO) {
        try {
            authenticateJwtLocal(jwt = jwt, maxTokenAgeSeconds = maxTokenAgeSeconds)
        } catch (e: JWTException) {
            when (val result = authenticate(AuthenticateRequest(sessionJwt = jwt))) {
                is StytchResult.Success -> StytchResult.Success(result.value.session)
                else -> StytchResult.Success(null)
            }
        }
    }

    override fun authenticateJwt(
        jwt: String,
        maxTokenAgeSeconds: Int?,
        callback: (StytchResult<Session?>) -> Unit,
    ) {
        coroutineScope.launch {
            callback(authenticateJwt(jwt, maxTokenAgeSeconds))
        }
    }

    override fun authenticateJwtCompletable(
        jwt: String,
        maxTokenAgeSeconds: Int?,
    ): CompletableFuture<StytchResult<Session?>> =
        coroutineScope.async {
            authenticateJwt(jwt, maxTokenAgeSeconds)
        }.asCompletableFuture()

    override suspend fun authenticateJwtLocal(
        jwt: String,
        maxTokenAgeSeconds: Int?,
        leeway: Int,
    ): StytchResult<Session?> {
        return try {
            val jwtClaims = parseJWTClaims(
                jwt = jwt,
                jwtOptions = jwtOptions,
                jwksClient = jwksClient,
                options = ParseJWTClaimsOptions(
                    leeway = leeway,
                    maxTokenAgeSeconds = maxTokenAgeSeconds,
                ),
            )
            val stytchSessionClaims = jwtClaims.payload.claimsMap["https://stytch.com/session"] as String
            val stytchSessionClaim =
                moshi.adapter(StytchSessionClaim::class.java).fromJson(stytchSessionClaims)
                    ?: throw JWTException.JwtMissingClaims
            return StytchResult.Success(
                Session(
                    sessionId = stytchSessionClaim.id,
                    attributes = stytchSessionClaim.attributes,
                    authenticationFactors = stytchSessionClaim.authenticationFactors,
                    userId = jwtClaims.payload.subject,
                    startedAt = Instant.ofEpochMilli(stytchSessionClaim.startedAt.toLong()),
                    lastAccessedAt = Instant.ofEpochMilli(stytchSessionClaim.lastAccessedAt.toLong()),
                    expiresAt = Instant.ofEpochMilli(stytchSessionClaim.expiresAt.toLong()),
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
