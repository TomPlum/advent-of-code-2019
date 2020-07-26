package com.aoc.shuffle.deck

import com.aoc.math.LinearFunction
import com.aoc.shuffle.card.SpaceCard
import java.math.BigInteger

/**
 * A deck of [SpaceCard] so big that it only exists in theory!
 * @param function A [LinearFunction] representing the current order of the deck.
 * @param deckSize The number of cards in the deck.
 */
class GiantSpaceCardDeck(private val function: LinearFunction, private val deckSize: BigInteger) {
    /**
     * Retrieves the value of the [SpaceCard] that would be at the given [position].
     */
    fun getCard(position: BigInteger): BigInteger = function.apply(position).mod(deckSize)
}