package com.aoc.solutions

import com.aoc.intcode.vacuum.VacuumRobot
import input.Day
import input.InputReader

fun main() {
    val instruction = InputReader().readInputAsSingleString(Day.from(17))
    val scaffoldMap = VacuumRobot(instruction).exploreShipsExteriorScaffolding()
    println("Solution Part 1: ${scaffoldMap.calculateAlignmentParameterSum()}")
    println(scaffoldMap)
}