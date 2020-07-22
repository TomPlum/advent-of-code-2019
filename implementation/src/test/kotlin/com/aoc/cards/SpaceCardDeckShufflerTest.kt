package com.aoc.cards

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.Day
import com.aoc.input.TestInputReader
import com.aoc.cards.TestSpaceDeckFactory.Companion.deckWithCards
import com.aoc.input.InputReader
import org.junit.jupiter.api.Test

class SpaceCardDeckShufflerTest {
    @Test
    fun exampleOne() {
        val input = TestInputReader().readInputAsString("/cards/example-instructions-1.txt")
        val shuffler = SpaceCardDeckShuffler(ShuffleInstructionParser.parse(input.value))
        val shuffledDeck = shuffler.shuffle(SpaceCardDeckFactory.factoryOrder(10))
        assertThat(shuffledDeck).isEqualTo(deckWithCards(0,3,6,9,2,5,8,1,4,7))
    }

    @Test
    fun exampleTwo() {
        val input = TestInputReader().readInputAsString("/cards/example-instructions-2.txt")
        val shuffler = SpaceCardDeckShuffler(ShuffleInstructionParser.parse(input.value))
        val shuffledDeck = shuffler.shuffle(SpaceCardDeckFactory.factoryOrder(10))
        assertThat(shuffledDeck).isEqualTo(deckWithCards(3,0,7,4,1,8,5,2,9,6))
    }

    @Test
    fun exampleThree() {
        val input = TestInputReader().readInputAsString("/cards/example-instructions-3.txt")
        val shuffler = SpaceCardDeckShuffler(ShuffleInstructionParser.parse(input.value))
        val shuffledDeck = shuffler.shuffle(SpaceCardDeckFactory.factoryOrder(10))
        assertThat(shuffledDeck).isEqualTo(deckWithCards(6,3,0,7,4,1,8,5,2,9))
    }

    @Test
    fun exampleFour() {
        val input = TestInputReader().readInputAsString("/cards/example-instructions-4.txt")
        val shuffler = SpaceCardDeckShuffler(ShuffleInstructionParser.parse(input.value))
        val shuffledDeck = shuffler.shuffle(SpaceCardDeckFactory.factoryOrder(10))
        assertThat(shuffledDeck).isEqualTo(deckWithCards(9,2,5,8,1,4,7,0,3,6))
    }

    @Test
    fun partOneSolution() {
        val input = InputReader.read<String>(Day(22))
        val instructions = ShuffleInstructionParser.parse(input.value)
        val deck = SpaceCardDeckFactory.default()
        val shuffler = SpaceCardDeckShuffler(instructions)
        assertThat(shuffler.shuffle(deck).getCardWithValue(2019)).isEqualTo(2604)
    }

    @Test
    fun shuffleLargeDeckButWithPartOneInput() {
        val m = 10007
    }
}