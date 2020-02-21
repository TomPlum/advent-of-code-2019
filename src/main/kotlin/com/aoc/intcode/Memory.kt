package com.aoc.intcode

import com.aoc.intcode.instructions.InstructionLength
import java.lang.IllegalArgumentException
import java.util.*

class Memory constructor(private val initialMemorySnapshot: List<Long>) {
    var instructions: MutableList<Long> = initialMemorySnapshot.toMutableList()
    var instructionPointer = 0L
    var relativeBase = 0
    val input = LinkedList<Long>()
    val output = LinkedList<Long>()

    fun reset() {
        instructions = initialMemorySnapshot.toMutableList()
    }

    fun getCurrentInstruction(): Long = getInstructionAtAddress(instructionPointer)

    fun updateInstructionAtAddress(addressIndex: Long, instructionValue: Long) {
        padMemory(addressIndex)
        instructions[addressIndex.toInt()] = instructionValue
    }

    fun getInstructionAtAddress(addressIndex: Long): Long {
        padMemory(addressIndex)
        return instructions[addressIndex.toInt()]
    }

    private fun padMemory(addressIndex: Long) {
        if (addressIndex < 0) throw IllegalArgumentException("Invalid Memory Address ($addressIndex)")
        if (addressIndex > instructions.size - 1) {
            instructions.apply { repeat((addressIndex - instructions.size + 1).toInt()) { this.add(0) } }
        }
    }

    fun incrementInstructionPointer(length: InstructionLength) {
        instructionPointer += length.value
    }

    fun systemInput(value: Long) = input.add(value)

    fun systemOutput(value: Long) = output.add(value)

    fun getLastOutputValue(): Long {
        if (output.size > 0) return output.last
        throw IllegalStateException("System output is empty!")
    }

    fun getInput(): Long? = input.poll()

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