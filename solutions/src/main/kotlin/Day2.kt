package com.aoc.solutions

import input.Day
import input.InputReader
import com.aoc.intcode.computer.IntCodeComputer

fun main() {
    val inputReader = InputReader()
    val memoryAddresses = inputReader.readInputAsSingleString(Day.from(2))

    partOne(memoryAddresses)
    partTwo(memoryAddresses)
}

private fun partOne(memoryAddresses: String) {
    val computer = IntCodeComputer(memoryAddresses)
    computer.restoreGravityAssistProgram(12, 2)
    computer.compute()
    println("Solution 1: " + computer.getProgramMemory().getInstructionAtAddress(0))
}

private fun partTwo(memoryAddresses: String) {
    val computer = IntCodeComputer(memoryAddresses)

    for (noun in 0L..99L) {
        for (verb in 0L..99L) {
            computer.restoreGravityAssistProgram(noun, verb)
            computer.compute()
            if (computer.getProgramMemory().getInstructionAtAddress(0) == 19690720L) println("Solution 2: " + (100 * noun + verb))
            computer.getProgramMemory().reset()
        }
    }
}