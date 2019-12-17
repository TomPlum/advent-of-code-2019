package com.aoc.solutions

import com.aoc.common.InputReader
import com.aoc.intcode.IntCodeComputer
import com.aoc.value.Day

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
    println("Solution 1: " + computer.getProgramMemory().getAddressValue(0))
}

private fun partTwo(memoryAddresses: String) {
    val computer = IntCodeComputer(memoryAddresses)

    for (noun in 0..99) {
        for (verb in 0..99) {
            computer.restoreGravityAssistProgram(noun, verb)
            computer.compute()
            if (computer.getProgramMemory().getAddressValue(0) == 19690720) println("Solution 2: " + (100 * noun + verb))
            computer.getProgramMemory().reset()
        }
    }
}