rootProject.name = "dms"
include("dms-sdk", "dms-core")
includeBuild("tools") {
    dependencySubstitution {
        substitute(module("com.monkeydp:tools")).with(project(":"))
    }
}