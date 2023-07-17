package com.stytch.kotlin.b2b.api.discoveryintermediatesessions

// !!!
// WARNING: This file is autogenerated
// Only modify code within MANUAL() sections
// or your changes may be overwritten later!
// !!!

import com.squareup.moshi.Moshi
import com.stytch.kotlin.b2b.models.discoveryintermediatesessions.ExchangeRequest
import com.stytch.kotlin.b2b.models.discoveryintermediatesessions.ExchangeResponse
import com.stytch.kotlin.common.StytchResult
import com.stytch.kotlin.http.HttpClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.future.asCompletableFuture
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.CompletableFuture

public interface IntermediateSessions {
    /**
     * Exchange an Intermediate Session for a fully realized [Member Session](https://stytch.com/docs/b2b/api/session-object)
     * in a desired [Organization](https://stytch.com/docs/b2b/api/organization-object).
     * This operation consumes the Intermediate Session.
     *
     * This endpoint can be used to accept invites and create new members via domain matching.
     */
    public suspend fun exchange(data: ExchangeRequest): StytchResult<ExchangeResponse>

    /**
     * Exchange an Intermediate Session for a fully realized [Member Session](https://stytch.com/docs/b2b/api/session-object)
     * in a desired [Organization](https://stytch.com/docs/b2b/api/organization-object).
     * This operation consumes the Intermediate Session.
     *
     * This endpoint can be used to accept invites and create new members via domain matching.
     */
    public fun exchange(data: ExchangeRequest, callback: (StytchResult<ExchangeResponse>) -> Unit)

    /**
     * Exchange an Intermediate Session for a fully realized [Member Session](https://stytch.com/docs/b2b/api/session-object)
     * in a desired [Organization](https://stytch.com/docs/b2b/api/organization-object).
     * This operation consumes the Intermediate Session.
     *
     * This endpoint can be used to accept invites and create new members via domain matching.
     */
    public fun exchangeCompletable(data: ExchangeRequest): CompletableFuture<StytchResult<ExchangeResponse>>
}

internal class IntermediateSessionsImpl(
    private val httpClient: HttpClient,
    private val coroutineScope: CoroutineScope,
) : IntermediateSessions {

    private val moshi = Moshi.Builder().build()

    override suspend fun exchange(data: ExchangeRequest): StytchResult<ExchangeResponse> = withContext(Dispatchers.IO) {
        val asJson = moshi.adapter(ExchangeRequest::class.java).toJson(data)
        httpClient.post("/v1/b2b/discovery/intermediate_sessions/exchange", asJson)
    }

    override fun exchange(data: ExchangeRequest, callback: (StytchResult<ExchangeResponse>) -> Unit) {
        coroutineScope.launch {
            callback(exchange(data))
        }
    }

    override fun exchangeCompletable(data: ExchangeRequest): CompletableFuture<StytchResult<ExchangeResponse>> =
        coroutineScope.async {
            exchange(data)
        }.asCompletableFuture()
}
