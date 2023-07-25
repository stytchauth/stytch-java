import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.util.archivesName

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    kotlin("jvm") version libs.versions.kotlin
    kotlin("kapt") version libs.versions.kotlin
    id("maven-publish")
    id("signing")
    id("org.jetbrains.dokka")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.coroutines)
    implementation(libs.coroutines.jdk8)
    implementation(libs.jose4j)
    implementation(libs.moshi)
    kapt(libs.moshi.codegen)
    implementation(libs.okhttp)

    testImplementation(libs.coroutines.test)
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.okhttp.mockwebserver)
}

kotlin {
    jvmToolchain(11)
    explicitApi()
}

val dokkaHtml by tasks.getting(org.jetbrains.dokka.gradle.DokkaTask::class)
val javadocJar: TaskProvider<Jar> by tasks.registering(Jar::class) {
    dependsOn(dokkaHtml)
    archiveClassifier.set("javadoc")
    from(dokkaHtml.outputDirectory)
}

// Configure module publishing
tasks.kotlinSourcesJar {
    archiveClassifier.set("sources")
    from(sourceSets.main.get().kotlin.srcDirs)
    from(sourceSets.main.get().java.srcDirs)
}
artifacts {
    archives(tasks.kotlinSourcesJar)
}
afterEvaluate {
    publishing {
        publications {
            create("release", MavenPublication::class.java) {
                groupId = project.rootProject.group as String
                artifactId = "sdk"
                version = project.rootProject.version as String
                from(components.findByName("kotlin"))
                artifact(tasks.kotlinSourcesJar)
                artifact(javadocJar)
                pom {
                    name.set("com.stytch.kotlin")
                    description.set("Stytch Kotlin SDK")
                    url.set("https://github.com/stytch-auth/stytch-kotlin")
                    licenses {
                        license {
                            name.set("Stytch License")
                            url.set("https://github.com/stytchauth/stytch-kotlin/blob/main/LICENSE")
                        }
                        developers {
                            developer {
                                id.set("Stytch")
                                name.set("Stytch")
                                email.set("developers@stytch.com")
                            }
                        }
                        scm {
                            connection.set("scm:git:github.com/stytchauth/stytch-kotlin.git")
                            developerConnection.set("scm:git:ssh://github.com/stytchauth/stytch-kotlin.git")
                            url.set("https://github.com/stytchauth/stytch-kotlin/tree/main")
                        }
                    }
                }
            }
        }
    }
}

signing {
    useInMemoryPgpKeys(
        rootProject.ext["signing.keyId"] as String,
        rootProject.ext["signing.key"] as String,
        rootProject.ext["signing.password"] as String,
    )
    sign(publishing.publications)
}
