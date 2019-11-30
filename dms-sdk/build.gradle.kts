import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    idea
    // kotlin
    val kotlinVersion = "1.3.50"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    kotlin("plugin.jpa") version kotlinVersion
}

group = "com.monkeydp.daios.dms"
java.sourceCompatibility = JavaVersion.VERSION_1_8

val generatedDir = File(buildDir, "classes/generated")
the<JavaPluginConvention>().sourceSets { "main"{ java.srcDir(generatedDir) } }
idea.module.generatedSourceDirs.add(generatedDir)

dependencies {
    // kotlin
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.2")
    // tools
    api("com.monkeydp:tools:+")
    // jackson
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.9.0")
    // swagger
    implementation("io.swagger:swagger-annotations:1.5.20")
    // persistence api
    implementation("javax.persistence:javax.persistence-api:2.2")
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
        jvmTarget = "1.8"
    }
}