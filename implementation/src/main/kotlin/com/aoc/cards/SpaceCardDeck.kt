package com.aoc.cards

import java.lang.IllegalArgumentException
import java.util.*

data class SpaceCardDeck(private val cards: Stack<SpaceCard>) {

    fun dealIntoNewStack(): SpaceCardDeck {
        cards.reverse()
        return this
    }

    fun getCard(position: Int): SpaceCard {
        try {
            return cards[position]
        } catch (e: ArrayIndexOutOfBoundsException) {
           throw IllegalArgumentException("The deck does not contain a card at position $position", e)
        }
    }

    fun size() = cards.size
}