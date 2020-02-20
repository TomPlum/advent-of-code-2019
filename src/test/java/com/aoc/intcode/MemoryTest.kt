package com.aoc.intcode

import assertk.assertThat
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalStateException

class MemoryTest {
    private val initialMemorySnapshot = listOf<Long>(1, 2, 3, 4, 5)

    @Test
    @DisplayName("Given a valid address input, when creating memory, it should return the addresses")
    fun createMemory() {
        val memory = Memory(initialMemorySnapshot)
        assertThat(memory.instructions).isEqualTo(listOf<Long>(1,2,3,4,5))
    }

    @Test
    @DisplayName("Given valid memory, when updating an address and then resetting, it should set the addresses back to their origin snapshot")
    fun reset() {
        val memory = Memory(initialMemorySnapshot)
        memory.updateInstructionAtAddress(0, 10)
        memory.reset()
        assertThat(memory.instructions).isEqualTo(listOf<Long>(1,2,3,4,5))
    }

    @Test
    @DisplayName("Given a valid address index, when updating the value at that address, then it should set the new value correctly")
    fun updateAddress() {
        val memory = Memory(initialMemorySnapshot)
        memory.updateInstructionAtAddress(0, 12)
        assertThat(memory.getInstructionAtAddress(0)).isEqualTo(12)
    }

    @Test
    @DisplayName("Given a valid set of addresses, when getting an address via index, it should return the correct addresss")
    fun getAddress() {
        val memory = Memory(initialMemorySnapshot)
        val addressValue = memory.getInstructionAtAddress(3)
        assertThat(addressValue).isEqualTo(4)
    }

    @Test
    @DisplayName("Given a memory with a single system input, when getting the input, then it should consume and return the value")
    fun getInputShouldConsumeValue() {
        val memory = Memory(initialMemorySnapshot)
        memory.systemInput(1)
        val input = memory.getInput()
        assertThat(input).isEqualTo(1)
        assertThat(memory.input).isEmpty()
    }

    @Test
    @DisplayName("Given a memory with no system input, when getting the input, then it should return null")
    fun getInputWhenEmpty() {
        val memory = Memory(initialMemorySnapshot)
        val input = memory.getInput()
        assertThat(input).isNull()
    }
}