package com.aoc.intcode.amplifier

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class AmplificationCircuitTest {
    @Test
    @DisplayName("Given a phase setting sequence of 4,3,2,1,0, when calculating the maximum thruster signal, then it should return 43210")
    fun partOneExampleOne() {
        val phaseSettings = PhaseSettings(setOf(4, 3, 2, 1, 0))
        val controller = AmplificationCircuit(phaseSettings)
        val maximumThrusterSignal = controller.calculateMaximumThrusterSignal()
        assertThat(maximumThrusterSignal).isEqualTo(43210)
    }

    @Test
    @DisplayName("Given a phase setting sequence of 0,1,2,3,4, when calculating the maximum thruster signal, then it should return 54321")
    fun partOneExampleTwo() {
        val phaseSettings = PhaseSettings(setOf(0, 1, 2, 3, 4))
        val controller = AmplificationCircuit(phaseSettings)
        val maximumThrusterSignal = controller.calculateMaximumThrusterSignal()
        assertThat(maximumThrusterSignal).isEqualTo(54321)
    }

    @Test
    @DisplayName("Given a phase setting sequence of 1,0,4,3,2, when calculating the maximum thruster signal, then it should return 65210")
    fun partOneExampleThree() {
        val phaseSettings = PhaseSettings(setOf(1, 0, 4, 3, 2))
        val controller = AmplificationCircuit(phaseSettings)
        val maximumThrusterSignal = controller.calculateMaximumThrusterSignal()
        assertThat(maximumThrusterSignal).isEqualTo(65210)
    }
}