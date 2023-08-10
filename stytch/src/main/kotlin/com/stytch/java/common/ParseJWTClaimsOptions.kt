package com.stytch.java.common

internal data class ParseJWTClaimsOptions(
    val leeway: Int,
    val maxTokenAgeSeconds: Int? = null,
)
