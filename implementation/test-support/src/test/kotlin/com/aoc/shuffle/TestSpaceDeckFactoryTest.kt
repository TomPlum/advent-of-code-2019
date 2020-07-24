package com.aoc.shuffle

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.shuffle.card.SpaceCard
import com.aoc.shuffle.deck.SpaceCardDeck
import org.junit.jupiter.api.Test
import java.util.*

class TestSpaceDeckFactoryTest {
    @Test
    fun deckWith() {
        assertThat(TestSpaceDeckFactory.deckWithCards(1, 2, 3)).isEqualTo(getExpectedSpaceCardDeck())
    }

    private fun getExpectedSpaceCardDeck(): SpaceCardDeck {
        val cards = LinkedList<SpaceCard>()
        cards.add(SpaceCard(1))
        cards.add(SpaceCard(2))
        cards.add(SpaceCard(3))
        return SpaceCardDeck(cards)
    }
}