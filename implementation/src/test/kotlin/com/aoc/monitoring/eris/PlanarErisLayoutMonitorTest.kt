package com.aoc.monitoring.eris

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.Day
import com.aoc.input.TestInputReader
import com.aoc.input.InputReader
import org.junit.jupiter.api.Test

class PlanarErisLayoutMonitorTest {
    @Test
    fun example() {
        val scanData = TestInputReader().readInputAsString("/eris/example-scan-1.txt").value
        val monitor = PlanarErisLayoutMonitor(PlanarErisPlanetLayout(scanData))
        assertThat(monitor.watchForRepeatingLayout().getBioDiversityRating()).isEqualTo(2129920)
    }

    @Test
    fun solutionPartOne() {
        val scanData = InputReader.read<String>(Day(24)).value
        val monitor = PlanarErisLayoutMonitor(PlanarErisPlanetLayout(scanData))
        assertThat(monitor.watchForRepeatingLayout().getBioDiversityRating()).isEqualTo(32506764)
    }
}