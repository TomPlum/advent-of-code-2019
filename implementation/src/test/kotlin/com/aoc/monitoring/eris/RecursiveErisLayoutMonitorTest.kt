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
        val scanData = TestInputReader().readInputAsString("/eris/example-scan-1.txt").value
        val monitor = RecursiveErisLayoutMonitor(RecursiveErisPlanetLayout(scanData))
        assertThat(monitor.watch(10)).isEqualTo(99)
    }

    @Test
    fun solutionPartTwo() {
        val scanData = InputReader.read<String>(Day(24)).value
        val monitor = RecursiveErisLayoutMonitor(RecursiveErisPlanetLayout(scanData))
        assertThat(monitor.watch(200)).isEqualTo(1963)
    }
}