@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    kotlin("jvm") version libs.versions.kotlin
    kotlin("kapt") version libs.versions.kotlin
    id("io.github.gradle-nexus.publish-plugin") version "1.1.0"
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

// Publishing setup
ext["ossrhUsername"] = System.getenv("OSSRH_USERNAME") ?: ""
ext["ossrhPassword"] = System.getenv("OSSRH_PASSWORD") ?: ""
ext["sonatypeStagingProfileId"] = System.getenv("SONATYPE_STAGING_PROFILE_ID") ?: ""
ext["signing.keyId"] = System.getenv("SIGNING_KEY_ID") ?: ""
ext["signing.password"] = System.getenv("SIGNING_PASSWORD") ?: ""
ext["signing.key"] = System.getenv("SIGNING_KEY") ?: ""

// Set up Sonatype repository
nexusPublishing {
    repositories {
        sonatype {
            stagingProfileId.set(project.ext["sonatypeStagingProfileId"] as String)
            username.set(project.ext["ossrhUsername"] as String)
            password.set(project.ext["ossrhPassword"] as String)
            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
        }
    }
}

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
