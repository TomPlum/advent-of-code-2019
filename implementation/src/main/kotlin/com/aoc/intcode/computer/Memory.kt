package com.aoc.intcode.computer

import com.aoc.intcode.computer.instructions.InstructionLength
import log.AdventLogger

/**
 * The main memory of the [IntCodeComputer].
 *
 * Maintains a [MutableList] of [Long] values that represent instructions.
 * Each instruction is uniquely identified by an [OpCode] and can have differing [InstructionLength].
 */
class Memory constructor(private val initialMemorySnapshot: List<Long>) {
    var instructions: MutableList<Long> = initialMemorySnapshot.toMutableList()
    //private val cache = hashMapOf<Long, Long>() //Key: Address Index, Value: Memory Instruction
    var instructionPointer = 0L
    var relativeBase = 0L
    val input = SystemInput()
    val output = SystemOutput()

    fun reset() {
        instructions = initialMemorySnapshot.toMutableList()
        instructionPointer = 0L
        relativeBase = 0L
        input.clear()
        output.clear()
    }

    fun getCurrentInstruction(): Long = getInstructionAtAddress(instructionPointer)

    fun updateInstructionAtAddress(addressIndex: Long, instructionValue: Long) {
        padMemory(addressIndex)
        instructions[addressIndex.toInt()] = instructionValue
    }

    fun getInstructionAtAddress(addressIndex: Long): Long {
        padMemory(addressIndex)
        //return cache.getOrPut(addressIndex) { instructions[addressIndex.toInt()] }
        return instructions[addressIndex.toInt()]
    }

    private fun padMemory(addressIndex: Long) {
        if (addressIndex < 0) throw IllegalArgumentException("Invalid Memory Address ($addressIndex)")
        if (addressIndex > instructions.size - 1) {
            instructions.apply { repeat((addressIndex - instructions.size + 1).toInt()) { this.add(0) } }
        }
    }

    fun incrementInstructionPointer(length: InstructionLength) {
        AdventLogger.trace("Incrementing Instruction Pointer")
        instructionPointer += length.value
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