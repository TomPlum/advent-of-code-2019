package com.aoc.intcode

import java.lang.IllegalArgumentException
import java.util.*
class IntCodeComputer constructor(programString: String) {
    private val program = Program(programString)

    private val systemInput = LinkedList<Int>()
    private val systemOutput = LinkedList<Int>()

    fun compute(): String {
        val memory = program.memory

        while(true) {
            val pointer = program.memory.instructionPointer
            val opCode = OpCode(memory.getCurrentInstruction().toString())

            when (opCode.operation()) {
                Operation.ADD -> {
                    val firstInputValue = getInstructionValue(opCode, memory, pointer + 1)
                    val secondInputValue = getInstructionValue(opCode, memory, pointer + 2)
                    val addressToUpdate = memory.getInstructionAtAddress(pointer + 3)
                    memory.updateInstructionAtAddress(addressToUpdate, firstInputValue + secondInputValue)
                }
                Operation.MULTIPLY -> {
                    val firstInputValue = getInstructionValue(opCode, memory, pointer + 1)
                    val secondInputValue = getInstructionValue(opCode, memory, pointer + 2)
                    val addressToUpdate = memory.getInstructionAtAddress(pointer + 3)
                    memory.updateInstructionAtAddress(addressToUpdate, firstInputValue * secondInputValue)
                }
                Operation.INPUT -> {
                    val inputAddress = memory.getInstructionAtAddress(pointer + 1)
                    memory.updateInstructionAtAddress(inputAddress, systemInput[0])
                }
                Operation.OUTPUT -> {
                    val input = getInstructionValue(opCode, memory, pointer + 1)
                    systemOutput(input)
                }
                Operation.JUMP_IF_TRUE -> {
                    val firstParameter = getInstructionValue(opCode, memory, pointer + 1)
                    val secondParameter = getInstructionValue(opCode, memory, pointer + 2)
                    if (firstParameter != 0) {
                        memory.instructionPointer = secondParameter
                    } else {
                        memory.incrementInstructionPointer(opCode.instructionLength())
                    }
                }
                Operation.JUMP_IF_FALSE -> {
                    val firstParameter = getInstructionValue(opCode, memory, pointer + 1)
                    val secondParameter = getInstructionValue(opCode, memory, pointer + 2)
                    if (firstParameter == 0) {
                        memory.instructionPointer = secondParameter
                    } else {
                        memory.incrementInstructionPointer(opCode.instructionLength())
                    }
                }
                Operation.LESS_THAN -> {
                    val firstParameter = getInstructionValue(opCode, memory, pointer + 1)
                    val secondParameter = getInstructionValue(opCode, memory, pointer + 2)
                    val updateIndex = memory.getInstructionAtAddress(pointer + 3)
                    if (firstParameter < secondParameter) {
                        memory.updateInstructionAtAddress(updateIndex, 1)
                    } else {
                        memory.updateInstructionAtAddress(updateIndex, 0)
                    }
                }
                Operation.EQUALS -> {
                    val firstParameter = getInstructionValue(opCode, memory, pointer + 1)
                    val secondParameter = getInstructionValue(opCode, memory, pointer + 2)
                    val updateIndex = memory.getInstructionAtAddress(pointer + 3)
                    if (firstParameter == secondParameter) {
                        memory.updateInstructionAtAddress(updateIndex, 1)
                    } else {
                        memory.updateInstructionAtAddress(updateIndex, 0)
                    }
                }
                Operation.HALT -> {
                    println("System Input: $systemInput")
                    println("System Output: $systemOutput")
                    return program.toString()
                }
                Operation.UNKNOWN -> throw IllegalArgumentException("Operation Unknown For Instruction ${opCode.getValue()}")
            }

            if (opCode.operation() != Operation.JUMP_IF_FALSE && opCode.operation() != Operation.JUMP_IF_TRUE) {
                memory.incrementInstructionPointer(opCode.instructionLength())
            }
        }
    }

    private fun getInstructionValue(opCode: OpCode, memory: Memory, addressIndex: Int): Int {
        val mode = opCode.getParameterMode()
        val index = memory.getInstructionAtAddress(addressIndex)
        return when (mode) {
            ParameterMode.POSITION -> memory.getInstructionAtAddress(index)
            ParameterMode.IMMEDIATE -> index
        }
    }

    fun restoreGravityAssistProgram(noun: Int, verb: Int) {
        program.memory.updateInstructionAtAddress(1, noun)
        program.memory.updateInstructionAtAddress(2, verb)
    }

    fun startAirConditionerDiagnosticTest() = systemInput(1)

    fun startThermalRadiatorControllerDiagnosticTest() = systemInput(5)

    fun getProgramMemory(): Memory {
        return program.memory
    }

    fun systemInput(value: Int) = systemInput.add(value)

    private fun systemOutput(value: Int) = systemOutput.add(value)

    fun getDiagnosticCode(): Int? {
        if (systemOutput.size > 0) return systemOutput.last
        throw IllegalStateException("System output is empty!")
    }

}

