package com.aoc.intcode.amplifier

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.input.Day
import com.aoc.input.InputReader
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CircuitControllerTest {
    private val puzzleInput = InputReader().readInputAsSingleString(Day.from(7))

    @Test
    @DisplayName("Given Day 7 Part 1 Puzzle Input, when calculating the maximum thruster signal, then it should return 21860")
    fun daySevenPartOneSolution() {
        val controller = CircuitController(puzzleInput)
        val maximumThrusterSignal = controller.calculateMaximumThrusterSignal()
        assertThat(maximumThrusterSignal).isEqualTo(21860)
    }
}