package com.aoc.intcode.instructions.strategies

import com.aoc.intcode.instructions.InstructionLength
import com.aoc.intcode.Memory
import com.aoc.intcode.ParameterMode
import com.aoc.intcode.instructions.InstructionStrategy
import java.util.*

class Output : InstructionStrategy {
    override fun execute(memorySnapshot: Memory, modes: Stack<ParameterMode>): Memory {
        val outputValue = getValue(memorySnapshot, modes.pop(), 1)
        memorySnapshot.output.add(outputValue)
        memorySnapshot.incrementInstructionPointer(InstructionLength.TWO_ADDRESS_INSTRUCTION)
        return memorySnapshot
    }
}