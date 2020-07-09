package com.aoc.cards.giant

import java.math.BigInteger

abstract class GiantShufflingStrategy {
    protected val targetIndex = 2020

    abstract fun shuffle(currentValue: Long): BigInteger

    abstract fun getA(): Int

    abstract fun getB(): Int
}