package com.aoc.solutions

import com.aoc.panel.Panel
import com.aoc.panel.Wire
import com.aoc.input.Day

fun main() {
    val inputReader = SolutionUtility().inputReader
    val wires = inputReader.readInputString(Day.from(3)).values
    val panel = Panel(Wire(wires[0]), Wire(wires[1]))

    println("Solution 1: " + panel.findIntersectionPointClosestToCentralPort())
}