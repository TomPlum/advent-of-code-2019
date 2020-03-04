package com.aoc.intcode.arcade

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.input.Day
import com.aoc.input.InputReader
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class ArcadeCabinetTest {
    @Test
    @DisplayName("Given Day 13 Part 1 Puzzle Input, when getting the quantity of BLOCK tiles, it should return 247")
    fun solutionPartOne() {
        val input = InputReader().readInputAsSingleString(Day.from(13))
        val blockQuantity = ArcadeCabinet(input).getTileQuantity(TileID.BLOCK)
        assertThat(blockQuantity).isEqualTo(247)
    }
}