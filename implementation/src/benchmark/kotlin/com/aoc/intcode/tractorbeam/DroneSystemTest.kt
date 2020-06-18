package com.aoc.intcode.tractorbeam

import com.aoc.input.Day
import com.aoc.input.InputReader
import org.junit.jupiter.api.Test
import org.openjdk.jmh.annotations.Benchmark
import kotlin.math.abs

class DroneSystemTest {
    @Test
    @Benchmark
    fun solutionPartTwo() {
        val input = InputReader().readInputAsSingleString(Day.from(19))
        val system = DroneSystem(input).scanAreaForSantasShip(100)
        abs(1)
    }
}
