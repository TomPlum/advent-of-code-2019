import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

apply(from = "$rootDir/gradle/testing-dependencies.gradle.kts")

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
        withConvention(KotlinSourceSet::class) {
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
    //Gradle Sub-Project Dependencies
    implementation(project(":implementation:common"))
    testImplementation(project(":implementation:test-support"))

    //JMH
    benchmarkImplementation("org.openjdk.jmh:jmh-core:1.23")
    //benchmarkRuntimeOnly("org.codehaus.mojo:exec-maven-plugin:3.0.0")
    benchmarkAnnotationProcessor("org.openjdk.jmh:jmh-generator-annprocess:1.23")
}

subprojects {
    apply(plugin = "jacoco")
}

val test by tasks.getting(Test::class) {
    useJUnitPlatform { }
}

val benchmark by tasks.registering(type = JavaExec::class) {
    dependsOn("benchmarkClasses", "compileKotlin")
    group = "benchmarking"
    description = "Runs the JMH benchmark tests."
    main = "org.openjdk.jmh.Main"
    classpath = sourceSets["benchmark"].runtimeClasspath
}
