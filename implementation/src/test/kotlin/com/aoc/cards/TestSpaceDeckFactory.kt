package com.aoc.cards

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