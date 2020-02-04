package com.aoc.intcode.strategy

import com.aoc.intcode.Memory
import com.aoc.intcode.ParameterMode
import java.util.*

interface InstructionStrategy {
    fun execute(memorySnapshot: Memory, modes: Stack<ParameterMode>): Memory

    fun getValue(memorySnapshot: Memory, mode: ParameterMode, addressesAfterPointer: Int): Int {
        val parameterIndex = memorySnapshot.instructionPointer + addressesAfterPointer
        val valueIndex = memorySnapshot.getInstructionAtAddress(parameterIndex)
        return when (mode) {
            ParameterMode.POSITION -> memorySnapshot.getInstructionAtAddress(valueIndex)
            ParameterMode.IMMEDIATE -> valueIndex
        }
    }
}