package com.aoc.solutions

import com.aoc.input.Day
import com.aoc.input.InputReader
import com.aoc.monitoring.eris.ErisLayoutMonitor
import com.aoc.monitoring.eris.PlanarErisPlanetLayout

fun main() {
    val scanData = InputReader().readInputString(Day.from(24))
    val monitor = ErisLayoutMonitor(PlanarErisPlanetLayout(scanData.values))
    println("Solution Part 1: ${monitor.watchForRepeatingLayout().getBioDiversityRating()}")
}