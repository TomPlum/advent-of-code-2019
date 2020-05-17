package com.aoc.intcode.arcade

import assertk.assertThat
import assertk.assertions.isEqualTo
import input.Day
import input.InputReader
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class ArcadeCabinetTest {
    @Test
    @DisplayName("Given Day 13 Puzzle Input, when getting the quantity of BLOCK tiles, it should return 247")
    fun solutionPartOne() {
        val input = InputReader().readInputAsSingleString(Day.from(13))
        val blockQuantity = ArcadeCabinet(input).getTileQuantity(TileID.BLOCK)
        assertThat(blockQuantity).isEqualTo(247)
    }

    @Test
    @DisplayName("Given Day 13 Puzzle Input, when beating the game, then it should return a final score of 12954")
    fun solutionPartTwo() {
        val input = InputReader().readInputAsSingleString(Day.from(13))
        val score = ArcadeCabinet(input).startGame()
        assertThat(score).isEqualTo(12954)
    }
}