package com.aoc.shuffle.strategy.giant

import com.aoc.math.LinearFunction
import java.math.BigInteger
import java.math.BigInteger.ONE

/**
 * Given a deck of size n, a target index of i, and a current value at the target index v, then
 * the output value, x, can be calculated with the following linear equation;
 *
 * f(x) = -x - 1 mod(m)   a=-1, b=-1
 *
 */
class GiantNewStackStrategy(private val deckSize: BigInteger) : GiantShufflingStrategy {
    override val a: BigInteger = ONE.negate()
    override val b: BigInteger = ONE.negate()

    override fun create() = LinearFunction(a, b.minus(deckSize))
}