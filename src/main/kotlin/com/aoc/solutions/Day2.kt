package com.aoc.solutions

import com.aoc.common.InputReader
import com.aoc.intcode.IntCodeComputer
import com.aoc.value.Day

fun main() {
    val inputReader = InputReader()
    val computer = IntCodeComputer()
    var input = inputReader.readInputAsSingleString(Day.from(2))
    input = restoreGravityAssistProgram(input)
    val output = computer.compute(input)
    println("Solution 1: " + output.split(",").toList()[0])
}

fun restoreGravityAssistProgram(input: String): String {
    val programCode = input.split(",").toMutableList()
    programCode[1] = "12"
    programCode[2] = "2"
    return programCode.joinToString(",", postfix = "") { it }
}