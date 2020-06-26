package com.aoc.cards

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.cards.TestSpaceDeckFactory.Companion.deckWithCards
import com.aoc.input.Day
import com.aoc.input.InputReader
import org.junit.jupiter.api.Test

class SpaceCardDeckShufflerTest {
    @Test
    fun exampleOne() {
        val input = InputReader().readInputAsString("/cards/example-instructions-1.txt")
        val shuffler = SpaceCardDeckShuffler(SpaceCardDeckFactory.factoryOrder(10))
        val shuffledDeck = shuffler.shuffle(ShuffleInstructionParser.parse(input.values))
        assertThat(shuffledDeck).isEqualTo(deckWithCards(0,3,6,9,2,5,8,1,4,7))
    }

    @Test
    fun exampleTwo() {
        val input = InputReader().readInputAsString("/cards/example-instructions-2.txt")
        val shuffler = SpaceCardDeckShuffler(SpaceCardDeckFactory.factoryOrder(10))
        val shuffledDeck = shuffler.shuffle(ShuffleInstructionParser.parse(input.values))
        assertThat(shuffledDeck).isEqualTo(deckWithCards(3,0,7,4,1,8,5,2,9,6))
    }

    @Test
    fun exampleThree() {
        val input = InputReader().readInputAsString("/cards/example-instructions-3.txt")
        val shuffler = SpaceCardDeckShuffler(SpaceCardDeckFactory.factoryOrder(10))
        val shuffledDeck = shuffler.shuffle(ShuffleInstructionParser.parse(input.values))
        assertThat(shuffledDeck).isEqualTo(deckWithCards(6,3,0,7,4,1,8,5,2,9))
    }

    @Test
    fun exampleFour() {
        val input = InputReader().readInputAsString("/cards/example-instructions-4.txt")
        val shuffler = SpaceCardDeckShuffler(SpaceCardDeckFactory.factoryOrder(10))
        val shuffledDeck = shuffler.shuffle(ShuffleInstructionParser.parse(input.values))
        assertThat(shuffledDeck).isEqualTo(deckWithCards(9,2,5,8,1,4,7,0,3,6))
    }

    @Test
    fun partOneSolution() {
        val input = InputReader().readInputString(Day.from(22))
        val instructions = ShuffleInstructionParser.parse(input.values)
        val deck = SpaceCardDeckFactory.default()
        val shuffler = SpaceCardDeckShuffler(deck)
        assertThat(shuffler.shuffle(instructions).getCardWithValue(2019)).isEqualTo(2604)
    }
}