package com.aoc.intcode.strategy

import com.aoc.intcode.Memory
import com.aoc.intcode.ParameterMode

abstract class OperationStrategy {
    abstract fun execute(memorySnapshot: Memory, mode: ParameterMode): Memory

    protected fun getValue(memorySnapshot: Memory, mode: ParameterMode, addressesAfterPointer: Int): Int {
        val index = memorySnapshot.getInstructionAtAddress(memorySnapshot.instructionPointer + addressesAfterPointer)
        return when (mode) {
            ParameterMode.POSITION -> memorySnapshot.getInstructionAtAddress(index)
            ParameterMode.IMMEDIATE -> index
        }
    }
}