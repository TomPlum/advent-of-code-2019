package com.aoc.intcode.tractorbeam;

import com.aoc.AdventBenchmarkingSuite;
import com.aoc.BenchmarkInputReader;
import com.aoc.Day;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Warmup;

@AdventBenchmarkingSuite
public class DroneSystemTest {
    @Benchmark
    @Warmup(iterations = 1)
    @Fork(value = 1, warmups = 1)
    public void solutionPartTwo() {
        final String input = BenchmarkInputReader.read(new Day(19)).asSingleString();
        new DroneSystem(input).scanAreaForSantasShip(100);
    }
}
