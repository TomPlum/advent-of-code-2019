package com.aoc.intcode.strategy

import com.aoc.intcode.Memory
import com.aoc.intcode.ParameterMode

abstract class OperationStrategy {
    abstract fun execute(memorySnapshot: Memory, mode: ParameterMode): Memory

    protected fun getValue(memorySnapshot: Memory, mode: ParameterMode, addressesAfterPointer: Int): Int {
        val parameterIndex = memorySnapshot.instructionPointer + addressesAfterPointer
        val valueIndex = memorySnapshot.getInstructionAtAddress(parameterIndex)
        return when (mode) {
            ParameterMode.POSITION -> memorySnapshot.getInstructionAtAddress(valueIndex)
            ParameterMode.IMMEDIATE -> valueIndex
        }
    }
}