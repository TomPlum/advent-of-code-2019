package com.aoc.solutions

import com.aoc.input.Day
import com.aoc.input.InputReader
import com.aoc.intcode.computer.IntCodeComputer

fun main() {
    val instructions = InputReader.read<String>(Day.from(2)).asSingleString()

    partOne(instructions)
    partTwo(instructions)
}

private fun partOne(memoryAddresses: String) {
    val computer = IntCodeComputer(memoryAddresses)
    computer.restoreGravityAssistProgram(12, 2)
    computer.run()
    val firstValue = computer.program.memory.getInstructionAtAddress(0)
    println("Solution 1: ${firstValue}is the value at position 0")
}

private fun partTwo(memoryAddresses: String) {
    val computer = IntCodeComputer(memoryAddresses)

    for (noun in 0L..99L) {
        for (verb in 0L..99L) {
            computer.restoreGravityAssistProgram(noun, verb)
            computer.run()
            if (computer.program.memory.getInstructionAtAddress(0) == 19690720L) println("Solution 2: " + (100 * noun + verb))
            computer.program.memory.reset()
        }
    }
}