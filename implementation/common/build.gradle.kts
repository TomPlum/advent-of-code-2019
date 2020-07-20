apply(from = "$rootDir/gradle/logging-dependencies.gradle.kts")

dependencies {
    testImplementation(project(":implementation:test-support"))
}