package com.aoc.cards.giant

import java.math.BigInteger


class GiantIncrementStrategy(private val deckSize: Long, private val n: Int) : GiantShufflingStrategy() {
    override fun shuffle(currentValue: Long): BigInteger {
        return 0.toBigInteger()
    }

    override fun getA(): Int = n

    override fun getB(): Int = 0
}