package com.aoc.solutions

import com.aoc.hull.HullPaint
import com.aoc.hull.SpaceshipController
import input.Day
import input.InputReader

fun main() {
    val input = InputReader().readInputAsSingleString(Day.from(11))
    val controller = SpaceshipController()
    val paintedHullStartingBlack = controller.deployEmergencyHullPaintingRobot(input, HullPaint.BLACK)
    println("Solution Part 1: ${paintedHullStartingBlack.getTimesPanelsPainted()}")

    val paintedHullStartingWhite = controller.deployEmergencyHullPaintingRobot(input, HullPaint.WHITE)
    println("Solution Part 2:\n${paintedHullStartingWhite.getRegistrationIdentifier()}")
}