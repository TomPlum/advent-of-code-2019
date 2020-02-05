package com.aoc.intcode.instructions.strategies

import com.aoc.intcode.instructions.InstructionLength
import com.aoc.intcode.Memory
import com.aoc.intcode.ParameterMode
import com.aoc.intcode.instructions.InstructionStrategy
import java.util.*

class Input : InstructionStrategy {
    override fun execute(memorySnapshot: Memory, modes: Stack<ParameterMode>): Memory {
        val inputAddress = getWriteToIndex(memorySnapshot, 1)
        memorySnapshot.updateInstructionAtAddress(inputAddress, memorySnapshot.getInput())
        memorySnapshot.incrementInstructionPointer(InstructionLength.TWO_ADDRESS_INSTRUCTION.length)
        return memorySnapshot
    }
}