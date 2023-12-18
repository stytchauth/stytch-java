package com.stytch.java.b2b.api.discoveryorganizations

// !!!
// WARNING: This file is autogenerated
// Only modify code within MANUAL() sections
// or your changes may be overwritten later!
// !!!

import com.squareup.moshi.Moshi
import com.stytch.java.b2b.models.discoveryorganizations.CreateRequest
import com.stytch.java.b2b.models.discoveryorganizations.CreateResponse
import com.stytch.java.b2b.models.discoveryorganizations.ListRequest
import com.stytch.java.b2b.models.discoveryorganizations.ListResponse
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

public interface Organizations {
    /**
     * If an end user does not want to join any already-existing Organization, or has no possible Organizations to join, this
     * endpoint can be used to create a new
     * [Organization](https://stytch.com/docs/b2b/api/organization-object) and
     * [Member](https://stytch.com/docs/b2b/api/member-object).
     *
     * This operation consumes the Intermediate Session.
     *
     * This endpoint will also create an initial Member Session for the newly created Member.
     *
     * The Member created by this endpoint will automatically be granted the `stytch_admin` Role. See the
     * [RBAC guide](https://stytch.com/docs/b2b/guides/rbac/stytch-defaults) for more details on this Role.
     *
     * If the new Organization is created with a `mfa_policy` of `REQUIRED_FOR_ALL`, the newly created Member will need to
     * complete an MFA step to log in to the Organization.
     * The `intermediate_session_token` will not be consumed and instead will be returned in the response.
     * The `intermediate_session_token` can be passed into the
     * [OTP SMS Authenticate endpoint](https://stytch.com/docs/b2b/api/authenticate-otp-sms) to complete the MFA step and
     * acquire a full member session.
     * The `intermediate_session_token` can also be used with the
     * [Exchange Intermediate Session endpoint](https://stytch.com/docs/b2b/api/exchange-intermediate-session) or the
     * [Create Organization via Discovery endpoint](https://stytch.com/docs/b2b/api/create-organization-via-discovery) to join
     * a different Organization or create a new one.
     * The `session_duration_minutes` and `session_custom_claims` parameters will be ignored.
     */
    public suspend fun create(data: CreateRequest): StytchResult<CreateResponse>

    /**
     * If an end user does not want to join any already-existing Organization, or has no possible Organizations to join, this
     * endpoint can be used to create a new
     * [Organization](https://stytch.com/docs/b2b/api/organization-object) and
     * [Member](https://stytch.com/docs/b2b/api/member-object).
     *
     * This operation consumes the Intermediate Session.
     *
     * This endpoint will also create an initial Member Session for the newly created Member.
     *
     * The Member created by this endpoint will automatically be granted the `stytch_admin` Role. See the
     * [RBAC guide](https://stytch.com/docs/b2b/guides/rbac/stytch-defaults) for more details on this Role.
     *
     * If the new Organization is created with a `mfa_policy` of `REQUIRED_FOR_ALL`, the newly created Member will need to
     * complete an MFA step to log in to the Organization.
     * The `intermediate_session_token` will not be consumed and instead will be returned in the response.
     * The `intermediate_session_token` can be passed into the
     * [OTP SMS Authenticate endpoint](https://stytch.com/docs/b2b/api/authenticate-otp-sms) to complete the MFA step and
     * acquire a full member session.
     * The `intermediate_session_token` can also be used with the
     * [Exchange Intermediate Session endpoint](https://stytch.com/docs/b2b/api/exchange-intermediate-session) or the
     * [Create Organization via Discovery endpoint](https://stytch.com/docs/b2b/api/create-organization-via-discovery) to join
     * a different Organization or create a new one.
     * The `session_duration_minutes` and `session_custom_claims` parameters will be ignored.
     */
    public fun create(
        data: CreateRequest,
        callback: (StytchResult<CreateResponse>) -> Unit,
    )

    /**
     * If an end user does not want to join any already-existing Organization, or has no possible Organizations to join, this
     * endpoint can be used to create a new
     * [Organization](https://stytch.com/docs/b2b/api/organization-object) and
     * [Member](https://stytch.com/docs/b2b/api/member-object).
     *
     * This operation consumes the Intermediate Session.
     *
     * This endpoint will also create an initial Member Session for the newly created Member.
     *
     * The Member created by this endpoint will automatically be granted the `stytch_admin` Role. See the
     * [RBAC guide](https://stytch.com/docs/b2b/guides/rbac/stytch-defaults) for more details on this Role.
     *
     * If the new Organization is created with a `mfa_policy` of `REQUIRED_FOR_ALL`, the newly created Member will need to
     * complete an MFA step to log in to the Organization.
     * The `intermediate_session_token` will not be consumed and instead will be returned in the response.
     * The `intermediate_session_token` can be passed into the
     * [OTP SMS Authenticate endpoint](https://stytch.com/docs/b2b/api/authenticate-otp-sms) to complete the MFA step and
     * acquire a full member session.
     * The `intermediate_session_token` can also be used with the
     * [Exchange Intermediate Session endpoint](https://stytch.com/docs/b2b/api/exchange-intermediate-session) or the
     * [Create Organization via Discovery endpoint](https://stytch.com/docs/b2b/api/create-organization-via-discovery) to join
     * a different Organization or create a new one.
     * The `session_duration_minutes` and `session_custom_claims` parameters will be ignored.
     */
    public fun createCompletable(data: CreateRequest): CompletableFuture<StytchResult<CreateResponse>>

    /**
     * List all possible organization relationships connected to a
     * [Member Session](https://stytch.com/docs/b2b/api/session-object) or Intermediate Session.
     *
     * When a Member Session is passed in, relationships with a type of `active_member`, `pending_member`, or `invited_member`
     * will be returned, and any membership can be assumed by calling the
     * [Exchange Session](https://stytch.com/docs/b2b/api/exchange-session) endpoint.
     *
     * When an Intermediate Session is passed in, all relationship types - `active_member`, `pending_member`,
     * `invited_member`,
     * and `eligible_to_join_by_email_domain` - will be returned,
     * and any membership can be assumed by calling the
     * [Exchange Intermediate Session](https://stytch.com/docs/b2b/api/exchange-intermediate-session) endpoint.
     *
     * This endpoint requires either an `intermediate_session_token`, `session_jwt` or `session_token` be included in the
     * request.
     * It will return an error if multiple are present.
     *
     * This operation does not consume the Intermediate Session or Session Token passed in.
     */
    public suspend fun list(data: ListRequest): StytchResult<ListResponse>

    /**
     * List all possible organization relationships connected to a
     * [Member Session](https://stytch.com/docs/b2b/api/session-object) or Intermediate Session.
     *
     * When a Member Session is passed in, relationships with a type of `active_member`, `pending_member`, or `invited_member`
     * will be returned, and any membership can be assumed by calling the
     * [Exchange Session](https://stytch.com/docs/b2b/api/exchange-session) endpoint.
     *
     * When an Intermediate Session is passed in, all relationship types - `active_member`, `pending_member`,
     * `invited_member`,
     * and `eligible_to_join_by_email_domain` - will be returned,
     * and any membership can be assumed by calling the
     * [Exchange Intermediate Session](https://stytch.com/docs/b2b/api/exchange-intermediate-session) endpoint.
     *
     * This endpoint requires either an `intermediate_session_token`, `session_jwt` or `session_token` be included in the
     * request.
     * It will return an error if multiple are present.
     *
     * This operation does not consume the Intermediate Session or Session Token passed in.
     */
    public fun list(
        data: ListRequest,
        callback: (StytchResult<ListResponse>) -> Unit,
    )

    /**
     * List all possible organization relationships connected to a
     * [Member Session](https://stytch.com/docs/b2b/api/session-object) or Intermediate Session.
     *
     * When a Member Session is passed in, relationships with a type of `active_member`, `pending_member`, or `invited_member`
     * will be returned, and any membership can be assumed by calling the
     * [Exchange Session](https://stytch.com/docs/b2b/api/exchange-session) endpoint.
     *
     * When an Intermediate Session is passed in, all relationship types - `active_member`, `pending_member`,
     * `invited_member`,
     * and `eligible_to_join_by_email_domain` - will be returned,
     * and any membership can be assumed by calling the
     * [Exchange Intermediate Session](https://stytch.com/docs/b2b/api/exchange-intermediate-session) endpoint.
     *
     * This endpoint requires either an `intermediate_session_token`, `session_jwt` or `session_token` be included in the
     * request.
     * It will return an error if multiple are present.
     *
     * This operation does not consume the Intermediate Session or Session Token passed in.
     */
    public fun listCompletable(data: ListRequest): CompletableFuture<StytchResult<ListResponse>>
}

internal class OrganizationsImpl(private val httpClient: HttpClient, private val coroutineScope: CoroutineScope) : Organizations {
    private val moshi = Moshi.Builder().add(InstantAdapter()).build()

    override suspend fun create(data: CreateRequest): StytchResult<CreateResponse> =
        withContext(Dispatchers.IO) {
            val asJson = moshi.adapter(CreateRequest::class.java).toJson(data)
            httpClient.post("/v1/b2b/discovery/organizations/create", asJson)
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

    override suspend fun list(data: ListRequest): StytchResult<ListResponse> =
        withContext(Dispatchers.IO) {
            val asJson = moshi.adapter(ListRequest::class.java).toJson(data)
            httpClient.post("/v1/b2b/discovery/organizations", asJson)
        }

    override fun list(
        data: ListRequest,
        callback: (StytchResult<ListResponse>) -> Unit,
    ) {
        coroutineScope.launch {
            callback(list(data))
        }
    }

    override fun listCompletable(data: ListRequest): CompletableFuture<StytchResult<ListResponse>> =
        coroutineScope.async {
            list(data)
        }.asCompletableFuture()
}
