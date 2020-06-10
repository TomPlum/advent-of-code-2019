package com.aoc.maze.donut

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.input.Day
import com.aoc.input.InputReader
import com.aoc.maze.donut.recursive.RecursiveDonutMaze
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class RecursiveDonutMazeTest {
    @Test
    fun example() {
        val input = InputReader().readInputAsString("/maze/recursive/example.txt")
        val stepsTaken = RecursiveDonutMaze(input.values).findShortestPath()
        assertThat(stepsTaken).isEqualTo(396)
    }

    @Test
    @Disabled
    fun solutionPart2() {
        val input = InputReader().readInputString(Day.from(20))
        val stepsTaken = RecursiveDonutMaze(input.values).findShortestPath()
        assertThat(stepsTaken).isEqualTo(6292)
    }
}