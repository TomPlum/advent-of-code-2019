package com.aoc.cards.giant

import com.aoc.math.LinearFunction
import java.math.BigInteger

/**
 * Given a deck of size d, a target index of i, and the number of cards to cut by, n , then
 * the output value, x, can be calculated with the following linear equation;
 *
 * f(x) = i - n % d
 */
class GiantCuttingStrategy(private val deckSize: BigInteger, private val n: BigInteger) : GiantShufflingStrategy() {
    override fun create(): LinearFunction {
        return LinearFunction(getA(), getB().mod(deckSize))
    }

    override fun getA(): BigInteger = BigInteger.ONE

    override fun getB(): BigInteger = n

}