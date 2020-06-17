plugins {
    jacoco
    kotlin("jvm")
    idea
}

val benchmarkingImplementation: Configuration by configurations.creating {
    extendsFrom(configurations.testImplementation.get())
}

val benchmarkingRuntimeOnly: Configuration by configurations.creating {
    extendsFrom(configurations.testRuntimeOnly.get())
}

sourceSets {
    val benchmark by creating {
        java.srcDir("src/benchmark/kotlin")
        compileClasspath += sourceSets["main"].output + sourceSets["test"].output
        runtimeClasspath += sourceSets["main"].output + sourceSets["test"].output

        dependencies {
            benchmarkingImplementation(project(":implementation:common"))
            benchmarkingImplementation(kotlin("stdlib-jdk8"))
        }
    }
}

idea {
    module {
        val testSources = testSourceDirs
        testSources.addAll(project.sourceSets.getByName("benchmark").java.srcDirs)
        testSources.addAll(project.sourceSets.getByName("benchmark").resources.srcDirs)
        testSourceDirs = testSources
    }
}

dependencies {
    //Compile 'Common' Sub-Module
    implementation(project(":implementation:common"))

    //JUnit & Testing
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.3.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.3.1")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.4.2")
    testImplementation("org.junit.platform:junit-platform-launcher:1.3.1")
    testImplementation("com.willowtreeapps.assertk:assertk-jvm:0.20")
    benchmarkingImplementation("org.openjdk.jmh:jmh-generator-annprocess:1.23")

    //Logging
    implementation("org.slf4j:slf4j-api:1.7.30")
    runtimeOnly("org.apache.logging.log4j:log4j-core:2.13.0")
    testRuntimeOnly("org.apache.logging.log4j:log4j-slf4j-impl:2.13.0")
}

subprojects {
    apply(plugin = "jacoco")
    apply(plugin = "kotlin")
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