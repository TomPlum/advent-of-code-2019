package com.aoc.intcode.strategy

import com.aoc.intcode.InstructionLength
import com.aoc.intcode.Memory
import com.aoc.intcode.ParameterMode
import java.util.*

class Equals: InstructionStrategy {
    override fun execute(memorySnapshot: Memory, modes: Stack<ParameterMode>): Memory {
        val firstParameter = getValue(memorySnapshot, modes.pop(), 1)
        val secondParameter = getValue(memorySnapshot, modes.pop(), 2)
        val updateIndex = getWriteToIndex(memorySnapshot, 3)
        if (firstParameter == secondParameter) {
            memorySnapshot.updateInstructionAtAddress(updateIndex, 1)
        } else {
            memorySnapshot.updateInstructionAtAddress(updateIndex, 0)
        }
        memorySnapshot.incrementInstructionPointer(InstructionLength.FOUR_ADDRESS_INSTRUCTION.length)
        return memorySnapshot
    }

}