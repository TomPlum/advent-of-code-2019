package com.aoc.intcode.computer.instructions.strategies

import com.aoc.intcode.computer.Memory
import com.aoc.intcode.computer.ParameterMode
import com.aoc.intcode.computer.exceptions.SignalInterrupt
import com.aoc.intcode.computer.instructions.InstructionLength
import com.aoc.intcode.computer.instructions.InstructionStrategy
import java.util.*

class Input : InstructionStrategy {
    override fun execute(memorySnapshot: Memory, modes: Stack<ParameterMode>): Memory {
        val inputAddress = getWriteToAddress(memorySnapshot, modes.pop(), 1)
        val systemInputValue = memorySnapshot.input.getValue() ?: throw SignalInterrupt()
        memorySnapshot.updateInstructionAtAddress(inputAddress, systemInputValue)
        memorySnapshot.incrementInstructionPointer(InstructionLength.TWO_ADDRESS_INSTRUCTION)
        return memorySnapshot
    }
}