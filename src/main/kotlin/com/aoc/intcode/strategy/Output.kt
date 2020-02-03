package com.aoc.intcode.strategy

import com.aoc.intcode.Memory
import com.aoc.intcode.ParameterMode

class Output: InstructionStrategy() {
    override fun execute(memorySnapshot: Memory, mode: ParameterMode): Memory {
        val outputValue = getValue(memorySnapshot, mode, 1)
        memorySnapshot.systemOutput(outputValue)
        return memorySnapshot
    }
}