package com.stytch.java.common.methodoptions

public data class Authorization(
    private val sessionToken: String? = null,
    private val sessionJwt: String? = null,
) {
    internal fun addHeaders(headers: Map<String, String> = emptyMap()): Map<String, String> {
        val authHeaders =
            mutableMapOf<String, String>().apply {
                if (sessionToken != null) {
                    put("X-Stytch-Member-Session", sessionToken)
                }
                if (sessionJwt != null) {
                    put("X-Stytch-Member-SessionJWT", sessionJwt)
                }
            }
        return authHeaders + headers
    }
}
