plugins {
    jacoco
}

val testCoverage by tasks.registering {
    group = "verification"
    description = "Runs the unit tests with coverage."

    dependsOn(":implementation:test", ":implementation:jacocoTestReport", ":implementation:common:jacocoTestReport",
            ":implementation:jacocoTestCoverageVerification", ":implementation:common:jacocoTestCoverageVerification")
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