apply(from = "$rootDir/gradle/testing-dependencies.gradle.kts")

dependencies {
    implementation(project(":implementation"))
    implementation(project(":implementation:common"))
    implementation("org.junit.jupiter:junit-jupiter-api:5.3.1")
}