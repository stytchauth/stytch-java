@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    kotlin("jvm") version libs.versions.kotlin
    kotlin("kapt") version libs.versions.kotlin
    id("maven-publish")
    id("org.jetbrains.dokka")
    id("org.jreleaser").version("1.20.0")
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

val dokkaJavadoc by tasks.getting(org.jetbrains.dokka.gradle.DokkaTask::class)
val javadocJar: TaskProvider<Jar> by tasks.registering(Jar::class) {
    dependsOn(dokkaJavadoc)
    archiveClassifier.set("javadoc")
    from(dokkaJavadoc.outputDirectory)
}

// Configure module publishing
tasks.kotlinSourcesJar {
    archiveClassifier.set("sources")
    from(
        sourceSets.main
            .get()
            .kotlin.srcDirs,
    )
    from(
        sourceSets.main
            .get()
            .java.srcDirs,
    )
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
                    name.set("com.stytch.java")
                    description.set("Stytch Java SDK")
                    url.set("https://github.com/stytch-auth/stytch-java")
                    licenses {
                        license {
                            name.set("Stytch License")
                            url.set("https://github.com/stytchauth/stytch-java/blob/main/LICENSE")
                        }
                        developers {
                            developer {
                                id.set("Stytch")
                                name.set("Stytch")
                                email.set("developers@stytch.com")
                            }
                        }
                        scm {
                            connection.set("scm:git:github.com/stytchauth/stytch-java.git")
                            developerConnection.set("scm:git:ssh://github.com/stytchauth/stytch-java.git")
                            url.set("https://github.com/stytchauth/stytch-java/tree/main")
                        }
                    }
                }
            }
            repositories {
                maven {
                    url =
                        layout.buildDirectory
                            .dir("staging-deploy")
                            .get()
                            .asFile
                            .toURI()
                }
            }
        }
    }
}

jreleaser {
    gitRootSearch = true
    signing {
        setActive("ALWAYS")
        armored = true
    }
    deploy {
        maven {
            mavenCentral {
                create("sonatype") {
                    setActive("ALWAYS")
                    setStage("FULL")
                    url = "https://central.sonatype.com/api/v1/publisher"
                    stagingRepository(
                        layout.buildDirectory
                            .dir("staging-deploy")
                            .get()
                            .asFile.absolutePath,
                    )
                    verifyPom = false
                }
            }
        }
    }
    project {
        name = "sdk"
        description = "Stytch Java SDK"
        version = rootProject.version as String
        authors.add("Stytch Developers")
        license.set("Stytch License")
    }
    release {
        github {
            enabled.set(false)
        }
    }
}
