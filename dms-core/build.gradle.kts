import org.gradle.api.JavaVersion.VERSION_1_8
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    // spring
    id("org.springframework.boot") version "2.1.9.RELEASE"
    id("io.spring.dependency-management") version "1.0.8.RELEASE"
    // kotlin
    val kotlinVersion = "1.3.50"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    kotlin("plugin.allopen") version kotlinVersion
    // aspectj
    id("io.freefair.aspectj.post-compile-weaving") version "4.1.5"
}

group = "com.monkeydp.daios.dms"
java.sourceCompatibility = VERSION_1_8

val developmentOnly by configurations.creating
configurations {
    runtimeClasspath {
        extendsFrom(developmentOnly)
    }
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

dependencies {
    // spring
    implementation("org.springframework.boot:spring-boot-configuration-processor")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    // sdk
    api(project(":dms-sdk")) {
        exclude("org.springframework.boot")
    }
    // zip
    implementation("net.lingala.zip4j:zip4j:2.2.1")
    // command line
    implementation("org.apache.commons:commons-exec:1.3")
    // swagger
    implementation("io.springfox:springfox-swagger2:2.9.2")
    implementation("com.github.xiaoymin:swagger-bootstrap-ui:1.9.6")
    // h2db
    runtimeOnly("com.h2database:h2")
    // aspect
    aspect(project(":dms-sdk")) { setTransitive(false) }
    testAspect(project(":dms-sdk")) { setTransitive(false) }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = VERSION_1_8.toString()
    }
}

/**
 * Auto expand gradle properties.
 */
tasks.withType<ProcessResources> {
    filesMatching("application.yml") {
        expand(project.properties)
    }
}