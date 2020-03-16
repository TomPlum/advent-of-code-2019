package com.aoc.intcode.computer.instructions.strategies

import com.aoc.intcode.computer.instructions.InstructionLength
import com.aoc.intcode.computer.Memory
import com.aoc.intcode.computer.ParameterMode
import com.aoc.intcode.computer.instructions.InstructionStrategy
import java.util.*

class Equals: InstructionStrategy {
    override fun execute(memorySnapshot: Memory, modes: Stack<ParameterMode>): Memory {
        val firstParameter = getValue(memorySnapshot, modes.pop(), 1)
        val secondParameter = getValue(memorySnapshot, modes.pop(), 2)
        val updateIndex = getWriteToAddress(memorySnapshot, modes.pop(), 3)
        if (firstParameter == secondParameter) {
            memorySnapshot.updateInstructionAtAddress(updateIndex, 1)
        } else {
            memorySnapshot.updateInstructionAtAddress(updateIndex, 0)
        }
        memorySnapshot.incrementInstructionPointer(InstructionLength.FOUR_ADDRESS_INSTRUCTION)
        return memorySnapshot
    }

}