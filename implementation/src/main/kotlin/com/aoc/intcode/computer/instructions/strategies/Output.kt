package com.aoc.intcode.computer.instructions.strategies

import com.aoc.intcode.computer.instructions.InstructionLength
import com.aoc.intcode.computer.Memory
import com.aoc.intcode.computer.ParameterMode
import com.aoc.intcode.computer.instructions.InstructionStrategy
import java.util.*

class Output : InstructionStrategy {
    override fun execute(memorySnapshot: Memory, modes: Stack<ParameterMode>): Memory {
        val outputValue = getValue(memorySnapshot, modes.pop(), 1)
        memorySnapshot.output.add(outputValue)
        memorySnapshot.incrementInstructionPointer(InstructionLength.TWO_ADDRESS_INSTRUCTION)
        return memorySnapshot
    }
}