rootProject.name = "stytch-java"
include("stytch", "workbench")

plugins {
    id("com.gradle.develocity") version ("3.17.5")
}
develocity {
    buildScan {
        termsOfUseUrl.set("https://gradle.com/help/legal-terms-of-use")
        termsOfUseAgree.set("yes")
    }
}
