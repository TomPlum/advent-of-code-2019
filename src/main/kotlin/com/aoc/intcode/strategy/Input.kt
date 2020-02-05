package com.aoc.intcode.strategy

import com.aoc.intcode.InstructionLength
import com.aoc.intcode.Memory
import com.aoc.intcode.ParameterMode
import java.util.*

class Input : InstructionStrategy {
    override fun execute(memorySnapshot: Memory, modes: Stack<ParameterMode>): Memory {
        val inputAddress = getWriteToIndex(memorySnapshot, 1)
        memorySnapshot.updateInstructionAtAddress(inputAddress, memorySnapshot.getInput())
        memorySnapshot.incrementInstructionPointer(InstructionLength.TWO_ADDRESS_INSTRUCTION.length)
        return memorySnapshot
    }
}