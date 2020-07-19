package com.aoc.solutions

import com.aoc.input.Day
import com.aoc.input.InputReader
import com.aoc.intcode.circuit.CircuitController
import com.aoc.intcode.circuit.LoopbackAmplificationCircuit
import com.aoc.intcode.circuit.SeriesAmplificationCircuit

fun main() {
    val software = InputReader.read<String>(Day.from(7)).asSingleString()
    val controller = CircuitController(software)
    val maximumThrusterSignal = controller.calculateMaximumThrusterSignal(SeriesAmplificationCircuit())
    println("Part 1 Solution: $maximumThrusterSignal")

    val maximumThrusterSignalWithFeedback = controller.calculateMaximumThrusterSignal(LoopbackAmplificationCircuit())
    println("Part 2 Solution: $maximumThrusterSignalWithFeedback")
}

