package com.stytch.java.consumer

// !!!
// WARNING: This file is autogenerated
// Only modify code within MANUAL() sections
// or your changes may be overwritten later!
// !!!
import com.stytch.java.common.BASE_LIVE_URL
import com.stytch.java.common.BASE_TEST_URL
import com.stytch.java.common.JwtOptions
import com.stytch.java.consumer.api.cryptowallets.CryptoWallets
import com.stytch.java.consumer.api.cryptowallets.CryptoWalletsImpl
import com.stytch.java.consumer.api.m2m.M2M
import com.stytch.java.consumer.api.m2m.M2MImpl
import com.stytch.java.consumer.api.magiclinks.MagicLinks
import com.stytch.java.consumer.api.magiclinks.MagicLinksImpl
import com.stytch.java.consumer.api.oauth.OAuth
import com.stytch.java.consumer.api.oauth.OAuthImpl
import com.stytch.java.consumer.api.otp.OTPs
import com.stytch.java.consumer.api.otp.OTPsImpl
import com.stytch.java.consumer.api.passwords.Passwords
import com.stytch.java.consumer.api.passwords.PasswordsImpl
import com.stytch.java.consumer.api.sessions.Sessions
import com.stytch.java.consumer.api.sessions.SessionsImpl
import com.stytch.java.consumer.api.totps.TOTPs
import com.stytch.java.consumer.api.totps.TOTPsImpl
import com.stytch.java.consumer.api.users.Users
import com.stytch.java.consumer.api.users.UsersImpl
import com.stytch.java.consumer.api.webauthn.WebAuthn
import com.stytch.java.consumer.api.webauthn.WebAuthnImpl
import com.stytch.java.http.HttpClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.jose4j.jwk.HttpsJwks
public object StytchClient {
    private lateinit var httpClient: HttpClient
    private lateinit var httpsJwks: HttpsJwks
    private lateinit var jwtOptions: JwtOptions

    @JvmStatic
    public lateinit var cryptoWallets: CryptoWallets

    @JvmStatic
    public lateinit var m2m: M2M

    @JvmStatic
    public lateinit var magicLinks: MagicLinks

    @JvmStatic
    public lateinit var oauth: OAuth

    @JvmStatic
    public lateinit var otps: OTPs

    @JvmStatic
    public lateinit var passwords: Passwords

    @JvmStatic
    public lateinit var sessions: Sessions

    @JvmStatic
    public lateinit var totps: TOTPs

    @JvmStatic
    public lateinit var users: Users

    @JvmStatic
    public lateinit var webauthn: WebAuthn

    @JvmStatic
    public fun configure(
        projectId: String,
        secret: String,
    ) {
        val baseUrl = getBaseUrl(projectId)
        httpClient =
            HttpClient(
                baseUrl = baseUrl,
                projectId = projectId,
                secret = secret,
            )
        jwtOptions =
            JwtOptions(
                audience = projectId,
                issuer = "stytch.com/$projectId",
                type = "JWT",
            )
        val coroutineScope = CoroutineScope(SupervisorJob())
        httpsJwks = HttpsJwks("$baseUrl/v1/sessions/jwks/$projectId")

        cryptoWallets = CryptoWalletsImpl(httpClient, coroutineScope)
        m2m = M2MImpl(httpClient, coroutineScope, httpsJwks, jwtOptions)
        magicLinks = MagicLinksImpl(httpClient, coroutineScope)
        oauth = OAuthImpl(httpClient, coroutineScope)
        otps = OTPsImpl(httpClient, coroutineScope)
        passwords = PasswordsImpl(httpClient, coroutineScope)
        sessions = SessionsImpl(httpClient, coroutineScope, httpsJwks, jwtOptions)
        totps = TOTPsImpl(httpClient, coroutineScope)
        users = UsersImpl(httpClient, coroutineScope)
        webauthn = WebAuthnImpl(httpClient, coroutineScope)
    }

    /**
     * Resolve the base URL for the Stytch API environment.
     */
    private fun getBaseUrl(projectId: String): String =
        when (projectId.startsWith("project-test")) {
            true -> BASE_TEST_URL
            false -> BASE_LIVE_URL
        }
}
