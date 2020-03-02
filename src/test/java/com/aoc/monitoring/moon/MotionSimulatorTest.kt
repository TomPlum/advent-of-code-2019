package com.aoc.monitoring.moon

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.input.InputReader
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class MotionSimulatorTest {
    @Test
    @DisplayName("Given Day 12 Example Input Data, when calculating the systems total energy after 10 time steps, then it should return 179")
    fun totalEnergyExample() {
        val exampleInput = InputReader().readInputAsString("/moon/example-moon-positions.txt").values
        val moonPositions = ScanningModule().scanLocalSectorForMoons(exampleInput)
        val motionSimulator = MotionSimulator(moonPositions)
        motionSimulator.simulate(10)
        val totalSystemEnergy = motionSimulator.calculateTotalSystemEnergy()
        assertThat(totalSystemEnergy).isEqualTo(179)
    }
}