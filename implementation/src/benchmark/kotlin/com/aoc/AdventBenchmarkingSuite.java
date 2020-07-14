package com.aoc;

import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.TimeUnit;

@Inherited
@Documented
@BenchmarkMode(Mode.AverageTime)
@Retention(RetentionPolicy.RUNTIME)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public @interface AdventBenchmarkingSuite { }
