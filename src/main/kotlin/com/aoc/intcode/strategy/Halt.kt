package com.aoc.intcode.strategy

import com.aoc.intcode.HaltProgram
import com.aoc.intcode.Memory
import com.aoc.intcode.ParameterMode
import java.util.*

class Halt: InstructionStrategy {
    override fun execute(memorySnapshot: Memory, modes: Stack<ParameterMode>): Memory {
        println("System Input: ${memorySnapshot.input}")
        println("System Output: ${memorySnapshot.output}")
        throw HaltProgram()
    }
}