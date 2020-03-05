package com.aoc.intcode

import com.aoc.intcode.instructions.InstructionLength
import org.slf4j.LoggerFactory
import java.util.*

class Memory constructor(private val initialMemorySnapshot: List<Long>) {
    var instructions: MutableList<Long> = initialMemorySnapshot.toMutableList()
    var instructionPointer = 0L
    var relativeBase = 0L
    val input = LinkedList<Long>()
    val output = SystemOutput()
    val logger = LoggerFactory.getLogger(javaClass)

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
        logger.debug("Incrementing Instruction Pointer")
        instructionPointer += length.value
    }

    fun systemInput(value: Long) = input.add(value)

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