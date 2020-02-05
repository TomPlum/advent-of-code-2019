package com.aoc.intcode

class IntCodeComputer constructor(programString: String) {
    private val program = Program(programString)

    fun compute(): String {
        var memory = program.memory

        while (true) {
            val opCode = OpCode(memory.getCurrentInstruction().toString())
            try {
                memory = opCode.getOperationStrategy().execute(memory, opCode.parameterModes)
            } catch (e: HaltProgram) {
                return memory.instructions.joinToString(",", postfix = "") { it.toString() }
            }
        }
    }

    fun restoreGravityAssistProgram(noun: Int, verb: Int) {
        program.memory.updateInstructionAtAddress(1, noun)
        program.memory.updateInstructionAtAddress(2, verb)
    }

    fun startAirConditionerDiagnosticTest() = program.memory.systemInput(1)

    fun startThermalRadiatorControllerDiagnosticTest() = program.memory.systemInput(5)

    fun getProgramMemory(): Memory {
        return program.memory
    }

    fun getDiagnosticCode(): Int? {
        //TODO: move into memory as something like "Get last output value", then expose here as diagnostic code
        if (program.memory.output.size > 0) return program.memory.getDiagnosticCode()
        throw IllegalStateException("System output is empty!")
    }
}


