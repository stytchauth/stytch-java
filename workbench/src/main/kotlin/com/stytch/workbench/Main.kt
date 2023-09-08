package com.stytch.workbench
import com.stytch.java.consumer.StytchClient
import com.stytch.java.consumer.models.magiclinksemail.LoginOrCreateRequest

suspend fun main() {
    StytchClient.configure(
        projectId = "project-test-....",
        secret = "secret-test-....",
    )
    val result =
        StytchClient.magicLinks.email.loginOrCreate(
            LoginOrCreateRequest(
                email = "email@address.com",
                signupExpirationMinutes = 30,
            ),
        )
    println(result)
}
