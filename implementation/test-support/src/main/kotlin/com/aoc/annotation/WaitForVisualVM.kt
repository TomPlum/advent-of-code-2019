package com.aoc.annotation

/**
 * Indicates that an individual test or suite should wait the specified time in [seconds] for VisualVM
 * to attach to the JVM process and give you time to start profiling or sampling the thread.
 */
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class WaitForVisualVM(val seconds: Int = 5)