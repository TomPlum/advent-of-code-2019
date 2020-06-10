package com.aoc.solutions

import com.aoc.maze.donut.DonutMaze
import com.aoc.input.Day
import com.aoc.input.InputReader
import com.aoc.maze.donut.RecursiveDonutMaze

fun main() {
    val input = InputReader().readInputString(Day.from(20))
    val maze = DonutMaze(input.values)
    println("Part 1 Solution: ${maze.findShortestPath()} steps")

    val recursiveMaze = RecursiveDonutMaze(input.values)
    println("Part 2 Solution: ${recursiveMaze.findShortestPath()} steps")
}