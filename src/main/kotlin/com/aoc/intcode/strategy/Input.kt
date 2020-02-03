package com.aoc.intcode.strategy

import com.aoc.intcode.Memory
import com.aoc.intcode.ParameterMode

class Input : OperationStrategy() {
    override fun execute(memorySnapshot: Memory, mode: ParameterMode): Memory {
        val inputAddress = getValue(memorySnapshot, mode, 1)
        memorySnapshot.updateInstructionAtAddress(inputAddress, memorySnapshot.getInput())
        return memorySnapshot
    }
}