dependencies {
    implementation(project(":implementation"))
    implementation(project(":implementation:common"))

    //JUnit & Testing
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.3.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.3.1")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.4.2")
    testImplementation("org.junit.platform:junit-platform-launcher:1.3.1")
    testImplementation("com.willowtreeapps.assertk:assertk-jvm:0.20")

    //Logging
    implementation("org.slf4j:slf4j-api:1.7.30")
    runtimeOnly("org.apache.logging.log4j:log4j-core:2.13.0")
    testRuntimeOnly("org.apache.logging.log4j:log4j-slf4j-impl:2.13.0")
}

val test by tasks.getting(Test::class) {
    useJUnitPlatform { }
}