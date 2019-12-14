package com.aoc.intcode

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class OpCodeTest {
    @Test
    @DisplayName("Given a valid OpCode value of 1, when creating a new OpCode, then it should set the value correctly")
    internal fun staticFactoryConstructorOne() {
        val opCode = OpCode.from(1)
        val value = opCode.value
        assertThat(value).isEqualTo(1);
    }

    @Test
    @DisplayName("Given a valid OpCode value of 2, when creating a new OpCode, then it should set the value correctly")
    internal fun staticFactoryConstructorTwo() {
        val opCode = OpCode.from(2)
        val value = opCode.value
        assertThat(value).isEqualTo(2);
    }

    @Test
    @DisplayName("Given a valid OpCode value of 99, when creating a new OpCode, then it should set the value correctly")
    internal fun staticFactoryConstructorNinetyNine() {
        val opCode = OpCode.from(99)
        val value = opCode.value
        assertThat(value).isEqualTo(99);
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, 0, 3, 4, 5, 98, 100])
    internal fun invalidValue(value: Int) {
        val opCode = OpCode.from(value)
        assertThat(opCode.isValid()).isFalse();
    }

    @Test
    @DisplayName("Given an OpCode of value 1, when getting the operation, then it should return ADD")
    internal fun operationOne() {
        val opCode = OpCode.from(1)
        val operation = opCode.operation()
        assertThat(operation).isEqualTo(Operation.ADD)
    }

    @Test
    @DisplayName("Given an OpCode of value 2, when getting the operation, then it should return MULTIPLY")
    internal fun operationTwo() {
        val opCode = OpCode.from(2)
        val operation = opCode.operation()
        assertThat(operation).isEqualTo(Operation.MULTIPLY)
    }

    @Test
    @DisplayName("Given an OpCode of value 99, when getting the operation, then it should return HALT")
    internal fun operationNinetyNine() {
        val opCode = OpCode.from(99)
        val operation = opCode.operation()
        assertThat(operation).isEqualTo(Operation.HALT)
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, 0, 3, 4, 5, 98, 100])
    @DisplayName("Given an OpCode with an invalid value, when getting the operation, then it should return UNKNOWN")
    internal fun operationInvalidCode() {
        val opCode = OpCode.from(99)
        val operation = opCode.operation()
        assertThat(operation).isEqualTo(Operation.HALT)
    }
}