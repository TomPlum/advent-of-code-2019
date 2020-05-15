package com.aoc.intcode.computer.instructions.strategies

import com.aoc.intcode.computer.exceptions.HaltProgram
import com.aoc.intcode.computer.Memory
import com.aoc.intcode.computer.ParameterMode
import com.aoc.intcode.computer.instructions.InstructionStrategy
import log.AdventLogger
import java.util.*

class Halt: InstructionStrategy {
    override fun execute(memorySnapshot: Memory, modes: Stack<ParameterMode>): Memory {
        AdventLogger.trace("Program Halted!")
        AdventLogger.trace("System Input: ${memorySnapshot.input}")
        AdventLogger.trace("System Output: ${memorySnapshot.output}")
        throw HaltProgram()
    }
}