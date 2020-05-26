package com.aoc.maze.donut

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.input.InputReader
import org.junit.jupiter.api.Test

class RecursiveDonutMazeTest {
    @Test
    fun example() {
        val input = InputReader().readInputAsString("/maze/recursive/example.txt")
        val stepsTaken = RecursiveDonutMaze(input.values).findShortestPath()
        assertThat(stepsTaken).isEqualTo(396)
    }
}