package com.aoc.cards.giant

import com.aoc.math.LinearFunction
import java.math.BigInteger


class GiantIncrementStrategy(private val deckSize: BigInteger, private val n: BigInteger) : GiantShufflingStrategy() {
    override fun create(): LinearFunction {
        return LinearFunction(BigInteger.ONE.multiply(getA().modInverse(deckSize)).mod(deckSize), getB())
    }

    override fun getA(): BigInteger = n

    override fun getB(): BigInteger = BigInteger.ZERO
}