apply(from = "$rootDir/gradle/logging-dependencies.gradle.kts")
apply(from = "$rootDir/gradle/testing-dependencies.gradle.kts")

dependencies {
    testImplementation(project(":implementation:test-support"))
}