package com.aoc.monitoring.eris

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.input.InputReader
import org.junit.jupiter.api.Test

class RecursiveErisLayoutMonitorTest {
    @Test
    fun example() {
        val scanData = InputReader().readInputAsString("/eris/example-scan-1.txt").values
        val monitor = RecursiveErisLayoutMonitor(RecursiveErisPlanetLayout(scanData))
        assertThat(monitor.watch(10)).isEqualTo(99)
    }
}