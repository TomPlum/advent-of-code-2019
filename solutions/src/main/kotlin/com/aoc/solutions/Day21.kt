package com.aoc.solutions

import com.aoc.input.Day
import com.aoc.input.InputReader
import com.aoc.intcode.droid.spring.SpringDroid
import com.aoc.intcode.droid.spring.survey.RunningStrategy
import com.aoc.intcode.droid.spring.survey.WalkingStrategy

fun main() {
    val input = InputReader().readInputAsSingleString(Day.from(21))

    val walkingDroid = SpringDroid(input)
    println("Part 1 Solution: ${walkingDroid.surveyHull(WalkingStrategy())} Damage")

    val runningDroid = SpringDroid(input)
    println("Part 2 Solution ${runningDroid.surveyHull(RunningStrategy())} Damage")
}