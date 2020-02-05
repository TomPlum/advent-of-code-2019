package com.aoc.intcode.strategy

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.intcode.Memory
import com.aoc.intcode.ParameterMode
import com.aoc.intcode.instructions.strategies.Output
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.util.*

class OutputTest {
    private val strategy = Output()
    private val parameterModes = Stack<ParameterMode>()

    @Test
    @DisplayName("Given a output OpCode in POSITION mode, when executing the strategy, then it should output the value at the index of the first parameter")
    fun outputPositionMode() {
        val memorySnapshot = Memory(listOf(3,50,4,1,99))
        memorySnapshot.incrementInstructionPointer(2)
        parameterModes.push(ParameterMode.POSITION)
        val finalSnapshot = strategy.execute(memorySnapshot, parameterModes)
        assertThat(finalSnapshot.getLastOutputValue()).isEqualTo(50)
    }

    @Test
    @DisplayName("Given a output OpCode in IMMEDIATE mode, when executing the strategy, then it should output the value at the index of the first parameter")
    fun outputImmediateMode() {
        val memorySnapshot = Memory(listOf(3,50,4,1,99))
        memorySnapshot.incrementInstructionPointer(2)
        parameterModes.push(ParameterMode.IMMEDIATE)
        val finalSnapshot = strategy.execute(memorySnapshot, parameterModes)
        assertThat(finalSnapshot.getLastOutputValue()).isEqualTo(1)
    }
}