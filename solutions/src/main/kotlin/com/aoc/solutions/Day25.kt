package com.aoc.solutions

import com.aoc.Day
import com.aoc.input.InputReader
import com.aoc.intcode.droid.cryo.CommandRuntime

fun main() {
    val input = InputReader.read<String>(Day(25)).asSingleString()
    val runtime = CommandRuntime(input)
    runtime.start()
}