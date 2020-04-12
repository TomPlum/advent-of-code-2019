dependencies {
    implementation(project(":implementation"))
    implementation(project(":implementation:common"))

    //Logging in Solutions
    runtimeOnly("org.apache.logging.log4j:log4j-slf4j-impl:2.13.0")
}