package com.aoc.intcode

import java.lang.IllegalArgumentException

class IntCodeComputer constructor(programString: String) {
    private val program = Program.from(programString)

    fun compute(): String {
        val memory = program.memory

        var firstInput = 0
        var secondInput = 0

        var operation = Operation.UNKNOWN

        for (v in memory.addresses) {
            val currentAction = program.currentActionType

            if (currentAction == AddressType.OPCODE) {
                operation = OpCode.from(v).operation()
                if (operation == Operation.HALT) return program.toString()
            }

            if (currentAction == AddressType.FIRST_INPUT) {
                firstInput = memory.getAddressValue(v)
            }

            if (currentAction == AddressType.SECOND_INPUT) {
                secondInput = memory.getAddressValue(v)
            }

            if (currentAction == AddressType.OUTPUT) {
                when (operation) {
                    Operation.ADD -> memory.updateAddress(v, firstInput + secondInput)
                    Operation.MULTIPLY -> memory.updateAddress(v, firstInput * secondInput)
                    Operation.HALT -> return program.toString()
                    Operation.UNKNOWN -> throw IllegalArgumentException("Operation Unknown For Address Value $v")
                }
            }

            program.updateNextActionType()
        }
        throw IllegalStateException("Program failed unexpectedly")
    }

    fun restoreGravityAssistProgram(noun: Int, verb: Int) {
        program.memory.updateAddress(1, noun)
        program.memory.updateAddress(2, verb)
    }

    fun getProgramMemory(): Memory {
        return program.memory
    }

}