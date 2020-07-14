package com.aoc;

import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * Defines the common configuration for all JMH (Java Micro-Benchmarking Harness) test suites.
 */
@Inherited
@Documented
@Target(ElementType.TYPE)
@BenchmarkMode(Mode.AverageTime)
@Retention(RetentionPolicy.RUNTIME)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public @interface AdventBenchmarkingSuite { }
