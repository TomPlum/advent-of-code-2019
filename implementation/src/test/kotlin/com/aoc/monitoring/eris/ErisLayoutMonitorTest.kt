package com.aoc.monitoring.eris

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.input.Day
import com.aoc.input.InputReader
import org.junit.jupiter.api.Test

class ErisLayoutMonitorTest {
    @Test
    fun example() {
        val scanData = InputReader().readInputAsString("/eris/example-scan-1.txt").values
        val monitor = ErisLayoutMonitor(ErisPlanetLayout(scanData))
        assertThat(monitor.watchForRepeatingLayout().getBioDiversityRating()).isEqualTo(2129920)
    }

    @Test
    fun solutionPartOne() {
        val scanData = InputReader().readInputString(Day.from(24)).values
        val monitor = ErisLayoutMonitor(ErisPlanetLayout(scanData))
        assertThat(monitor.watchForRepeatingLayout().getBioDiversityRating()).isEqualTo(32506764)
    }
}