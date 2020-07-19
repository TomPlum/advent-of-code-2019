package com.aoc.maze.donut.planar

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.TestInputReader
import com.aoc.input.Day
import com.aoc.input.InputReader
import com.aoc.math.Point2D
import org.junit.jupiter.api.Test

class PlanarDonutMazeTest {
    @Test
    fun parsePuzzleInputData() {
        val input = InputReader.read<String>(Day(20))
        PlanarDonutMaze(input.value)
    }

    @Test
    fun exampleMazeShouldHaveThreePortalPairs() {
        val input = TestInputReader().readInputAsString("/maze/donut/example-maze.txt")
        val maze = PlanarDonutMaze(input.value)
        assertThat(maze.portals.size).isEqualTo(3)
    }

    @Test
    fun exampleMazeShouldParseEntranceCorrectly() {
        val input = TestInputReader().readInputAsString("/maze/donut/example-maze.txt")
        val maze = PlanarDonutMaze(input.value)
        assertThat(maze.entrance).isEqualTo(Point2D(9,2))
    }

    @Test
    fun exampleMazeShouldParseExitCorrectly() {
        val input = TestInputReader().readInputAsString("/maze/donut/example-maze.txt")
        val maze = PlanarDonutMaze(input.value)
        assertThat(maze.exit).isEqualTo(Point2D(13,16))
    }

    @Test
    fun findShortestPathExampleMaze() {
        val input = TestInputReader().readInputAsString("/maze/donut/example-maze.txt")
        val maze = PlanarDonutMaze(input.value)
        assertThat(maze.findShortestPath()).isEqualTo(23)
    }

    @Test
    fun findShortestPathLargeExampleMaze() {
        val input = TestInputReader().readInputAsString("/maze/donut/large-example-maze.txt")
        val maze = PlanarDonutMaze(input.value)
        assertThat(maze.findShortestPath()).isEqualTo(58)
    }

    @Test
    fun solutionPartOne() {
        val input = InputReader.read<String>(Day(20))
        val maze = PlanarDonutMaze(input.value)
        assertThat(maze.findShortestPath()).isEqualTo(526)
    }
}