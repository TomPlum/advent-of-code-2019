package com.aoc.shuffle.parser

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import com.aoc.math.LinearFunction
import com.aoc.shuffle.strategy.giant.GiantCuttingStrategy
import com.aoc.shuffle.strategy.giant.GiantIncrementStrategy
import com.aoc.shuffle.strategy.giant.GiantNewStackStrategy
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EmptySource
import org.junit.jupiter.params.provider.ValueSource
import java.math.BigInteger.ONE
import java.math.BigInteger.ZERO

class GiantShuffleInstructionParserTest {
    @Nested
    inner class ReturnsCorrectStrategyInstance {
        @Test
        fun parseNewStack() {
            val instructions = listOf("deal into new stack")
            val strategies = GiantShuffleInstructionParser(ONE).parse(instructions)
            assertThat(strategies.first()).isInstanceOf(GiantNewStackStrategy::class)
        }

        @Test
        fun parseDealWithIncrement() {
            val instructions = listOf("deal with increment 7")
            val strategies = GiantShuffleInstructionParser(ONE).parse(instructions)
            assertThat(strategies.first()).isInstanceOf(GiantIncrementStrategy::class)
        }

        @Test
        fun parseCutDeck() {
            val instructions = listOf("cut 9002")
            val strategies = GiantShuffleInstructionParser(ONE).parse(instructions)
            assertThat(strategies.first()).isInstanceOf(GiantCuttingStrategy::class)
        }

        @EmptySource
        @ParameterizedTest
        @ValueSource(strings = ["CUT 5", "DEAL with increment 3", "cut a"])
        fun invalidInstruction(instruction: String) {
            val e = assertThrows<IllegalArgumentException> { GiantShuffleInstructionParser(ONE).parse(listOf(instruction)) }
            assertThat(e.message).isEqualTo("Invalid instruction: $instruction")
        }
    }

    @Nested
    inner class LinearFunctionTerms {
        @Test
        fun newStackFirstTerm() {
            val instructions = listOf("deal into new stack")
            val strategies = GiantShuffleInstructionParser(ONE).parse(instructions)
            assertThat(strategies.first().a).isEqualTo(ONE.negate())
        }

        @Test
        fun newStackSecondTerm() {
            val instructions = listOf("deal into new stack")
            val strategies = GiantShuffleInstructionParser(ONE).parse(instructions)
            assertThat(strategies.first().b).isEqualTo(ONE.negate())
        }

        @Test
        fun dealWithIncrementFirstTerm() {
            val instructions = listOf("deal with increment 7")
            val strategies = GiantShuffleInstructionParser(ONE).parse(instructions)
            assertThat(strategies.first().a).isEqualTo(7.toBigInteger())
        }

        @Test
        fun dealWithIncrementSecondTerm() {
            val instructions = listOf("deal with increment 7")
            val strategies = GiantShuffleInstructionParser(ONE).parse(instructions)
            assertThat(strategies.first().b).isEqualTo(ZERO)
        }

        @Test
        fun cutFirstTerm() {
            val instructions = listOf("cut 7")
            val strategies = GiantShuffleInstructionParser(ONE).parse(instructions)
            assertThat(strategies.first().a).isEqualTo(ONE)
        }

        @Test
        fun cutSecondTerm() {
            val instructions = listOf("cut 12")
            val strategies = GiantShuffleInstructionParser(ONE).parse(instructions)
            assertThat(strategies.first().b).isEqualTo(12.toBigInteger())
        }
    }

    @Nested
    inner class LinearFunctions {
        @Test
        fun newStack() {
            val instructions = listOf("deal into new stack")
            val strategies = GiantShuffleInstructionParser(12.toBigInteger()).parse(instructions)
            assertThat(strategies.first().create()).isEqualTo(LinearFunction(ONE.negate(), (-13).toBigInteger()))
        }

        @Test
        fun dealWithIncrement() {
            val instructions = listOf("deal with increment 7")
            val strategies = GiantShuffleInstructionParser(100.toBigInteger()).parse(instructions)
            assertThat(strategies.first().create()).isEqualTo(LinearFunction(43.toBigInteger(), ZERO))
        }

        @Test
        fun cut() {
            val instructions = listOf("cut 28")
            val strategies = GiantShuffleInstructionParser(5.toBigInteger()).parse(instructions)
            assertThat(strategies.first().create()).isEqualTo(LinearFunction(ONE, 3.toBigInteger()))
        }
    }

}