package com.stytch.kotlin.common

internal data class JwtOptions(
    val audience: String,
    val issuer: String,
    val type: String,
)
