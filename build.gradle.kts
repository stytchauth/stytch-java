@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    kotlin("jvm") version libs.versions.kotlin
    kotlin("kapt") version libs.versions.kotlin
    id("org.jlleitschuh.gradle.ktlint") version "12.1.0"
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
}

allprojects {
    ktlint {
        version.set("1.2.1")
        verbose.set(true)
        outputToConsole.set(true)
    }
    configurations.all {
        resolutionStrategy {
            // Dependabot forces
            force("com.fasterxml.woodstox:woodstox-core:6.4.0")
            force("com.google.guava:guava:32.0.1-jre")
            force("com.fasterxml.jackson.core:jackson-core:2.15.0")
            force("com.fasterxml.jackson.core:jackson-databind:2.15.2")
            force("ch.qos.logback:logback-core:1.3.15")
        }
    }
}

buildscript {
    dependencies {
        classpath(libs.dokka)
    }
}

repositories {
    mavenCentral()
}

group = "com.stytch.java"
apply(from = project.rootProject.file("version.gradle.kts"))

task("printVersion") {
    group = "Documentation"
    description = "Prints the version of the SDK. Used for autoreleasing the SDK from GitHub"
    doLast {
        println(version)
    }
}

task("runOnGitHub") {
    group = "custom"
    description = "\$ ./gradlew runOnGitHub # runs on GitHub Action"
    dependsOn(
        ":stytch:ktlintCheck",
        ":stytch:test",
    )
}
