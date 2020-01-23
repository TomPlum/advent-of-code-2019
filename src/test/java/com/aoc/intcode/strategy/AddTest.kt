package com.aoc.intcode.strategy

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.intcode.Memory
import com.aoc.intcode.ParameterMode
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class AddTest {
    private val strategy = Add()

    @Test
    @DisplayName("Given a valid memory snapshot with the correct pointer, when executing add, then it should return the correct updated memory snapshot")
    fun add() {
        val memorySnapshot = Memory(listOf(1,0,0,0))
        val updatedMemory = strategy.execute(memorySnapshot, ParameterMode.POSITION)
        assertThat(updatedMemory).isEqualTo(Memory(listOf(2,0,0,0)))
    }

    @Test
    @DisplayName("Given a valid memory snapshot with a non-zero instruction pointer, when executing add, then it should update the correct addresses in memory")
    fun addWithNonZeroInstructionPointer() {
        val memorySnapshot = Memory(listOf(3,4,99,1,3,3,3))
        memorySnapshot.incrementInstructionPointer(3)
        val updatedMemory = strategy.execute(memorySnapshot, ParameterMode.POSITION)
        assertThat(updatedMemory).isEqualTo(Memory(listOf(3,4,99,2,3,3,3)))
    }
}
