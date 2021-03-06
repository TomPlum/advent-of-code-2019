package com.aoc.intcode.computer

import com.aoc.intcode.computer.State.*
import com.aoc.intcode.computer.boot.BootMode
import com.aoc.intcode.computer.exceptions.SignalTerminate
import com.aoc.intcode.computer.exceptions.SignalInterrupt
import com.aoc.intcode.computer.instructions.InstructionStrategy
import com.aoc.intcode.computer.instructions.strategies.Halt
import com.aoc.intcode.computer.instructions.strategies.Input
import com.aoc.intcode.computer.boot.TestBootMode

/**
 * This class is the heart of Advent of Code 2019.
 * Every other day utilises the [IntCodeComputer]. It is completed by Day 9.
 *
 * The computer can be started with a [TestBootMode] which will start the next boot with a value
 * in the [Memory] [SystemInput] and will modify the [Program] behaviour.
 */
class IntCodeComputer constructor(instructions: String) {
    val program = Program(instructions)
    var state = WAITING

    /**
     * Runs the [program] in it's current state until the [IntCodeComputer] is either [WAITING], or [TERMINATED].
     *
     * The [IntCodeComputer] will only enter [WAITING] state when an [Input] [OpCode] is executed
     * and a [SignalInterrupt] is thrown. The [SystemInput] must be provided a value for the [Program] to proceed.
     *
     * Furthermore, it will only become [TERMINATED] when a [Halt] [OpCode] is executed. The only way to recover from
     * this scenario is to create a new instance of the [IntCodeComputer] or [reset].
     *
     * The main design pattern in this implementation is the 'Strategy' Pattern, abstracted by [InstructionStrategy].
     */
    fun run() {
        state = RUNNING

        while (state == RUNNING) {
            val operation = OpCode(program.memory.getCurrentInstruction().toString())
            try {
                program.memory = operation.getInstructionStrategy().execute(program.memory, operation.parameterModes)
            } catch (e: SignalInterrupt) {
                state = WAITING
            } catch (e: SignalTerminate) {
                state = TERMINATED
                break
            }
        }
    }

    /**
     * Restores the [Program] to the "1202 Program Alarm" state.
     * @return The instruction value at the first address after restoring the gravity assist program.
     */
    fun restoreGravityAssistProgram(noun: Long, verb: Long): Long {
        program.memory.updateInstructionAtAddress(1, noun)
        program.memory.updateInstructionAtAddress(2, verb)
        run()
        return program.memory.getInstructionAtAddress(0)
    }

    /**
     * Starts the computer in an alternate mode so the Thermal Environment Supervision Terminal (TEST)
     * can run a diagnostic program.
     */
    fun onNextBoot(mode: BootMode) = program.memory.input.add(mode.getCode())

    /**
     * When running TEST programs, the [IntCodeComputer] will often output a diagnostic code
     * if the [Program] malfunctions at runtime.
     * @return The last value from the [Memory] [SystemOutput]
     */
    fun getDiagnosticCode(): Long = program.memory.output.getLastValue()

    /**
     * Resets the [IntCodeComputer] back to it's original state immediately after instantiation.
     * @see Memory.reset
     */
    fun reset() {
        program.memory.reset()
        state = WAITING
    }

}


