package com.aoc.solutions

import com.aoc.input.Day
import com.aoc.input.InputReader
import com.aoc.monitoring.moon.MotionSimulator
import com.aoc.monitoring.moon.ScanningModule

fun main() {
    val input = InputReader.read<String>(Day.from(12)).value

    partOne(input)
    partTwo(input)
}

private fun partTwo(input: List<String>) {
    val motionSimulator = MotionSimulator(ScanningModule().scanLocalSectorForMoons(input))
    println("Part 2 Solution: ${motionSimulator.determineTimeStepsUntilHistoryRepeats()}")
}

private fun partOne(input: List<String>) {
    val motionSimulator = MotionSimulator(ScanningModule().scanLocalSectorForMoons(input))
    motionSimulator.simulate(1000)
    println("Part 1 Solution: ${motionSimulator.calculateTotalSystemEnergy()}\n")
}