package com.aoc.intcode.strategy

import com.aoc.intcode.Memory
import com.aoc.intcode.ParameterMode

class Add : InstructionStrategy {
    override fun execute(memorySnapshot: Memory, mode: ParameterMode): Memory {
        val firstValue = getValue(memorySnapshot, mode, 1)
        val secondValue = getValue(memorySnapshot, mode, 2)
        val updateAddress = memorySnapshot.getInstructionAtAddress(memorySnapshot.instructionPointer + 3)
        memorySnapshot.updateInstructionAtAddress(updateAddress, firstValue + secondValue)
        return memorySnapshot
    }
}
