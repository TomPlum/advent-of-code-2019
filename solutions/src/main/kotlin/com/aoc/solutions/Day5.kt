package com.aoc.solutions

import com.aoc.input.Day
import com.aoc.input.InputReader
import com.aoc.intcode.computer.boot.TestBootMode
import com.aoc.intcode.computer.IntCodeComputer

fun main() {
    val memoryAddresses = InputReader.read<String>(Day(5)).asSingleString()
    partOne(memoryAddresses)
    partTwo(memoryAddresses)
}

private fun partTwo(memoryAddresses: String) {
    val computer = IntCodeComputer(memoryAddresses)
    computer.onNextBoot(TestBootMode.THERMAL_RADIATOR_CONTROLLER_DIAGNOSTIC_TEST)
    val finalProgramState = computer.run()
    println("Final Program State $finalProgramState")
}

private fun partOne(memoryAddresses: String) {
    val computer = IntCodeComputer(memoryAddresses)
    computer.onNextBoot(TestBootMode.AIR_CONDITIONER_DIAGNOSTIC_TEST)
    val finalProgramState = computer.run()
    println("Final Program State $finalProgramState")
}