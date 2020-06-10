package com.aoc.maze.donut

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import com.aoc.input.Day
import com.aoc.input.InputReader
import com.aoc.math.Point2D
import org.junit.jupiter.api.Test

//TODO: Broken by Part 2, fix (Maybe restore old portals?)
class DonutMazeTest {
    @Test
    fun parsePuzzleInputData() {
        val input = InputReader().readInputString(Day.from(20))
        DonutMaze(input.values)
    }

    @Test
    fun isOutsideWithOuterPortal() {
        val input = InputReader().readInputAsString("/maze/donut/example-maze.txt")
        val maze = DonutMaze(input.values)
        assertThat(maze.portals.find { it.entrances.second.warpCode.charOne == 'B' }!!.entrances.second.isOuter()).isTrue()
    }

    @Test
    fun isOutsideWithInnerPortal() {
        val input = InputReader().readInputAsString("/maze/donut/example-maze.txt")
        val maze = DonutMaze(input.values)
        assertThat(maze.portals.find { it.entrances.first.warpCode.charOne == 'B' }!!.entrances.first.isOuter()).isFalse()
    }

    @Test
    fun exampleMazeShouldHaveThreePortalPairs() {
        val input = InputReader().readInputAsString("/maze/donut/example-maze.txt")
        val maze = DonutMaze(input.values)
        assertThat(maze.portals.size).isEqualTo(3)
    }

    @Test
    fun exampleMazeShouldParseEntranceCorrectly() {
        val input = InputReader().readInputAsString("/maze/donut/example-maze.txt")
        val maze = DonutMaze(input.values)
        assertThat(maze.entrance).isEqualTo(Point2D(9,2))
    }

    @Test
    fun exampleMazeShouldParseExitCorrectly() {
        val input = InputReader().readInputAsString("/maze/donut/example-maze.txt")
        val maze = DonutMaze(input.values)
        assertThat(maze.exit).isEqualTo(Point2D(13,16))
    }

    @Test
    fun findShortestPathExampleMaze() {
        val input = InputReader().readInputAsString("/maze/donut/example-maze.txt")
        val maze = DonutMaze(input.values)
        assertThat(maze.findShortestPath()).isEqualTo(23)
    }

    @Test
    fun findShortestPathLargeExampleMaze() {
        val input = InputReader().readInputAsString("/maze/donut/large-example-maze.txt")
        val maze = DonutMaze(input.values)
        assertThat(maze.findShortestPath()).isEqualTo(58)
    }

    @Test
    fun solutionPartOne() {
        val input = InputReader().readInputString(Day.from(20))
        val maze = DonutMaze(input.values)
        assertThat(maze.findShortestPath()).isEqualTo(526)
    }
}