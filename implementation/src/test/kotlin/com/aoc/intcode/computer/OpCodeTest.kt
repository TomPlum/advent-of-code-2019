package com.aoc.intcode.computer

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import com.aoc.intcode.computer.OpCode
import com.aoc.intcode.computer.ParameterMode
import com.aoc.intcode.computer.instructions.strategies.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class OpCodeTest {
    @Test
    @DisplayName("Given an OpCode of value 1, when getting the operation, then it should return ADD")
    fun operationOne() {
        val opCode = OpCode("1")
        val operation = opCode.getInstructionStrategy()
        assertThat(operation).isInstanceOf(Add::class)
    }

    @Test
    @DisplayName("Given an OpCode of value 2, when getting the operation, then it should return MULTIPLY")
    fun operationTwo() {
        val opCode = OpCode("2")
        val operation = opCode.getInstructionStrategy()
        assertThat(operation).isInstanceOf(Multiply::class)
    }

    @Test
    @DisplayName("Given an OpCode of value 3, when getting the operation, then it should return INPUT")
    fun operationThree() {
        val opCode = OpCode("3")
        val operation = opCode.getInstructionStrategy()
        assertThat(operation).isInstanceOf(Input::class)
    }

    @Test
    @DisplayName("Given an OpCode of value 4, when getting the operation, then it should return OUTPUT")
    fun operationFour() {
        val opCode = OpCode("4")
        val operation = opCode.getInstructionStrategy()
        assertThat(operation).isInstanceOf(Output::class)
    }

    @Test
    @DisplayName("Given an OpCode of value 5, when getting the operation, then it should return JUMP_IF_TRUE")
    fun operationFive() {
        val opCode = OpCode("5")
        val operation = opCode.getInstructionStrategy()
        assertThat(operation).isInstanceOf(JumpIfTrue::class)
    }

    @Test
    @DisplayName("Given an OpCode of value 6, when getting the operation, then it should return JUMP_IF_FALSE")
    fun operationSix() {
        val opCode = OpCode("6")
        val operation = opCode.getInstructionStrategy()
        assertThat(operation).isInstanceOf(JumpIfFalse::class)
    }

    @Test
    @DisplayName("Given an OpCode of value 7, when getting the operation, then it should return LESS_THAN")
    fun operationSeven() {
        val opCode = OpCode("7")
        val operation = opCode.getInstructionStrategy()
        assertThat(operation).isInstanceOf(LessThan::class)
    }

    @Test
    @DisplayName("Given an OpCode of value 8, when getting the operation, then it should return EQUALS")
    fun operationEight() {
        val opCode = OpCode("8")
        val operation = opCode.getInstructionStrategy()
        assertThat(operation).isInstanceOf(Equals::class)
    }

    @Test
    @DisplayName("Given an OpCode of value 9, when getting the operation, then it should return OFFSET_RELATIVE_BASE")
    fun operationNine() {
        val opCode = OpCode("9")
        val operation = opCode.getInstructionStrategy()
        assertThat(operation).isInstanceOf(OffsetRelativeBase::class)
    }

    @Test
    @DisplayName("Given an OpCode of value 99, when getting the operation, then it should return HALT")
    fun operationNinetyNine() {
        val opCode = OpCode("99")
        val operation = opCode.getInstructionStrategy()
        assertThat(operation).isInstanceOf(Halt::class)
    }

    @Test
    @DisplayName("Given an OpCode with 2 digits, when getting the operation, then it should parse based on the right-most integer")
    fun doubleDigitOpCode() {
        val opCode = OpCode("01")
        val operation = opCode.getInstructionStrategy()
        assertThat(operation).isInstanceOf(Add::class)
    }

    @ParameterizedTest
    @ValueSource(strings = ["1", "01", "2", "02", "3", "03"])
    @DisplayName("Given an OpCode with 1 or 2 digits, when getting the parameter mode, then it should be POSITION")
    fun singleOrDoubleDigitOpCodeShouldDefaultToPositionMode(opCodeValue: String) {
        val opCode = OpCode(opCodeValue)
        val mode = opCode.getParameterMode()
        assertThat(mode).isEqualTo(ParameterMode.POSITION)
    }

    @Test
    @DisplayName("Given an OpCode with 3 digits and the first is 0, when getting the parameter mode, then it should be POSITION")
    fun tripleDigitOpCodePositionMode() {
        val opCode = OpCode("001")
        val mode = opCode.getParameterMode()
        assertThat(mode).isEqualTo(ParameterMode.POSITION)
    }

    @Test
    @DisplayName("Given an OpCode with 3 digits and the first is 1, when getting the parameter mode, then it should be IMMEDIATE")
    fun tripleDigitOpCodeImmediateMode() {
        val opCode = OpCode("105")
        val mode = opCode.getParameterMode()
        assertThat(mode).isEqualTo(ParameterMode.IMMEDIATE)
    }

    @Test
    @DisplayName("Given an OpCode with 3 digits and the first is 2, when getting the parameter mode, then it should be RELATIVE")
    fun tripleDigitOpCodeRelativeMode() {
        val opCode = OpCode("203")
        val mode = opCode.getParameterMode()
        assertThat(mode).isEqualTo(ParameterMode.RELATIVE)
    }
}