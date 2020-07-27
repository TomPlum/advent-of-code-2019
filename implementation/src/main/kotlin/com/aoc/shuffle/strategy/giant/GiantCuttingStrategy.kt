package com.aoc.shuffle.strategy.giant

import com.aoc.math.LinearFunction
import java.math.BigInteger
import com.aoc.shuffle.deck.GiantSpaceCardDeck
import com.aoc.shuffle.strategy.small.CuttingStrategy

/**
 * The giant variant of the deck 'cutting' shuffling technique, applied to a [GiantSpaceCardDeck] instead.
 * @see CuttingStrategy
 *
 * @param deckSize The hypothetical number of cards in the [GiantSpaceCardDeck].
 * @param n The parameter of the shuffle technique; the number of cards to take off the top of the deck.
 */
class GiantCuttingStrategy(private val deckSize: BigInteger, n: BigInteger) : GiantShufflingStrategy {
    override val a: BigInteger = BigInteger.ONE
    override val b: BigInteger = n

    /**
     * Creates a [LinearFunction] whose variables represent a single shuffle for this technique.
     */
    override fun create() = LinearFunction(a, b.mod(deckSize))
}