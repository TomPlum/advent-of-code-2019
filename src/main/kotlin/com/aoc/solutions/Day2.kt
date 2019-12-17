package com.aoc.solutions

import com.aoc.common.InputReader
import com.aoc.intcode.IntCodeComputer
import com.aoc.value.Day

fun main() {
    val inputReader = InputReader()

    partOne(inputReader)

    val computer = IntCodeComputer(inputReader.readInputAsSingleString(Day.from(2)))

    for (noun in 0..99) {
        for (verb in 0..99) {

            //Pass in noun and verb for [1] and [2] until answer = 19690720 then return noun and verb and do (100 * noun + verb)
        }
    }
}

private fun partOne(inputReader: InputReader) {
    val memoryAddresses = inputReader.readInputAsSingleString(Day.from(2))
    val computer = IntCodeComputer(memoryAddresses)
    computer.restoreGravityAssistProgram(12, 2)
    computer.compute()
    println("Solution 1: " + computer.getProgramMemory().getAddressValue(0))
}