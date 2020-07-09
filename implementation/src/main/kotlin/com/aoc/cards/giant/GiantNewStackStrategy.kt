package com.aoc.cards.giant

import com.aoc.math.LinearCongruentialFunction
import java.math.BigInteger

/**
 * Given a deck of size n, a target index of i, and a current value at the target index v, then
 * the output value, x, can be calculated with the following linear equation;
 *
 * f(x) = -x - 1 mod(m)   a=-1, b=-1
 *
 */
class GiantNewStackStrategy(private val deckSize: BigInteger) : GiantShufflingStrategy() {
    override fun shuffle(currentValue: Long): BigInteger {
        return LinearCongruentialFunction(-1, -1, deckSize, 1.toBigInteger()).apply()
    }

    override fun getA(): Int = -1

    override fun getB(): Int = -1
}