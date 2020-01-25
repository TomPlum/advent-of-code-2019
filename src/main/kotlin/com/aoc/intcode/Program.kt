package com.aoc.intcode

class Program (instructionString: String) {

    var memory: Memory

    override fun toString(): String {
        return memory.instructions.joinToString(",", postfix = "") { it.toString() }
    }

    private fun parseInstructions(actions: String): MutableList<Int> = actions.split(",").map { it.toInt() }.toMutableList()

    init {
        this.memory = Memory(parseInstructions(instructionString))
    }

}