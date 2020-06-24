package com.aoc.cards

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import com.aoc.cards.strategy.CuttingStrategy
import com.aoc.cards.strategy.IncrementStrategy
import com.aoc.cards.strategy.NewStackStrategy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EmptySource
import org.junit.jupiter.params.provider.ValueSource

class ShuffleInstructionParserTest {
    @Test
    fun dealWithIncrement() {
        val instructions = listOf("deal with increment 7")
        val strategies = ShuffleInstructionParser.parse(instructions)
        assertThat(strategies.first()).isEqualTo(IncrementStrategy(7))
    }

    @Test
    fun newStack() {
        val instructions = listOf("deal into new stack")
        val strategies = ShuffleInstructionParser.parse(instructions)
        assertThat(strategies.first()).isInstanceOf(NewStackStrategy::class)
    }

    @Test
    fun cut() {
        val instructions = listOf("cut 6")
        val strategies = ShuffleInstructionParser.parse(instructions)
        assertThat(strategies.first()).isEqualTo(CuttingStrategy(6))
    }

    @Test
    fun cutNegativeQuantity() {
        val instructions = listOf("cut -2")
        val strategies = ShuffleInstructionParser.parse(instructions)
        assertThat(strategies.first()).isEqualTo(CuttingStrategy(-2))
    }

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = ["CUT 5", "deal with imcremement 3", "cut a"])
    fun invalidInstruction(instruction: String) {
        val e = assertThrows<IllegalArgumentException> { ShuffleInstructionParser.parse(listOf(instruction)) }
        assertThat(e.message).isEqualTo("Invalid instruction: $instruction")
    }
}