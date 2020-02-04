package com.aoc.intcode.strategy

import com.aoc.intcode.Memory
import com.aoc.intcode.ParameterMode
import java.util.*

class Output : InstructionStrategy {
    override fun execute(memorySnapshot: Memory, modes: Stack<ParameterMode>): Memory {
        val outputValue = getValue(memorySnapshot, modes.pop(), 1)
        memorySnapshot.systemOutput(outputValue)
        return memorySnapshot
    }
}