package com.aoc.intcode

import java.lang.IllegalArgumentException
import java.util.*

class IntCodeComputer constructor(programString: String) {
    private val program = Program.from(programString)

    val systemInput = LinkedList<Int>()
    val systemOutput = LinkedList<Int>()

    fun compute(): String {
        val memory = program.memory

        while(true) {
            val pointer = program.instructionPointer
            val opCode = OpCode(program.getCurrentInstruction().toString())

            when (opCode.operation()) {
                Operation.ADD -> {
                    val firstParameterMode = opCode.getParameterMode()
                    val firstInputIndex = memory.getInstructionAtAddress(pointer + 1)
                    val firstInputValue = when (firstParameterMode) {
                        ParameterMode.POSITION -> memory.getInstructionAtAddress(firstInputIndex)
                        ParameterMode.IMMEDIATE -> firstInputIndex
                    }

                    val secondParameterMode = opCode.getParameterMode()
                    val secondInputIndex = memory.getInstructionAtAddress(pointer + 2)
                    val secondInputValue = when (secondParameterMode) {
                        ParameterMode.POSITION -> memory.getInstructionAtAddress(secondInputIndex)
                        ParameterMode.IMMEDIATE -> secondInputIndex
                    }

                    val addressToUpdate = memory.getInstructionAtAddress(pointer + 3)
                    memory.updateInstructionAtAddress(addressToUpdate, firstInputValue + secondInputValue)
                }
                Operation.MULTIPLY -> {
                    val firstParameterMode = opCode.getParameterMode()
                    val firstInputIndex = memory.getInstructionAtAddress(pointer + 1)
                    val firstInputValue = when (firstParameterMode) {
                        ParameterMode.POSITION -> memory.getInstructionAtAddress(firstInputIndex)
                        ParameterMode.IMMEDIATE -> firstInputIndex
                    }

                    val secondParameterMode = opCode.getParameterMode()
                    val secondInputIndex = memory.getInstructionAtAddress(pointer + 2)
                    val secondInputValue = when (secondParameterMode) {
                        ParameterMode.POSITION -> memory.getInstructionAtAddress(secondInputIndex)
                        ParameterMode.IMMEDIATE -> secondInputIndex
                    }

                    val addressToUpdate = memory.getInstructionAtAddress(pointer + 3)
                    memory.updateInstructionAtAddress(addressToUpdate, firstInputValue * secondInputValue)
                }
                Operation.INPUT -> {
                    val parameterMode = opCode.getParameterMode()
                    val index = memory.getInstructionAtAddress(pointer + 1)
                    val input = when(parameterMode) {
                        ParameterMode.POSITION -> memory.getInstructionAtAddress(index)
                        ParameterMode.IMMEDIATE -> index
                    }
                    systemInput(input)
                }
                Operation.OUTPUT -> {
                    val parameterMode = opCode.getParameterMode()
                    val index = memory.getInstructionAtAddress(pointer + 1)
                    val input = when(parameterMode) {
                        ParameterMode.POSITION -> memory.getInstructionAtAddress(index)
                        ParameterMode.IMMEDIATE -> index
                    }
                    systemOutput(input)
                }
                Operation.HALT -> {
                    println("System Input: $systemInput")
                    println("System Output: $systemOutput")
                    return program.toString()
                }
                Operation.UNKNOWN -> throw IllegalArgumentException("Operation Unknown For Instruction ${opCode.getValue()}")
            }

            program.incrementInstructionPointer(opCode)
        }
    }

    fun restoreGravityAssistProgram(noun: Int, verb: Int) {
        program.memory.updateInstructionAtAddress(1, noun)
        program.memory.updateInstructionAtAddress(2, verb)
    }

    fun getProgramMemory(): Memory {
        return program.memory
    }

    fun systemInput(value: Int) = systemInput.add(value)

    private fun systemOutput(value: Int) = systemOutput.add(value)

}