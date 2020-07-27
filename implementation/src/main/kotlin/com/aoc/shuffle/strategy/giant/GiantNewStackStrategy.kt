package com.aoc.shuffle.strategy.giant

import com.aoc.math.LinearFunction
import com.aoc.shuffle.deck.GiantSpaceCardDeck
import com.aoc.shuffle.strategy.small.NewStackStrategy
import java.math.BigInteger
import java.math.BigInteger.ONE

/**
 * The giant variant of the 'deal into new stack' shuffling technique, applied to a [GiantSpaceCardDeck] instead.
 * @see NewStackStrategy
 *
 * @param deckSize The hypothetical number of cards in the [GiantSpaceCardDeck].
 */
class GiantNewStackStrategy(private val deckSize: BigInteger) : GiantShufflingStrategy {
    override val a: BigInteger = ONE.negate()
    override val b: BigInteger = ONE.negate()

    /**
     * Creates a [LinearFunction] whose variables represent a single shuffle for this technique.
     */
    override fun create() = LinearFunction(a, b.minus(deckSize))
}