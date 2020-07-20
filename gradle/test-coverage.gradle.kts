val testCoverage by tasks.registering {
    group = "verification"
    description = "Runs the unit tests with coverage."

    dependsOn(":implementation:test", ":implementation:jacocoTestReport", ":implementation:common:jacocoTestReport",
            ":implementation:jacocoTestCoverageVerification", ":implementation:common:jacocoTestCoverageVerification")
    val jacocoTestReport = tasks.findByName("jacocoTestReport")
    jacocoTestReport?.mustRunAfter(tasks.findByName("test"))
    tasks.findByName("jacocoTestCoverageVerification")?.mustRunAfter(jacocoTestReport)
}

configure<JacocoPluginExtension> {
    toolVersion = "0.8.5"
    reportsDir = file("$buildDir/reports")
}

tasks.getByName("jacocoTestReport") {
    group = "Reporting"
    description = "Generate Jacoco test coverage report"

    configure<JacocoReportsContainer> {
        xml.isEnabled = true
        html.isEnabled = true
        csv.isEnabled = false
    }
}

tasks.getByName("jacocoTestCoverageVerification") {
    configure<JacocoCoverageVerification> {
        configure<JacocoViolationRulesContainer> {
            configure<JacocoViolationRule> {
                configure<JacocoLimit> {
                    minimum = "0.9".toBigDecimal()
                }
            }
        }
    }
}