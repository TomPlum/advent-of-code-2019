package com.aoc.maze.donut

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.TestInputReader
import com.aoc.input.Day
import com.aoc.input.InputReader
import com.aoc.maze.donut.recursive.RecursiveDonutMaze
import org.junit.jupiter.api.Test

class RecursiveDonutMazeTest {
    @Test
    fun example() {
        val input = TestInputReader().readInputAsString("/maze/recursive/example.txt")
        val stepsTaken = RecursiveDonutMaze(input.value).findShortestPath()
        assertThat(stepsTaken).isEqualTo(396)
    }

    @Test
    fun solutionPart2() {
        val input = InputReader.read<String>(Day.from(20)).value
        val stepsTaken = RecursiveDonutMaze(input).findShortestPath()
        assertThat(stepsTaken).isEqualTo(6292)
    }
}