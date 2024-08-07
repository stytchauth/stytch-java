package com.stytch.java.consumer.api.cryptowallets

// !!!
// WARNING: This file is autogenerated
// Only modify code within MANUAL() sections
// or your changes may be overwritten later!
// !!!

import com.squareup.moshi.Moshi
import com.stytch.java.common.InstantAdapter
import com.stytch.java.common.StytchResult
import com.stytch.java.consumer.models.cryptowallets.AuthenticateRequest
import com.stytch.java.consumer.models.cryptowallets.AuthenticateResponse
import com.stytch.java.consumer.models.cryptowallets.AuthenticateStartRequest
import com.stytch.java.consumer.models.cryptowallets.AuthenticateStartResponse
import com.stytch.java.http.HttpClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.future.asCompletableFuture
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.CompletableFuture

public interface CryptoWallets {
    /**
     * Initiate the authentication of a crypto wallet. After calling this endpoint, the user will need to sign a message
     * containing the returned `challenge` field.
     *
     * For Ethereum crypto wallets, you can optionally use the Sign In With Ethereum (SIWE) protocol for the message by
     * passing in the `siwe_params`. The only required fields are `domain` and `uri`.
     * If the crypto wallet detects that the domain in the message does not match the website's domain, it will display a
     * warning to the user.
     *
     * If not using the SIWE protocol, the message will simply consist of the project name and a random string.
     */
    public suspend fun authenticateStart(data: AuthenticateStartRequest): StytchResult<AuthenticateStartResponse>

    /**
     * Initiate the authentication of a crypto wallet. After calling this endpoint, the user will need to sign a message
     * containing the returned `challenge` field.
     *
     * For Ethereum crypto wallets, you can optionally use the Sign In With Ethereum (SIWE) protocol for the message by
     * passing in the `siwe_params`. The only required fields are `domain` and `uri`.
     * If the crypto wallet detects that the domain in the message does not match the website's domain, it will display a
     * warning to the user.
     *
     * If not using the SIWE protocol, the message will simply consist of the project name and a random string.
     */
    public fun authenticateStart(
        data: AuthenticateStartRequest,
        callback: (StytchResult<AuthenticateStartResponse>) -> Unit,
    )

    /**
     * Initiate the authentication of a crypto wallet. After calling this endpoint, the user will need to sign a message
     * containing the returned `challenge` field.
     *
     * For Ethereum crypto wallets, you can optionally use the Sign In With Ethereum (SIWE) protocol for the message by
     * passing in the `siwe_params`. The only required fields are `domain` and `uri`.
     * If the crypto wallet detects that the domain in the message does not match the website's domain, it will display a
     * warning to the user.
     *
     * If not using the SIWE protocol, the message will simply consist of the project name and a random string.
     */
    public fun authenticateStartCompletable(data: AuthenticateStartRequest): CompletableFuture<StytchResult<AuthenticateStartResponse>>

    /**
     * Complete the authentication of a crypto wallet by passing the signature.
     */
    public suspend fun authenticate(data: AuthenticateRequest): StytchResult<AuthenticateResponse>

    /**
     * Complete the authentication of a crypto wallet by passing the signature.
     */
    public fun authenticate(
        data: AuthenticateRequest,
        callback: (StytchResult<AuthenticateResponse>) -> Unit,
    )

    /**
     * Complete the authentication of a crypto wallet by passing the signature.
     */
    public fun authenticateCompletable(data: AuthenticateRequest): CompletableFuture<StytchResult<AuthenticateResponse>>
}

internal class CryptoWalletsImpl(
    private val httpClient: HttpClient,
    private val coroutineScope: CoroutineScope,
) : CryptoWallets {
    private val moshi = Moshi.Builder().add(InstantAdapter()).build()

    override suspend fun authenticateStart(data: AuthenticateStartRequest): StytchResult<AuthenticateStartResponse> =
        withContext(Dispatchers.IO) {
            var headers = emptyMap<String, String>()

            val asJson = moshi.adapter(AuthenticateStartRequest::class.java).toJson(data)
            httpClient.post("/v1/crypto_wallets/authenticate/start", asJson, headers)
        }

    override fun authenticateStart(
        data: AuthenticateStartRequest,
        callback: (StytchResult<AuthenticateStartResponse>) -> Unit,
    ) {
        coroutineScope.launch {
            callback(authenticateStart(data))
        }
    }

    override fun authenticateStartCompletable(data: AuthenticateStartRequest): CompletableFuture<StytchResult<AuthenticateStartResponse>> =
        coroutineScope.async {
            authenticateStart(data)
        }.asCompletableFuture()

    override suspend fun authenticate(data: AuthenticateRequest): StytchResult<AuthenticateResponse> =
        withContext(Dispatchers.IO) {
            var headers = emptyMap<String, String>()

            val asJson = moshi.adapter(AuthenticateRequest::class.java).toJson(data)
            httpClient.post("/v1/crypto_wallets/authenticate", asJson, headers)
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
}
