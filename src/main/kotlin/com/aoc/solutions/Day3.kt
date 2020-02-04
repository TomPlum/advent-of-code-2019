package com.aoc.solutions

import com.aoc.input.Day
import com.aoc.panel.Panel
import com.aoc.panel.Wire

fun main() {
    val inputReader = SolutionUtility().inputReader
    val wires = inputReader.readInputString(Day.from(3)).values
    val panel = Panel(Wire(wires[0]), Wire(wires[1]))

    println("Solution 1: " + panel.findIntersectionPointClosestToCentralPort())
    println("Solution 2: " + panel.findShortestCombinedIntersectionPaths())
}