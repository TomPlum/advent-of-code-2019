package com.aoc.shuffle.deck

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.shuffle.TestSpaceDeckFactory.Companion.deckWithCards
import org.junit.jupiter.api.Test

class SpaceCardTableTest {
    @Test
    fun exampleDeal() {
        val deck = SpaceCardDeckFactory.factoryOrder(10)
        assertThat(SpaceCardTable(deck).deal(3)).isEqualTo(deckWithCards(0,7,4,1,8,5,2,9,6,3))
    }
}