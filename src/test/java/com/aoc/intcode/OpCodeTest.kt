package com.aoc.intcode

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isInstanceOf
import assertk.assertions.isTrue
import com.aoc.intcode.instructions.strategies.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class OpCodeTest {
    @ParameterizedTest
    @ValueSource(ints = [-10, 0, 9, 100])
    @DisplayName("Given an invalid OpCode value, when checking if valid, then it should return false")
    fun invalidValue(value: Int) {
        val opCode = OpCode(value.toString())
        val isValid = opCode.isValid()
        assertThat(isValid).isFalse()
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4, 5, 6, 7, 8, 99])
    @DisplayName("Given a valid OpCode value, when checking if valid, then it should return true")
    fun validValue(value: Int) {
        val opCode = OpCode(value.toString())
        val isValid = opCode.isValid()
        assertThat(isValid).isTrue()
    }

    @Test
    @DisplayName("Given an OpCode of value 1, when getting the operation, then it should return ADD")
    fun operationOne() {
        val opCode = OpCode("1")
        val operation = opCode.getOperationStrategy()
        assertThat(operation).isInstanceOf(Add::class)
    }

    @Test
    @DisplayName("Given an OpCode of value 2, when getting the operation, then it should return MULTIPLY")
    fun operationTwo() {
        val opCode = OpCode("2")
        val operation = opCode.getOperationStrategy()
        assertThat(operation).isInstanceOf(Multiply::class)
    }

    @Test
    @DisplayName("Given an OpCode of value 3, when getting the operation, then it should return INPUT")
    fun operationThree() {
        val opCode = OpCode("3")
        val operation = opCode.getOperationStrategy()
        assertThat(operation).isInstanceOf(Input::class)
    }

    @Test
    @DisplayName("Given an OpCode of value 4, when getting the operation, then it should return OUTPUT")
    fun operationFour() {
        val opCode = OpCode("4")
        val operation = opCode.getOperationStrategy()
        assertThat(operation).isInstanceOf(Output::class)
    }

    @Test
    @DisplayName("Given an OpCode of value 5, when getting the operation, then it should return JUMP_IF_TRUE")
    fun operationFive() {
        val opCode = OpCode("5")
        val operation = opCode.getOperationStrategy()
        assertThat(operation).isInstanceOf(JumpIfTrue::class)
    }

    @Test
    @DisplayName("Given an OpCode of value 6, when getting the operation, then it should return JUMP_IF_FALSE")
    fun operationSix() {
        val opCode = OpCode("6")
        val operation = opCode.getOperationStrategy()
        assertThat(operation).isInstanceOf(JumpIfFalse::class)
    }

    @Test
    @DisplayName("Given an OpCode of value 7, when getting the operation, then it should return LESS_THAN")
    fun operationSeven() {
        val opCode = OpCode("7")
        val operation = opCode.getOperationStrategy()
        assertThat(operation).isInstanceOf(LessThan::class)
    }

    @Test
    @DisplayName("Given an OpCode of value 8, when getting the operation, then it should return EQUALS")
    fun operationEight() {
        val opCode = OpCode("8")
        val operation = opCode.getOperationStrategy()
        assertThat(operation).isInstanceOf(Equals::class)
    }

    @Test
    @DisplayName("Given an OpCode of value 99, when getting the operation, then it should return HALT")
    fun operationNinetyNine() {
        val opCode = OpCode("99")
        val operation = opCode.getOperationStrategy()
        assertThat(operation).isInstanceOf(Halt::class)
    }

    @Test
    @DisplayName("Given an OpCode with 2 digits, when getting the operation, then it should parse based on the right-most integer")
    fun doubleDigitOpCode() {
        val opCode = OpCode("01")
        val operation = opCode.getOperationStrategy()
        assertThat(operation).isInstanceOf(Add::class)
    }

    @Test
    @DisplayName("Given an OpCode with 3 digits and the first is 0, when getting the parameter mode, then it should be POSITION")
    fun doubleDigitOpCodePositionMode() {
        val opCode = OpCode("001")
        val mode = opCode.getParameterMode()
        assertThat(mode).isEqualTo(ParameterMode.POSITION)
    }

    @Test
    @DisplayName("Given an OpCode with 3 digits and the first is 1, when getting the parameter mode, then it should be IMMEDIATE")
    fun tripleDigitOpCode() {
        val opCode = OpCode("102")
        val mode = opCode.getParameterMode()
        assertThat(mode).isEqualTo(ParameterMode.IMMEDIATE)
    }

}