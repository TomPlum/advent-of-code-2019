package com.aoc.intcode.strategy

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.intcode.Memory
import com.aoc.intcode.ParameterMode
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class OutputTest {
    private val strategy = Output()

    @Test
    @DisplayName("Given a output OpCode in POSITION mode, when executing the strategy, then it should output the value at the index of the first parameter")
    fun outputPositionMode() {
        val memorySnapshot = Memory(listOf(3,50,4,1,99))
        memorySnapshot.incrementInstructionPointer(2)
        val finalSnapshot = strategy.execute(memorySnapshot, ParameterMode.POSITION)
        assertThat(finalSnapshot.getDiagnosticCode()).isEqualTo(50)
    }

    @Test
    @DisplayName("Given a output OpCode in IMMEDIATE mode, when executing the strategy, then it should output the value at the index of the first parameter")
    fun outputImmediateMode() {
        val memorySnapshot = Memory(listOf(3,50,4,1,99))
        memorySnapshot.incrementInstructionPointer(2)
        val finalSnapshot = strategy.execute(memorySnapshot, ParameterMode.IMMEDIATE)
        assertThat(finalSnapshot.getDiagnosticCode()).isEqualTo(1)
    }
}