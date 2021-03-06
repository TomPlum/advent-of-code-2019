package com.aoc.intcode.computer

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.Day
import com.aoc.input.InputReader
import com.aoc.intcode.computer.boot.TestBootMode
import com.aoc.intcode.network.NetworkAddress
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class IntCodeComputerTest {
    private val largeExampleProgram = "3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31," +
            "1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104," +
            "999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99"

    @Nested
    inner class DayTwo {
        @Test
        @DisplayName("Given a valid program (example one), when running, then it should return the correct final state")
        fun exampleProgramOne() {
            val computer = IntCodeComputer("1,0,0,0,99")
            computer.run()
            assertThat(computer.program.toString()).isEqualTo("2,0,0,0,99")
        }

        @Test
        @DisplayName("Given a valid program (example two), when running, then it should return the correct final state")
        fun exampleProgramTwo() {
            val computer = IntCodeComputer("2,3,0,3,99")
            computer.run()
            assertThat(computer.program.toString()).isEqualTo("2,3,0,6,99")
        }

        @Test
        @DisplayName("Given a valid program (example three), when running, then it should return the correct final state")
        fun exampleProgramThree() {
            val computer = IntCodeComputer("2,4,4,5,99,0")
            computer.run()
            assertThat(computer.program.toString()).isEqualTo("2,4,4,5,99,9801")
        }

        @Test
        @DisplayName("Given a valid program (example four), when running, then it should return the correct final state")
        fun exampleProgramFour() {
            val computer = IntCodeComputer("1,1,1,4,99,5,6,0,99")
            computer.run()
            assertThat(computer.program.toString()).isEqualTo("30,1,1,4,2,5,6,0,99")
        }

        @Test
        @DisplayName("Given a valid program, when restoring the gravity assist program, then it should update the noun and verb correctly")
        fun restoreGravityAssistProgram() {
            val computer = IntCodeComputer("1,2,3,3,99")
            computer.restoreGravityAssistProgram(1, 2)
            assertThat(computer.program.toString()).isEqualTo("1,1,2,3,99")
        }

        @Test
        @DisplayName("Given Day 2 - Part 1 puzzle input, when restoring the gravity assist program, then after running the first memory address value should be 10566835")
        fun dayTwoPartOneSolution() {
            val puzzleInput = InputReader.read<String>(Day(2)).asSingleString()
            val output = IntCodeComputer(puzzleInput).restoreGravityAssistProgram(12, 2)
            assertThat(output).isEqualTo(10566835)
        }

        @Test
        @DisplayName("Given a noun of 23 and a verb of 47, when restoring the gravity assist program, then the output should be 19690720")
        fun dayTwoPartTwoSolution() {
            val puzzleInput = InputReader.read<String>(Day(2)).asSingleString()
            val output = IntCodeComputer(puzzleInput).restoreGravityAssistProgram(23, 47)
            assertThat(output).isEqualTo(19690720)
        }
    }

    @Nested
    inner class DayFive {
        @Test
        @DisplayName("Given a Input OpCode (3) and NO system input, when running, then it should suspend execution and" +
        "wait for further input. The instruction pointer should remain where it is")
        fun inputShouldSetComputerToWaitingWhenNoSystemInput() {
            val computer = IntCodeComputer("1,0,0,0,3,0,99")
            computer.run()
            assertThat(computer.state).isEqualTo(State.WAITING)
            assertThat(computer.program.memory.instructionPointer).isEqualTo(4)
        }

        @Test
        @DisplayName("Given a JUMP_IF_TRUE OpCode(5) in IMMEDIATE_MODE, when the first parameter is non-zero, then it should set the instruction pointer to the value from the second parameter")
        fun jumpIfTrue() {
            val computer = IntCodeComputer("1105,1,3,99")
            computer.run()
            assertThat(computer.program.memory.instructionPointer).isEqualTo(3)
        }

        @Test
        @DisplayName("Given a JUMP_IF_TRUE OpCode(5) in IMMEDIATE_MODE, when the first parameter is zero, then it should do nothing")
        fun jumpIfTrueWithZeroParameter() {
            val computer = IntCodeComputer("1105,0,10,99")
            computer.run()
            assertThat(computer.program.toString()).isEqualTo("1105,0,10,99")
        }

        @Test
        @DisplayName("Given a JUMP_IF_FALSE OpCode(1106) in IMMEDIATE mode, when the first parameter is zero, then it should" +
                " set the instruction pointer to the value from the second parameter")
        fun jumpIfFalse() {
            val computer = IntCodeComputer("1106,0,3,99")
            computer.run()
            assertThat(computer.program.memory.instructionPointer).isEqualTo(3)
        }

        @Test
        @DisplayName("Given a JUMP_IF_FALSE OpCode(1106) in IMMEDIATE mode, when the first parameter is non-zero, then it should do nothing")
        fun jumpIfFalseWithNonZeroParameter() {
            val computer = IntCodeComputer("1106,1,3,99")
            computer.run()
            assertThat(computer.program.toString()).isEqualTo("1106,1,3,99")
        }

        @Test
        @DisplayName("Given a LESS_THAN OpCode(7), when the first parameter is less than the second parameter, then it should store 1 in the position given by the third parameter")
        fun lessThan() {
            val computer = IntCodeComputer("7,1,2,0,99")
            computer.run()
            assertThat(computer.program.toString()).isEqualTo("1,1,2,0,99")
        }

        @Test
        @DisplayName("Given a LESS_THAN OpCode(7) in IMMEDIATE_MODE, when the first parameter is greater than the second parameter, then it should store 0 in the position given by the third parameter")
        fun lessThanWhenFirstParameterIsGreaterThanSecond() {
            val computer = IntCodeComputer("1107,3,2,0,99")
            computer.run()
            val finalProgramState = computer.program.toString()
            assertThat(finalProgramState).isEqualTo("0,3,2,0,99")
        }

        @Test
        @DisplayName("Given an EQUALS OpCode(8) in IMMEDIATE_MODE, when the first parameter is equals to the second parameter, then it should store 1 in the position given by the third parameter")
        fun equals() {
            val computer = IntCodeComputer("1108,5,5,1,99")
            computer.run()
            assertThat(computer.program.toString()).isEqualTo("1108,1,5,1,99")
        }

        @Test
        @DisplayName("Given an EQUALS OpCode(8) in IMMEDIATE_MODE, when the first parameter is not equal to the second parameter, then it should store 0 in the position given by the third parameter")
        fun equalsWhenNotEqual() {
            val computer = IntCodeComputer("1108,4,5,2,99")
            computer.run()
            assertThat(computer.program.toString()).isEqualTo("1108,4,0,2,99")
        }

        @Test
        @DisplayName("Given Day 5 Example 1 (POSITION MODE), when inputting 8 into the computer, then it should output 1")
        fun equalsExampleTestInPositionModeWithCorrectInput() {
            val computer = IntCodeComputer("3,9,8,9,10,9,4,9,99,-1,8")
            computer.program.memory.input.add(8)
            computer.run()
            assertThat(computer.getDiagnosticCode()).isEqualTo(1)
        }

        @Test
        @DisplayName("Given Day 5 Example 1 (POSITION MODE), when inputting 2 into the computer, then it should output 0")
        fun equalsExampleTestInPositionModeWithIncorrectInput() {
            val computer = IntCodeComputer("3,9,8,9,10,9,4,9,99,-1,8")
            computer.program.memory.input.add(2)
            computer.run()
            assertThat(computer.getDiagnosticCode()).isEqualTo(0)
        }

        @Test
        @DisplayName("Given Day 5 Example 2 (POSITION MODE), when inputting 5 into the computer, then it should output 1")
        fun lessThanExampleTestInPositionModeWithCorrectInput() {
            val computer = IntCodeComputer("3,9,7,9,10,9,4,9,99,-1,8")
            computer.program.memory.input.add(5)
            computer.run()
            assertThat(computer.getDiagnosticCode()).isEqualTo(1)
        }

        @Test
        @DisplayName("Given Day 5 Example 2 (POSITION MODE), when inputting 9 into the computer, then it should output 0")
        fun lessExampleTestInPositionModeWithIncorrectInput() {
            val computer = IntCodeComputer("3,9,7,9,10,9,4,9,99,-1,8")
            computer.program.memory.input.add(9)
            computer.run()
            assertThat(computer.getDiagnosticCode()).isEqualTo(0)
        }

        @Test
        @DisplayName("Given Day 5 Example 1 (IMMEDIATE MODE), when inputting 8 into the computer, then it should output 1")
        fun equalsExampleTestInImmediateModeWithCorrectInput() {
            val computer = IntCodeComputer("3,3,1108,-1,8,3,4,3,99")
            computer.program.memory.input.add(8)
            computer.run()
            assertThat(computer.getDiagnosticCode()).isEqualTo(1)
        }

        @Test
        @DisplayName("Given Day 5 Example 1 (IMMEDIATE MODE), when inputting 2 into the computer, then it should output 0")
        fun equalsExampleTestInImmediateModeWithIncorrectInput() {
            val computer = IntCodeComputer("3,3,1108,-1,8,3,4,3,99")
            computer.program.memory.input.add(2)
            computer.run()
            assertThat(computer.getDiagnosticCode()).isEqualTo(0)
        }

        @Test
        @DisplayName("Given Day 5 Example 2 (IMMEDIATE MODE), when inputting 5 into the computer, then it should output 1")
        fun lessThanExampleTestInImmediateModeWithCorrectInput() {
            val computer = IntCodeComputer("3,3,1107,-1,8,3,4,3,99")
            computer.program.memory.input.add(5)
            computer.run()
            assertThat(computer.getDiagnosticCode()).isEqualTo(1)
        }

        @Test
        @DisplayName("Given Day 5 Example 2 (IMMEDIATE MODE), when inputting 9 into the computer, then it should output 0")
        fun lessExampleTestInImmediateModeWithIncorrectInput() {
            val computer = IntCodeComputer("3,3,1107,-1,8,3,4,3,99")
            computer.program.memory.input.add(9)
            computer.run()
            assertThat(computer.getDiagnosticCode()).isEqualTo(0)
        }

        @Test
        @DisplayName("Given Day 5 Jump Test 1 (POSITION MODE), when inputting a 0, then it should output a 0")
        fun jumpTestOne() {
            val computer = IntCodeComputer("3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9")
            computer.program.memory.input.add(0)
            computer.run()
            assertThat(computer.getDiagnosticCode()).isEqualTo(0)
        }

        @Test
        @DisplayName("Given Day 5 Jump Test 2 (IMMEDIATE MODE), when inputting a non-zero number, then it should output a 1")
        fun jumpTestTwo() {
            val computer = IntCodeComputer("3,3,1105,-1,9,1101,0,0,12,4,12,99,1")
            computer.program.memory.input.add(24)
            computer.run()
            assertThat(computer.getDiagnosticCode()).isEqualTo(1)
        }


        @Test
        @DisplayName("Given the Day 5 larger example, when inputting a number less than 8, then it should output 999")
        fun largeExampleWhenInputLessThanEight() {
            val computer = IntCodeComputer(largeExampleProgram)
            computer.program.memory.input.add(4)
            computer.run()
            assertThat(computer.getDiagnosticCode()).isEqualTo(999)
        }

        @Test
        @DisplayName("Given the Day 5 larger example, when inputting 8, then it should output 1000")
        fun largeExampleWhenInputEqualsEight() {
            val computer = IntCodeComputer(largeExampleProgram)
            computer.program.memory.input.add(8)
            computer.run()
            assertThat(computer.getDiagnosticCode()).isEqualTo(1000)
        }

        @Test
        @DisplayName("Given the Day 5 larger example, when inputting a value greater than 8, then it should output 1001")
        fun largeExampleWhenInputGreaterThanEight() {
            val computer = IntCodeComputer(largeExampleProgram)
            computer.program.memory.input.add(23)
            computer.run()
            assertThat(computer.getDiagnosticCode()).isEqualTo(1001)
        }

        @Test
        fun dayFivePartTwoSolution() {
            val puzzleInput = InputReader.read<String>(Day(5)).asSingleString()
            val computer = IntCodeComputer(puzzleInput)
            computer.onNextBoot(TestBootMode.THERMAL_RADIATOR_CONTROLLER_DIAGNOSTIC_TEST)
            computer.run()
            assertThat(computer.getDiagnosticCode()).isEqualTo(7408802)
        }

        @Test
        @DisplayName("Given Day 5 - Part 1 puzzle input, when inputting the air condition code, then it should output 5044655 as the final non-zero diagnostic code")
        fun dayFivePartOneSolution() {
            val puzzleInput = InputReader.read<String>(Day(5)).asSingleString()
            val computer = IntCodeComputer(puzzleInput)
            computer.onNextBoot(TestBootMode.AIR_CONDITIONER_DIAGNOSTIC_TEST)
            computer.run()
            assertThat(computer.getDiagnosticCode()).isEqualTo(5044655)
        }
    }

    @Nested
    inner class DayNine {
        @Test
        @DisplayName("Given Day 9 Part 1 Example 1, when running, then it should output a copy of itself (Quine)")
        fun dayNineExampleOne() {
            val computer = IntCodeComputer("109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99")
            computer.run()
            assertThat(computer.program.memory.output.getValues()).isEqualTo(listOf<Long>(109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99))
        }

        @Test
        @DisplayName("Given Day 9 Part 1 Example 2, when running, then it should output a 16 digit number")
        fun dayNineExampleTwo() {
            val computer = IntCodeComputer("1102,34915192,34915192,7,4,7,99,0")
            computer.run()
            assertThat(computer.getDiagnosticCode().toString().length).isEqualTo(16)
        }

        @Test
        @DisplayName("Given Day 9 Part 1 Example 3, when running, then it should output 1125899906842624")
        fun dayNineExampleThree() {
            val computer = IntCodeComputer("104,1125899906842624,99")
            computer.run()
            assertThat(computer.getDiagnosticCode()).isEqualTo(1125899906842624)
        }

        @Test
        @DisplayName("Given Day 9 - Part 1 puzzle input, when inputting the BOOST test code, then it should output 3100786347")
        fun dayNinePartOneSolution() {
            val puzzleInput = InputReader.read<String>(Day(9)).asSingleString()
            val computer = IntCodeComputer(puzzleInput)
            computer.onNextBoot(TestBootMode.BOOST_TEST)
            computer.run()
            assertThat(computer.getDiagnosticCode()).isEqualTo(3100786347L)
        }

        @Test
        @DisplayName("Given Day 9 - Part 2 puzzle input, when inputting the sensor boost code, then it should output 87023")
        fun dayNinePartTwoSolution() {
            val puzzleInput = InputReader.read<String>(Day(9)).asSingleString()
            val computer = IntCodeComputer(puzzleInput)
            computer.onNextBoot(TestBootMode.SENSOR_BOOST)
            computer.run()
            assertThat(computer.getDiagnosticCode()).isEqualTo(87023)
        }
    }

    @Nested
    inner class DayTwentyThree {

    }

    @Nested
    inner class DiagnosticCode {
        @Test
        @DisplayName("Given the output has at least one code, when getting the diagnostic code, then it should return the final code from the output")
        fun getDiagnosticCode() {
            val computer = IntCodeComputer("4,3,99,250")
            computer.run()
            assertThat(computer.getDiagnosticCode()).isEqualTo(250)
        }

        @Test
        @DisplayName("Given the output is empty, when getting the diagnostic code, then it should throw an exception")
        fun getDiagnosticCodeWhenEmptyOutput() {
            val computer = IntCodeComputer("1,0,0,0,99")
            computer.run()
            assertThrows<IllegalStateException> { computer.getDiagnosticCode() }
        }
    }

    @Nested
    inner class OnNextBoot {
        @ParameterizedTest
        @EnumSource(value = TestBootMode::class)
        @DisplayName("Given a custom boot mode for the IntCodeComputer, when setting the mode for the next boot," +
        "then it should set the associated code in the computers' system input")
        fun onNextBootTEST(bootMode: TestBootMode) {
            val computer = IntCodeComputer("99")
            computer.onNextBoot(bootMode)
            assertThat(getFirstSystemInputValue(computer)).isEqualTo(bootMode.getCode())
        }

        @Test
        @DisplayName("Given a Network Address, when booting, then it should add the address value in the System Input")
        fun onNextBootNetworkAddress() {
            val computer = IntCodeComputer("99")
            computer.onNextBoot(NetworkAddress(45))
            assertThat(getFirstSystemInputValue(computer)).isEqualTo(45)
        }

        private fun getFirstSystemInputValue(computer: IntCodeComputer): Long {
            return computer.program.memory.input.values[0]
        }
    }

    @Test
    @DisplayName("Given an unknown OpCode, when running, then it should throw an exception")
    fun unknownValue() {
        val computer = IntCodeComputer("1220")
        val e = assertThrows<IllegalArgumentException> { computer.run() }
        assertThat(e.message).isEqualTo("Operation unknown for instruction 1220")
    }

    @Test
    fun reset() {
        val computer = IntCodeComputer("7,1,2,0,99")
        computer.run()

        //Before
        assertThat(computer.state).isEqualTo(State.TERMINATED)
        assertThat(computer.program.toString()).isEqualTo("1,1,2,0,99")

        computer.reset()

        //After
        assertThat(computer.state).isEqualTo(State.WAITING)
        assertThat(computer.program.toString()).isEqualTo("7,1,2,0,99")
    }
}