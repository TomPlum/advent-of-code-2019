package com.aoc.shuffle.strategy.small

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.shuffle.TestSpaceDeckFactory
import com.aoc.shuffle.deck.SpaceCardDeckFactory
import org.junit.jupiter.api.Test

class NewStackStrategyTest {
    @Test
    fun dealIntoNewStack() {
        val deck = SpaceCardDeckFactory.factoryOrder(10)
        val expected = TestSpaceDeckFactory.deckWithCards(9, 8, 7, 6, 5, 4, 3, 2, 1, 0)
        assertThat(NewStackStrategy().shuffle(deck)).isEqualTo(expected)
    }
}