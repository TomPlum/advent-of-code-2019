package com.aoc.common

import com.aoc.value.Day
import java.io.File

class InputReader {
    fun readInputString(day: Day): Input<String> = Input(read(day))
    fun readInputInteger(day: Day): Input<Int> = Input(read(day).map { it.toInt() })
    fun readInputDouble(day: Day): Input<Double> = Input(read(day).map { it.toDouble() })

    private fun read(day: Day) = javaClass.getResource("/day${day.value}/input.txt").path.lines()
}