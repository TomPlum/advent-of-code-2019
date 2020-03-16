package com.aoc.intcode.computer.instructions.strategies

import com.aoc.intcode.computer.Memory
import com.aoc.intcode.computer.ParameterMode
import com.aoc.intcode.computer.instructions.InstructionStrategy
import java.util.*

class Unknown: InstructionStrategy {
    override fun execute(memorySnapshot: Memory, modes: Stack<ParameterMode>): Memory {
        val currentOpCodeValue = memorySnapshot.getCurrentInstruction()
        throw IllegalArgumentException("Operation unknown for instruction $currentOpCodeValue")
    }
}