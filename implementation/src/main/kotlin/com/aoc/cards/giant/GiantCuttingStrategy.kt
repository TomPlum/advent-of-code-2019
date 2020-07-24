package com.aoc.cards.giant

import com.aoc.math.LinearFunction
import java.math.BigInteger

/**
 * Given a deck of size d, a target index of i, and the number of cards to cut by, n , then
 * the output value, x, can be calculated with the following linear equation;
 *
 * f(x) = i - n % d
 */
class GiantCuttingStrategy(private val deckSize: BigInteger, n: BigInteger) : GiantShufflingStrategy {
    override val a: BigInteger = BigInteger.ONE
    override val b: BigInteger = n

    override fun create() = LinearFunction(a, b.mod(deckSize))
}