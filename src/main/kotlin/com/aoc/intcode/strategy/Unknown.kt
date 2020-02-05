package com.aoc.intcode.strategy

import com.aoc.intcode.Memory
import com.aoc.intcode.ParameterMode
import java.util.*

class Unknown: InstructionStrategy {
    override fun execute(memorySnapshot: Memory, modes: Stack<ParameterMode>): Memory {
        val currentOpCodeValue = memorySnapshot.getCurrentInstruction()
        throw IllegalArgumentException("Operation unknown for instruction $currentOpCodeValue")
    }
}