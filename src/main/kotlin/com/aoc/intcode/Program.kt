package com.aoc.intcode

class Program private constructor(actionString: String) {

    var currentActionType = ActionType.OPCODE
    var actions: MutableList<Int>

    companion object {
        fun from(actionString: String): Program = Program(actionString)
    }

    fun updateAction(index: Int, value: Int) {
        actions[index] = value
    }

    fun getAction(index: Int): Int {
        return actions[index]
    }

    override fun toString(): String {
        return actions.joinToString(",", postfix = "") { it.toString() }
    }

    fun updateNextActionType() {
        currentActionType = when (currentActionType) {
            ActionType.OPCODE -> ActionType.FIRST_INPUT
            ActionType.FIRST_INPUT -> ActionType.SECOND_INPUT
            ActionType.SECOND_INPUT -> ActionType.OUTPUT
            ActionType.OUTPUT -> ActionType.OPCODE
        }
    }

    private fun parseProgram(actions: String): MutableList<Int> = actions.split(",").map { it.toInt() }.toMutableList()

    init {
        this.actions = parseProgram(actionString)
    }

}