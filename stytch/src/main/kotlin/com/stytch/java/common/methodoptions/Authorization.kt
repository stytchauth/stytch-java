package com.stytch.java.common.methodoptions

class Authorization(private val sessionToken: String? = null, private val sessionJwt: String? = null) {
    fun addHeaders(headers: Map<String, String>): Map<String, String> {
        if (sessionToken != null) {
            headers["X-Stytch-Member-Session"] = sessionToken
        }
        if (sessionJwt != null) {
            headers["X-Stytch-Member-SessionJWT"] = sessionJwt
        }
        return headers
    }
}
