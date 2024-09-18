package com.stytch.java.b2b.api.oauth

// !!!
// WARNING: This file is autogenerated
// Only modify code within MANUAL() sections
// or your changes may be overwritten later!
// !!!

import com.squareup.moshi.Moshi
import com.stytch.java.b2b.api.oauthdiscovery.Discovery
import com.stytch.java.b2b.api.oauthdiscovery.DiscoveryImpl
import com.stytch.java.b2b.models.oauth.AuthenticateRequest
import com.stytch.java.b2b.models.oauth.AuthenticateResponse
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

public interface OAuth {
    public val discovery: Discovery

    /**
     * Authenticate a given a `token`. This endpoint verifies that the member completed the flow by verifying that the token
     * is valid and hasn't expired.  Provide the `session_duration_minutes` parameter to set the lifetime of the session. If
     * the `session_duration_minutes` parameter is not specified, a Stytch session will be created with a 60 minute duration.
     *
     * If the Member is required to complete MFA to log in to the, the returned value of `member_authenticated` will be
     * `false`, and an `intermediate_session_token` will be returned.
     * The `intermediate_session_token` can be passed into the
     * [OTP SMS Authenticate endpoint](https://stytch.com/docs/b2b/api/authenticate-otp-sms) to complete the MFA step and
     * acquire a full member session.
     * The `intermediate_session_token` can also be used with the
     * [Exchange Intermediate Session endpoint](https://stytch.com/docs/b2b/api/exchange-intermediate-session) or the
     * [Create Organization via Discovery endpoint](https://stytch.com/docs/b2b/api/create-organization-via-discovery) to join
     * a different Organization or create a new one.
     * The `session_duration_minutes` and `session_custom_claims` parameters will be ignored.
     *
     * If a valid `session_token` or `session_jwt` is passed in, the Member will not be required to complete an MFA step.
     *
     * If the Member is logging in via an OAuth provider that does not fully verify the email, the returned value of
     * `member_authenticated` will be `false`, and an `intermediate_session_token` will be returned.
     * The `primary_required` field details the authentication flow the Member must perform in order to
     * [complete a step-up authentication](https://stytch.com/docs/b2b/guides/oauth/auth-flows) into the organization. The
     * `intermediate_session_token` must be passed into that authentication flow.
     *
     * We’re actively accepting requests for new OAuth providers! Please [email us](mailto:support@stytch.com) or
     * [post in our community](https://stytch.com/docs/b2b/resources) if you are looking for an OAuth provider that is not
     * currently supported.
     */
    public suspend fun authenticate(data: AuthenticateRequest): StytchResult<AuthenticateResponse>

    /**
     * Authenticate a given a `token`. This endpoint verifies that the member completed the flow by verifying that the token
     * is valid and hasn't expired.  Provide the `session_duration_minutes` parameter to set the lifetime of the session. If
     * the `session_duration_minutes` parameter is not specified, a Stytch session will be created with a 60 minute duration.
     *
     * If the Member is required to complete MFA to log in to the, the returned value of `member_authenticated` will be
     * `false`, and an `intermediate_session_token` will be returned.
     * The `intermediate_session_token` can be passed into the
     * [OTP SMS Authenticate endpoint](https://stytch.com/docs/b2b/api/authenticate-otp-sms) to complete the MFA step and
     * acquire a full member session.
     * The `intermediate_session_token` can also be used with the
     * [Exchange Intermediate Session endpoint](https://stytch.com/docs/b2b/api/exchange-intermediate-session) or the
     * [Create Organization via Discovery endpoint](https://stytch.com/docs/b2b/api/create-organization-via-discovery) to join
     * a different Organization or create a new one.
     * The `session_duration_minutes` and `session_custom_claims` parameters will be ignored.
     *
     * If a valid `session_token` or `session_jwt` is passed in, the Member will not be required to complete an MFA step.
     *
     * If the Member is logging in via an OAuth provider that does not fully verify the email, the returned value of
     * `member_authenticated` will be `false`, and an `intermediate_session_token` will be returned.
     * The `primary_required` field details the authentication flow the Member must perform in order to
     * [complete a step-up authentication](https://stytch.com/docs/b2b/guides/oauth/auth-flows) into the organization. The
     * `intermediate_session_token` must be passed into that authentication flow.
     *
     * We’re actively accepting requests for new OAuth providers! Please [email us](mailto:support@stytch.com) or
     * [post in our community](https://stytch.com/docs/b2b/resources) if you are looking for an OAuth provider that is not
     * currently supported.
     */
    public fun authenticate(
        data: AuthenticateRequest,
        callback: (StytchResult<AuthenticateResponse>) -> Unit,
    )

    /**
     * Authenticate a given a `token`. This endpoint verifies that the member completed the flow by verifying that the token
     * is valid and hasn't expired.  Provide the `session_duration_minutes` parameter to set the lifetime of the session. If
     * the `session_duration_minutes` parameter is not specified, a Stytch session will be created with a 60 minute duration.
     *
     * If the Member is required to complete MFA to log in to the, the returned value of `member_authenticated` will be
     * `false`, and an `intermediate_session_token` will be returned.
     * The `intermediate_session_token` can be passed into the
     * [OTP SMS Authenticate endpoint](https://stytch.com/docs/b2b/api/authenticate-otp-sms) to complete the MFA step and
     * acquire a full member session.
     * The `intermediate_session_token` can also be used with the
     * [Exchange Intermediate Session endpoint](https://stytch.com/docs/b2b/api/exchange-intermediate-session) or the
     * [Create Organization via Discovery endpoint](https://stytch.com/docs/b2b/api/create-organization-via-discovery) to join
     * a different Organization or create a new one.
     * The `session_duration_minutes` and `session_custom_claims` parameters will be ignored.
     *
     * If a valid `session_token` or `session_jwt` is passed in, the Member will not be required to complete an MFA step.
     *
     * If the Member is logging in via an OAuth provider that does not fully verify the email, the returned value of
     * `member_authenticated` will be `false`, and an `intermediate_session_token` will be returned.
     * The `primary_required` field details the authentication flow the Member must perform in order to
     * [complete a step-up authentication](https://stytch.com/docs/b2b/guides/oauth/auth-flows) into the organization. The
     * `intermediate_session_token` must be passed into that authentication flow.
     *
     * We’re actively accepting requests for new OAuth providers! Please [email us](mailto:support@stytch.com) or
     * [post in our community](https://stytch.com/docs/b2b/resources) if you are looking for an OAuth provider that is not
     * currently supported.
     */
    public fun authenticateCompletable(data: AuthenticateRequest): CompletableFuture<StytchResult<AuthenticateResponse>>
}

internal class OAuthImpl(
    private val httpClient: HttpClient,
    private val coroutineScope: CoroutineScope,
) : OAuth {
    private val moshi = Moshi.Builder().add(InstantAdapter()).build()

    override val discovery: Discovery = DiscoveryImpl(httpClient, coroutineScope)

    override suspend fun authenticate(data: AuthenticateRequest): StytchResult<AuthenticateResponse> =
        withContext(Dispatchers.IO) {
            var headers = emptyMap<String, String>()

            val asJson = moshi.adapter(AuthenticateRequest::class.java).toJson(data)
            httpClient.post("/v1/b2b/oauth/authenticate", asJson, headers)
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
