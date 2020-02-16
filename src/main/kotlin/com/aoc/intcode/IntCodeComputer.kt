package com.aoc.intcode

import com.aoc.intcode.exceptions.HaltProgram

class IntCodeComputer constructor(programString: String) {
    private val program = Program(programString)

    fun compute(): String {
        var memory = program.memory

        while (true) {
            val opCode = OpCode(memory.getCurrentInstruction().toString())
            try {
                memory = opCode.getInstructionStrategy().execute(memory, opCode.parameterModes)
            } catch (e: HaltProgram) {
                return program.toString()
            }
        }
    }

    fun restoreGravityAssistProgram(noun: Int, verb: Int) {
        program.memory.updateInstructionAtAddress(1, noun)
        program.memory.updateInstructionAtAddress(2, verb)
    }

    fun startAirConditionerDiagnosticTest() = program.memory.systemInput(1)

    fun startThermalRadiatorControllerDiagnosticTest() = program.memory.systemInput(5)

    fun getProgramMemory(): Memory = program.memory

    fun getDiagnosticCode(): Int = getProgramMemory().getLastOutputValue()
}


