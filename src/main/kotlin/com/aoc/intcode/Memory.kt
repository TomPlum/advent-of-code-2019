package com.aoc.intcode

class Memory constructor(private val initialMemorySnapshot: List<Int>) {
    var instructions: MutableList<Int> = initialMemorySnapshot.toMutableList()
    var instructionPointer = 0

    fun reset() {
        instructions = initialMemorySnapshot.toMutableList()
    }

    fun updateInstructionAtAddress(addressIndex: Int, instructionValue: Int) {
        instructions[addressIndex] = instructionValue
    }

    fun getInstructionAtAddress(addressIndex: Int): Int = instructions[addressIndex]

    fun incrementInstructionPointer(value: Int) {
        instructionPointer += value
    }

    override fun toString() = instructions.toString()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Memory

        if (instructions != other.instructions) return false

        return true
    }

    override fun hashCode(): Int {
        return instructions.hashCode()
    }


}