package com.stytch.java.b2b.api.magiclinksemaildiscovery

// !!!
// WARNING: This file is autogenerated
// Only modify code within MANUAL() sections
// or your changes may be overwritten later!
// !!!

import com.squareup.moshi.Moshi
import com.stytch.java.b2b.models.magiclinksemaildiscovery.SendRequest
import com.stytch.java.b2b.models.magiclinksemaildiscovery.SendResponse
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

public interface Discovery {
    /**
     * Send a discovery magic link to an email address.
     */
    public suspend fun send(data: SendRequest): StytchResult<SendResponse>

    /**
     * Send a discovery magic link to an email address.
     */
    public fun send(
        data: SendRequest,
        callback: (StytchResult<SendResponse>) -> Unit,
    )

    /**
     * Send a discovery magic link to an email address.
     */
    public fun sendCompletable(data: SendRequest): CompletableFuture<StytchResult<SendResponse>>
}

internal class DiscoveryImpl(private val httpClient: HttpClient, private val coroutineScope: CoroutineScope) : Discovery {
    private val moshi = Moshi.Builder().add(InstantAdapter()).build()

    override suspend fun send(data: SendRequest): StytchResult<SendResponse> =
        withContext(Dispatchers.IO) {
            val asJson = moshi.adapter(SendRequest::class.java).toJson(data)
            httpClient.post("/v1/b2b/magic_links/email/discovery/send", asJson)
        }

    override fun send(
        data: SendRequest,
        callback: (StytchResult<SendResponse>) -> Unit,
    ) {
        coroutineScope.launch {
            callback(send(data))
        }
    }

    override fun sendCompletable(data: SendRequest): CompletableFuture<StytchResult<SendResponse>> =
        coroutineScope.async {
            send(data)
        }.asCompletableFuture()
}
