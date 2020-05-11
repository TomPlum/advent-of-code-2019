package com.aoc.intcode.computer

import com.aoc.intcode.computer.instructions.strategies.Input
import com.aoc.intcode.computer.instructions.strategies.Halt
import com.aoc.intcode.computer.instructions.InstructionStrategy
import com.aoc.intcode.computer.exceptions.HaltProgram
import com.aoc.intcode.computer.exceptions.SignalInterrupt

class IntCodeComputer constructor(programString: String) {
    private val program = Program(programString)
    var waiting = true
    var halted = false

    /**
     * Runs the [program] in it's current state until the [IntCodeComputer] is either [waiting], or [halted].
     *
     * The [IntCodeComputer] will only enter [waiting] state when an [Input] [OpCode] is executed
     * and a [SignalInterrupt] is thrown. The [SystemInput] must be provided a value for the [Program] to proceed.
     *
     * Furthermore, it will only become [halted] when a [Halt] [OpCode] is executed. The only way to recover from
     * this scenario is to create a new instance of the [IntCodeComputer] or [reset].
     *
     * The main design pattern in this implementation is the 'Strategy' Pattern, abstracted by [InstructionStrategy]
     */
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
                halted = true
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
        halted = false
    }

}


