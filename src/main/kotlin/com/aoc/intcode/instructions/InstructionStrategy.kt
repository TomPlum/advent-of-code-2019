package com.aoc.intcode.instructions

import com.aoc.intcode.Memory
import com.aoc.intcode.ParameterMode
import java.util.*

interface InstructionStrategy {
    fun execute(memorySnapshot: Memory, modes: Stack<ParameterMode>): Memory

    fun getValue(memorySnapshot: Memory, mode: ParameterMode, addressesAfterPointer: Int): Long {
        val parameterIndex = memorySnapshot.instructionPointer + addressesAfterPointer
        val valueIndex = memorySnapshot.getInstructionAtAddress(parameterIndex)
        return when (mode) {
            ParameterMode.POSITION -> memorySnapshot.getInstructionAtAddress(valueIndex)
            ParameterMode.IMMEDIATE -> valueIndex
            ParameterMode.RELATIVE -> memorySnapshot.getInstructionAtAddress(memorySnapshot.relativeBase + valueIndex)
        }
    }

    fun getWriteToIndex(memorySnapshot: Memory, addressesAfterPointer: Int): Long {
        val parameterIndex = memorySnapshot.instructionPointer + addressesAfterPointer
        return memorySnapshot.getInstructionAtAddress(parameterIndex)
    }
}