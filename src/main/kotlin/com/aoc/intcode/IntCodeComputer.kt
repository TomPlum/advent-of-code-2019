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
            val opCode = OpCode.from(program.getCurrentInstruction())

            when (opCode.operation()) {
                Operation.ADD -> {
                    val firstInputIndex = memory.getInstructionAtAddress(pointer + 1)
                    val secondInputIndex = memory.getInstructionAtAddress(pointer + 2)
                    val firstInputValue = memory.getInstructionAtAddress(firstInputIndex)
                    val secondInputValue = memory.getInstructionAtAddress(secondInputIndex)
                    val addressToUpdate = memory.getInstructionAtAddress(pointer + 3)
                    memory.updateInstructionAtAddress(addressToUpdate, firstInputValue + secondInputValue)
                }
                Operation.MULTIPLY -> {
                    val firstInputIndex = memory.getInstructionAtAddress(pointer + 1)
                    val secondInputIndex = memory.getInstructionAtAddress(pointer + 2)
                    val firstInputValue = memory.getInstructionAtAddress(firstInputIndex)
                    val secondInputValue = memory.getInstructionAtAddress(secondInputIndex)
                    val addressToUpdate = memory.getInstructionAtAddress(pointer + 3)
                    memory.updateInstructionAtAddress(addressToUpdate, firstInputValue * secondInputValue)
                }
                Operation.INPUT -> {
                    val input = memory.getInstructionAtAddress(pointer + 1)
                    systemInput(input)
                }
                Operation.OUTPUT -> {
                    val input = memory.getInstructionAtAddress(pointer + 1)
                    systemOutput(input)
                }
                Operation.HALT -> return program.toString()
                Operation.UNKNOWN -> throw IllegalArgumentException("Operation Unknown For Instruction ${opCode.value}")
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

    private fun systemInput(value: Int) = systemInput.add(value)

    private fun systemOutput(value: Int) = systemOutput.add(value)

}