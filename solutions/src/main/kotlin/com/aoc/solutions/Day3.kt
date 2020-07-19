package com.aoc.solutions

import com.aoc.input.Day
import com.aoc.input.InputReader
import com.aoc.panel.Panel
import com.aoc.panel.Wire

fun main() {
    val wires = InputReader.read<String>(Day(3)).value
    val panel = Panel(Wire(wires[0]), Wire(wires[1]))

    val p1 = panel.findIntersectionPointClosestToCentralPort()
    println("Solution 1: $p1 distance between central port and closest intersection")


    val p2 = panel.findShortestCombinedIntersectionPaths()
    println("Solution 2: $p2 is the fewest combined steps to reach an intersection")
}