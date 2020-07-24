package com.aoc.cards.giant

import com.aoc.math.LinearFunction
import java.math.BigInteger

interface GiantShufflingStrategy {
    fun create(): LinearFunction
    val a: BigInteger
    val b: BigInteger
}