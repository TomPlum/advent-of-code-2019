package com.aoc.common

import com.aoc.value.Day
import java.io.File

class InputReader {
    fun readInputString(day: Day): Input<String> = Input(readLines(day))
    fun readInputInteger(day: Day): Input<Int> = Input(readLines(day).map { it.toInt() })
    fun readInputDouble(day: Day): Input<Double> = Input(readLines(day).map { it.toDouble() })
    fun readInputAsSingleString(day: Day): String = readLines(day)[0]

    private fun readLines(day: Day) = readFile(day).readLines()
    private fun readFile(day: Day) = File(javaClass.getResource("/day${day.value}/input.txt").path)
}