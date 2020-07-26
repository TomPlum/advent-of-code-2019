package com.aoc.shuffle.deck

import com.aoc.shuffle.card.SpaceCard
import java.util.*

/**
 * A deck of [SpaceCard] found in the ships storage.
 * By default, there are 10007 cards in the deck numbered from 0 to 10006.
 * They come in factory order with 0 on the top.
 * @see SpaceCardDeckFactory
 */
data class SpaceCardDeck(private val cards: LinkedList<SpaceCard>) {

    /**
     * Retrieves the [SpaceCard] at the given [position].
     * @throws IllegalArgumentException if there is no card at the given [position].
     */
    fun getCard(position: Int): SpaceCard {
        try {
            return cards[position]
        } catch (e: IndexOutOfBoundsException) {
           throw IllegalArgumentException("The deck does not contain a card at position $position", e)
        }
    }

    /**
     * Retrieves the [SpaceCard] with the given [value].
     */
    fun getCardWithValue(value: Long) = cards.indexOf(SpaceCard(value))

    /**
     * Adds the given [card] on top of the deck.
     */
    fun addOnTop(card: SpaceCard) = cards.addFirst(card)

    /**
     * Adds the given [card] to the bottom of the deck.
     */
    fun addToBottom(card: SpaceCard) = cards.addLast(card)

    /**
     * Retrieves the [SpaceCard] from the top of the deck.
     */
    fun takeFromTop(): SpaceCard = cards.removeFirst()

    /**
     * Retieves the [SpaceCard] from the bottom of the deck.
     */
    fun takeFromBottom(): SpaceCard = cards.removeLast()

    /**
     * @return The number of [SpaceCard] currently in the deck.
     */
    fun size() = cards.size

    /**
     * @return true if the deck has at least 1 [SpaceCard], else false.
     */
    fun isNotEmpty() = cards.size > 0

    /**
     * Reverses the order of the [SpaceCard]s inside the deck.
     */
    fun reverseCards() = cards.reverse()

    override fun toString() = cards.toString()
}