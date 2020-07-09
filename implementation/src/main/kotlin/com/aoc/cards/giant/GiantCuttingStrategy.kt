package com.aoc.cards.giant

import java.math.BigInteger

/**
 * Given a deck of size d, a target index of i, and the number of cards to cut by, n , then
 * the output value, x, can be calculated with the following linear equation;
 *
 * f(x) = i - n % d
 */
class GiantCuttingStrategy(private val deckSize: Long, private val n: Int) : GiantShufflingStrategy() {
    override fun shuffle(currentValue: Long): BigInteger {
        return (targetIndex - n % deckSize).toBigInteger()
    }

    override fun getA(): Int = 1

    override fun getB(): Int = -n

}