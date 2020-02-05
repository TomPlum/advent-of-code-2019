package com.aoc.intcode.instructions.strategies

import com.aoc.intcode.exceptions.HaltProgram
import com.aoc.intcode.Memory
import com.aoc.intcode.ParameterMode
import com.aoc.intcode.instructions.InstructionStrategy
import java.util.*

class Halt: InstructionStrategy {
    override fun execute(memorySnapshot: Memory, modes: Stack<ParameterMode>): Memory {
        println("System Input: ${memorySnapshot.input}")
        println("System Output: ${memorySnapshot.output}")
        throw HaltProgram()
    }
}