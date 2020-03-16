package com.aoc.intcode.strategy

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.intcode.Memory
import com.aoc.intcode.OpCode
import com.aoc.intcode.instructions.strategies.LessThan
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class LessThanTest {
    private val strategy = LessThan()

    @Test
    @DisplayName("Given a Less Than OpCode (7) in POSITION mode, when the value at the index given by the first parameter" +
    "is less than the value at the index given by the second parameter, then it should set the value at the index" +
    "given by the third parameter to 1")
    fun lessThanFirstParameterLessThanSecondPositionMode() {
        val opCode = OpCode("7")
        val memorySnapshot = Memory(listOf(7,5,6,5,99,12,24))
        val finalSnapshot = strategy.execute(memorySnapshot, opCode.parameterModes)
        assertThat(finalSnapshot).isEqualTo(Memory(listOf(7,5,6,5,99,1,24)))
    }

    @Test
    @DisplayName("Given a Less Than OpCode (7) in POSITION mode, when the value at the index given by the first parameter" +
    "is greater than the value at the index given by the second parameter, then it should set the value at the index" +
    "given by the third parameter to 0")
    fun lessThanFirstParameterGreaterThanSecondPositionMode() {
        val opCode = OpCode("7")
        val memorySnapshot = Memory(listOf(7,5,6,5,99,67,13))
        val finalSnapshot = strategy.execute(memorySnapshot, opCode.parameterModes)
        assertThat(finalSnapshot).isEqualTo(Memory(listOf(7,5,6,5,99,0,13)))
    }

    @Test
    @DisplayName("Given a Less Than OpCode (1107) in IMMEDIATE mode, when the value of the first parameter is less than" +
    "the value of the second parameter, then it should set the value at the index given by the third parameter to 1")
    fun lessThanFirstParameterLessThanSecondImmediateMode() {
        val opCode = OpCode("1107")
        val memorySnapshot = Memory(listOf(1107,34,35,5,99,0,0))
        val finalSnapshot = strategy.execute(memorySnapshot, opCode.parameterModes)
        assertThat(finalSnapshot).isEqualTo(Memory(listOf(1107,34,35,5,99,1,0)))
    }

    @Test
    @DisplayName("Given a Less Than OpCode (1107) in IMMEDIATE mode, when the value of the first parameter is greater than" +
    "the value of the second parameter, then it should set the value at the index given by the third parameter to 0")
    fun lessThanFirstParameterGreaterThanSecondImmediateMode() {
        val opCode = OpCode("1107")
        val memorySnapshot = Memory(listOf(1107,78,12,5,99,0,0))
        val finalSnapshot = strategy.execute(memorySnapshot, opCode.parameterModes)
        assertThat(finalSnapshot).isEqualTo(Memory(listOf(1107,78,12,5,99,0,0)))
    }

    @Test
    @DisplayName("Given a Less Than OpCode (22207) in RELATIVE mode, when the value at the index given by the sum of the" +
    "relative base and the first parameter is less than the value at the index given by the sum of the relative" +
    "base and the second parameter, then it should write a 1 at the index given by the sum of the relative base" +
    "and the third parameter")
    fun lessThanFirstParameterLessThanSecondRelativeMode() {
        val opCode = OpCode("22207")
        val memorySnapshot = Memory(listOf(22207,4,3,5,99,12,24))
        memorySnapshot.relativeBase = 1
        val finalSnapshot = strategy.execute(memorySnapshot, opCode.parameterModes)
        assertThat(finalSnapshot).isEqualTo(Memory(listOf(22207,4,3,5,99,12,1)))
    }

    @Test
    @DisplayName("Given a Less Than OpCode (22207) in RELATIVE mode, when the value at the index given by the sum of the" +
    "relative base and the first parameter is greater than the value at the index given by the sum of the relative" +
    "base and the second parameter, then it should write a 0 at the index given by the sum of the relative base" +
    "and the third parameter")
    fun lessThanFirstParameterGreaterThanSecondRelativeMode() {
        val opCode = OpCode("22207")
        val memorySnapshot = Memory(listOf(22207,-1,2,4,99,12,24))
        memorySnapshot.relativeBase = 1
        val finalSnapshot = strategy.execute(memorySnapshot, opCode.parameterModes)
        assertThat(finalSnapshot).isEqualTo(Memory(listOf(22207,-1,2,4,99,0,24)))
    }
}