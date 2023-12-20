package com.stytch.java.consumer.api.m2mclients

// !!!
// WARNING: This file is autogenerated
// Only modify code within MANUAL() sections
// or your changes may be overwritten later!
// !!!

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.stytch.java.common.InstantAdapter
import com.stytch.java.common.StytchResult
import com.stytch.java.consumer.api.m2mclientssecrets.Secrets
import com.stytch.java.consumer.api.m2mclientssecrets.SecretsImpl
import com.stytch.java.consumer.models.m2mclients.CreateRequest
import com.stytch.java.consumer.models.m2mclients.CreateResponse
import com.stytch.java.consumer.models.m2mclients.DeleteRequest
import com.stytch.java.consumer.models.m2mclients.DeleteResponse
import com.stytch.java.consumer.models.m2mclients.GetRequest
import com.stytch.java.consumer.models.m2mclients.GetResponse
import com.stytch.java.consumer.models.m2mclients.SearchRequest
import com.stytch.java.consumer.models.m2mclients.SearchResponse
import com.stytch.java.consumer.models.m2mclients.UpdateRequest
import com.stytch.java.consumer.models.m2mclients.UpdateResponse
import com.stytch.java.http.HttpClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.future.asCompletableFuture
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.CompletableFuture

public interface Clients {
    public val secrets: Secrets

    /**
     * Gets information about an existing M2M Client.
     */
    public suspend fun get(data: GetRequest): StytchResult<GetResponse>

    /**
     * Gets information about an existing M2M Client.
     */
    public fun get(
        data: GetRequest,
        callback: (StytchResult<GetResponse>) -> Unit,
    )

    /**
     * Gets information about an existing M2M Client.
     */
    public fun getCompletable(data: GetRequest): CompletableFuture<StytchResult<GetResponse>>

    /**
     * Search for M2M Clients within your Stytch Project. Submit an empty `query` in the request to return all M2M Clients.
     *
     * The following search filters are supported today:
     * - `client_id`: Pass in a list of client IDs to get many clients in a single request
     * - `client_name`: Search for clients by exact match on client name
     * - `scopes`: Search for clients assigned a specific scope
     */
    public suspend fun search(data: SearchRequest): StytchResult<SearchResponse>

    /**
     * Search for M2M Clients within your Stytch Project. Submit an empty `query` in the request to return all M2M Clients.
     *
     * The following search filters are supported today:
     * - `client_id`: Pass in a list of client IDs to get many clients in a single request
     * - `client_name`: Search for clients by exact match on client name
     * - `scopes`: Search for clients assigned a specific scope
     */
    public fun search(
        data: SearchRequest,
        callback: (StytchResult<SearchResponse>) -> Unit,
    )

    /**
     * Search for M2M Clients within your Stytch Project. Submit an empty `query` in the request to return all M2M Clients.
     *
     * The following search filters are supported today:
     * - `client_id`: Pass in a list of client IDs to get many clients in a single request
     * - `client_name`: Search for clients by exact match on client name
     * - `scopes`: Search for clients assigned a specific scope
     */
    public fun searchCompletable(data: SearchRequest): CompletableFuture<StytchResult<SearchResponse>>

    /**
     * Updates an existing M2M Client. You can use this endpoint to activate or deactivate a M2M Client by changing its
     * `status`. A deactivated M2M Client will not be allowed to perform future token exchange flows until it is reactivated.
     *
     * **Important:** Deactivating a M2M Client will not invalidate any existing JWTs issued to the client, only prevent it
     * from receiving new ones.
     * To protect more-sensitive routes, pass a lower `max_token_age` value
     * when[authenticating the token](https://stytch.com/docs/b2b/api/authenticate-m2m-token)[authenticating the token](https://stytch.com/docs/api/authenticate-m2m-token).
     */
    public suspend fun update(data: UpdateRequest): StytchResult<UpdateResponse>

    /**
     * Updates an existing M2M Client. You can use this endpoint to activate or deactivate a M2M Client by changing its
     * `status`. A deactivated M2M Client will not be allowed to perform future token exchange flows until it is reactivated.
     *
     * **Important:** Deactivating a M2M Client will not invalidate any existing JWTs issued to the client, only prevent it
     * from receiving new ones.
     * To protect more-sensitive routes, pass a lower `max_token_age` value
     * when[authenticating the token](https://stytch.com/docs/b2b/api/authenticate-m2m-token)[authenticating the token](https://stytch.com/docs/api/authenticate-m2m-token).
     */
    public fun update(
        data: UpdateRequest,
        callback: (StytchResult<UpdateResponse>) -> Unit,
    )

    /**
     * Updates an existing M2M Client. You can use this endpoint to activate or deactivate a M2M Client by changing its
     * `status`. A deactivated M2M Client will not be allowed to perform future token exchange flows until it is reactivated.
     *
     * **Important:** Deactivating a M2M Client will not invalidate any existing JWTs issued to the client, only prevent it
     * from receiving new ones.
     * To protect more-sensitive routes, pass a lower `max_token_age` value
     * when[authenticating the token](https://stytch.com/docs/b2b/api/authenticate-m2m-token)[authenticating the token](https://stytch.com/docs/api/authenticate-m2m-token).
     */
    public fun updateCompletable(data: UpdateRequest): CompletableFuture<StytchResult<UpdateResponse>>

    /**
     * Deletes the M2M Client.
     *
     * **Important:** Deleting a M2M Client will not invalidate any existing JWTs issued to the client, only prevent it from
     * receiving new ones.
     * To protect more-sensitive routes, pass a lower `max_token_age` value
     * when[authenticating the token](https://stytch.com/docs/b2b/api/authenticate-m2m-token)[authenticating the token](https://stytch.com/docs/api/authenticate-m2m-token).
     */
    public suspend fun delete(data: DeleteRequest): StytchResult<DeleteResponse>

    /**
     * Deletes the M2M Client.
     *
     * **Important:** Deleting a M2M Client will not invalidate any existing JWTs issued to the client, only prevent it from
     * receiving new ones.
     * To protect more-sensitive routes, pass a lower `max_token_age` value
     * when[authenticating the token](https://stytch.com/docs/b2b/api/authenticate-m2m-token)[authenticating the token](https://stytch.com/docs/api/authenticate-m2m-token).
     */
    public fun delete(
        data: DeleteRequest,
        callback: (StytchResult<DeleteResponse>) -> Unit,
    )

    /**
     * Deletes the M2M Client.
     *
     * **Important:** Deleting a M2M Client will not invalidate any existing JWTs issued to the client, only prevent it from
     * receiving new ones.
     * To protect more-sensitive routes, pass a lower `max_token_age` value
     * when[authenticating the token](https://stytch.com/docs/b2b/api/authenticate-m2m-token)[authenticating the token](https://stytch.com/docs/api/authenticate-m2m-token).
     */
    public fun deleteCompletable(data: DeleteRequest): CompletableFuture<StytchResult<DeleteResponse>>

    /**
     * Creates a new M2M Client. On initial client creation, you may pass in a custom `client_id` or `client_secret` to import
     * an existing M2M client. If you do not pass in a custom `client_id` or `client_secret`, one will be generated
     * automatically. The `client_id` must be unique among all clients in your project.
     *
     * **Important:** This is the only time you will be able to view the generated `client_secret` in the API response. Stytch
     * stores a hash of the `client_secret` and cannot recover the value if lost. Be sure to persist the `client_secret` in a
     * secure location. If the `client_secret` is lost, you will need to trigger a secret rotation flow to receive another one.
     */
    public suspend fun create(data: CreateRequest): StytchResult<CreateResponse>

    /**
     * Creates a new M2M Client. On initial client creation, you may pass in a custom `client_id` or `client_secret` to import
     * an existing M2M client. If you do not pass in a custom `client_id` or `client_secret`, one will be generated
     * automatically. The `client_id` must be unique among all clients in your project.
     *
     * **Important:** This is the only time you will be able to view the generated `client_secret` in the API response. Stytch
     * stores a hash of the `client_secret` and cannot recover the value if lost. Be sure to persist the `client_secret` in a
     * secure location. If the `client_secret` is lost, you will need to trigger a secret rotation flow to receive another one.
     */
    public fun create(
        data: CreateRequest,
        callback: (StytchResult<CreateResponse>) -> Unit,
    )

    /**
     * Creates a new M2M Client. On initial client creation, you may pass in a custom `client_id` or `client_secret` to import
     * an existing M2M client. If you do not pass in a custom `client_id` or `client_secret`, one will be generated
     * automatically. The `client_id` must be unique among all clients in your project.
     *
     * **Important:** This is the only time you will be able to view the generated `client_secret` in the API response. Stytch
     * stores a hash of the `client_secret` and cannot recover the value if lost. Be sure to persist the `client_secret` in a
     * secure location. If the `client_secret` is lost, you will need to trigger a secret rotation flow to receive another one.
     */
    public fun createCompletable(data: CreateRequest): CompletableFuture<StytchResult<CreateResponse>>
}

internal class ClientsImpl(private val httpClient: HttpClient, private val coroutineScope: CoroutineScope) : Clients {
    private val moshi = Moshi.Builder().add(InstantAdapter()).build()

    override val secrets: Secrets = SecretsImpl(httpClient, coroutineScope)

    override suspend fun get(data: GetRequest): StytchResult<GetResponse> =
        withContext(Dispatchers.IO) {
            var headers = emptyMap<String, String>()

            val asJson = moshi.adapter(GetRequest::class.java).toJson(data)
            val type = Types.newParameterizedType(Map::class.java, String::class.java, Any::class.java)
            val adapter: JsonAdapter<Map<String, Any>> = moshi.adapter(type)
            val asMap = adapter.fromJson(asJson) ?: emptyMap()
            httpClient.get("/v1/m2m/clients/${data.clientId}", asMap, headers)
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

    override suspend fun search(data: SearchRequest): StytchResult<SearchResponse> =
        withContext(Dispatchers.IO) {
            var headers = emptyMap<String, String>()

            val asJson = moshi.adapter(SearchRequest::class.java).toJson(data)
            httpClient.post("/v1/m2m/clients/search", asJson, headers)
        }

    override fun search(
        data: SearchRequest,
        callback: (StytchResult<SearchResponse>) -> Unit,
    ) {
        coroutineScope.launch {
            callback(search(data))
        }
    }

    override fun searchCompletable(data: SearchRequest): CompletableFuture<StytchResult<SearchResponse>> =
        coroutineScope.async {
            search(data)
        }.asCompletableFuture()

    override suspend fun update(data: UpdateRequest): StytchResult<UpdateResponse> =
        withContext(Dispatchers.IO) {
            var headers = emptyMap<String, String>()

            val asJson = moshi.adapter(UpdateRequest::class.java).toJson(data)
            httpClient.put("/v1/m2m/clients/${data.clientId}", asJson, headers)
        }

    override fun update(
        data: UpdateRequest,
        callback: (StytchResult<UpdateResponse>) -> Unit,
    ) {
        coroutineScope.launch {
            callback(update(data))
        }
    }

    override fun updateCompletable(data: UpdateRequest): CompletableFuture<StytchResult<UpdateResponse>> =
        coroutineScope.async {
            update(data)
        }.asCompletableFuture()

    override suspend fun delete(data: DeleteRequest): StytchResult<DeleteResponse> =
        withContext(Dispatchers.IO) {
            var headers = emptyMap<String, String>()

            httpClient.delete("/v1/m2m/clients/${data.clientId}", headers)
        }

    override fun delete(
        data: DeleteRequest,
        callback: (StytchResult<DeleteResponse>) -> Unit,
    ) {
        coroutineScope.launch {
            callback(delete(data))
        }
    }

    override fun deleteCompletable(data: DeleteRequest): CompletableFuture<StytchResult<DeleteResponse>> =
        coroutineScope.async {
            delete(data)
        }.asCompletableFuture()

    override suspend fun create(data: CreateRequest): StytchResult<CreateResponse> =
        withContext(Dispatchers.IO) {
            var headers = emptyMap<String, String>()

            val asJson = moshi.adapter(CreateRequest::class.java).toJson(data)
            httpClient.post("/v1/m2m/clients", asJson, headers)
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
}
