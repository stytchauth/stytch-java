@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    kotlin("jvm") version libs.versions.kotlin
    kotlin("kapt") version libs.versions.kotlin
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":stytch"))
}

kotlin {
    jvmToolchain(11)
}

application {
    mainClass.set("MainKt")
}

tasks.withType<JavaCompile> {
    options.compilerArgs.add("-parameters")
}
