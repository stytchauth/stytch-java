package com.stytch.kotlin.consumer.api.magiclinksemail

// !!!
// WARNING: This file is autogenerated
// Only modify code within MANUAL() sections
// or your changes may be overwritten later!
// !!!

import com.squareup.moshi.Moshi
import com.stytch.kotlin.common.StytchResult
import com.stytch.kotlin.consumer.models.magiclinksemail.InviteRequest
import com.stytch.kotlin.consumer.models.magiclinksemail.InviteResponse
import com.stytch.kotlin.consumer.models.magiclinksemail.LoginOrCreateRequest
import com.stytch.kotlin.consumer.models.magiclinksemail.LoginOrCreateResponse
import com.stytch.kotlin.consumer.models.magiclinksemail.RevokeInviteRequest
import com.stytch.kotlin.consumer.models.magiclinksemail.RevokeInviteResponse
import com.stytch.kotlin.consumer.models.magiclinksemail.SendRequest
import com.stytch.kotlin.consumer.models.magiclinksemail.SendResponse
import com.stytch.kotlin.http.HttpClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executors

public interface Email {
    /**
     * Send a magic link to an existing Stytch user using their email address. If you'd like to create a user and send them a
     * magic link by email with one request, use our
     * [log in or create endpoint](https://stytch.com/docs/api/log-in-or-create-user-by-email).
     *
     * ### Add an email to an existing user
     * This endpoint also allows you to add a new email to an existing Stytch User. Including a `user_id`, `session_token`, or
     * `session_jwt` in the request will add the email to the pre-existing Stytch User upon successful authentication.
     *
     * Adding a new email to an existing Stytch User requires the user to be present and validate the email via magic link.
     * This requirement is in place to prevent account takeover attacks.
     *
     * ### Next steps
     * The user is emailed a magic link which redirects them to the provided
     * [redirect URL](https://stytch.com/docs/magic-links#email-magic-links_redirect-routing). Collect the `token` from the
     * URL query parameters, and call [Authenticate magic link](https://stytch.com/docs/api/authenticate-magic-link) to
     * complete authentication.
     */
    public suspend fun send(data: SendRequest): StytchResult<SendResponse>

    /**
     * Send a magic link to an existing Stytch user using their email address. If you'd like to create a user and send them a
     * magic link by email with one request, use our
     * [log in or create endpoint](https://stytch.com/docs/api/log-in-or-create-user-by-email).
     *
     * ### Add an email to an existing user
     * This endpoint also allows you to add a new email to an existing Stytch User. Including a `user_id`, `session_token`, or
     * `session_jwt` in the request will add the email to the pre-existing Stytch User upon successful authentication.
     *
     * Adding a new email to an existing Stytch User requires the user to be present and validate the email via magic link.
     * This requirement is in place to prevent account takeover attacks.
     *
     * ### Next steps
     * The user is emailed a magic link which redirects them to the provided
     * [redirect URL](https://stytch.com/docs/magic-links#email-magic-links_redirect-routing). Collect the `token` from the
     * URL query parameters, and call [Authenticate magic link](https://stytch.com/docs/api/authenticate-magic-link) to
     * complete authentication.
     */
    public fun send(data: SendRequest, callback: (StytchResult<SendResponse>) -> Unit)

    /**
     * Send a magic link to an existing Stytch user using their email address. If you'd like to create a user and send them a
     * magic link by email with one request, use our
     * [log in or create endpoint](https://stytch.com/docs/api/log-in-or-create-user-by-email).
     *
     * ### Add an email to an existing user
     * This endpoint also allows you to add a new email to an existing Stytch User. Including a `user_id`, `session_token`, or
     * `session_jwt` in the request will add the email to the pre-existing Stytch User upon successful authentication.
     *
     * Adding a new email to an existing Stytch User requires the user to be present and validate the email via magic link.
     * This requirement is in place to prevent account takeover attacks.
     *
     * ### Next steps
     * The user is emailed a magic link which redirects them to the provided
     * [redirect URL](https://stytch.com/docs/magic-links#email-magic-links_redirect-routing). Collect the `token` from the
     * URL query parameters, and call [Authenticate magic link](https://stytch.com/docs/api/authenticate-magic-link) to
     * complete authentication.
     */
    public fun sendCompletable(data: SendRequest): CompletableFuture<StytchResult<SendResponse>>

    /**
     * Send either a login or signup Magic Link to the User based on if the email is associated with a User already. A new or
     * pending User will receive a signup Magic Link. An active User will receive a login Magic Link. For more information on
     * how to control the status your Users are created in see the `create_user_as_pending` flag.
     *
     * ### Next steps
     * The User is emailed a Magic Link which redirects them to the provided
     * [redirect URL](https://stytch.com/docs/magic-links#email-magic-links_redirect-routing). Collect the `token` from the
     * URL query parameters and call [Authenticate Magic Link](https://stytch.com/docs/api/authenticate-magic-link) to
     * complete authentication.
     */
    public suspend fun loginOrCreate(data: LoginOrCreateRequest): StytchResult<LoginOrCreateResponse>

    /**
     * Send either a login or signup Magic Link to the User based on if the email is associated with a User already. A new or
     * pending User will receive a signup Magic Link. An active User will receive a login Magic Link. For more information on
     * how to control the status your Users are created in see the `create_user_as_pending` flag.
     *
     * ### Next steps
     * The User is emailed a Magic Link which redirects them to the provided
     * [redirect URL](https://stytch.com/docs/magic-links#email-magic-links_redirect-routing). Collect the `token` from the
     * URL query parameters and call [Authenticate Magic Link](https://stytch.com/docs/api/authenticate-magic-link) to
     * complete authentication.
     */
    public fun loginOrCreate(data: LoginOrCreateRequest, callback: (StytchResult<LoginOrCreateResponse>) -> Unit)

    /**
     * Send either a login or signup Magic Link to the User based on if the email is associated with a User already. A new or
     * pending User will receive a signup Magic Link. An active User will receive a login Magic Link. For more information on
     * how to control the status your Users are created in see the `create_user_as_pending` flag.
     *
     * ### Next steps
     * The User is emailed a Magic Link which redirects them to the provided
     * [redirect URL](https://stytch.com/docs/magic-links#email-magic-links_redirect-routing). Collect the `token` from the
     * URL query parameters and call [Authenticate Magic Link](https://stytch.com/docs/api/authenticate-magic-link) to
     * complete authentication.
     */
    public fun loginOrCreateCompletable(data: LoginOrCreateRequest): CompletableFuture<StytchResult<LoginOrCreateResponse>>

    /**
     * Create a User and send an invite Magic Link to the provided `email`. The User will be created with a `pending` status
     * until they click the Magic Link in the invite email.
     *
     * ### Next steps
     * The User is emailed a Magic Link which redirects them to the provided
     * [redirect URL](https://stytch.com/docs/magic-links#email-magic-links_redirect-routing). Collect the `token` from the
     * URL query parameters and call [Authenticate Magic Link](https://stytch.com/docs/api/authenticate-magic-link) to
     * complete authentication.
     */
    public suspend fun invite(data: InviteRequest): StytchResult<InviteResponse>

    /**
     * Create a User and send an invite Magic Link to the provided `email`. The User will be created with a `pending` status
     * until they click the Magic Link in the invite email.
     *
     * ### Next steps
     * The User is emailed a Magic Link which redirects them to the provided
     * [redirect URL](https://stytch.com/docs/magic-links#email-magic-links_redirect-routing). Collect the `token` from the
     * URL query parameters and call [Authenticate Magic Link](https://stytch.com/docs/api/authenticate-magic-link) to
     * complete authentication.
     */
    public fun invite(data: InviteRequest, callback: (StytchResult<InviteResponse>) -> Unit)

    /**
     * Create a User and send an invite Magic Link to the provided `email`. The User will be created with a `pending` status
     * until they click the Magic Link in the invite email.
     *
     * ### Next steps
     * The User is emailed a Magic Link which redirects them to the provided
     * [redirect URL](https://stytch.com/docs/magic-links#email-magic-links_redirect-routing). Collect the `token` from the
     * URL query parameters and call [Authenticate Magic Link](https://stytch.com/docs/api/authenticate-magic-link) to
     * complete authentication.
     */
    public fun inviteCompletable(data: InviteRequest): CompletableFuture<StytchResult<InviteResponse>>

    /**
     * Revoke a pending invite based on the `email` provided.
     */
    public suspend fun revokeInvite(data: RevokeInviteRequest): StytchResult<RevokeInviteResponse>

    /**
     * Revoke a pending invite based on the `email` provided.
     */
    public fun revokeInvite(data: RevokeInviteRequest, callback: (StytchResult<RevokeInviteResponse>) -> Unit)

    /**
     * Revoke a pending invite based on the `email` provided.
     */
    public fun revokeInviteCompletable(data: RevokeInviteRequest): CompletableFuture<StytchResult<RevokeInviteResponse>>
}

internal class EmailImpl(
    private val httpClient: HttpClient,
    private val coroutineScope: CoroutineScope,
) : Email {

    private val moshi = Moshi.Builder().build()

    override suspend fun send(data: SendRequest): StytchResult<SendResponse> = withContext(Dispatchers.IO) {
        val asJson = moshi.adapter(SendRequest::class.java).toJson(data)
        httpClient.post("/v1/magic_links/email/send", asJson)
    }

    override fun send(data: SendRequest, callback: (StytchResult<SendResponse>) -> Unit) {
        coroutineScope.launch {
            callback(send(data))
        }
    }

    override fun sendCompletable(data: SendRequest): CompletableFuture<StytchResult<SendResponse>> {
        val executor = Executors.newFixedThreadPool(1)
        return CompletableFuture.supplyAsync({
            val asJson = moshi.adapter(SendRequest::class.java).toJson(data)
            httpClient.post("/v1/magic_links/email/send", asJson)
        }, executor)
    }
    override suspend fun loginOrCreate(data: LoginOrCreateRequest): StytchResult<LoginOrCreateResponse> = withContext(Dispatchers.IO) {
        val asJson = moshi.adapter(LoginOrCreateRequest::class.java).toJson(data)
        httpClient.post("/v1/magic_links/email/login_or_create", asJson)
    }

    override fun loginOrCreate(data: LoginOrCreateRequest, callback: (StytchResult<LoginOrCreateResponse>) -> Unit) {
        coroutineScope.launch {
            callback(loginOrCreate(data))
        }
    }

    override fun loginOrCreateCompletable(data: LoginOrCreateRequest): CompletableFuture<StytchResult<LoginOrCreateResponse>> {
        val executor = Executors.newFixedThreadPool(1)
        return CompletableFuture.supplyAsync({
            val asJson = moshi.adapter(LoginOrCreateRequest::class.java).toJson(data)
            httpClient.post("/v1/magic_links/email/login_or_create", asJson)
        }, executor)
    }
    override suspend fun invite(data: InviteRequest): StytchResult<InviteResponse> = withContext(Dispatchers.IO) {
        val asJson = moshi.adapter(InviteRequest::class.java).toJson(data)
        httpClient.post("/v1/magic_links/email/invite", asJson)
    }

    override fun invite(data: InviteRequest, callback: (StytchResult<InviteResponse>) -> Unit) {
        coroutineScope.launch {
            callback(invite(data))
        }
    }

    override fun inviteCompletable(data: InviteRequest): CompletableFuture<StytchResult<InviteResponse>> {
        val executor = Executors.newFixedThreadPool(1)
        return CompletableFuture.supplyAsync({
            val asJson = moshi.adapter(InviteRequest::class.java).toJson(data)
            httpClient.post("/v1/magic_links/email/invite", asJson)
        }, executor)
    }
    override suspend fun revokeInvite(data: RevokeInviteRequest): StytchResult<RevokeInviteResponse> = withContext(Dispatchers.IO) {
        val asJson = moshi.adapter(RevokeInviteRequest::class.java).toJson(data)
        httpClient.post("/v1/magic_links/email/revoke_invite", asJson)
    }

    override fun revokeInvite(data: RevokeInviteRequest, callback: (StytchResult<RevokeInviteResponse>) -> Unit) {
        coroutineScope.launch {
            callback(revokeInvite(data))
        }
    }

    override fun revokeInviteCompletable(data: RevokeInviteRequest): CompletableFuture<StytchResult<RevokeInviteResponse>> {
        val executor = Executors.newFixedThreadPool(1)
        return CompletableFuture.supplyAsync({
            val asJson = moshi.adapter(RevokeInviteRequest::class.java).toJson(data)
            httpClient.post("/v1/magic_links/email/revoke_invite", asJson)
        }, executor)
    }
}
