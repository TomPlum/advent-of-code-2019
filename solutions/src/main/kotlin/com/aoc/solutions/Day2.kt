package com.aoc.solutions

import com.aoc.Day
import com.aoc.input.InputReader
import com.aoc.intcode.computer.IntCodeComputer

fun main() {
    val instructions = InputReader.read<String>(Day(2)).asSingleString()

    partOne(instructions)
    partTwo(instructions)
}

private fun partOne(memoryAddresses: String) {
    val computer = IntCodeComputer(memoryAddresses)
    println("Solution 1: ${computer.restoreGravityAssistProgram(12, 2)} is the value at position 0")
}

private fun partTwo(memoryAddresses: String) {
    val computer = IntCodeComputer(memoryAddresses)

    for (noun in 0L..99L) {
        for (verb in 0L..99L) {
            val value = computer.restoreGravityAssistProgram(noun, verb)
            if (value == 19690720L) {
                println("Solution 2: " + (100 * noun + verb))
            }
            computer.program.memory.reset()
        }
    }
}