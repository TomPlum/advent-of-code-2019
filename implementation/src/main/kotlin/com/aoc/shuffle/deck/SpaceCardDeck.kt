package com.aoc.shuffle.deck

import com.aoc.shuffle.card.SpaceCard
import java.util.*

/**
 * A deck of [SpaceCard]
 */
data class SpaceCardDeck(private val cards: LinkedList<SpaceCard>) {

    fun getCard(position: Int): SpaceCard {
        try {
            return cards[position]
        } catch (e: IndexOutOfBoundsException) {
           throw IllegalArgumentException("The deck does not contain a card at position $position", e)
        }
    }

    fun getCardWithValue(value: Long) = cards.indexOf(SpaceCard(value))

    fun addOnTop(card: SpaceCard) = cards.addFirst(card)

    fun addToBottom(card: SpaceCard) = cards.addLast(card)

    fun takeFromTop(): SpaceCard = cards.removeFirst()

    fun takeFromBottom(): SpaceCard = cards.removeLast()

    fun size() = cards.size

    fun isNotEmpty() = cards.size > 0

    fun reverseCards() = cards.reverse()

    override fun toString() = cards.toString()
}