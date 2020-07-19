package com.aoc.intcode.circuit

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.Day
import com.aoc.input.InputReader
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CircuitControllerTest {
    private val input = InputReader.read<String>(Day(7)).asSingleString()

    @Test
    @DisplayName("Given Day 7 Part 1 Puzzle Input, when calculating the maximum thruster signal, then it should return 21860")
    fun daySevenPartOneSolution() {
        val controller = CircuitController(input)
        val maximumThrusterSignal = controller.calculateMaximumThrusterSignal(SeriesAmplificationCircuit())
        assertThat(maximumThrusterSignal).isEqualTo(21860)
    }

    @Test
    @DisplayName("Given Day 7 Part 2 Puzzle Input, when calculating the maximum thruster signal, then it should return 2645740")
    fun daySevenPartTwoSolution() {
        val controller = CircuitController(input)
        val maximumThrusterSignal = controller.calculateMaximumThrusterSignal(LoopbackAmplificationCircuit())
        assertThat(maximumThrusterSignal).isEqualTo(2645740)
    }
}