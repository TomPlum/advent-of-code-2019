package com.aoc.intcode

class Memory constructor(private val initialMemorySnapshot: List<Int>) {
    var addresses: MutableList<Int> = initialMemorySnapshot.toMutableList()

    fun reset() {
        addresses = initialMemorySnapshot.toMutableList()
    }

    fun updateAddress(index: Int, value: Int) {
        addresses[index] = value
    }

    fun getAddressValue(index: Int): Int = addresses[index]
}