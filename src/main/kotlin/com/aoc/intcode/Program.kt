package com.aoc.intcode

class Program (instructionString: String) {

    var instructionPointer = 0
    var memory: Memory

    override fun toString(): String {
        return memory.instructions.joinToString(",", postfix = "") { it.toString() }
    }

    fun incrementInstructionPointer(opCode: OpCode) {
        instructionPointer += opCode.instructionLength()
    }

    fun getCurrentInstruction(): Int = memory.getInstructionAtAddress(instructionPointer)


    private fun parseInstructions(actions: String): MutableList<Int> = actions.split(",").map { it.toInt() }.toMutableList()

    init {
        this.memory = Memory(parseInstructions(instructionString))
    }

}