package com.aoc.intcode.network;

import com.aoc.AdventBenchmarkingSuite;
import com.aoc.BenchmarkInputReader;
import com.aoc.TestInputReader;
import com.aoc.input.Day;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Warmup;

@AdventBenchmarkingSuite
public class IntCodeComputerNetworkTest {

    private static final String software = BenchmarkInputReader.read(Day.Companion.from(23)).asSingleString();

    @Benchmark
    @Warmup(iterations = 1)
    public void solutionPartOne() {
        final IntCodeComputerNetwork network = new IntCodeComputerNetwork(software);
        network.attachPacketAnalyserAtTargetAddress(new NetworkAddress(255));
        network.boot();
    }

    @Benchmark
    public void solutionPartTwo() {
        new IntCodeComputerNetwork(software).boot();
    }
}
