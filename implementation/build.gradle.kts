plugins {
    jacoco
    idea
}

val benchmarkImplementation: Configuration by configurations.creating {
    extendsFrom(configurations.testImplementation.get())
}

val benchmarkRuntimeOnly: Configuration by configurations.creating {
    extendsFrom(configurations.testRuntimeOnly.get())
}

val benchmarkAnnotationProcessor: Configuration by configurations.creating {
    extendsFrom(configurations.annotationProcessor.get())
}

sourceSets {
    create("benchmark") {
        withConvention(org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet::class) {
            kotlin.srcDir("src/benchmark/kotlin")
            resources.srcDir("src/benchmark/resources")
            compileClasspath += sourceSets["main"].output + configurations["testRuntimeClasspath"]
            runtimeClasspath += output + compileClasspath + sourceSets["test"].runtimeClasspath
        }
    }
}

idea {
    module {
        val testSources = testSourceDirs
        testSources.addAll(project.sourceSets.getByName("benchmark").allSource.srcDirs)
        testSources.addAll(project.sourceSets.getByName("benchmark").resources.srcDirs)
        testSourceDirs = testSources
    }
}

dependencies {
    //Compile 'Common' Sub-Module
    implementation(project(":implementation:common"))

    //JUnit 5 & Testing
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.3.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.3.1")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.4.2")
    testImplementation("org.junit.platform:junit-platform-launcher:1.3.1")
    testImplementation("com.willowtreeapps.assertk:assertk-jvm:0.20")

    //JMH
    benchmarkImplementation("org.openjdk.jmh:jmh-core:1.23")
    //benchmarkRuntimeOnly("org.codehaus.mojo:exec-maven-plugin:3.0.0")
    benchmarkAnnotationProcessor("org.openjdk.jmh:jmh-generator-annprocess:1.23")

    //Logging
    implementation("org.slf4j:slf4j-api:1.7.30")
    runtimeOnly("org.apache.logging.log4j:log4j-core:2.13.0")
    testRuntimeOnly("org.apache.logging.log4j:log4j-slf4j-impl:2.13.0")
}

subprojects {
    apply(plugin = "jacoco")
}

val test by tasks.getting(Test::class) {
    useJUnitPlatform { }
}

val testCoverage by tasks.registering {
    group = "verification"
    description = "Runs the unit tests with coverage."

    dependsOn(":test", ":jacocoTestReport", ":jacocoTestCoverageVerification")
    val jacocoTestReport = tasks.findByName("jacocoTestReport")
    jacocoTestReport?.mustRunAfter(tasks.findByName("test"))
    tasks.findByName("jacocoTestCoverageVerification")?.mustRunAfter(jacocoTestReport)
}

val benchmark by tasks.registering(type = JavaExec::class) {
    dependsOn("benchmarkClasses")
    group = "benchmarking"
    description = "Runs the JMH benchmark tests."
    main = "org.openjdk.jmh.Main"
    classpath = sourceSets["benchmark"].runtimeClasspath
}

jacoco {
    toolVersion = "0.8.5"
    reportsDir = file("$buildDir/reports")
}

tasks.jacocoTestReport {
    group = "Reporting"
    description = "Generate Jacoco test coverage report"

    reports {
        xml.isEnabled = true
        html.isEnabled = true
        csv.isEnabled = false
    }
}

tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {
            limit {
                minimum = "0.9".toBigDecimal()
            }
        }
    }
}