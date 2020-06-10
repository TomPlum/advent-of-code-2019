package com.aoc.solutions

import com.aoc.maze.donut.DonutMaze2D
import com.aoc.input.Day
import com.aoc.input.InputReader
import com.aoc.maze.donut.DonutMaze3D

fun main() {
    val input = InputReader().readInputString(Day.from(20))
    val maze = DonutMaze2D(input.values)
    println("Part 1 Solution: ${maze.findShortestPath()} steps")

    val recursiveMaze = DonutMaze3D(input.values)
    println("Part 2 Solution: ${recursiveMaze.findShortestPath()} steps")
}