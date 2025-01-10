package com.stytch.java.consumer.api.magiclinks

// !!!
// WARNING: This file is autogenerated
// Only modify code within MANUAL() sections
// or your changes may be overwritten later!
// !!!

import com.squareup.moshi.Moshi
import com.stytch.java.common.InstantAdapter
import com.stytch.java.common.StytchResult
import com.stytch.java.consumer.api.magiclinksemail.Email
import com.stytch.java.consumer.api.magiclinksemail.EmailImpl
import com.stytch.java.consumer.models.magiclinks.AuthenticateRequest
import com.stytch.java.consumer.models.magiclinks.AuthenticateResponse
import com.stytch.java.consumer.models.magiclinks.CreateRequest
import com.stytch.java.consumer.models.magiclinks.CreateResponse
import com.stytch.java.http.HttpClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.future.asCompletableFuture
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.CompletableFuture

public interface MagicLinks {
    public val email: Email

    /**
     * Authenticate a User given a Magic Link. This endpoint verifies that the Magic Link token is valid, hasn't expired or
     * been previously used, and any optional security settings such as IP match or user agent match are satisfied.
     */
    public suspend fun authenticate(data: AuthenticateRequest): StytchResult<AuthenticateResponse>

    /**
     * Authenticate a User given a Magic Link. This endpoint verifies that the Magic Link token is valid, hasn't expired or
     * been previously used, and any optional security settings such as IP match or user agent match are satisfied.
     */
    public fun authenticate(
        data: AuthenticateRequest,
        callback: (StytchResult<AuthenticateResponse>) -> Unit,
    )

    /**
     * Authenticate a User given a Magic Link. This endpoint verifies that the Magic Link token is valid, hasn't expired or
     * been previously used, and any optional security settings such as IP match or user agent match are satisfied.
     */
    public fun authenticateCompletable(data: AuthenticateRequest): CompletableFuture<StytchResult<AuthenticateResponse>>

    /**
     * Create an Embeddable Magic Link token for a User. Access to this endpoint is restricted. To enable it, please send us a
     * note at support@stytch.com.
     *
     * ### Next steps
     * Send the returned `token` value to the end user in a link which directs to your application. When the end user follows
     * your link, collect the token, and call [Authenticate Magic Link](https://stytch.com/docs/api/authenticate-magic-link)
     * to complete authentication.
     *
     * **Note:** Authenticating an Embeddable Magic Link token will **not** result in any of the Stytch User's factors (email
     * address or phone number) being marked as verified, as Stytch cannot confirm where the user received the token.
     */
    public suspend fun create(data: CreateRequest): StytchResult<CreateResponse>

    /**
     * Create an Embeddable Magic Link token for a User. Access to this endpoint is restricted. To enable it, please send us a
     * note at support@stytch.com.
     *
     * ### Next steps
     * Send the returned `token` value to the end user in a link which directs to your application. When the end user follows
     * your link, collect the token, and call [Authenticate Magic Link](https://stytch.com/docs/api/authenticate-magic-link)
     * to complete authentication.
     *
     * **Note:** Authenticating an Embeddable Magic Link token will **not** result in any of the Stytch User's factors (email
     * address or phone number) being marked as verified, as Stytch cannot confirm where the user received the token.
     */
    public fun create(
        data: CreateRequest,
        callback: (StytchResult<CreateResponse>) -> Unit,
    )

    /**
     * Create an Embeddable Magic Link token for a User. Access to this endpoint is restricted. To enable it, please send us a
     * note at support@stytch.com.
     *
     * ### Next steps
     * Send the returned `token` value to the end user in a link which directs to your application. When the end user follows
     * your link, collect the token, and call [Authenticate Magic Link](https://stytch.com/docs/api/authenticate-magic-link)
     * to complete authentication.
     *
     * **Note:** Authenticating an Embeddable Magic Link token will **not** result in any of the Stytch User's factors (email
     * address or phone number) being marked as verified, as Stytch cannot confirm where the user received the token.
     */
    public fun createCompletable(data: CreateRequest): CompletableFuture<StytchResult<CreateResponse>>
}

internal class MagicLinksImpl(
    private val httpClient: HttpClient,
    private val coroutineScope: CoroutineScope,
) : MagicLinks {
    private val moshi = Moshi.Builder().add(InstantAdapter()).build()

    override val email: Email = EmailImpl(httpClient, coroutineScope)

    override suspend fun authenticate(data: AuthenticateRequest): StytchResult<AuthenticateResponse> =
        withContext(Dispatchers.IO) {
            var headers = emptyMap<String, String>()

            val asJson = moshi.adapter(AuthenticateRequest::class.java).toJson(data)
            httpClient.post("/v1/magic_links/authenticate", asJson, headers)
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

    override suspend fun create(data: CreateRequest): StytchResult<CreateResponse> =
        withContext(Dispatchers.IO) {
            var headers = emptyMap<String, String>()

            val asJson = moshi.adapter(CreateRequest::class.java).toJson(data)
            httpClient.post("/v1/magic_links", asJson, headers)
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
