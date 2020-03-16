package com.aoc.solutions

import com.aoc.input.Day
import com.aoc.input.InputReader
import com.aoc.intcode.computer.BootMode
import com.aoc.intcode.computer.IntCodeComputer

fun main() {
    val inputReader = InputReader()
    val memoryAddresses = inputReader.readInputAsSingleString(Day.from(5))
    partOne(memoryAddresses)

    partTwo(memoryAddresses)
}

private fun partTwo(memoryAddresses: String) {
    val computer = IntCodeComputer(memoryAddresses)
    computer.onNextBoot(BootMode.THERMAL_RADIATOR_CONTROLLER_DIAGNOSTIC_TEST)
    val finalProgramState = computer.compute()
    println("Final Program State $finalProgramState")
}

private fun partOne(memoryAddresses: String) {
    val computer = IntCodeComputer(memoryAddresses)
    computer.onNextBoot(BootMode.AIR_CONDITIONER_DIAGNOSTIC_TEST)
    val finalProgramState = computer.compute()
    println("Final Program State $finalProgramState")
}