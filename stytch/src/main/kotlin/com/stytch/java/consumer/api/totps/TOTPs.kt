package com.stytch.java.consumer.api.totps

// !!!
// WARNING: This file is autogenerated
// Only modify code within MANUAL() sections
// or your changes may be overwritten later!
// !!!

import com.squareup.moshi.Moshi
import com.stytch.java.common.InstantAdapter
import com.stytch.java.common.StytchResult
import com.stytch.java.consumer.models.totps.AuthenticateRequest
import com.stytch.java.consumer.models.totps.AuthenticateResponse
import com.stytch.java.consumer.models.totps.CreateRequest
import com.stytch.java.consumer.models.totps.CreateResponse
import com.stytch.java.consumer.models.totps.RecoverRequest
import com.stytch.java.consumer.models.totps.RecoverResponse
import com.stytch.java.consumer.models.totps.RecoveryCodesRequest
import com.stytch.java.consumer.models.totps.RecoveryCodesResponse
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
     * Create a new TOTP instance for a user. The user can use the authenticator application of their choice to scan the QR
     * code or enter the secret.
     */
    public suspend fun create(data: CreateRequest): StytchResult<CreateResponse>

    /**
     * Create a new TOTP instance for a user. The user can use the authenticator application of their choice to scan the QR
     * code or enter the secret.
     */
    public fun create(
        data: CreateRequest,
        callback: (StytchResult<CreateResponse>) -> Unit,
    )

    /**
     * Create a new TOTP instance for a user. The user can use the authenticator application of their choice to scan the QR
     * code or enter the secret.
     */
    public fun createCompletable(data: CreateRequest): CompletableFuture<StytchResult<CreateResponse>>

    /**
     * Authenticate a TOTP code entered by a user.
     */
    public suspend fun authenticate(data: AuthenticateRequest): StytchResult<AuthenticateResponse>

    /**
     * Authenticate a TOTP code entered by a user.
     */
    public fun authenticate(
        data: AuthenticateRequest,
        callback: (StytchResult<AuthenticateResponse>) -> Unit,
    )

    /**
     * Authenticate a TOTP code entered by a user.
     */
    public fun authenticateCompletable(data: AuthenticateRequest): CompletableFuture<StytchResult<AuthenticateResponse>>

    /**
     * Retrieve the recovery codes for a TOTP instance tied to a User.
     */
    public suspend fun recoveryCodes(data: RecoveryCodesRequest): StytchResult<RecoveryCodesResponse>

    /**
     * Retrieve the recovery codes for a TOTP instance tied to a User.
     */
    public fun recoveryCodes(
        data: RecoveryCodesRequest,
        callback: (StytchResult<RecoveryCodesResponse>) -> Unit,
    )

    /**
     * Retrieve the recovery codes for a TOTP instance tied to a User.
     */
    public fun recoveryCodesCompletable(data: RecoveryCodesRequest): CompletableFuture<StytchResult<RecoveryCodesResponse>>

    /**
     * Authenticate a recovery code for a TOTP instance.
     */
    public suspend fun recover(data: RecoverRequest): StytchResult<RecoverResponse>

    /**
     * Authenticate a recovery code for a TOTP instance.
     */
    public fun recover(
        data: RecoverRequest,
        callback: (StytchResult<RecoverResponse>) -> Unit,
    )

    /**
     * Authenticate a recovery code for a TOTP instance.
     */
    public fun recoverCompletable(data: RecoverRequest): CompletableFuture<StytchResult<RecoverResponse>>
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
            httpClient.post("/v1/totps", asJson, headers)
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
            httpClient.post("/v1/totps/authenticate", asJson, headers)
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

    override suspend fun recoveryCodes(data: RecoveryCodesRequest): StytchResult<RecoveryCodesResponse> =
        withContext(Dispatchers.IO) {
            var headers = emptyMap<String, String>()

            val asJson = moshi.adapter(RecoveryCodesRequest::class.java).toJson(data)
            httpClient.post("/v1/totps/recovery_codes", asJson, headers)
        }

    override fun recoveryCodes(
        data: RecoveryCodesRequest,
        callback: (StytchResult<RecoveryCodesResponse>) -> Unit,
    ) {
        coroutineScope.launch {
            callback(recoveryCodes(data))
        }
    }

    override fun recoveryCodesCompletable(data: RecoveryCodesRequest): CompletableFuture<StytchResult<RecoveryCodesResponse>> =
        coroutineScope.async {
            recoveryCodes(data)
        }.asCompletableFuture()

    override suspend fun recover(data: RecoverRequest): StytchResult<RecoverResponse> =
        withContext(Dispatchers.IO) {
            var headers = emptyMap<String, String>()

            val asJson = moshi.adapter(RecoverRequest::class.java).toJson(data)
            httpClient.post("/v1/totps/recover", asJson, headers)
        }

    override fun recover(
        data: RecoverRequest,
        callback: (StytchResult<RecoverResponse>) -> Unit,
    ) {
        coroutineScope.launch {
            callback(recover(data))
        }
    }

    override fun recoverCompletable(data: RecoverRequest): CompletableFuture<StytchResult<RecoverResponse>> =
        coroutineScope.async {
            recover(data)
        }.asCompletableFuture()
}
