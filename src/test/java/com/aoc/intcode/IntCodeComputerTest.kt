package com.aoc.intcode

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class IntCodeComputerTest {
    private val computer = IntCodeComputer()

    @Test
    @DisplayName("Given a valid program (example one), when computing, then it should return the correct final state")
    internal fun exampleProgramOne() {
        val program = "1,0,0,0,99"
        val finalState = computer.compute(program)
        assertThat(finalState).isEqualTo("2,0,0,0,99")
    }

    @Test
    @DisplayName("Given a valid program (example two), when computing, then it should return the correct final state")
    internal fun exampleProgramTwo() {
        val program = "2,3,0,3,99"
        val finalState = computer.compute(program)
        assertThat(finalState).isEqualTo("2,3,0,6,99")
    }

    @Test
    @DisplayName("Given a valid program (example three), when computing, then it should return the correct final state")
    internal fun exampleProgramThree() {
        val program = "2,4,4,5,99,0"
        val finalState = computer.compute(program)
        assertThat(finalState).isEqualTo("2,4,4,5,99,9801")
    }

    @Test
    @DisplayName("Given a valid program (example four), when computing, then it should return the correct final state")
    internal fun exampleProgramFour() {
        val program = "1,1,1,4,99,5,6,0,99"
        val finalState = computer.compute(program)
        assertThat(finalState).isEqualTo("30,1,1,4,2,5,6,0,99")
    }
}