package com.aoc.intcode

class IntCodeComputer constructor(private val programString: String) {
    private val program = Program.from(programString)

    fun compute(): String {
        val memory = program.memory

        var firstInput = 0
        var secondInput = 0

        var operation = Operation.UNKNOWN

        for (v in memory.addresses) {
            val currentAction = program.currentActionType

            if (currentAction == ActionType.OPCODE) {
                operation = OpCode.from(v).operation()
                if (operation == Operation.HALT) return program.toString()
            }

            if (currentAction == ActionType.FIRST_INPUT) {
                firstInput = memory.getAddressValue(v)
            }

            if (currentAction == ActionType.SECOND_INPUT) {
                secondInput = memory.getAddressValue(v)
            }

            if (currentAction == ActionType.OUTPUT) {
                when (operation) {
                    Operation.ADD -> memory.updateAddress(v, firstInput + secondInput)
                    Operation.MULTIPLY -> memory.updateAddress(v, firstInput * secondInput)
                    Operation.HALT -> return program.toString()
                    Operation.UNKNOWN -> TODO()
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