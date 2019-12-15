package com.aoc.intcode

class IntCodeComputer {

    fun compute(programString: String): String {
        val program = Program.from(programString)
        var operation = Operation.UNKNOWN
        var firstInput = 0
        var secondInput = 0
        for (v in program.actions) {
            val currentAction = program.currentActionType

            if (currentAction == ActionType.OPCODE) {
                operation = OpCode.from(v).operation()
                if (operation == Operation.HALT) return program.toString()
            }

            if (currentAction == ActionType.FIRST_INPUT) {
                firstInput = program.actions[v]
            }

            if (currentAction == ActionType.SECOND_INPUT) {
                secondInput = program.actions[v]
            }

            if (currentAction == ActionType.OUTPUT) {
                when (operation) {
                    Operation.ADD -> program.actions[v] = firstInput + secondInput
                    Operation.MULTIPLY -> program.actions[v] = firstInput * secondInput
                    Operation.HALT -> return program.toString()
                    Operation.UNKNOWN -> TODO()
                }
            }

            program.updateNextActionType()
        }
        throw IllegalStateException("Program failed unexpectedly")
    }

}