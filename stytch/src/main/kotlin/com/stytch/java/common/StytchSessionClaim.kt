package com.stytch.java.common

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.stytch.java.consumer.models.attribute.Attributes
import com.stytch.java.consumer.models.sessions.AuthenticationFactor

@JsonClass(generateAdapter = true)
internal data class StytchSessionClaim(
    val id: String,
    @Json(name = "started_at")
    val startedAt: String,
    @Json(name = "last_accessed_at")
    val lastAccessedAt: String,
    @Json(name = "expires_at")
    val expiresAt: String,
    val attributes: Attributes?,
    @Json(name = "authentication_factors")
    val authenticationFactors: List<AuthenticationFactor>,
    @Json(name = "roles")
    val roles: List<String>? = null,
)
