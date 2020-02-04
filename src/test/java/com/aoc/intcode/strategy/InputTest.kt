package com.aoc.intcode.strategy

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.intcode.Memory
import com.aoc.intcode.ParameterMode
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.util.*

class InputTest {
    private val strategy = Input()
    private val parameterModes = Stack<ParameterMode>()

    @Test
    @DisplayName("Given an initial memory snapshot with a valid input OpCode, when executing the Input strategy in POSITION, then it should mutate the memory correctly")
    fun inputPositionMode() {
        val memorySnapshot = Memory(listOf(1,0,15,12,3,1,99))
        memorySnapshot.incrementInstructionPointer(4)
        memorySnapshot.systemInput(12)
        parameterModes.push(ParameterMode.POSITION)
        val finalSnapshot = strategy.execute(memorySnapshot, parameterModes)
        assertThat(finalSnapshot).isEqualTo(Memory(listOf(12,0,15,12,3,1,99)))
    }

    @Test
    @DisplayName("Given an initial memory snapshot with a valid input OpCode, when executing the Input strategy in IMMEDIATE, then it should mutate the memory correctly")
    fun inputImmediateMode() {
        val memorySnapshot = Memory(listOf(4,9,3,1,99))
        memorySnapshot.incrementInstructionPointer(2)
        memorySnapshot.systemInput(3)
        parameterModes.push(ParameterMode.IMMEDIATE)
        val finalSnapshot = strategy.execute(memorySnapshot, parameterModes)
        assertThat(finalSnapshot).isEqualTo(Memory(listOf(4,3,3,1,99)))
    }
}