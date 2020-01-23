package com.aoc.intcode.strategy

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.intcode.Memory
import com.aoc.intcode.ParameterMode
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class MultiplyTest {
    private val strategy = Multiply()

    @Test
    @DisplayName("Given a valid memory snapshot with the correct pointer, when executing multiply, then it should return the correct updated memory snapshot")
    fun multiply() {
        val memorySnapshot = Memory(listOf(3,0,0,0))
        val updatedMemory = strategy.execute(memorySnapshot, ParameterMode.POSITION)
        assertThat(updatedMemory).isEqualTo(Memory(listOf(9,0,0,0)))
    }

    @Test
    @DisplayName("Given a valid memory snapshot with a non-zero instruction pointer, when executing multiply, then it should update the correct addresses in memory")
    fun multiplyWithNonZeroInstructionPointer() {
        val memorySnapshot = Memory(listOf(3,4,99,10,3,3,3))
        memorySnapshot.incrementInstructionPointer(3)
        val updatedMemory = strategy.execute(memorySnapshot, ParameterMode.POSITION)
        assertThat(updatedMemory).isEqualTo(Memory(listOf(3,4,99,100,3,3,3)))
    }
}