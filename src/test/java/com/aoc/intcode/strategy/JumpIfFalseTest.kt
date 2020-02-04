package com.aoc.intcode.strategy

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.intcode.Memory
import com.aoc.intcode.OpCode
import com.aoc.intcode.ParameterMode
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.util.*

class JumpIfFalseTest {
    private val strategy = JumpIfFalse()

    @Test
    @DisplayName("Given a Jump If False OpCode in POSITION mode, when the first parameter is non-zero," +
            "then it should increment the instruction pointer by the instruction length")
    fun jumpIfFalseFirstParameterNonZeroPositionMode() {
        val opCode = OpCode("6")
        val memorySnapshot = Memory(listOf(6,2,1,99))
        val finalSnapshot = strategy.execute(memorySnapshot, StrategyTestUtility.getParameterModes(opCode))
        assertThat(finalSnapshot.instructionPointer).isEqualTo(2)
    }

    @Test
    @DisplayName("Given a Jump If False OpCode in POSITION mode, when the first parameter is zero, " +
            "then it should set the instruction pointer to the value at the index of the second parameter")
    fun jumpIfFalseFirstParameterZeroPositionMode() {
        val opCode = OpCode("6")
        val memorySnapshot = Memory(listOf(6,2,0,99))
        val finalSnapshot = strategy.execute(memorySnapshot, StrategyTestUtility.getParameterModes(opCode))
        assertThat(finalSnapshot.instructionPointer).isEqualTo(6)
    }

    @Test
    @DisplayName("Given a Jump If False OpCode in IMMEDIATE mode, when the first parameter is non-zero," +
            "then it should increment the instruction pointer by it's instruction length")
    fun jumpIfFalseFirstParameterNonZeroImmediateMode() {
        val opCode = OpCode("1106")
        val memorySnapshot = Memory(listOf(6,3,1,99))
        val finalSnapshot = strategy.execute(memorySnapshot, StrategyTestUtility.getParameterModes(opCode))
        assertThat(finalSnapshot.instructionPointer).isEqualTo(2)
    }

    @Test
    @DisplayName("Given a Jump If False OpCode in IMMEDIATE mode, when the first parameter is zero, " +
            "then it should set the instruction pointer to the value of the second parameter")
    fun jumpIfFalseFirstParameterZeroImmediateMode() {
        val opCode = OpCode("1106")
        val memorySnapshot = Memory(listOf(6,0,1,99))
        val finalSnapshot = strategy.execute(memorySnapshot, StrategyTestUtility.getParameterModes(opCode))
        assertThat(finalSnapshot.instructionPointer).isEqualTo(1)
    }

}
