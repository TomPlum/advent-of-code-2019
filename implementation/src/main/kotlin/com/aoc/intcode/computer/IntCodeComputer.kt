package com.aoc.intcode.computer

import com.aoc.intcode.computer.exceptions.HaltProgram
import com.aoc.intcode.computer.exceptions.SignalInterrupt

class IntCodeComputer constructor(programString: String) {
    private val program = Program(programString)
    var waiting = true
    var programHalted = false

    fun run() {
        var memory = program.memory

        waiting = false

        while (!waiting) {
            val opCode = OpCode(memory.getCurrentInstruction().toString())
            try {
                memory = opCode.getInstructionStrategy().execute(memory, opCode.parameterModes)
            } catch (e: SignalInterrupt) {
                waiting = true
            } catch (e: HaltProgram) {
                programHalted = true
                break
            }
        }
    }

    fun restoreGravityAssistProgram(noun: Long, verb: Long) {
        program.memory.updateInstructionAtAddress(1, noun)
        program.memory.updateInstructionAtAddress(2, verb)
    }

    fun onNextBoot(mode: BootMode) = program.memory.input.add(mode.systemInputCode)

    fun getProgramMemory(): Memory = program.memory

    fun getDiagnosticCode(): Long = program.memory.output.getLastValue()

    fun getProgramCurrentState(): String = program.toString()

    fun reset() {
        program.memory.reset()
        waiting = true
        programHalted = false
    }

}


