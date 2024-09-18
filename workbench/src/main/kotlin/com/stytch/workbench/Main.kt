package com.stytch.workbench
import com.stytch.java.consumer.StytchClient
import com.stytch.java.consumer.models.magiclinksemail.LoginOrCreateRequest

suspend fun main() {
    val stytchClient = StytchClient(
        projectId = "project-test-....",
        secret = "secret-test-....",
    )
    val result = stytchClient.magicLinks.email.loginOrCreate(
            LoginOrCreateRequest(
                email = "email@address.com",
                signupExpirationMinutes = 30,
            ),
        )
    println(result)
}
