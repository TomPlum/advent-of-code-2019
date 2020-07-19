package com.aoc.monitoring.eris

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.TestInputReader
import com.aoc.input.Day
import com.aoc.input.InputReader
import org.junit.jupiter.api.Test

class PlanarErisLayoutMonitorTest {
    @Test
    fun example() {
        val scanData = TestInputReader().readInputAsString("/eris/example-scan-1.txt").values
        val monitor = PlanarErisLayoutMonitor(PlanarErisPlanetLayout(scanData))
        assertThat(monitor.watchForRepeatingLayout().getBioDiversityRating()).isEqualTo(2129920)
    }

    @Test
    fun solutionPartOne() {
        val scanData = InputReader().readInputString(Day.from(24)).values
        val monitor = PlanarErisLayoutMonitor(PlanarErisPlanetLayout(scanData))
        assertThat(monitor.watchForRepeatingLayout().getBioDiversityRating()).isEqualTo(32506764)
    }
}