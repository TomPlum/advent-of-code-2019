package com.aoc.intcode.computer.strategy

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.intcode.computer.Memory
import com.aoc.intcode.computer.OpCode
import com.aoc.intcode.computer.exceptions.SignalInterrupt
import com.aoc.intcode.computer.instructions.InstructionLength
import com.aoc.intcode.computer.instructions.strategies.Input
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class InputTest {
    private val strategy = Input()

    @Test
    @DisplayName("Given a single digit Input OpCode (3), when executing the strategy, then it should take a value from " +
    "the system input and store it in the address indexed by the first parameter value")
    fun inputPositionMode() {
        val opCode = OpCode("3")
        val memorySnapshot = Memory(listOf(1, 0, 15, 12, 3, 1, 99))
        memorySnapshot.incrementInstructionPointer(InstructionLength.FOUR_ADDRESS_INSTRUCTION)
        memorySnapshot.input.add(12)

        val finalSnapshot = strategy.execute(memorySnapshot, opCode.parameterModes)

        assertThat(finalSnapshot).isEqualTo(Memory(listOf(1, 12, 15, 12, 3, 1, 99)))
    }

    @Test
    @DisplayName("Given an Input OpCode (3) and NO system input, when executing the strategy, then it should throw a " +
    "signal interrupt to inform the computer to wait")
    fun inputWhenSystemMemoryHasNoInput() {
        val opCode = OpCode("3")
        val memorySnapshot = Memory(listOf(3, 0, 99))
        val e = assertThrows<SignalInterrupt> { strategy.execute(memorySnapshot, opCode.parameterModes) }
        assertThat(e.message).isEqualTo("SIGINT")
    }

    @Test
    fun inputRelativeMode() {
        val opCode = OpCode("203")
        val memorySnapshot = Memory(listOf(203, 7))
        memorySnapshot.relativeBase = -6
        memorySnapshot.input.add(105L)
        val finalSnapshot = strategy.execute(memorySnapshot, opCode.parameterModes)
        assertThat(finalSnapshot).isEqualTo(Memory(listOf(203, 105)))
    }
}