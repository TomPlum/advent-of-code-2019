package com.aoc.intcode

import java.lang.IllegalArgumentException

class IntCodeComputer constructor(programString: String) {
    private val program = Program.from(programString)

    fun compute(): String {
        val memory = program.memory

        var firstInput = 0
        var secondInput = 0

        var operation = Operation.UNKNOWN

        for (instruction in memory.instructions) {
            val currentAction = program.currentInstruction

            if (currentAction == InstructionType.OPCODE) {
                operation = OpCode.from(instruction).operation()
                if (operation == Operation.HALT) return program.toString()
            }

            if (currentAction == InstructionType.FIRST_INPUT) {
                firstInput = memory.getInstructionAtAddress(instruction)
            }

            if (currentAction == InstructionType.SECOND_INPUT) {
                secondInput = memory.getInstructionAtAddress(instruction)
            }

            if (currentAction == InstructionType.OUTPUT) {
                when (operation) {
                    Operation.ADD -> memory.updateInstructionAtAddress(instruction, firstInput + secondInput)
                    Operation.MULTIPLY -> memory.updateInstructionAtAddress(instruction, firstInput * secondInput)
                    Operation.HALT -> return program.toString()
                    Operation.UNKNOWN -> throw IllegalArgumentException("Operation Unknown For Instruction $instruction")
                }
            }

            program.updateNextInstructionType()
        }
        throw IllegalStateException("Program failed unexpectedly")
    }

    fun restoreGravityAssistProgram(noun: Int, verb: Int) {
        program.memory.updateInstructionAtAddress(1, noun)
        program.memory.updateInstructionAtAddress(2, verb)
    }

    fun getProgramMemory(): Memory {
        return program.memory
    }

}