package com.stytch.java.consumer.api.m2m

// !!!
// WARNING: This file is autogenerated
// Only modify code within MANUAL() sections
// or your changes may be overwritten later!
// !!!

import com.squareup.moshi.Moshi
import com.stytch.java.common.InstantAdapter
import com.stytch.java.common.JWTException
import com.stytch.java.common.JwtOptions
import com.stytch.java.common.ParseJWTClaimsOptions
import com.stytch.java.common.StytchException
import com.stytch.java.common.StytchResult
import com.stytch.java.common.parseJWTClaims
import com.stytch.java.consumer.api.m2mclients.Clients
import com.stytch.java.consumer.api.m2mclients.ClientsImpl
import com.stytch.java.consumer.models.m2m.AuthenticateTokenRequest
import com.stytch.java.consumer.models.m2m.AuthenticateTokenResponse
import com.stytch.java.consumer.models.m2m.TokenRequest
import com.stytch.java.consumer.models.m2m.TokenResponse
import com.stytch.java.http.HttpClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.future.asCompletableFuture
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import org.jose4j.jwk.HttpsJwks
import java.util.concurrent.CompletableFuture

public interface M2M {
    public val clients: Clients

    // MANUAL(Token)(INTERFACE_METHOD)
    // ADDIMPORT: import com.stytch.java.consumer.models.m2m.TokenRequest
    // ADDIMPORT: import com.stytch.java.consumer.models.m2m.TokenResponse
    // ADDIMPORT: import okhttp3.MediaType.Companion.toMediaType

    /**
     * Token retrieves an access token for the given M2M Client. Access tokens are JWTs signed with
     * the project's JWKs, and are valid for one hour after issuance. M2M Access tokens contain a
     * standard set of claims as well as any custom claims generated from templates. M2M Access tokens
     * can be validated locally using the Authenticate Access Token method in the Stytch Backend SDKs,
     * or with any library that supports JWT signature validation.
     *
     * Here is an example of a standard set of claims from a M2M Access Token:
     * ```
     *
     * 	{
     * 	  "sub": "m2m-client-test-d731954d-dab3-4a2b-bdee-07f3ad1be885",
     * 	  "iss": "stytch.com/project-test-3e71d0a1-1e3e-4ee2-9be0-d7c0900f02c2",
     * 	  "aud": ["project-test-3e71d0a1-1e3e-4ee2-9be0-d7c0900f02c2"],
     * 	  "scope": "read:users write:users",
     * 	  "iat": 4102473300,
     * 	  "nbf": 4102473300,
     * 	  "exp": 4102476900
     * 	}
     *
     * ```
     */
    public suspend fun token(data: TokenRequest): StytchResult<TokenResponse>

    /**
     * Token retrieves an access token for the given M2M Client. Access tokens are JWTs signed with
     * the project's JWKs, and are valid for one hour after issuance. M2M Access tokens contain a
     * standard set of claims as well as any custom claims generated from templates. M2M Access tokens
     * can be validated locally using the Authenticate Access Token method in the Stytch Backend SDKs,
     * or with any library that supports JWT signature validation.
     *
     * Here is an example of a standard set of claims from a M2M Access Token:
     * ```
     *
     * 	{
     * 	  "sub": "m2m-client-test-d731954d-dab3-4a2b-bdee-07f3ad1be885",
     * 	  "iss": "stytch.com/project-test-3e71d0a1-1e3e-4ee2-9be0-d7c0900f02c2",
     * 	  "aud": ["project-test-3e71d0a1-1e3e-4ee2-9be0-d7c0900f02c2"],
     * 	  "scope": "read:users write:users",
     * 	  "iat": 4102473300,
     * 	  "nbf": 4102473300,
     * 	  "exp": 4102476900
     * 	}
     *
     * ```
     */
    public fun token(
        data: TokenRequest,
        callback: (StytchResult<TokenResponse>) -> Unit,
    )

    /**
     * Token retrieves an access token for the given M2M Client. Access tokens are JWTs signed with
     * the project's JWKs, and are valid for one hour after issuance. M2M Access tokens contain a
     * standard set of claims as well as any custom claims generated from templates. M2M Access tokens
     * can be validated locally using the Authenticate Access Token method in the Stytch Backend SDKs,
     * or with any library that supports JWT signature validation.
     *
     * Here is an example of a standard set of claims from a M2M Access Token:
     * ```
     *
     * 	{
     * 	  "sub": "m2m-client-test-d731954d-dab3-4a2b-bdee-07f3ad1be885",
     * 	  "iss": "stytch.com/project-test-3e71d0a1-1e3e-4ee2-9be0-d7c0900f02c2",
     * 	  "aud": ["project-test-3e71d0a1-1e3e-4ee2-9be0-d7c0900f02c2"],
     * 	  "scope": "read:users write:users",
     * 	  "iat": 4102473300,
     * 	  "nbf": 4102473300,
     * 	  "exp": 4102476900
     * 	}
     *
     * ```
     */
    public fun tokenCompletable(data: TokenRequest): CompletableFuture<StytchResult<TokenResponse>>
    // ENDMANUAL(Token)

    // MANUAL(AuthenticateM2MToken)(INTERFACE_METHOD)
    // ADDIMPORT: import com.stytch.java.consumer.models.m2m.AuthenticateTokenRequest
    // ADDIMPORT: import com.stytch.java.consumer.models.m2m.AuthenticateTokenResponse
    // ADDIMPORT: import com.stytch.java.common.ParseJWTClaimsOptions
    // ADDIMPORT: import com.stytch.java.common.parseJWTClaims
    // ADDIMPORT: import com.stytch.java.common.ParsedJWTClaims
    // ADDIMPORT: import com.stytch.java.common.JWTException

    /**
     * AuthenticateToken validates an access token issued by Stytch from the Token endpoint. M2M
     * access tokens are JWTs signed with the project's JWKs, and can be validated locally using any
     * Stytch client library. You may pass in an optional set of scopes that the JWT must contain in
     * order to enforce permissions.
     */
    public suspend fun authenticateToken(data: AuthenticateTokenRequest): StytchResult<AuthenticateTokenResponse>

    /**
     * AuthenticateToken validates an access token issued by Stytch from the Token endpoint. M2M
     * access tokens are JWTs signed with the project's JWKs, and can be validated locally using any
     * Stytch client library. You may pass in an optional set of scopes that the JWT must contain in
     * order to enforce permissions.
     */
    public suspend fun authenticateToken(
        data: AuthenticateTokenRequest,
        callback: (StytchResult<AuthenticateTokenResponse>) -> Unit,
    )

    /**
     * AuthenticateToken validates an access token issued by Stytch from the Token endpoint. M2M
     * access tokens are JWTs signed with the project's JWKs, and can be validated locally using any
     * Stytch client library. You may pass in an optional set of scopes that the JWT must contain in
     * order to enforce permissions.
     */
    public suspend fun authenticateTokenCompletable(
        data: AuthenticateTokenRequest,
    ): CompletableFuture<StytchResult<AuthenticateTokenResponse>>
    // ENDMANUAL(AuthenticateM2MToken)
}

internal class M2MImpl(
    private val httpClient: HttpClient,
    private val coroutineScope: CoroutineScope,
    private val jwksClient: HttpsJwks,
    private val jwtOptions: JwtOptions,
) : M2M {
    private val moshi = Moshi.Builder().add(InstantAdapter()).build()

    override val clients: Clients = ClientsImpl(httpClient, coroutineScope)

    // MANUAL(token_impl)(SERVICE_METHOD)
    override suspend fun token(data: TokenRequest): StytchResult<TokenResponse> =
        withContext(Dispatchers.IO) {
            val params =
                mutableMapOf(
                    "client_id" to data.clientId,
                    "client_secret" to data.clientSecret,
                    "grant_type" to "client_credentials",
                )
            if (!data.scopes.isNullOrEmpty()) {
                params["scope"] = data.scopes.joinToString(" ")
            }
            val payload = moshi.adapter(Map::class.java).toJson(params)
            httpClient.post(
                path = "/v1/public/${jwtOptions.audience}/oauth2/token",
                json = payload,
                mediaType = "application/x-www-form-urlencoded".toMediaType(),
            )
        }

    override fun token(
        data: TokenRequest,
        callback: (StytchResult<TokenResponse>) -> Unit,
    ) {
        coroutineScope.launch { callback(token(data)) }
    }

    override fun tokenCompletable(data: TokenRequest): CompletableFuture<StytchResult<TokenResponse>> {
        return coroutineScope.async { token(data) }.asCompletableFuture()
    }
    // ENDMANUAL(token_impl)

    // MANUAL(authenticateToken_impl)(SERVICE_METHOD)
    override suspend fun authenticateToken(data: AuthenticateTokenRequest): StytchResult<AuthenticateTokenResponse> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val jwtClaims =
                    parseJWTClaims(
                        jwt = data.accessToken,
                        jwtOptions = jwtOptions,
                        jwksClient = jwksClient,
                        options =
                            ParseJWTClaimsOptions(
                                leeway = 0,
                                maxTokenAgeSeconds = data.maxTokenAgeSeconds,
                            ),
                    )
                val scopeString = jwtClaims.customClaims["scope"] as? String
                val scopes = scopeString?.split(" ") ?: emptyList()
                if (!data.requiredScopes.isNullOrEmpty()) {
                    performAuthorizationCheck(scopes, data.requiredScopes)
                }
                StytchResult.Success(
                    AuthenticateTokenResponse(
                        clientId = jwtClaims.payload.subject,
                        scopes = scopes,
                        customClaims = jwtClaims.customClaims,
                    ),
                )
            } catch (e: JWTException.JwtTooOld) {
                StytchResult.Error(StytchException.Critical(e))
            } catch (e: JWTException.JwtMissingAction) {
                StytchResult.Error(StytchException.Critical(e))
            } catch (e: JWTException.JwtMissingScope) {
                StytchResult.Error(StytchException.Critical(e))
            } catch (e: Exception) {
                StytchResult.Error(StytchException.Critical(JWTException.JwtError(e)))
            }
        }

    override suspend fun authenticateToken(
        data: AuthenticateTokenRequest,
        callback: (StytchResult<AuthenticateTokenResponse>) -> Unit,
    ) {
        coroutineScope.launch { callback(authenticateToken(data)) }
    }

    override suspend fun authenticateTokenCompletable(
        data: AuthenticateTokenRequest,
    ): CompletableFuture<StytchResult<AuthenticateTokenResponse>> {
        return coroutineScope.async { authenticateToken(data) }.asCompletableFuture()
    }

    companion object {
        @JvmStatic
        fun performAuthorizationCheck(
            hasScopes: List<String>,
            requiredScopes: List<String>,
        ) {
            val clientScopes: MutableMap<String, MutableSet<String>> = mutableMapOf()
            hasScopes.forEach { scope ->
                val (action, resource) =
                    if (scope.contains(":")) {
                        scope.split(":")
                    } else {
                        listOf(scope, "-")
                    }
                clientScopes.computeIfAbsent(action) { mutableSetOf() }.add(resource)
            }

            requiredScopes.forEach { scope ->
                val (requiredAction, requiredResource) =
                    if (scope.contains(":")) {
                        scope.split(":")
                    } else {
                        listOf(scope, "-")
                    }
                val resources = clientScopes[requiredAction] ?: throw JWTException.JwtMissingAction(requiredAction)
                if ("*" !in resources && requiredResource !in resources) {
                    throw JWTException.JwtMissingScope(scope)
                }
            }
        }
    }
    // ENDMANUAL(authenticateToken_impl)
}
