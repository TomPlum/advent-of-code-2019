package com.aoc.solutions

import com.aoc.input.Day
import com.aoc.input.InputReader
import com.aoc.monitoring.moon.MotionSimulator
import com.aoc.monitoring.moon.ScanningModule

fun main() {
    val input = InputReader().readInputString(Day.from(12)).values
    val motionSimulator = MotionSimulator(ScanningModule().scanLocalSectorForMoons(input))
    motionSimulator.simulate(1000)
    println("Part 1 Solution: ${motionSimulator.calculateTotalSystemEnergy()}\n")
    println("Part 2 Solution: ${motionSimulator.determineTimeStepsUntilHistoryRepeats()}")
}