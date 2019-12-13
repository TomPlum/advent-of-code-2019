package com.aoc.common

import com.aoc.value.Day
import java.io.File

class InputReader {
    fun readInput(day: Day): List<Any> = File("day${day.getValue()}").readLines()
}