rootProject.name = "dms-sdk"
includeBuild("../tools") {
    dependencySubstitution {
        substitute(module("com.monkeydp:tools")).with(project(":"))
    }
}