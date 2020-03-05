package com.aoc.intcode.strategy

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.intcode.Memory
import com.aoc.intcode.OpCode
import com.aoc.intcode.instructions.InstructionLength
import com.aoc.intcode.instructions.strategies.Output
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class OutputTest {
    private val strategy = Output()

    @Test
    @DisplayName("Given a output OpCode in POSITION mode, when executing the strategy, then it should output the value " +
    "at the index of the first parameter")
    fun outputPositionMode() {
        val opCode = OpCode("4")
        val memorySnapshot = Memory(listOf(3,50,4,1,99))
        memorySnapshot.incrementInstructionPointer(InstructionLength.TWO_ADDRESS_INSTRUCTION)
        val finalSnapshot = strategy.execute(memorySnapshot, opCode.parameterModes)
        assertThat(finalSnapshot.output.getLastValue()).isEqualTo(50)
    }

    @Test
    @DisplayName("Given a output OpCode in IMMEDIATE mode, when executing the strategy, then it should output the value of the first parameter")
    fun outputImmediateMode() {
        val opCode = OpCode("104")
        val memorySnapshot = Memory(listOf(3,50,4,1,99))
        memorySnapshot.incrementInstructionPointer(InstructionLength.TWO_ADDRESS_INSTRUCTION)
        val finalSnapshot = strategy.execute(memorySnapshot, opCode.parameterModes)
        assertThat(finalSnapshot.output.getLastValue()).isEqualTo(1)
    }

    @Test
    @DisplayName("Given an output OpCode (204) in RELATIVE mode, when executing the strategy, then it should output the" +
    "value at the index of the sum of the relative base and the first parameter")
    fun outputRelativeMode() {
        val opCode = OpCode("204")
        val memorySnapshot = Memory(listOf(204,-1999,99))
        memorySnapshot.relativeBase = 2000
        val finalSnapshot = strategy.execute(memorySnapshot, opCode.parameterModes)
        assertThat(finalSnapshot.output.getLastValue()).isEqualTo(-1999)
    }
}