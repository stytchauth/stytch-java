package com.stytch.java.consumer.api.fraudrules

// !!!
// WARNING: This file is autogenerated
// Only modify code within MANUAL() sections
// or your changes may be overwritten later!
// !!!

import com.squareup.moshi.Moshi
import com.stytch.java.common.InstantAdapter
import com.stytch.java.common.StytchResult
import com.stytch.java.consumer.models.fraudrules.ListRequest
import com.stytch.java.consumer.models.fraudrules.ListResponse
import com.stytch.java.consumer.models.fraudrules.SetRequest
import com.stytch.java.consumer.models.fraudrules.SetResponse
import com.stytch.java.http.HttpClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.future.asCompletableFuture
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.CompletableFuture

public interface Rules {
    /**
     * Set a rule for a particular `visitor_id`, `browser_id`, `visitor_fingerprint`, `browser_fingerprint`,
     * `hardware_fingerprint`, `network_fingerprint`, `cidr_block`, `asn`, or `country_code`. This is helpful in cases where
     * you want to allow or block a specific user or fingerprint. You should be careful when setting rules for
     * `browser_fingerprint`, `hardware_fingerprint`, or `network_fingerprint` as they can be shared across multiple users,
     * and you could affect more users than intended.
     *
     * You may not set an `ALLOW` rule for a `country_code`.
     *
     * Rules are applied in the order specified above. For example, if an end user has an `ALLOW` rule set for their
     * `visitor_id` but a `BLOCK` rule set for their `hardware_fingerprint`, they will receive an `ALLOW` verdict because the
     * `visitor_id` rule takes precedence.
     *
     * If there are conflicts between multiple `cidr_block` rules (for example, if the `ip_address` of the end user overlaps
     * with multiple CIDR blocks that have rules set), the conflicts are resolved as follows:
     * - The smallest block size takes precedence. For example, if an `ip_address` overlaps with a `cidr_block` rule of
     * `ALLOW` for a block with a prefix of `/32` and a `cidr_block` rule of `BLOCK` with a prefix of `/24`, the rule match
     * verdict will be `ALLOW`.
     * - Among equivalent size blocks, `BLOCK` takes precedence over `CHALLENGE`, which takes precedence over `ALLOW`. For
     * example, if an `ip_address` overlaps with two `cidr_block` rules with blocks of the same size that return `CHALLENGE`
     * and `ALLOW`, the rule match verdict will be `CHALLENGE`.
     */
    public suspend fun set(data: SetRequest): StytchResult<SetResponse>

    /**
     * Set a rule for a particular `visitor_id`, `browser_id`, `visitor_fingerprint`, `browser_fingerprint`,
     * `hardware_fingerprint`, `network_fingerprint`, `cidr_block`, `asn`, or `country_code`. This is helpful in cases where
     * you want to allow or block a specific user or fingerprint. You should be careful when setting rules for
     * `browser_fingerprint`, `hardware_fingerprint`, or `network_fingerprint` as they can be shared across multiple users,
     * and you could affect more users than intended.
     *
     * You may not set an `ALLOW` rule for a `country_code`.
     *
     * Rules are applied in the order specified above. For example, if an end user has an `ALLOW` rule set for their
     * `visitor_id` but a `BLOCK` rule set for their `hardware_fingerprint`, they will receive an `ALLOW` verdict because the
     * `visitor_id` rule takes precedence.
     *
     * If there are conflicts between multiple `cidr_block` rules (for example, if the `ip_address` of the end user overlaps
     * with multiple CIDR blocks that have rules set), the conflicts are resolved as follows:
     * - The smallest block size takes precedence. For example, if an `ip_address` overlaps with a `cidr_block` rule of
     * `ALLOW` for a block with a prefix of `/32` and a `cidr_block` rule of `BLOCK` with a prefix of `/24`, the rule match
     * verdict will be `ALLOW`.
     * - Among equivalent size blocks, `BLOCK` takes precedence over `CHALLENGE`, which takes precedence over `ALLOW`. For
     * example, if an `ip_address` overlaps with two `cidr_block` rules with blocks of the same size that return `CHALLENGE`
     * and `ALLOW`, the rule match verdict will be `CHALLENGE`.
     */
    public fun set(
        data: SetRequest,
        callback: (StytchResult<SetResponse>) -> Unit,
    )

    /**
     * Set a rule for a particular `visitor_id`, `browser_id`, `visitor_fingerprint`, `browser_fingerprint`,
     * `hardware_fingerprint`, `network_fingerprint`, `cidr_block`, `asn`, or `country_code`. This is helpful in cases where
     * you want to allow or block a specific user or fingerprint. You should be careful when setting rules for
     * `browser_fingerprint`, `hardware_fingerprint`, or `network_fingerprint` as they can be shared across multiple users,
     * and you could affect more users than intended.
     *
     * You may not set an `ALLOW` rule for a `country_code`.
     *
     * Rules are applied in the order specified above. For example, if an end user has an `ALLOW` rule set for their
     * `visitor_id` but a `BLOCK` rule set for their `hardware_fingerprint`, they will receive an `ALLOW` verdict because the
     * `visitor_id` rule takes precedence.
     *
     * If there are conflicts between multiple `cidr_block` rules (for example, if the `ip_address` of the end user overlaps
     * with multiple CIDR blocks that have rules set), the conflicts are resolved as follows:
     * - The smallest block size takes precedence. For example, if an `ip_address` overlaps with a `cidr_block` rule of
     * `ALLOW` for a block with a prefix of `/32` and a `cidr_block` rule of `BLOCK` with a prefix of `/24`, the rule match
     * verdict will be `ALLOW`.
     * - Among equivalent size blocks, `BLOCK` takes precedence over `CHALLENGE`, which takes precedence over `ALLOW`. For
     * example, if an `ip_address` overlaps with two `cidr_block` rules with blocks of the same size that return `CHALLENGE`
     * and `ALLOW`, the rule match verdict will be `CHALLENGE`.
     */
    public fun setCompletable(data: SetRequest): CompletableFuture<StytchResult<SetResponse>>

    /**
     * Get all rules that have been set for your project.
     */
    public suspend fun list(data: ListRequest): StytchResult<ListResponse>

    /**
     * Get all rules that have been set for your project.
     */
    public fun list(
        data: ListRequest,
        callback: (StytchResult<ListResponse>) -> Unit,
    )

    /**
     * Get all rules that have been set for your project.
     */
    public fun listCompletable(data: ListRequest): CompletableFuture<StytchResult<ListResponse>>
}

internal class RulesImpl(
    private val httpClient: HttpClient,
    private val coroutineScope: CoroutineScope,
) : Rules {
    private val moshi = Moshi.Builder().add(InstantAdapter()).build()

    override suspend fun set(data: SetRequest): StytchResult<SetResponse> =
        withContext(Dispatchers.IO) {
            var headers = emptyMap<String, String>()

            val asJson = moshi.adapter(SetRequest::class.java).toJson(data)
            httpClient.post("/v1/rules/set", asJson, headers)
        }

    override fun set(
        data: SetRequest,
        callback: (StytchResult<SetResponse>) -> Unit,
    ) {
        coroutineScope.launch {
            callback(set(data))
        }
    }

    override fun setCompletable(data: SetRequest): CompletableFuture<StytchResult<SetResponse>> =
        coroutineScope.async {
            set(data)
        }.asCompletableFuture()

    override suspend fun list(data: ListRequest): StytchResult<ListResponse> =
        withContext(Dispatchers.IO) {
            var headers = emptyMap<String, String>()

            val asJson = moshi.adapter(ListRequest::class.java).toJson(data)
            httpClient.post("/v1/rules/list", asJson, headers)
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
