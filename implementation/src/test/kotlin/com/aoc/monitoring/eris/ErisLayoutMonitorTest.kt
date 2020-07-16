package com.aoc.monitoring.eris

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.input.InputReader
import org.junit.jupiter.api.Test

class ErisLayoutMonitorTest {
    @Test
    fun example() {
        val scanData = InputReader().readInputAsString("/eris/example-scan-1.txt").values
        val monitor = ErisLayoutMonitor(ErisPlanetLayout(scanData))
        assertThat(monitor.watchForMatchingLayout().getBioDiversityRating()).isEqualTo(2129920)
    }
}