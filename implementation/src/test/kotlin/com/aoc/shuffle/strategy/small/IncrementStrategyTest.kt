package com.aoc.shuffle.strategy.small

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.shuffle.TestSpaceDeckFactory
import org.junit.jupiter.api.Test

class IncrementStrategyTest {
    @Test
    fun exampleDeal() {
        val factory = TestSpaceDeckFactory()
        val deck = factory.with(10)
        assertThat(IncrementStrategy(3).shuffle(deck)).isEqualTo(factory.deckWithCards(0,7,4,1,8,5,2,9,6,3))
    }
}