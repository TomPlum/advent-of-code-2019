package com.aoc.cards.giant

import com.aoc.math.LinearFunction
import java.math.BigInteger

abstract class GiantShufflingStrategy {
    abstract fun create(): LinearFunction
    abstract fun getA(): BigInteger
    abstract fun getB(): BigInteger
}