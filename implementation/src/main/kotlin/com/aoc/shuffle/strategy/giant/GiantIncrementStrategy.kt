package com.aoc.shuffle.strategy.giant

import com.aoc.math.LinearFunction
import com.aoc.shuffle.deck.GiantSpaceCardDeck
import java.math.BigInteger
import java.math.BigInteger.*
import com.aoc.shuffle.strategy.small.IncrementStrategy

/**
 * The giant version of the 'deal with increment' deck shuffling technique, applied to a [GiantSpaceCardDeck] instead.
 * @see IncrementStrategy
 *
 * @param deckSize The hypothetical number of cards in the [GiantSpaceCardDeck].
 * @param n The parameter of the shuffle technique; the number of positions to move to the right when dealing.
 */
class GiantIncrementStrategy(private val deckSize: BigInteger, n: BigInteger) : GiantShufflingStrategy {
    override val a: BigInteger = n
    override val b: BigInteger = ZERO

    /**
     * Creates a [LinearFunction] whose variables represent a single shuffle for this technique.
     */
    override fun create() = LinearFunction(ONE.multiply(a.modInverse(deckSize)).mod(deckSize), b)
}