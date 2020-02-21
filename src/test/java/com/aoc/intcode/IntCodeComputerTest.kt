package com.aoc.intcode

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isTrue
import com.aoc.input.InputReader
import com.aoc.input.Day
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

class IntCodeComputerTest {
    private val largeExampleProgram = "3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31," +
            "1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104," +
            "999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99"

    @Test
    @DisplayName("Given a valid program (example one), when computing, then it should return the correct final state")
    fun exampleProgramOne() {
        val computer = IntCodeComputer("1,0,0,0,99")
        computer.compute()
        assertThat(computer.getProgramCurrentState()).isEqualTo("2,0,0,0,99")
    }

    @Test
    @DisplayName("Given a valid program (example two), when computing, then it should return the correct final state")
    fun exampleProgramTwo() {
        val computer = IntCodeComputer("2,3,0,3,99")
        computer.compute()
        assertThat(computer.getProgramCurrentState()).isEqualTo("2,3,0,6,99")
    }

    @Test
    @DisplayName("Given a valid program (example three), when computing, then it should return the correct final state")
    fun exampleProgramThree() {
        val computer = IntCodeComputer("2,4,4,5,99,0")
        computer.compute()
        assertThat(computer.getProgramCurrentState()).isEqualTo("2,4,4,5,99,9801")
    }

    @Test
    @DisplayName("Given a valid program (example four), when computing, then it should return the correct final state")
    fun exampleProgramFour() {
        val computer = IntCodeComputer("1,1,1,4,99,5,6,0,99")
        computer.compute()
        assertThat(computer.getProgramCurrentState()).isEqualTo("30,1,1,4,2,5,6,0,99")
    }

    @Test
    @DisplayName("Given a valid program, when restoring the gravity assist program, then it should update the noun and verb correctly")
    fun restoreGravityAssistProgram() {
        val computer = IntCodeComputer("1,2,3,3,99")
        computer.restoreGravityAssistProgram(1, 2)
        computer.compute()
        assertThat(computer.getProgramCurrentState()).isEqualTo("1,1,2,3,99")
    }

    @Test
    @DisplayName("Given Day 2 - Part 1 puzzle input, when restoring the gravity assist program, then after computing the first memory address value should be 10566835")
    fun dayTwoPartOneSolution() {
        val puzzleInput = InputReader().readInputAsSingleString(Day.from(2))
        val computer = IntCodeComputer(puzzleInput)
        computer.restoreGravityAssistProgram(12, 2)
        computer.compute()
        assertThat(computer.getProgramMemory().getInstructionAtAddress(0)).isEqualTo(10566835)
    }

    @Test
    @DisplayName("Given a noun of 23 and a verb of 47, when restoring the gravity assist program, then the output should be 19690720")
    fun dayTwoPartTwoSolution() {
        val puzzleInput = InputReader().readInputAsSingleString(Day.from(2))
        val computer = IntCodeComputer(puzzleInput)
        computer.restoreGravityAssistProgram(23, 47)
        computer.compute()
        assertThat(computer.getProgramMemory().getInstructionAtAddress(0)).isEqualTo(19690720)
    }

    @Test
    fun dayFivePartTwoSolution() {
        val puzzleInput = InputReader().readInputAsSingleString(Day.from(5))
        val computer = IntCodeComputer(puzzleInput)
        computer.startThermalRadiatorControllerDiagnosticTest()
        computer.compute()
        assertThat(computer.getDiagnosticCode()).isEqualTo(7408802)
    }

    @Test
    @DisplayName("Given Day 5 - Part 1 puzzle input, when inputting the air condition code, then it should output 5044655 as the final non-zero diagnostic code")
    fun dayFivePartOneSolution() {
        val puzzleInput = InputReader().readInputAsSingleString(Day.from(5))
        val computer = IntCodeComputer(puzzleInput)
        computer.startAirConditionerDiagnosticTest()
        computer.compute()
        assertThat(computer.getDiagnosticCode()).isEqualTo(5044655)
    }

    @Test
    @DisplayName("Given a JUMP_IF_TRUE OpCode(5) in IMMEDIATE_MODE, when the first parameter is non-zero, then it should set the instruction pointer to the value from the second parameter")
    fun jumpIfTrue() {
        val computer = IntCodeComputer("1105,1,3,99")
        computer.compute()
        assertThat(computer.getProgramMemory().instructionPointer).isEqualTo(3)
    }

    @Test
    @DisplayName("Given a JUMP_IF_TRUE OpCode(5) in IMMEDIATE_MODE, when the first parameter is zero, then it should do nothing")
    fun jumpIfTrueWithZeroParameter() {
        val computer = IntCodeComputer("1105,0,10,99")
        computer.compute()
        assertThat(computer.getProgramCurrentState()).isEqualTo("1105,0,10,99")
    }

    @Test
    @DisplayName("Given a JUMP_IF_FALSE OpCode(1106) in IMMEDIATE mode, when the first parameter is zero, then it should" +
            " set the instruction pointer to the value from the second parameter")
    fun jumpIfFalse() {
        val computer = IntCodeComputer("1106,0,3,99")
        computer.compute()
        assertThat(computer.getProgramMemory().instructionPointer).isEqualTo(3)
    }

    @Test
    @DisplayName("Given a JUMP_IF_FALSE OpCode(1106) in IMMEDIATE mode, when the first parameter is non-zero, then it should do nothing")
    fun jumpIfFalseWithNonZeroParameter() {
        val computer = IntCodeComputer("1106,1,3,99")
        computer.compute()
        assertThat(computer.getProgramCurrentState()).isEqualTo("1106,1,3,99")
    }

    @Test
    @DisplayName("Given a LESS_THAN OpCode(7), when the first parameter is less than the second parameter, then it should store 1 in the position given by the third parameter")
    fun lessThan() {
        val computer = IntCodeComputer("7,1,2,0,99")
        computer.compute()
        assertThat(computer.getProgramCurrentState()).isEqualTo("1,1,2,0,99")
    }

    @Test
    @DisplayName("Given a LESS_THAN OpCode(7) in IMMEDIATE_MODE, when the first parameter is greater than the second parameter, then it should store 0 in the position given by the third parameter")
    fun lessThanWhenFirstParameterIsGreaterThanSecond() {
        val computer = IntCodeComputer("1107,3,2,0,99")
        computer.compute()
        val finalProgramState = computer.getProgramCurrentState()
        assertThat(finalProgramState).isEqualTo("0,3,2,0,99")
    }

    @Test
    @DisplayName("Given an EQUALS OpCode(8) in IMMEDIATE_MODE, when the first parameter is equals to the second parameter, then it should store 1 in the position given by the third parameter")
    fun equals() {
        val computer = IntCodeComputer("1108,5,5,1,99")
        computer.compute()
        assertThat(computer.getProgramCurrentState()).isEqualTo("1108,1,5,1,99")
    }

    @Test
    @DisplayName("Given an EQUALS OpCode(8) in IMMEDIATE_MODE, when the first parameter is not equal to the second parameter, then it should store 0 in the position given by the third parameter")
    fun equalsWhenNotEqual() {
        val computer = IntCodeComputer("1108,4,5,2,99")
        computer.compute()
        assertThat(computer.getProgramCurrentState()).isEqualTo("1108,4,0,2,99")
    }

    @Test
    @DisplayName("Given Day 5 Example 1 (POSITION MODE), when inputting 8 into the computer, then it should output 1")
    fun equalsExampleTestInPositionModeWithCorrectInput() {
        val computer = IntCodeComputer("3,9,8,9,10,9,4,9,99,-1,8")
        computer.getProgramMemory().systemInput(8)
        computer.compute()
        assertThat(computer.getDiagnosticCode()).isEqualTo(1)
    }

    @Test
    @DisplayName("Given Day 5 Example 1 (POSITION MODE), when inputting 2 into the computer, then it should output 0")
    fun equalsExampleTestInPositionModeWithIncorrectInput() {
        val computer = IntCodeComputer("3,9,8,9,10,9,4,9,99,-1,8")
        computer.getProgramMemory().systemInput(2)
        computer.compute()
        assertThat(computer.getDiagnosticCode()).isEqualTo(0)
    }

    @Test
    @DisplayName("Given Day 5 Example 2 (POSITION MODE), when inputting 5 into the computer, then it should output 1")
    fun lessThanExampleTestInPositionModeWithCorrectInput() {
        val computer = IntCodeComputer("3,9,7,9,10,9,4,9,99,-1,8")
        computer.getProgramMemory().systemInput(5)
        computer.compute()
        assertThat(computer.getDiagnosticCode()).isEqualTo(1)
    }

    @Test
    @DisplayName("Given Day 5 Example 2 (POSITION MODE), when inputting 9 into the computer, then it should output 0")
    fun lessExampleTestInPositionModeWithIncorrectInput() {
        val computer = IntCodeComputer("3,9,7,9,10,9,4,9,99,-1,8")
        computer.getProgramMemory().systemInput(9)
        computer.compute()
        assertThat(computer.getDiagnosticCode()).isEqualTo(0)
    }

    @Test
    @DisplayName("Given Day 5 Example 1 (IMMEDIATE MODE), when inputting 8 into the computer, then it should output 1")
    fun equalsExampleTestInImmediateModeWithCorrectInput() {
        val computer = IntCodeComputer("3,3,1108,-1,8,3,4,3,99")
        computer.getProgramMemory().systemInput(8)
        computer.compute()
        assertThat(computer.getDiagnosticCode()).isEqualTo(1)
    }

    @Test
    @DisplayName("Given Day 5 Example 1 (IMMEDIATE MODE), when inputting 2 into the computer, then it should output 0")
    fun equalsExampleTestInImmediateModeWithIncorrectInput() {
        val computer = IntCodeComputer("3,3,1108,-1,8,3,4,3,99")
        computer.getProgramMemory().systemInput(2)
        computer.compute()
        assertThat(computer.getDiagnosticCode()).isEqualTo(0)
    }

    @Test
    @DisplayName("Given Day 5 Example 2 (IMMEDIATE MODE), when inputting 5 into the computer, then it should output 1")
    fun lessThanExampleTestInImmediateModeWithCorrectInput() {
        val computer = IntCodeComputer("3,3,1107,-1,8,3,4,3,99")
        computer.getProgramMemory().systemInput(5)
        computer.compute()
        assertThat(computer.getDiagnosticCode()).isEqualTo(1)
    }

    @Test
    @DisplayName("Given Day 5 Example 2 (IMMEDIATE MODE), when inputting 9 into the computer, then it should output 0")
    fun lessExampleTestInImmediateModeWithIncorrectInput() {
        val computer = IntCodeComputer("3,3,1107,-1,8,3,4,3,99")
        computer.getProgramMemory().systemInput(9)
        computer.compute()
        assertThat(computer.getDiagnosticCode()).isEqualTo(0)
    }

    @Test
    @DisplayName("Given Day 5 Jump Test 1 (POSITION MODE), when inputting a 0, then it should output a 0")
    fun jumpTestOne() {
        val computer = IntCodeComputer("3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9")
        computer.getProgramMemory().systemInput(0)
        computer.compute()
        assertThat(computer.getDiagnosticCode()).isEqualTo(0)
    }

    @Test
    @DisplayName("Given Day 5 Jump Test 2 (IMMEDIATE MODE), when inputting a non-zero number, then it should output a 1")
    fun jumpTestTwo() {
        val computer = IntCodeComputer("3,3,1105,-1,9,1101,0,0,12,4,12,99,1")
        computer.getProgramMemory().systemInput(24)
        computer.compute()
        assertThat(computer.getDiagnosticCode()).isEqualTo(1)
    }


    @Test
    @DisplayName("Given the Day 5 larger example, when inputting a number less than 8, then it should output 999")
    fun largeExampleWhenInputLessThanEight() {
        val computer = IntCodeComputer(largeExampleProgram)
        computer.getProgramMemory().systemInput(4)
        computer.compute()
        assertThat(computer.getDiagnosticCode()).isEqualTo(999)
    }

    @Test
    @DisplayName("Given the Day 5 larger example, when inputting 8, then it should output 1000")
    fun largeExampleWhenInputEqualsEight() {
        val computer = IntCodeComputer(largeExampleProgram)
        computer.getProgramMemory().systemInput(8)
        computer.compute()
        assertThat(computer.getDiagnosticCode()).isEqualTo(1000)
    }

    @Test
    @DisplayName("Given the Day 5 larger example, when inputting a value greater than 8, then it should output 1001")
    fun largeExampleWhenInputGreaterThanEight() {
        val computer = IntCodeComputer(largeExampleProgram)
        computer.getProgramMemory().systemInput(23)
        computer.compute()
        assertThat(computer.getDiagnosticCode()).isEqualTo(1001)
    }

    @Test
    @DisplayName("Given the output has at least one code, when getting the diagnostic code, then it should return the final code from the output")
    fun getDiagnosticCode() {
        val computer = IntCodeComputer("4,3,99,250")
        computer.compute()
        assertThat(computer.getDiagnosticCode()).isEqualTo(250)
    }

    @Test
    @DisplayName("Given the output is empty, when getting the diagnostic code, then it should throw an exception")
    fun getDiagnosticCodeWhenEmptyOutput() {
        val computer = IntCodeComputer("1,0,0,0,99")
        computer.compute()
        assertThrows<IllegalStateException> { computer.getDiagnosticCode() }
    }

    @Test
    @DisplayName("Given an unknown OpCode, when computing, then it should throw an exception")
    fun unknownValue() {
        val computer = IntCodeComputer("1220")
        val e = assertThrows<IllegalArgumentException> { computer.compute() }
        assertThat(e.message).isEqualTo("Operation unknown for instruction 1220")
    }

    @Test
    @DisplayName("Given a Input OpCode (3) and NO system input, when computing, then it should suspend execution and" +
    "wait for further input. The instruction pointer should remain where it is")
    fun inputShouldSetComputerToWaitingWhenNoSystemInput() {
        val computer = IntCodeComputer("1,0,0,0,3,0,99")
        computer.compute()
        assertThat(computer.waiting).isTrue()
        assertThat(computer.getProgramMemory().instructionPointer).isEqualTo(4)
    }

    @Test
    @DisplayName("Given Day 9 Part 1 Example 1, when computing, then it should output a copy of itself (Quine)")
    fun dayNineExampleOne() {
        val computer = IntCodeComputer("109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99")
        computer.compute()
        assertThat(computer.getProgramMemory().output).isEqualTo(listOf<Long>(109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99))
    }

    @Test
    @DisplayName("Given Day 9 Part 1 Example 2, when computing, then it should output a 16 digit number")
    fun dayNineExampleTwo() {
        val computer = IntCodeComputer("1102,34915192,34915192,7,4,7,99,0")
        computer.compute()
        assertThat(computer.getDiagnosticCode().toString().length).isEqualTo(16)
    }

    @Test
    @DisplayName("Given Day 9 Part 1 Example 3, when computing, then it should output 1125899906842624")
    fun dayNineExampleThree() {
        val computer = IntCodeComputer("104,1125899906842624,99")
        computer.compute()
        assertThat(computer.getDiagnosticCode()).isEqualTo(1125899906842624)
    }
}