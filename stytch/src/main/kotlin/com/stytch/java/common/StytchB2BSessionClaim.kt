package com.stytch.java.common

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.stytch.java.consumer.models.sessions.AuthenticationFactor

@JsonClass(generateAdapter = true)
internal data class StytchB2BSessionClaim(
    val id: String,
    @Json(name = "organization_id")
    val organizationId: String,
    @Json(name = "started_at")
    val startedAt: String,
    @Json(name = "last_accessed_at")
    val lastAccessedAt: String,
    @Json(name = "expires_at")
    val expiresAt: String,
    @Json(name = "authentication_factors")
    val authenticationFactors: List<AuthenticationFactor>,
    val roles: List<String>,
)
