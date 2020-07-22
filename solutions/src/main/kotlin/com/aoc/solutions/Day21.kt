package com.aoc.solutions

import com.aoc.Day
import com.aoc.input.InputReader
import com.aoc.intcode.droid.spring.SpringDroid
import com.aoc.intcode.droid.spring.survey.RunningStrategy
import com.aoc.intcode.droid.spring.survey.WalkingStrategy

fun main() {
    val input = InputReader.read<String>(Day(21)).asSingleString()

    val walkingDroid = SpringDroid(input)
    println("Part 1 Solution: ${walkingDroid.surveyHull(WalkingStrategy())} Damage")

    val runningDroid = SpringDroid(input)
    println("Part 2 Solution ${runningDroid.surveyHull(RunningStrategy())} Damage")
}