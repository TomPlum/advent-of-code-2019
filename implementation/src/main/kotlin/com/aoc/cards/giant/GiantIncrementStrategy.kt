package com.aoc.cards.giant

import com.aoc.math.LinearFunction
import java.math.BigInteger
import java.math.BigInteger.*


class GiantIncrementStrategy(private val deckSize: BigInteger, n: BigInteger) : GiantShufflingStrategy {
    override val a: BigInteger = n
    override val b: BigInteger = ZERO

    override fun create() = LinearFunction(ONE.multiply(a.modInverse(deckSize)).mod(deckSize), b)
}