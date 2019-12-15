package com.aoc.intcode

class IntCodeComputer {

    fun compute(programString: String): String {
        val program = parseProgram(programString)
        var operation = Operation.UNKNOWN
        var firstInput = 0
        var secondInput = 0
        var currentAction = Action.OPCODE
        for (v in program) {

            if (currentAction == Action.OPCODE) {
                operation = OpCode.from(v).operation()
                if (operation == Operation.HALT) return toProgramString(program)
            }

            if (currentAction == Action.FIRST_INPUT) {
                firstInput = program[v]
            }

            if (currentAction == Action.SECOND_INPUT) {
                secondInput = program[v]
            }

            if (currentAction == Action.OUTPUT) {
                when (operation) {
                    Operation.ADD -> program[v] = firstInput + secondInput
                    Operation.MULTIPLY -> program[v] = firstInput * secondInput
                    Operation.HALT -> return toProgramString(program)
                    Operation.UNKNOWN -> TODO()
                }
            }

            //Set Next Action
            currentAction = when (currentAction) {
                Action.OPCODE ->  Action.FIRST_INPUT
                Action.FIRST_INPUT ->  Action.SECOND_INPUT
                Action.SECOND_INPUT -> Action.OUTPUT
                Action.OUTPUT -> Action.OPCODE
            }
        }
        throw IllegalStateException("Program failed unexpectedly")
    }

    private enum class Action {
        OPCODE, FIRST_INPUT, SECOND_INPUT, OUTPUT
    }

    private fun parseProgram(program: String): MutableList<Int> = program.split(",").map { it.toInt() }.toMutableList()

    private fun toProgramString(program: MutableList<Int>): String = program.joinToString(",", postfix = "") { it.toString() }

}