package com.aoc.cards

import java.lang.IllegalArgumentException
import java.lang.IndexOutOfBoundsException
import java.util.*
import kotlin.math.abs

//TODO: Remove cut(), dealWithNewStack() as they're in strategies. Move relevant tests too.
data class SpaceCardDeck(private val cards: LinkedList<SpaceCard>) {

    fun dealIntoNewStack(): SpaceCardDeck {
        cards.reverse()
        return this
    }

    fun cut(quantity: Int): SpaceCardDeck {
        if (quantity > 0) {
            (0 until quantity).map { cards.removeFirst() }.forEach { cards.addLast(it) }
        } else {
            (0 until abs(quantity)).map { cards.removeLast() }.forEach { cards.addFirst(it) }
        }
        return this
    }

    fun getCard(position: Int): SpaceCard {
        try {
            return cards[position]
        } catch (e: IndexOutOfBoundsException) {
           throw IllegalArgumentException("The deck does not contain a card at position $position", e)
        }
    }

    fun getCardWithValue(value: Int) = cards.indexOf(SpaceCard(value))

    fun addOnTop(card: SpaceCard) = cards.addFirst(card)

    fun addToBottom(card: SpaceCard) = cards.addLast(card)

    fun takeFromTop(): SpaceCard = cards.removeFirst()

    fun takeFromBottom(): SpaceCard = cards.removeLast()

    fun size() = cards.size

    fun isNotEmpty() = cards.size > 0

    fun reverseCards() = cards.reverse()

    override fun toString() = cards.toString()
}