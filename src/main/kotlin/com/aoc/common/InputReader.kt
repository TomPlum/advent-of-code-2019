package com.aoc.common

import com.aoc.value.Day
import java.io.File

class InputReader {
    fun readInputString(day: Day): Input<String> = Input(File(getPath(day)).readLines())
    fun readInputInteger(day: Day): Input<Int> = Input(File(getPath(day)).readLines().map { it.toInt() })
    fun readInputDouble(day: Day): Input<Double> = Input(File(getPath(day)).readLines().map { it.toDouble() })

    private fun getPath(day: Day) = "/day${day.value}/input.txt"
}