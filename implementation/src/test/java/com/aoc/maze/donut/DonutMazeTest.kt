package com.aoc.maze.donut

import assertk.assertThat
import assertk.assertions.isEqualTo
import input.Day
import input.InputReader
import org.junit.jupiter.api.Test

class DonutMazeTest {
    @Test
    fun parsePuzzleInputData() {
        val input = InputReader().readInputString(Day.from(20))
        DonutMaze(input.values)
    }

    @Test
    fun exampleMazeShouldHaveThreePortalPairs() {
        val input = InputReader().readInputAsString("maze/donut/example-maze.txt")
        val maze = DonutMaze(input.values)
        assertThat(maze.getPortals().size).isEqualTo(3)
    }
}