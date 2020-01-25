package com.aoc.solutions

import com.aoc.input.Day
import com.aoc.input.InputReader
import com.aoc.intcode.IntCodeComputer

fun main() {
    val inputReader = InputReader()
    val memoryAddresses = inputReader.readInputAsSingleString(Day.from(5))
    val computer = IntCodeComputer(memoryAddresses)
    computer.startAirConditionerDiagnosticTest()
    val finalProgramState = computer.compute()
    println("Final Program State $finalProgramState")
}