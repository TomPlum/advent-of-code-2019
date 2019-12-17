package com.aoc.intcode

class Program private constructor(actionString: String) {

    var currentActionType = ActionType.OPCODE
    var memory: Memory

    companion object {
        fun from(actionString: String): Program = Program(actionString)
    }

    override fun toString(): String {
        return memory.addresses.joinToString(",", postfix = "") { it.toString() }
    }

    fun updateNextActionType() {
        currentActionType = when (currentActionType) {
            ActionType.OPCODE -> ActionType.FIRST_INPUT
            ActionType.FIRST_INPUT -> ActionType.SECOND_INPUT
            ActionType.SECOND_INPUT -> ActionType.OUTPUT
            ActionType.OUTPUT -> ActionType.OPCODE
        }
    }

    private fun parseMemoryAddresses(actions: String): MutableList<Int> = actions.split(",").map { it.toInt() }.toMutableList()

    init {
        this.memory = Memory(parseMemoryAddresses(actionString))
    }

}