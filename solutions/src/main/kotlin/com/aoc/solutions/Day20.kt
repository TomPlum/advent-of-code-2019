package com.aoc.solutions

import com.aoc.maze.donut.DonutMaze
import com.aoc.input.Day
import com.aoc.input.InputReader

fun main() {
    val input = InputReader().readInputString(Day.from(20))
    val maze = DonutMaze(input.values)
    println("Part 1 Solution: ${maze.findTheShortestPath()} steps")
}