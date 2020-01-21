package com.aoc.intcode

class Program private constructor(instructionString: String) {

    var instructionPointer = 0
    var currentInstruction = InstructionType.OPCODE
    var memory: Memory

    companion object {
        fun from(instructionList: String): Program = Program(instructionList)
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

    fun incrementInstructionPointer(opCode: OpCode) {
        instructionPointer += opCode.instructionLength()
    }

    fun getCurrentInstruction(): Int = memory.getInstructionAtAddress(instructionPointer)


    private fun parseInstructions(actions: String): MutableList<Int> = actions.split(",").map { it.toInt() }.toMutableList()

    init {
        this.memory = Memory(parseInstructions(instructionString))
    }

}