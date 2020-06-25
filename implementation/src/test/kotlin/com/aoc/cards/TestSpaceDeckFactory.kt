package com.aoc.cards

import java.util.*

class TestSpaceDeckFactory {
    companion object {
        public fun deckWithCards(vararg values: Int): SpaceCardDeck {
            val deck = LinkedList<SpaceCard>()
            deck.addAll(values.map(::SpaceCard).toList())
            return SpaceCardDeck(deck)
        }
    }
}