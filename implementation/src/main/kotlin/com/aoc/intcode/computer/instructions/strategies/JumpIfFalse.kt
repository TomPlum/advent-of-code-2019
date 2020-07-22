package com.aoc.intcode.computer.instructions.strategies

import com.aoc.intcode.computer.Memory
import com.aoc.intcode.computer.ParameterMode
import com.aoc.intcode.computer.instructions.InstructionLength
import com.aoc.intcode.computer.instructions.InstructionStrategy
import java.util.*

class JumpIfFalse : InstructionStrategy {
    override fun execute(memorySnapshot: Memory, modes: Stack<ParameterMode>): Memory {
        val firstParameter = getValue(memorySnapshot, modes.pop(), 1)
        val secondParameter = getValue(memorySnapshot, modes.pop(), 2)
        if (firstParameter == 0L) {
            memorySnapshot.instructionPointer = secondParameter
        } else {
            memorySnapshot.incrementInstructionPointer(InstructionLength.THREE_ADDRESS_INSTRUCTION)
        }
        return memorySnapshot
    }
}