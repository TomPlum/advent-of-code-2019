package com.aoc.monitoring.eris

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.TestInputReader
import com.aoc.input.Day
import com.aoc.input.InputReader
import org.junit.jupiter.api.Test

class RecursiveErisLayoutMonitorTest {
    @Test
    fun example() {
        val scanData = TestInputReader().readInputAsString("/eris/example-scan-1.txt").values
        val monitor = RecursiveErisLayoutMonitor(RecursiveErisPlanetLayout(scanData))
        assertThat(monitor.watch(10)).isEqualTo(99)
    }

    @Test
    fun solutionPartTwo() {
        val scanData = InputReader().readInputString(Day.from(24)).values
        val monitor = RecursiveErisLayoutMonitor(RecursiveErisPlanetLayout(scanData))
        assertThat(monitor.watch(200)).isEqualTo(1963)
    }
}