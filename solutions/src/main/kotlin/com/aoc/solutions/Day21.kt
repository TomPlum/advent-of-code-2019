package com.aoc.solutions

import com.aoc.input.Day
import com.aoc.input.InputReader
import com.aoc.intcode.droid.spring.SpringDroid

fun main() {
    val input = InputReader().readInputAsSingleString(Day.from(21))
    val droid = SpringDroid(input)
    println("Part 1 Solution: ${droid.surveyHull()} Damage")
}