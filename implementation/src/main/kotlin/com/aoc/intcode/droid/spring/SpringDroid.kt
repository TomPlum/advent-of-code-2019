package com.aoc.intcode.droid.spring

import com.aoc.intcode.computer.IntCodeComputer

class SpringDroid(private val instructions: String) {
    private val computer = IntCodeComputer(instructions)
}