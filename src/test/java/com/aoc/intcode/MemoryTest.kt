package com.aoc.intcode

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class MemoryTest {
    private val initialMemorySnapshot = listOf(1, 2, 3, 4, 5)

    @Test
    @DisplayName("Given a valid address input, when creating memory, it should return the addresses")
    internal fun createMemory() {
        val memory = Memory(initialMemorySnapshot)
        assertThat(memory.addresses).isEqualTo(listOf(1,2,3,4,5))
    }

    @Test
    @DisplayName("Given valid memory, when updating an address and then resetting, it should set the addresses back to their origin snapshot")
    internal fun reset() {
        val memory = Memory(initialMemorySnapshot)
        memory.updateAddress(0, 10)
        memory.reset()
        assertThat(memory.addresses).isEqualTo(listOf(1,2,3,4,5))
    }

    @Test
    @DisplayName("Given a valid address index, when updating the value at that address, then it should set the new value correctly")
    internal fun updateAddress() {
        val memory = Memory(initialMemorySnapshot)
        memory.updateAddress(0, 12)
        assertThat(memory.getAddressValue(0)).isEqualTo(12)
    }

    @Test
    @DisplayName("Given a valid set of addresses, when getting an address via index, it should return the correct addresss")
    internal fun getAddress() {
        val memory = Memory(initialMemorySnapshot)
        val addressValue = memory.getAddressValue(3)
        assertThat(addressValue).isEqualTo(4)
    }
}