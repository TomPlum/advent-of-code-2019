package com.aoc.cards.strategy

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.cards.SpaceCardDeckFactory
import com.aoc.cards.TestSpaceDeckFactory.Companion.deckWithCards
import org.junit.jupiter.api.Test

class CuttingStrategyTest {
    @Test
    fun exampleDeckSizeTenCutThree() {
        val deck = SpaceCardDeckFactory.withCardQuantity(10)
        assertThat(CuttingStrategy(3).shuffle(deck)).isEqualTo(deckWithCards(3,4,5,6,7,8,9,0,1,2))
    }

    @Test
    fun exampleTwoDeckSizeTenCutNegativeFour() {
        val deck = SpaceCardDeckFactory.withCardQuantity(10)
        assertThat(CuttingStrategy(-4).shuffle(deck)).isEqualTo(deckWithCards(6,7,8,9,0,1,2,3,4,5))
    }
}