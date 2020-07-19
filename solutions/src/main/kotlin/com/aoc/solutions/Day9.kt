package com.aoc.solutions

import com.aoc.input.Day
import com.aoc.input.InputReader
import com.aoc.intcode.computer.IntCodeComputer
import com.aoc.intcode.computer.boot.TestBootMode

fun main() {
    val input = InputReader.read<String>(Day(9)).asSingleString()
    partOne(input)
    partTwo(input)
}

private fun partOne(input: String) {
    val computer = IntCodeComputer(input)
    computer.onNextBoot(TestBootMode.BOOST_TEST)
    computer.run()
    print("Part 1 Solution: ${computer.getDiagnosticCode()}")
}

private fun partTwo(input: String) {
    val computer = IntCodeComputer(input)
    computer.onNextBoot(TestBootMode.SENSOR_BOOST)
    computer.run()
    print("Part 2 Solution: ${computer.getDiagnosticCode()}")
}