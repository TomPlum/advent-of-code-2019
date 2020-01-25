package com.aoc.intcode

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.input.InputReader
import com.aoc.input.Day
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class IntCodeComputerTest {

    @Test
    @DisplayName("Given a valid program (example one), when computing, then it should return the correct final state")
    fun exampleProgramOne() {
        val computer = IntCodeComputer("1,0,0,0,99")
        val finalState = computer.compute()
        assertThat(finalState).isEqualTo("2,0,0,0,99")
    }

    @Test
    @DisplayName("Given a valid program (example two), when computing, then it should return the correct final state")
    fun exampleProgramTwo() {
        val computer = IntCodeComputer("2,3,0,3,99")
        val finalState = computer.compute()
        assertThat(finalState).isEqualTo("2,3,0,6,99")
    }

    @Test
    @DisplayName("Given a valid program (example three), when computing, then it should return the correct final state")
    fun exampleProgramThree() {
        val computer = IntCodeComputer("2,4,4,5,99,0")
        val finalState = computer.compute()
        assertThat(finalState).isEqualTo("2,4,4,5,99,9801")
    }

    @Test
    @DisplayName("Given a valid program (example four), when computing, then it should return the correct final state")
    fun exampleProgramFour() {
        val computer = IntCodeComputer("1,1,1,4,99,5,6,0,99")
        val finalState = computer.compute()
        assertThat(finalState).isEqualTo("30,1,1,4,2,5,6,0,99")
    }

    @Test
    @DisplayName("Given a valid program, when restoring the gravity assist program, then it should update the noun and verb correctly")
    fun restoreGravityAssistProgram() {
        val computer = IntCodeComputer("1,2,3,3,99")
        computer.restoreGravityAssistProgram(1, 2)
        assertThat(computer.compute()).isEqualTo("1,1,2,3,99")
    }

    @Test
    @DisplayName("Given Day 2 - Part 1 puzzle input, when restoring the gravity assist program, then after computing the first memory address value should be 10566835")
    fun partOneSolution() {
        val puzzleInput = InputReader().readInputAsSingleString(Day.from(2))
        val computer = IntCodeComputer(puzzleInput)
        computer.restoreGravityAssistProgram(12, 2)
        computer.compute()
        assertThat(computer.getProgramMemory().getInstructionAtAddress(0)).isEqualTo(10566835)
    }

    @Test
    @DisplayName("Given Day 5 - Part 1 puzzle input, when inputting the air condition code, then it should output 5044655 as the final non-zero diagnostic code")
    fun dayFivePartOne() {
        val puzzleInput = InputReader().readInputAsSingleString(Day.from(5))
        val computer = IntCodeComputer(puzzleInput)
        computer.systemInput(1) //Air Conditioner
        computer.compute()
        assertThat(computer.getDiagnosticCode()).isEqualTo(5044655)
    }
}