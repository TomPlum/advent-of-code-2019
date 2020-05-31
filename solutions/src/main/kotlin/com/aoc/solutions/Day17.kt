package com.aoc.solutions

import com.aoc.intcode.vacuum.VacuumRobot
import com.aoc.input.Day
import com.aoc.input.InputReader

fun main() {
    val instruction = InputReader().readInputAsSingleString(Day.from(17))
    val scaffoldMap = VacuumRobot(instruction).scanShipsExteriorScaffolding()
    println("Solution Part 1: ${scaffoldMap.calculateAlignmentParameterSum()}")
    println(scaffoldMap)
    println("Solution Part 2: ${VacuumRobot(instruction).notifyRobotsAboutSolarFlare()}")
}