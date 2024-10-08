package com.stytch.java.b2b.api.totps

// !!!
// WARNING: This file is autogenerated
// Only modify code within MANUAL() sections
// or your changes may be overwritten later!
// !!!

import com.squareup.moshi.Moshi
import com.stytch.java.b2b.models.totps.AuthenticateRequest
import com.stytch.java.b2b.models.totps.AuthenticateResponse
import com.stytch.java.b2b.models.totps.CreateRequest
import com.stytch.java.b2b.models.totps.CreateResponse
import com.stytch.java.b2b.models.totps.MigrateRequest
import com.stytch.java.b2b.models.totps.MigrateResponse
import com.stytch.java.common.InstantAdapter
import com.stytch.java.common.StytchResult
import com.stytch.java.http.HttpClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.future.asCompletableFuture
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.CompletableFuture

public interface TOTPs {
    /**
     * Create a new TOTP instance for a. The Member can use the authenticator application of their choice to scan the QR code
     * or enter the secret.
     *
     * Passing an intermediate session token, session token, or session JWT is not required, but if passed must match the
     * Member ID passed.
     */
    public suspend fun create(data: CreateRequest): StytchResult<CreateResponse>

    /**
     * Create a new TOTP instance for a. The Member can use the authenticator application of their choice to scan the QR code
     * or enter the secret.
     *
     * Passing an intermediate session token, session token, or session JWT is not required, but if passed must match the
     * Member ID passed.
     */
    public fun create(
        data: CreateRequest,
        callback: (StytchResult<CreateResponse>) -> Unit,
    )

    /**
     * Create a new TOTP instance for a. The Member can use the authenticator application of their choice to scan the QR code
     * or enter the secret.
     *
     * Passing an intermediate session token, session token, or session JWT is not required, but if passed must match the
     * Member ID passed.
     */
    public fun createCompletable(data: CreateRequest): CompletableFuture<StytchResult<CreateResponse>>

    /**
     * Authenticate a Member provided TOTP.
     */
    public suspend fun authenticate(data: AuthenticateRequest): StytchResult<AuthenticateResponse>

    /**
     * Authenticate a Member provided TOTP.
     */
    public fun authenticate(
        data: AuthenticateRequest,
        callback: (StytchResult<AuthenticateResponse>) -> Unit,
    )

    /**
     * Authenticate a Member provided TOTP.
     */
    public fun authenticateCompletable(data: AuthenticateRequest): CompletableFuture<StytchResult<AuthenticateResponse>>

    /**
     * Migrate an existing TOTP instance for a. Recovery codes are not required and will be minted for the Member if not
     * provided.
     */
    public suspend fun migrate(data: MigrateRequest): StytchResult<MigrateResponse>

    /**
     * Migrate an existing TOTP instance for a. Recovery codes are not required and will be minted for the Member if not
     * provided.
     */
    public fun migrate(
        data: MigrateRequest,
        callback: (StytchResult<MigrateResponse>) -> Unit,
    )

    /**
     * Migrate an existing TOTP instance for a. Recovery codes are not required and will be minted for the Member if not
     * provided.
     */
    public fun migrateCompletable(data: MigrateRequest): CompletableFuture<StytchResult<MigrateResponse>>
}

internal class TOTPsImpl(
    private val httpClient: HttpClient,
    private val coroutineScope: CoroutineScope,
) : TOTPs {
    private val moshi = Moshi.Builder().add(InstantAdapter()).build()

    override suspend fun create(data: CreateRequest): StytchResult<CreateResponse> =
        withContext(Dispatchers.IO) {
            var headers = emptyMap<String, String>()

            val asJson = moshi.adapter(CreateRequest::class.java).toJson(data)
            httpClient.post("/v1/b2b/totp", asJson, headers)
        }

    override fun create(
        data: CreateRequest,
        callback: (StytchResult<CreateResponse>) -> Unit,
    ) {
        coroutineScope.launch {
            callback(create(data))
        }
    }

    override fun createCompletable(data: CreateRequest): CompletableFuture<StytchResult<CreateResponse>> =
        coroutineScope.async {
            create(data)
        }.asCompletableFuture()

    override suspend fun authenticate(data: AuthenticateRequest): StytchResult<AuthenticateResponse> =
        withContext(Dispatchers.IO) {
            var headers = emptyMap<String, String>()

            val asJson = moshi.adapter(AuthenticateRequest::class.java).toJson(data)
            httpClient.post("/v1/b2b/totp/authenticate", asJson, headers)
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

    override suspend fun migrate(data: MigrateRequest): StytchResult<MigrateResponse> =
        withContext(Dispatchers.IO) {
            var headers = emptyMap<String, String>()

            val asJson = moshi.adapter(MigrateRequest::class.java).toJson(data)
            httpClient.post("/v1/b2b/totp/migrate", asJson, headers)
        }

    override fun migrate(
        data: MigrateRequest,
        callback: (StytchResult<MigrateResponse>) -> Unit,
    ) {
        coroutineScope.launch {
            callback(migrate(data))
        }
    }

    override fun migrateCompletable(data: MigrateRequest): CompletableFuture<StytchResult<MigrateResponse>> =
        coroutineScope.async {
            migrate(data)
        }.asCompletableFuture()
}
