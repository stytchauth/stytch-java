package com.stytch.java.b2b.api.magiclinksemail

// !!!
// WARNING: This file is autogenerated
// Only modify code within MANUAL() sections
// or your changes may be overwritten later!
// !!!

import com.squareup.moshi.Moshi
import com.stytch.java.b2b.api.magiclinksemaildiscovery.Discovery
import com.stytch.java.b2b.api.magiclinksemaildiscovery.DiscoveryImpl
import com.stytch.java.b2b.models.magiclinksemail.InviteRequest
import com.stytch.java.b2b.models.magiclinksemail.InviteResponse
import com.stytch.java.b2b.models.magiclinksemail.LoginOrSignupRequest
import com.stytch.java.b2b.models.magiclinksemail.LoginOrSignupResponse
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

public interface Email {
    public val discovery: Discovery

    /**
     * Send either a login or signup magic link to a Member. A new, pending, or invited Member will receive a signup Email
     * Magic Link. Members will have a `pending` status until they successfully authenticate. An active Member will receive a
     * login Email Magic Link.
     */
    public suspend fun loginOrSignup(data: LoginOrSignupRequest): StytchResult<LoginOrSignupResponse>

    /**
     * Send either a login or signup magic link to a Member. A new, pending, or invited Member will receive a signup Email
     * Magic Link. Members will have a `pending` status until they successfully authenticate. An active Member will receive a
     * login Email Magic Link.
     */
    public fun loginOrSignup(
        data: LoginOrSignupRequest,
        callback: (StytchResult<LoginOrSignupResponse>) -> Unit,
    )

    /**
     * Send either a login or signup magic link to a Member. A new, pending, or invited Member will receive a signup Email
     * Magic Link. Members will have a `pending` status until they successfully authenticate. An active Member will receive a
     * login Email Magic Link.
     */
    public fun loginOrSignupCompletable(data: LoginOrSignupRequest): CompletableFuture<StytchResult<LoginOrSignupResponse>>

    /**
     * Send an invite email to a new Member to join an Organization. The Member will be created with an `invited` status until
     * they successfully authenticate. Sending invites to `pending` Members will update their status to `invited`. Sending
     * invites to already `active` Members will return an error. /%}
     */
    public suspend fun invite(data: InviteRequest): StytchResult<InviteResponse>

    /**
     * Send an invite email to a new Member to join an Organization. The Member will be created with an `invited` status until
     * they successfully authenticate. Sending invites to `pending` Members will update their status to `invited`. Sending
     * invites to already `active` Members will return an error. /%}
     */
    public fun invite(
        data: InviteRequest,
        callback: (StytchResult<InviteResponse>) -> Unit,
    )

    /**
     * Send an invite email to a new Member to join an Organization. The Member will be created with an `invited` status until
     * they successfully authenticate. Sending invites to `pending` Members will update their status to `invited`. Sending
     * invites to already `active` Members will return an error. /%}
     */
    public fun inviteCompletable(data: InviteRequest): CompletableFuture<StytchResult<InviteResponse>>
}

internal class EmailImpl(private val httpClient: HttpClient, private val coroutineScope: CoroutineScope) : Email {
    private val moshi = Moshi.Builder().add(InstantAdapter()).build()

    override val discovery: Discovery = DiscoveryImpl(httpClient, coroutineScope)

    override suspend fun loginOrSignup(data: LoginOrSignupRequest): StytchResult<LoginOrSignupResponse> =
        withContext(Dispatchers.IO) {
            val asJson = moshi.adapter(LoginOrSignupRequest::class.java).toJson(data)
            httpClient.post("/v1/b2b/magic_links/email/login_or_signup", asJson)
        }

    override fun loginOrSignup(
        data: LoginOrSignupRequest,
        callback: (StytchResult<LoginOrSignupResponse>) -> Unit,
    ) {
        coroutineScope.launch {
            callback(loginOrSignup(data))
        }
    }

    override fun loginOrSignupCompletable(data: LoginOrSignupRequest): CompletableFuture<StytchResult<LoginOrSignupResponse>> =
        coroutineScope.async {
            loginOrSignup(data)
        }.asCompletableFuture()

    override suspend fun invite(data: InviteRequest): StytchResult<InviteResponse> =
        withContext(Dispatchers.IO) {
            val asJson = moshi.adapter(InviteRequest::class.java).toJson(data)
            httpClient.post("/v1/b2b/magic_links/email/invite", asJson)
        }

    override fun invite(
        data: InviteRequest,
        callback: (StytchResult<InviteResponse>) -> Unit,
    ) {
        coroutineScope.launch {
            callback(invite(data))
        }
    }

    override fun inviteCompletable(data: InviteRequest): CompletableFuture<StytchResult<InviteResponse>> =
        coroutineScope.async {
            invite(data)
        }.asCompletableFuture()
}
