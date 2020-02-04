package com.aoc.intcode.strategy

import com.aoc.intcode.Memory
import com.aoc.intcode.ParameterMode
import java.util.*

class Multiply : InstructionStrategy {
    override fun execute(memorySnapshot: Memory, modes: Stack<ParameterMode>): Memory {
        val firstValue = getValue(memorySnapshot, modes.pop(), 1)
        val secondValue = getValue(memorySnapshot, modes.pop(), 2)
        val updateAddress = memorySnapshot.getInstructionAtAddress(memorySnapshot.instructionPointer + 3)
        memorySnapshot.updateInstructionAtAddress(updateAddress, firstValue * secondValue)
        return memorySnapshot
    }
}