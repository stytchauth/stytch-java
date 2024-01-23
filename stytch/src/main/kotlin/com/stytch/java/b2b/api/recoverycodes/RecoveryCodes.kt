package com.stytch.java.b2b.api.recoverycodes

// !!!
// WARNING: This file is autogenerated
// Only modify code within MANUAL() sections
// or your changes may be overwritten later!
// !!!

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.stytch.java.b2b.models.recoverycodes.B2BGetRequest
import com.stytch.java.b2b.models.recoverycodes.B2BGetResponse
import com.stytch.java.b2b.models.recoverycodes.RecoverRequest
import com.stytch.java.b2b.models.recoverycodes.RecoverResponse
import com.stytch.java.b2b.models.recoverycodes.RotateRequest
import com.stytch.java.b2b.models.recoverycodes.RotateResponse
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

public interface RecoveryCodes {
    /**
     * Allows a Member to complete an MFA flow by consuming a recovery code. This consumes the recovery code and returns a
     * session token that can be used to authenticate the Member.
     */
    public suspend fun recover(data: RecoverRequest): StytchResult<RecoverResponse>

    /**
     * Allows a Member to complete an MFA flow by consuming a recovery code. This consumes the recovery code and returns a
     * session token that can be used to authenticate the Member.
     */
    public fun recover(
        data: RecoverRequest,
        callback: (StytchResult<RecoverResponse>) -> Unit,
    )

    /**
     * Allows a Member to complete an MFA flow by consuming a recovery code. This consumes the recovery code and returns a
     * session token that can be used to authenticate the Member.
     */
    public fun recoverCompletable(data: RecoverRequest): CompletableFuture<StytchResult<RecoverResponse>>

    /**
     * Returns a Member's full set of active recovery codes.
     */
    public suspend fun b2BGet(data: B2BGetRequest): StytchResult<B2BGetResponse>

    /**
     * Returns a Member's full set of active recovery codes.
     */
    public fun b2BGet(
        data: B2BGetRequest,
        callback: (StytchResult<B2BGetResponse>) -> Unit,
    )

    /**
     * Returns a Member's full set of active recovery codes.
     */
    public fun b2BGetCompletable(data: B2BGetRequest): CompletableFuture<StytchResult<B2BGetResponse>>

    /**
     * Rotate a Member's recovery codes. This invalidates all existing recovery codes and generates a new set of recovery
     * codes.
     */
    public suspend fun rotate(data: RotateRequest): StytchResult<RotateResponse>

    /**
     * Rotate a Member's recovery codes. This invalidates all existing recovery codes and generates a new set of recovery
     * codes.
     */
    public fun rotate(
        data: RotateRequest,
        callback: (StytchResult<RotateResponse>) -> Unit,
    )

    /**
     * Rotate a Member's recovery codes. This invalidates all existing recovery codes and generates a new set of recovery
     * codes.
     */
    public fun rotateCompletable(data: RotateRequest): CompletableFuture<StytchResult<RotateResponse>>
}

internal class RecoveryCodesImpl(
    private val httpClient: HttpClient,
    private val coroutineScope: CoroutineScope,
) : RecoveryCodes {
    private val moshi = Moshi.Builder().add(InstantAdapter()).build()

    override suspend fun recover(data: RecoverRequest): StytchResult<RecoverResponse> =
        withContext(Dispatchers.IO) {
            var headers = emptyMap<String, String>()

            val asJson = moshi.adapter(RecoverRequest::class.java).toJson(data)
            httpClient.post("/v1/b2b/recovery_codes/recover", asJson, headers)
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

    override suspend fun b2BGet(data: B2BGetRequest): StytchResult<B2BGetResponse> =
        withContext(Dispatchers.IO) {
            var headers = emptyMap<String, String>()

            val asJson = moshi.adapter(B2BGetRequest::class.java).toJson(data)
            val type = Types.newParameterizedType(Map::class.java, String::class.java, Any::class.java)
            val adapter: JsonAdapter<Map<String, Any>> = moshi.adapter(type)
            val asMap = adapter.fromJson(asJson) ?: emptyMap()
            httpClient.get("/v1/b2b/recovery_codes/${data.organizationId}/${data.memberId}", asMap, headers)
        }

    override fun b2BGet(
        data: B2BGetRequest,
        callback: (StytchResult<B2BGetResponse>) -> Unit,
    ) {
        coroutineScope.launch {
            callback(b2BGet(data))
        }
    }

    override fun b2BGetCompletable(data: B2BGetRequest): CompletableFuture<StytchResult<B2BGetResponse>> =
        coroutineScope.async {
            b2BGet(data)
        }.asCompletableFuture()

    override suspend fun rotate(data: RotateRequest): StytchResult<RotateResponse> =
        withContext(Dispatchers.IO) {
            var headers = emptyMap<String, String>()

            val asJson = moshi.adapter(RotateRequest::class.java).toJson(data)
            httpClient.post("/v1/b2b/recovery_codes/rotate", asJson, headers)
        }

    override fun rotate(
        data: RotateRequest,
        callback: (StytchResult<RotateResponse>) -> Unit,
    ) {
        coroutineScope.launch {
            callback(rotate(data))
        }
    }

    override fun rotateCompletable(data: RotateRequest): CompletableFuture<StytchResult<RotateResponse>> =
        coroutineScope.async {
            rotate(data)
        }.asCompletableFuture()
}
