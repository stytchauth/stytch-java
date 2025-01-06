package com.stytch.java.common

public data class OptionalClientConfig
    @JvmOverloads
    constructor(
        val apiBaseUrl: String = "",
        val fraudBaseUrl: String = BASE_FRAUD_URL,
    )
