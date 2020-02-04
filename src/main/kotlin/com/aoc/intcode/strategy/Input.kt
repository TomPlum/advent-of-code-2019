package com.aoc.intcode.strategy

import com.aoc.intcode.Memory
import com.aoc.intcode.ParameterMode
import java.util.*

class Input : InstructionStrategy {
    override fun execute(memorySnapshot: Memory, modes: Stack<ParameterMode>): Memory {
        val inputAddress = getValue(memorySnapshot, modes.pop(), 1)
        memorySnapshot.updateInstructionAtAddress(inputAddress, memorySnapshot.getInput())
        return memorySnapshot
    }
}