package com.aoc.intcode.instructions.strategies

import com.aoc.intcode.instructions.InstructionLength
import com.aoc.intcode.Memory
import com.aoc.intcode.ParameterMode
import com.aoc.intcode.exceptions.SignalInterrupt
import com.aoc.intcode.instructions.InstructionStrategy
import java.util.*

class Input : InstructionStrategy {
    override fun execute(memorySnapshot: Memory, modes: Stack<ParameterMode>): Memory {
        val inputAddress = getWriteToAddress(memorySnapshot, modes.pop(), 1)
        val systemInputValue = memorySnapshot.getInput() ?: throw SignalInterrupt()
        memorySnapshot.updateInstructionAtAddress(inputAddress, systemInputValue)
        memorySnapshot.incrementInstructionPointer(InstructionLength.TWO_ADDRESS_INSTRUCTION)
        return memorySnapshot
    }
}