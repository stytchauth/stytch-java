package com.stytch.java.consumer.api.fraud

// !!!
// WARNING: This file is autogenerated
// Only modify code within MANUAL() sections
// or your changes may be overwritten later!
// !!!

import com.squareup.moshi.Moshi
import com.stytch.java.common.InstantAdapter
import com.stytch.java.consumer.api.fraudfingerprint.Fingerprint
import com.stytch.java.consumer.api.fraudfingerprint.FingerprintImpl
import com.stytch.java.consumer.api.fraudrules.Rules
import com.stytch.java.consumer.api.fraudrules.RulesImpl
import com.stytch.java.consumer.api.fraudverdictreasons.VerdictReasons
import com.stytch.java.consumer.api.fraudverdictreasons.VerdictReasonsImpl
import com.stytch.java.http.HttpClient
import kotlinx.coroutines.CoroutineScope

public interface Fraud {
    public val fingerprint: Fingerprint

    public val rules: Rules

    public val verdictReasons: VerdictReasons
}

internal class FraudImpl(
    private val httpClient: HttpClient,
    private val coroutineScope: CoroutineScope,
) : Fraud {
    private val moshi = Moshi.Builder().add(InstantAdapter()).build()

    override val fingerprint: Fingerprint = FingerprintImpl(httpClient, coroutineScope)
    override val rules: Rules = RulesImpl(httpClient, coroutineScope)
    override val verdictReasons: VerdictReasons = VerdictReasonsImpl(httpClient, coroutineScope)
}
