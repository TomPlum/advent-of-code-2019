package com.aoc.intcode.computer.instructions.strategies

import com.aoc.intcode.computer.instructions.InstructionLength
import com.aoc.intcode.computer.Memory
import com.aoc.intcode.computer.ParameterMode
import com.aoc.intcode.computer.instructions.InstructionStrategy
import java.util.*

class Multiply : InstructionStrategy {
    override fun execute(memorySnapshot: Memory, modes: Stack<ParameterMode>): Memory {
        val firstValue = getValue(memorySnapshot, modes.pop(), 1)
        val secondValue = getValue(memorySnapshot, modes.pop(), 2)
        val updateAddress = getWriteToAddress(memorySnapshot, modes.pop(), 3)
        memorySnapshot.updateInstructionAtAddress(updateAddress, firstValue * secondValue)
        memorySnapshot.incrementInstructionPointer(InstructionLength.FOUR_ADDRESS_INSTRUCTION)
        return memorySnapshot
    }
}