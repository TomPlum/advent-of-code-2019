package com.aoc.solutions

import com.aoc.input.Day
import com.aoc.input.InputReader
import com.aoc.intcode.IntCodeComputer

fun main() {
    val input = InputReader().readInputAsSingleString(Day.from(9))
    val computer = IntCodeComputer(input)
    computer.startBoostTest()
    computer.compute()
    print("Part 1 Solution: ${computer.getDiagnosticCode()}")
}