package com.aoc.intcode.strategy

import com.aoc.intcode.Memory
import com.aoc.intcode.ParameterMode

abstract class InstructionStrategy {
    abstract fun execute(memorySnapshot: Memory, mode: ParameterMode): Memory //TODO: Pass list of ParameterModes. E.g. 1003 has two modes for Input

    protected fun getValue(memorySnapshot: Memory, mode: ParameterMode, addressesAfterPointer: Int): Int {
        val parameterIndex = memorySnapshot.instructionPointer + addressesAfterPointer
        val valueIndex = memorySnapshot.getInstructionAtAddress(parameterIndex)
        return when (mode) {
            ParameterMode.POSITION -> memorySnapshot.getInstructionAtAddress(valueIndex)
            ParameterMode.IMMEDIATE -> valueIndex
        }
    }
}