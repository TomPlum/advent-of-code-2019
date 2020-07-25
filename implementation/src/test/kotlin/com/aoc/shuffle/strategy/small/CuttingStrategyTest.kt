package com.aoc.shuffle.strategy.small

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.shuffle.TestSpaceDeckFactory
import org.junit.jupiter.api.Test

class CuttingStrategyTest {
    private val factory = TestSpaceDeckFactory()

    @Test
    fun exampleDeckSizeTenCutThree() {
        val deck = factory.with(10)
        assertThat(CuttingStrategy(3).shuffle(deck)).isEqualTo(factory.deckWithCards(3,4,5,6,7,8,9,0,1,2))
    }

    @Test
    fun exampleTwoDeckSizeTenCutNegativeFour() {
        val deck = factory.with(10)
        assertThat(CuttingStrategy(-4).shuffle(deck)).isEqualTo(factory.deckWithCards(6,7,8,9,0,1,2,3,4,5))
    }
}