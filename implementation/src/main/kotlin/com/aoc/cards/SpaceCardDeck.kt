package com.aoc.cards

import java.lang.IllegalArgumentException
import java.lang.IndexOutOfBoundsException
import java.util.*

data class SpaceCardDeck(private val cards: LinkedList<SpaceCard>) {

    fun dealIntoNewStack(): SpaceCardDeck {
        cards.reverse()
        return this
    }

    fun cut(quantity: Int): SpaceCardDeck {
        (0 until quantity).map { cards.pop() }.forEach { cards.addLast(it) }
        return this
    }

    fun getCard(position: Int): SpaceCard {
        try {
            return cards[position]
        } catch (e: IndexOutOfBoundsException) {
           throw IllegalArgumentException("The deck does not contain a card at position $position", e)
        }
    }

    fun size() = cards.size

    override fun toString() = cards.toString()
}