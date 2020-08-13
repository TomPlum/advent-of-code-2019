package com.aoc.annotation

/**
 * Indicates that an individual test or suite should wait the specified [time] for VisualVM
 * to attach to the JVM process and give you time to start profiling or sampling the thread.
 */
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class WaitForVisualVM(val time: Int = 5000)