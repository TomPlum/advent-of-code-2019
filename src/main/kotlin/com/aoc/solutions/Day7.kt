package com.aoc.solutions

import com.aoc.input.Day
import com.aoc.input.InputReader
import com.aoc.intcode.amplifier.CircuitController
import com.aoc.intcode.amplifier.LoopbackAmplificationCircuit
import com.aoc.intcode.amplifier.SeriesAmplificationCircuit

fun main() {
    val inputReader = InputReader()
    val software = inputReader.readInputAsSingleString(Day.from(7))
    val controller = CircuitController(software)
    val maximumThrusterSignal = controller.calculateMaximumThrusterSignal(SeriesAmplificationCircuit())
    println("Part 1 Solution: $maximumThrusterSignal")

    val maximumThrusterSignalWithFeedback = controller.calculateMaximumThrusterSignal(LoopbackAmplificationCircuit())
    println("Part 2 Solution: $maximumThrusterSignalWithFeedback")
}

