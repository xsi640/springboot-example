rootProject.name = "springboot-example"

fun defineSubProject(name: String, subdir: String = "") {
    val projectName = "springboot-$name"
    include(projectName)
    if (subdir != "") {
        val dir = "$subdir/$projectName"
        mkdir("$dir/src/main/java")
        mkdir("$dir/src/main/resources")
        project(":$projectName").projectDir = file(dir)
    } else {
        mkdir("$projectName/src/main/java")
        mkdir("$projectName/src/main/resources")
    }
}

defineSubProject("aop")
defineSubProject("async")
defineSubProject("commandline")
defineSubProject("freemaker")
defineSubProject("interceptor")
defineSubProject("jdbctemplate")
defineSubProject("jpa")
defineSubProject("listener")
defineSubProject("mongo")
defineSubProject("mongotemplate")
defineSubProject("neo4j")
defineSubProject("properties")
defineSubProject("rabbitmq")
defineSubProject("kafka")
defineSubProject("redis")
defineSubProject("schedule")
defineSubProject("slf4j-log4j2")
defineSubProject("slf4j-logback")
defineSubProject("sse")
defineSubProject("thymeleaf")
defineSubProject("updatefile")
defineSubProject("webflux")
defineSubProject("websocket")
defineSubProject("websocket2")
defineSubProject("graphql")

defineSubProject("demo-starter", "starter-demo")
defineSubProject("starter-demo-test", "starter-demo")

defineSubProject("grpc-server", "grpc")
defineSubProject("grpc-facade", "grpc")
defineSubProject("grpc-client", "grpc")

defineSubProject("security-jwt")

defineSubProject("invocation-interface")