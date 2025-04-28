package com.stytch.java.common

internal data class JwtOptions(
    val audience: String,
    val issuers: List<String>,
    val type: String,
)
