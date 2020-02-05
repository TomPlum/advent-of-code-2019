package com.aoc.intcode.strategy.instructions

import com.aoc.intcode.InstructionLength
import com.aoc.intcode.Memory
import com.aoc.intcode.ParameterMode
import com.aoc.intcode.strategy.InstructionStrategy
import java.util.*

class Output : InstructionStrategy {
    override fun execute(memorySnapshot: Memory, modes: Stack<ParameterMode>): Memory {
        val outputValue = getValue(memorySnapshot, modes.pop(), 1)
        memorySnapshot.systemOutput(outputValue)
        memorySnapshot.incrementInstructionPointer(InstructionLength.TWO_ADDRESS_INSTRUCTION.length)
        return memorySnapshot
    }
}