package com.aoc.intcode.computer.instructions.strategies

import com.aoc.intcode.computer.exceptions.HaltProgram
import com.aoc.intcode.computer.Memory
import com.aoc.intcode.computer.ParameterMode
import com.aoc.intcode.computer.instructions.InstructionStrategy
import java.util.*

class Halt: InstructionStrategy {
    override fun execute(memorySnapshot: Memory, modes: Stack<ParameterMode>): Memory {
        println("System Input: ${memorySnapshot.input}")
        println("System Output: ${memorySnapshot.output}")
        throw HaltProgram()
    }
}