package com.aoc.intcode

import java.util.*

class Memory constructor(private val initialMemorySnapshot: List<Int>) {
    var instructions: MutableList<Int> = initialMemorySnapshot.toMutableList()
    var instructionPointer = 0
    val input = LinkedList<Int>()
    val output = LinkedList<Int>()

    fun reset() {
        instructions = initialMemorySnapshot.toMutableList()
    }

    fun getCurrentInstruction(): Int = getInstructionAtAddress(instructionPointer)

    fun updateInstructionAtAddress(addressIndex: Int, instructionValue: Int) {
        instructions[addressIndex] = instructionValue
    }

    fun getInstructionAtAddress(addressIndex: Int): Int = instructions[addressIndex]

    fun incrementInstructionPointer(value: Int) {
        instructionPointer += value
    }

    fun systemInput(value: Int) = input.add(value)

    fun systemOutput(value: Int) = output.add(value)

    fun getLastOutputValue(): Int {
        if (output.size > 0) return output.last
        throw IllegalStateException("System output is empty!")
    }

    fun getInput(): Int? = input.poll()

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