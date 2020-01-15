package com.aoc.intcode

class Program private constructor(actionString: String) {

    var currentInstruction = InstructionType.OPCODE
    var memory: Memory

    companion object {
        fun from(actionString: String): Program = Program(actionString)
    }

    override fun toString(): String {
        return memory.instructions.joinToString(",", postfix = "") { it.toString() }
    }

    fun updateNextInstructionType() {
        currentInstruction = when (currentInstruction) {
            InstructionType.OPCODE -> InstructionType.FIRST_INPUT
            InstructionType.FIRST_INPUT -> InstructionType.SECOND_INPUT
            InstructionType.SECOND_INPUT -> InstructionType.OUTPUT
            InstructionType.OUTPUT -> InstructionType.OPCODE
        }
    }

    private fun parseInstruction(actions: String): MutableList<Int> = actions.split(",").map { it.toInt() }.toMutableList()

    init {
        this.memory = Memory(parseInstruction(actionString))
    }

}