package com.aoc.solutions

import com.aoc.Day
import com.aoc.input.InputReader
import com.aoc.intcode.droid.cryo.CommandRuntime
import com.aoc.intcode.droid.cryo.CryostasisDroid
import com.aoc.intcode.droid.cryo.Item
import com.aoc.intcode.droid.cryo.command.DropCommand
import com.aoc.intcode.droid.cryo.command.MovementCommand
import com.aoc.intcode.droid.cryo.command.TakeCommand
import com.aoc.intcode.droid.cryo.controller.ManualDroidController
import com.aoc.log.AdventLogger

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