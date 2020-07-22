package com.aoc.intcode.computer.instructions

import com.aoc.intcode.computer.Memory
import com.aoc.intcode.computer.ParameterMode
import java.util.*

interface InstructionStrategy {
    fun execute(memorySnapshot: Memory, modes: Stack<ParameterMode>): Memory

    fun getValue(memorySnapshot: Memory, mode: ParameterMode, addressesAfterPointer: Int): Long {
        val parameterIndex = memorySnapshot.instructionPointer + addressesAfterPointer
        val parameterValue = memorySnapshot.getInstructionAtAddress(parameterIndex)
        return when (mode) {
            ParameterMode.POSITION -> memorySnapshot.getInstructionAtAddress(parameterValue)
            ParameterMode.IMMEDIATE -> parameterValue
            ParameterMode.RELATIVE -> memorySnapshot.getInstructionAtAddress(memorySnapshot.relativeBase + parameterValue)
        }
    }

    fun getWriteToAddress(memorySnapshot: Memory, mode: ParameterMode, addressesAfterPointer: Int): Long {
        val parameterIndex = memorySnapshot.instructionPointer + addressesAfterPointer
        val parameterValue = memorySnapshot.getInstructionAtAddress(parameterIndex)
        return when (mode) {
            ParameterMode.POSITION -> parameterValue
            ParameterMode.IMMEDIATE -> throw IllegalStateException("You cannot write to a memory address in immediate mode")
            ParameterMode.RELATIVE -> memorySnapshot.relativeBase + parameterValue
        }
    }
}