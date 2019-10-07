import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    // spring
    id("org.springframework.boot") version "2.1.9.RELEASE"
    id("io.spring.dependency-management") version "1.0.8.RELEASE"
    // kotlin
    kotlin("jvm") version "1.2.71"
    kotlin("plugin.spring") version "1.2.71"
}

group = "com.monkeydp.daios"
version = "0.0.1-SNAPSHOT"

allprojects{
    // spring
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    // kotlin
    apply(plugin ="org.jetbrains.kotlin.jvm")
    apply(plugin ="org.jetbrains.kotlin.plugin.spring")

    java.sourceCompatibility = JavaVersion.VERSION_1_8

    dependencies {
        // kotlin
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        // monkeydp
        implementation("com.monkeydp:tools")
    }
}

subprojects{
    group = "com.monkeydp.daios.dms"

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "1.8"
        }
    }
}
