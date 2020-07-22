package com.aoc.solutions

import com.aoc.Day
import com.aoc.input.InputReader
import com.aoc.orbit.OrbitalMap

fun main() {
    val bodies = InputReader.read<String>(Day(6)).value

    val orbitalMap = OrbitalMap(bodies)
    println("Solution - Part 1: ${orbitalMap.readOrbits()}")
    println("Solution - Part 2: ${orbitalMap.orbitalTransfersRequiredToReachSanta()}")
}