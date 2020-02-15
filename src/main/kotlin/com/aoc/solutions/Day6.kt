package com.aoc.solutions

import com.aoc.input.Day
import com.aoc.input.InputReader
import com.aoc.orbit.OrbitalMap

fun main() {
    val inputReader = InputReader()
    val bodies = inputReader.readInputString(Day.from(6))

    val orbitalMap = OrbitalMap(bodies.values)
    println("Solution - Part 1: ${orbitalMap.readOrbits()}")
    println("Solution - Part 2: ${orbitalMap.orbitalTransfersRequiredToReachSanta()}")
}