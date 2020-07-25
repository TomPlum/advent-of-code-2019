package com.aoc.shuffle.parser

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import com.aoc.shuffle.strategy.small.CuttingStrategy
import com.aoc.shuffle.strategy.small.IncrementStrategy
import com.aoc.shuffle.strategy.small.NewStackStrategy
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
    fun dealWithIncrementMultipleDigits() {
        val instructions = listOf("deal with increment 17")
        val strategies = ShuffleInstructionParser.parse(instructions)
        assertThat(strategies.first()).isEqualTo(IncrementStrategy(17))
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
    fun cutWhenQuantityMultipleDigits() {
        val instructions = listOf("cut 9002")
        val strategies = ShuffleInstructionParser.parse(instructions)
        assertThat(strategies.first()).isEqualTo(CuttingStrategy(9002))
    }

    @Test
    fun cutNegativeQuantity() {
        val instructions = listOf("cut -2")
        val strategies = ShuffleInstructionParser.parse(instructions)
        assertThat(strategies.first()).isEqualTo(CuttingStrategy(-2))
    }

    @EmptySource
    @ParameterizedTest
    @ValueSource(strings = ["CUT 5", "DEAL with increment 3", "cut a"])
    fun invalidInstruction(instruction: String) {
        val e = assertThrows<IllegalArgumentException> { ShuffleInstructionParser.parse(listOf(instruction)) }
        assertThat(e.message).isEqualTo("Invalid instruction: $instruction")
    }
}