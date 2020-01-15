package com.aoc.intcode

class Memory constructor(private val initialMemorySnapshot: List<Int>) {
    var instructions: MutableList<Int> = initialMemorySnapshot.toMutableList()

    fun reset() {
        instructions = initialMemorySnapshot.toMutableList()
    }

    fun updateInstructionAtAddress(addressIndex: Int, instructionValue: Int) {
        instructions[addressIndex] = instructionValue
    }

    fun getInstructionAtAddress(addressIndex: Int): Int = instructions[addressIndex]
}