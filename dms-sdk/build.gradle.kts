import org.gradle.api.JavaVersion.VERSION_1_8
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    idea
    // kotlin
    val kotlinVersion = "1.3.50"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.jpa") version kotlinVersion
}

group = "com.monkeydp.daios.dms"
java.sourceCompatibility = VERSION_1_8

dependencies {
    // kotlin
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.2")
    // tools
    api("com.monkeydp:tools:+")
    // jackson
    api("com.fasterxml.jackson.core:jackson-annotations:2.9.0")
    // swagger
    api("io.swagger:swagger-annotations:1.5.20")
    // persistence api
    api("javax.persistence:javax.persistence-api:2.2")
    // faker
    implementation("com.github.javafaker:javafaker:1.0.1")
    // code generator
    implementation("com.squareup:kotlinpoet:1.4.4")
    // junit
    testImplementation("junit:junit:4.12")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = VERSION_1_8.toString()
    }
}