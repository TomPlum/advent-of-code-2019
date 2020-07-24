package com.aoc.shuffle

import com.aoc.shuffle.card.SpaceCard
import com.aoc.shuffle.deck.SpaceCardDeck
import java.util.*

class TestSpaceDeckFactory {
    companion object {
        fun deckWithCards(vararg values: Long): SpaceCardDeck {
            val deck = LinkedList<SpaceCard>()
            deck.addAll(values.map(::SpaceCard).toList())
            return SpaceCardDeck(deck)
        }
    }
}