package com.aoc.solutions

import com.aoc.input.Day
import com.aoc.input.InputReader
import com.aoc.maze.donut.planar.PlanarDonutMaze
import com.aoc.maze.donut.recursive.RecursiveDonutMaze

fun main() {
    val input = InputReader.read<String>(Day.from(20)).value
    val maze = PlanarDonutMaze(input)
    println("Part 1 Solution: ${maze.findShortestPath()} steps")

    val recursiveMaze = RecursiveDonutMaze(input)
    println("Part 2 Solution: ${recursiveMaze.findShortestPath()} steps")
}