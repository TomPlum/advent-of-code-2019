package com.aoc.intcode.strategy

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.intcode.Memory
import com.aoc.intcode.OpCode
import com.aoc.intcode.instructions.strategies.Equals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class EqualsTest {
    private val strategy = Equals()

    @Test
    @DisplayName("Given a single digit Equals OpCode (8) in POSITION mode, when the values at the indices given by the " +
    "first and second parameters are equal, then it should store a 1 at the index given by the third parameter")
    fun equalsPositionModeWhenEqual() {
        val opCode = OpCode("8")
        val memorySnapshot = Memory(listOf(8,4,5,5,6,6,99))
        val finalSnapshot = strategy.execute(memorySnapshot, opCode.parameterModes)
        assertThat(finalSnapshot).isEqualTo(Memory(listOf(8,4,5,5,6,1,99)))
    }

    @Test
    @DisplayName("Given a single digit Equals OpCode (8) in POSITION mode, when the values at the indices given by the " +
    "first and second parameters are not equal, then it should store a 0 at the index given by the third parameter")
    fun equalsPositionModeWhenNotEqual() {
        val opCode = OpCode("8")
        val memorySnapshot = Memory(listOf(8,4,5,5,28,6,99))
        val finalSnapshot = strategy.execute(memorySnapshot, opCode.parameterModes)
        assertThat(finalSnapshot).isEqualTo(Memory(listOf(8,4,5,5,28,0,99)))
    }

    @Test
    @DisplayName("Given a four digit Equals OpCode (1108) in IMMEDIATE mode, when the values of the first and second " +
    "parameters are equal, then it should store a 1 at the index given by the third parameter")
    fun equalsImmediateModeWhenEqual() {
        val opCode = OpCode("1108")
        val memorySnapshot = Memory(listOf(8,1,1,5,6,6,99))
        val finalSnapshot = strategy.execute(memorySnapshot, opCode.parameterModes)
        assertThat(finalSnapshot).isEqualTo(Memory(listOf(8,1,1,5,6,1,99)))
    }

    @Test
    @DisplayName("Given a four digit Equals OpCode (1108) in IMMEDIATE mode, when the values of the first and second " +
    "parameters are not equal, then it should store a 0 at the index given by the third parameter")
    fun equalsImmediateModeWhenNotEqual() {
        val opCode = OpCode("1108")
        val memorySnapshot = Memory(listOf(8,1,97,5,6,6,99))
        val finalSnapshot = strategy.execute(memorySnapshot, opCode.parameterModes)
        assertThat(finalSnapshot).isEqualTo(Memory(listOf(8,1,97,5,6,0,99)))
    }

    @Test
    @DisplayName("Given a four digit Equals OpCode (2208) in RELATIVE mode, when the values of the sum of the relative" +
            "base at the first and second parameters respectively are equal, then it should store a 1 at the index given" +
            "by the sum of the current relative base and the third parameter")
    fun equalsRelativeModeWhenEqual() {
        val opCode = OpCode("2208")
        val memorySnapshot = Memory(listOf(2208,1,1,3,99))
        val finalSnapshot = strategy.execute(memorySnapshot, opCode.parameterModes)
        assertThat(finalSnapshot).isEqualTo(Memory(listOf(2208,1,1,1,99)))
    }

    @Test
    @DisplayName("Given a four digit Equals OpCode (2208) in RELATIVE mode, when the values of the sum of the relative" +
            "base at the first and second parameters respectively are not equal, then it should store a 0 at the index given" +
            "by the sum of the current relative base and the third parameter")
    fun equalsRelativeModeWhenNotEqual() {
        val opCode = OpCode("2208")
        val memorySnapshot = Memory(listOf(2208,1,4,3,99))
        val finalSnapshot = strategy.execute(memorySnapshot, opCode.parameterModes)
        assertThat(finalSnapshot).isEqualTo(Memory(listOf(2208,1,4,0,99)))
    }
}