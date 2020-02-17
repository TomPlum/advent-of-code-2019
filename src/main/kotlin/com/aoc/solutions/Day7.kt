package com.aoc.solutions

import com.aoc.input.Day
import com.aoc.input.InputReader
import com.aoc.intcode.amplifier.CircuitController

fun main() {
    val inputReader = InputReader()
    val software = inputReader.readInputAsSingleString(Day.from(7))
    val controller = CircuitController(software)
    println("Part 1 Solution: ${controller.calculateMaximumThrusterSignal()}")
}

