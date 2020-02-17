package com.aoc.intcode.amplifier

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class SeriesAmplificationCircuitTest {
    private val circuit = SeriesAmplificationCircuit()

    @Test
    @DisplayName("Given a phase setting sequence of 4,3,2,1,0, when calculating the maximum thruster signal, then it should return 43210")
    fun partOneExampleOne() {
        val software = "3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0"
        val phaseSettings = PhaseSettings(setOf(4, 3, 2, 1, 0))
        val maximumThrusterSignal = circuit.calculateThrusterSignal(software, phaseSettings)
        assertThat(maximumThrusterSignal).isEqualTo(43210)
    }

    @Test
    @DisplayName("Given a phase setting sequence of 0,1,2,3,4, when calculating the maximum thruster signal, then it should return 54321")
    fun partOneExampleTwo() {
        val software = "3,23,3,24,1002,24,10,24,1002,23,-1,23,101,5,23,23,1,24,23,23,4,23,99,0,0"
        val phaseSettings = PhaseSettings(setOf(0, 1, 2, 3, 4))
        val maximumThrusterSignal = circuit.calculateThrusterSignal(software, phaseSettings)
        assertThat(maximumThrusterSignal).isEqualTo(54321)
    }

    @Test
    @DisplayName("Given a phase setting sequence of 1,0,4,3,2, when calculating the maximum thruster signal, then it should return 65210")
    fun partOneExampleThree() {
        val software = "3,31,3,32,1002,32,10,32,1001,31,-2,31,1007,31,0,33,1002,33,7,33,1,33,31,31,1,32,31,31,4,31,99,0,0,0"
        val phaseSettings = PhaseSettings(setOf(1, 0, 4, 3, 2))
        val maximumThrusterSignal = circuit.calculateThrusterSignal(software, phaseSettings)
        assertThat(maximumThrusterSignal).isEqualTo(65210)
    }
}