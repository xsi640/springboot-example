dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("net.devh:grpc-client-spring-boot-starter:2.4.0.RELEASE")
    implementation(project(":springboot-grpc-facade"))
}