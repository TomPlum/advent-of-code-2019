package com.aoc.solutions

import com.aoc.Day
import com.aoc.input.InputReader
import com.aoc.intcode.droid.cryo.CommandRuntime
import com.aoc.intcode.droid.cryo.controller.ManualDroidController

fun main() {
   //launchGame()
    val instructions = InputReader.read<String>(Day(25)).asSingleString()
    val password = ManualDroidController(instructions).findPassword()
    println("Solution Part 1: $password")
}

private fun launchGame() {
    val input = InputReader.read<String>(Day(25)).asSingleString()
    val runtime = CommandRuntime(input)
    runtime.start()
}