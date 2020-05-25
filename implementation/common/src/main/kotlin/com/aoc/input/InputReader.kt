package com.aoc.input

import java.io.File

class InputReader {
    fun readInputAsString(filePath: String): Input<String> = Input(readFile(filePath).readLines())
    fun readInputAsSingleString(filePath: String): String = readInputAsString(filePath).values[0]

    fun readInputString(day: Day): Input<String> = Input(readLines(day))
    fun readInputInteger(day: Day): Input<Int> = Input(readLines(day).map { it.toInt() })
    fun readInputDouble(day: Day): Input<Double> = Input(readLines(day).map { it.toDouble() })
    fun readInputAsSingleString(day: Day): String = readLines(day)[0]

    private fun readLines(day: Day) = readPuzzleInputFile(day).readLines()
    private fun readPuzzleInputFile(day: Day) = File(javaClass.getResource("/day${day.value}/input.txt").path)
    private fun readFile(filePath: String) = File(javaClass.getResource(filePath).path)
}