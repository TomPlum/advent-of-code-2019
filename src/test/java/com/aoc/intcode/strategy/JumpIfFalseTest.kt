package com.aoc.intcode.strategy

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.intcode.Memory
import com.aoc.intcode.OpCode
import com.aoc.intcode.instructions.strategies.JumpIfFalse
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class JumpIfFalseTest {
    private val strategy = JumpIfFalse()

    @Test
    @DisplayName("Given a Jump If False OpCode in POSITION mode, when the first parameter is non-zero," +
            "then it should increment the instruction pointer by the instruction length")
    fun jumpIfFalseFirstParameterNonZeroPositionMode() {
        val opCode = OpCode("6")
        val memorySnapshot = Memory(listOf(6,2,1,99))
        val finalSnapshot = strategy.execute(memorySnapshot, opCode.parameterModes)
        assertThat(finalSnapshot.instructionPointer).isEqualTo(3)
    }

    @Test
    @DisplayName("Given a Jump If False OpCode in POSITION mode, when the first parameter is zero, " +
            "then it should set the instruction pointer to the value at the index of the second parameter")
    fun jumpIfFalseFirstParameterZeroPositionMode() {
        val opCode = OpCode("6")
        val memorySnapshot = Memory(listOf(6,2,0,99))
        val finalSnapshot = strategy.execute(memorySnapshot, opCode.parameterModes)
        assertThat(finalSnapshot.instructionPointer).isEqualTo(6)
    }

    @Test
    @DisplayName("Given a Jump If False OpCode in IMMEDIATE mode, when the first parameter is non-zero," +
            "then it should increment the instruction pointer by it's instruction length")
    fun jumpIfFalseFirstParameterNonZeroImmediateMode() {
        val opCode = OpCode("1106")
        val memorySnapshot = Memory(listOf(6,3,1,99))
        val finalSnapshot = strategy.execute(memorySnapshot, opCode.parameterModes)
        assertThat(finalSnapshot.instructionPointer).isEqualTo(3)
    }

    @Test
    @DisplayName("Given a Jump If False OpCode in IMMEDIATE mode, when the first parameter is zero, " +
            "then it should set the instruction pointer to the value of the second parameter")
    fun jumpIfFalseFirstParameterZeroImmediateMode() {
        val opCode = OpCode("1106")
        val memorySnapshot = Memory(listOf(6,0,1,99))
        val finalSnapshot = strategy.execute(memorySnapshot, opCode.parameterModes)
        assertThat(finalSnapshot.instructionPointer).isEqualTo(1)
    }

    @Test
    @DisplayName("Given a JIF OpCode (2206) in RELATIVE mode, when the value at the index given by the sum of the relative" +
    "base and the first parameter is zero, then the instruction pointer should be set to the value of the sum " +
    "of the relative base and the second parameter")
    fun jumpIfFalseFirstParameterZeroRelativeMode() {
        val opCode = OpCode("2206")
        val memorySnapshot = Memory(listOf(2206,4,1,99,0))
        val finalSnapshot = strategy.execute(memorySnapshot, opCode.parameterModes)
        assertThat(finalSnapshot.instructionPointer).isEqualTo(4)
    }

    @Test
    @DisplayName("Given a JIF OpCode (2206) in RELATIVE mode, when the value at the index given by the sum of the relative" +
    "base and the first parameter is not-zero, then the instruction pointer by the instruction length")
    fun jumpIfFalseFirstParameterNonZeroRelativeMode() {
        val opCode = OpCode("2206")
        val memorySnapshot = Memory(listOf(2206,4,1,99,50))
        val finalSnapshot = strategy.execute(memorySnapshot, opCode.parameterModes)
        assertThat(finalSnapshot.instructionPointer).isEqualTo(3)
    }

}
