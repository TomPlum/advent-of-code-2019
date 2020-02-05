package com.aoc.intcode.instructions.strategies

import com.aoc.intcode.instructions.InstructionLength
import com.aoc.intcode.Memory
import com.aoc.intcode.ParameterMode
import com.aoc.intcode.instructions.InstructionStrategy
import java.util.*

class JumpIfFalse : InstructionStrategy {
    override fun execute(memorySnapshot: Memory, modes: Stack<ParameterMode>): Memory {
        val firstParameter = getValue(memorySnapshot, modes.pop(), 1)
        val secondParameter = getValue(memorySnapshot, modes.pop(), 2)
        if (firstParameter == 0) {
            memorySnapshot.instructionPointer = secondParameter
        } else {
            memorySnapshot.incrementInstructionPointer(InstructionLength.THREE_ADDRESS_INSTRUCTION.length)
        }
        return memorySnapshot
    }
}