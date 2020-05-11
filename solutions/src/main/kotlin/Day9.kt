package com.aoc.solutions

import input.Day
import input.InputReader
import com.aoc.intcode.computer.BootMode
import com.aoc.intcode.computer.IntCodeComputer

fun main() {
    val input = InputReader().readInputAsSingleString(Day.from(9))
    partOne(input)
    partTwo(input)
}

private fun partOne(input: String) {
    val computer = IntCodeComputer(input)
    computer.onNextBoot(BootMode.BOOST_TEST)
    computer.run()
    print("Part 1 Solution: ${computer.getDiagnosticCode()}")
}

private fun partTwo(input: String) {
    val computer = IntCodeComputer(input)
    computer.onNextBoot(BootMode.SENSOR_BOOST)
    computer.run()
    print("Part 2 Solution: ${computer.getDiagnosticCode()}")
}