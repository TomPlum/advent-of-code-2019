val benchmarkImplementation: Configuration by configurations.creating {
    extendsFrom(configurations.getByName("testImplementation").copy())
}

val benchmarkRuntimeOnly: Configuration by configurations.creating {
    extendsFrom(configurations.getByName("testRuntimeOnly").copy())
}

val benchmarkAnnotationProcessor: Configuration by configurations.creating {
    extendsFrom(configurations.getByName("annotationProcessor").copy())
}

configure<SourceSetContainer> {
    named("benchmark") {
        withConvention(org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet::class) {
            kotlin.srcDir("src/benchmark/kotlin")
            resources.srcDir("src/benchmark/resources")
            compileClasspath += sourceSets["main"].output + configurations["testRuntimeClasspath"]
            runtimeClasspath += output + compileClasspath + sourceSets["test"].runtimeClasspath
        }
    }
}

val benchmark by tasks.registering(type = JavaExec::class) {
    dependsOn("benchmarkClasses", "compileKotlin")
    group = "benchmarking"
    description = "Runs the JMH benchmark tests."
    main = "org.openjdk.jmh.Main"
    classpath = sourceSets["benchmark"].runtimeClasspath
}