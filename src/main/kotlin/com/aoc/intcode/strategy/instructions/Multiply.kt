package com.aoc.intcode.strategy.instructions

import com.aoc.intcode.InstructionLength
import com.aoc.intcode.Memory
import com.aoc.intcode.ParameterMode
import com.aoc.intcode.strategy.InstructionStrategy
import java.util.*

class Multiply : InstructionStrategy {
    override fun execute(memorySnapshot: Memory, modes: Stack<ParameterMode>): Memory {
        val firstValue = getValue(memorySnapshot, modes.pop(), 1)
        val secondValue = getValue(memorySnapshot, modes.pop(), 2)
        val updateAddress = getWriteToIndex(memorySnapshot, 3)
        memorySnapshot.updateInstructionAtAddress(updateAddress, firstValue * secondValue)
        memorySnapshot.incrementInstructionPointer(InstructionLength.FOUR_ADDRESS_INSTRUCTION.length)
        return memorySnapshot
    }
}