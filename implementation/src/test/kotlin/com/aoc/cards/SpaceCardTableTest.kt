package com.aoc.cards

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import java.util.*

class SpaceCardTableTest {
    @Test
    fun exampleDeal() {
        val deck = SpaceCardDeckFactory.withCardQuantity(10)
        assertThat(SpaceCardTable(deck).deal(3)).isEqualTo(deckWithCards(0,7,4,1,8,5,2,9,6,3))
    }

    private fun deckWithCards(vararg values: Int): SpaceCardDeck {
        val deck = LinkedList<SpaceCard>()
        deck.addAll(values.map(::SpaceCard).toList())
        return SpaceCardDeck(deck)
    }
}