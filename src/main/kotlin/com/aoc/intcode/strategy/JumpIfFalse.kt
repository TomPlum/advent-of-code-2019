package com.aoc.intcode.strategy

import com.aoc.intcode.InstructionLength
import com.aoc.intcode.Memory
import com.aoc.intcode.ParameterMode

class JumpIfFalse : InstructionStrategy {
    override fun execute(memorySnapshot: Memory, mode: ParameterMode): Memory {
        val firstParameter = getValue(memorySnapshot, mode, 1)
        val secondParameter = getValue(memorySnapshot, mode, 2)
        if (firstParameter == 0) {
            memorySnapshot.instructionPointer = secondParameter
        } else {
            memorySnapshot.incrementInstructionPointer(InstructionLength.TWO_ADDRESS_INSTRUCTION.length)
        }
        return memorySnapshot
    }
}