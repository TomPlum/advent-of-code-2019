package com.aoc.intcode

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.common.InputReader
import com.aoc.value.Day
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class IntCodeComputerTest {
    private val puzzleInput = InputReader().readInputAsSingleString(Day.from(2))

    @Test
    @DisplayName("Given a valid program (example one), when computing, then it should return the correct final state")
    internal fun exampleProgramOne() {
        val computer = IntCodeComputer("1,0,0,0,99")
        val finalState = computer.compute()
        assertThat(finalState).isEqualTo("2,0,0,0,99")
    }

    @Test
    @DisplayName("Given a valid program (example two), when computing, then it should return the correct final state")
    internal fun exampleProgramTwo() {
        val computer = IntCodeComputer("2,3,0,3,99")
        val finalState = computer.compute()
        assertThat(finalState).isEqualTo("2,3,0,6,99")
    }

    @Test
    @DisplayName("Given a valid program (example three), when computing, then it should return the correct final state")
    internal fun exampleProgramThree() {
        val computer = IntCodeComputer("2,4,4,5,99,0")
        val finalState = computer.compute()
        assertThat(finalState).isEqualTo("2,4,4,5,99,9801")
    }

    @Test
    @DisplayName("Given a valid program (example four), when computing, then it should return the correct final state")
    internal fun exampleProgramFour() {
        val computer = IntCodeComputer("1,1,1,4,99,5,6,0,99")
        val finalState = computer.compute()
        assertThat(finalState).isEqualTo("30,1,1,4,2,5,6,0,99")
    }

    @Test
    @DisplayName("Given a valid program, when restoring the gravity assist program, then it should update the noun and verb correctly")
    internal fun restoreGravityAssistProgram() {
        val computer = IntCodeComputer("1,2,3,3,99")
        computer.restoreGravityAssistProgram(1, 2)
        assertThat(computer.compute()).isEqualTo("1,1,2,3,99")
    }

    @Test
    @DisplayName("Given Day 2 - Part 1 puzzle input, when restoring the gravity assist program, then after computing the first memory address value should be 10566835")
    internal fun partOneSolution() {
        val computer = IntCodeComputer(puzzleInput)
        computer.restoreGravityAssistProgram(12, 2)
        computer.compute()
        assertThat(computer.getProgramMemory().getAddressValue(0)).isEqualTo(10566835)
    }
}