import org.gradle.api.JavaVersion.VERSION_1_8
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    idea
    // kotlin
    val kotlinVersion = "1.3.72"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.jpa") version kotlinVersion
    id("io.freefair.aspectj.post-compile-weaving") version "4.1.5"
}

group = "com.monkeydp.daios.dms"
version = "0.0.4-SNAPSHOT"
java.sourceCompatibility = VERSION_1_8

dependencies {
    // kotlin
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.2")
    // tools
    api("com.monkeydp:tools:1.0.+")
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
    testImplementation(kotlin("test-junit5"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.3.2")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = VERSION_1_8.toString()
    }
}