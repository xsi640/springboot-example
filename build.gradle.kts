import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot") version "2.4.2"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("java")
}

allprojects {
    apply {
        plugin("java")
        plugin("idea")
        plugin("org.springframework.boot")
        plugin("io.spring.dependency-management")
    }
    group = "com.suyang"
    version = "1.0.0-SNAPSHOT"
    java.sourceCompatibility = JavaVersion.VERSION_1_8

    val user = System.getProperty("repoUser")
    val pwd = System.getProperty("repoPassword")

    repositories {
        mavenLocal()
        maven {
            credentials {
                username = user
                password = pwd
                isAllowInsecureProtocol = true
            }
            url = uri("http://172.16.11.231:8081/nexus/repository/maven2-group/")
        }
    }

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter")
        implementation("org.springframework.boot:spring-boot-starter-validation")
        implementation("org.springframework.boot:spring-boot-configuration-processor")

        implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

        compileOnly("org.projectlombok:lombok")
        annotationProcessor("org.projectlombok:lombok")

        testImplementation("org.springframework.boot:spring-boot-starter-test")
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    val jar: Jar by tasks
    val bootJar: BootJar by tasks
    jar.enabled = false
    bootJar.enabled = true
}