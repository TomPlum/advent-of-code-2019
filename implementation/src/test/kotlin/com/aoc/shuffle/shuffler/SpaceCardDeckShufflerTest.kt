package com.aoc.shuffle.shuffler

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.Day
import com.aoc.input.TestInputReader
import com.aoc.input.InputReader
import com.aoc.shuffle.TestSpaceDeckFactory
import com.aoc.shuffle.deck.SpaceCardDeckFactory
import com.aoc.shuffle.parser.SmallShuffleInstructionParser
import org.junit.jupiter.api.Test

class SpaceCardDeckShufflerTest {
    private val factory = TestSpaceDeckFactory()
    @Test
    fun exampleOne() {
        val input = TestInputReader().readInputAsString("/cards/example-instructions-1.txt")
        val shuffler = SpaceCardDeckShuffler(SmallShuffleInstructionParser().parse(input.value))
        val shuffledDeck = shuffler.shuffle(factory.with(10))
        assertThat(shuffledDeck).isEqualTo(factory.deckWithCards(0,3,6,9,2,5,8,1,4,7))
    }

    @Test
    fun exampleTwo() {
        val input = TestInputReader().readInputAsString("/cards/example-instructions-2.txt")
        val shuffler = SpaceCardDeckShuffler(SmallShuffleInstructionParser().parse(input.value))
        val shuffledDeck = shuffler.shuffle(factory.with(10))
        assertThat(shuffledDeck).isEqualTo(factory.deckWithCards(3,0,7,4,1,8,5,2,9,6))
    }

    @Test
    fun exampleThree() {
        val input = TestInputReader().readInputAsString("/cards/example-instructions-3.txt")
        val shuffler = SpaceCardDeckShuffler(SmallShuffleInstructionParser().parse(input.value))
        val shuffledDeck = shuffler.shuffle(factory.with(10))
        assertThat(shuffledDeck).isEqualTo(factory.deckWithCards(6,3,0,7,4,1,8,5,2,9))
    }

    @Test
    fun exampleFour() {
        val input = TestInputReader().readInputAsString("/cards/example-instructions-4.txt")
        val shuffler = SpaceCardDeckShuffler(SmallShuffleInstructionParser().parse(input.value))
        val shuffledDeck = shuffler.shuffle(factory.with(10))
        assertThat(shuffledDeck).isEqualTo(factory.deckWithCards(9,2,5,8,1,4,7,0,3,6))
    }

    @Test
    fun partOneSolution() {
        val input = InputReader.read<String>(Day(22))
        val instructions = SmallShuffleInstructionParser().parse(input.value)
        val deck = SpaceCardDeckFactory().factoryOrder()
        val shuffler = SpaceCardDeckShuffler(instructions)
        assertThat(shuffler.shuffle(deck).getCardWithValue(2019)).isEqualTo(2604)
    }
}