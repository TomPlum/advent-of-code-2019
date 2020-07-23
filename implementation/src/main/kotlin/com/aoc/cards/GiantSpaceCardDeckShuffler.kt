package com.aoc.cards

import com.aoc.cards.giant.GiantShufflingStrategy
import com.aoc.math.LinearFunction
import java.math.BigInteger
import java.math.BigInteger.*

class GiantSpaceCardDeckShuffler(private val strategies: List<GiantShufflingStrategy>) {
    private val identity = LinearFunction(ONE, ZERO)

    fun shuffle(times: BigInteger): BigInteger? {
        val aggregate = strategies.fold(identity) { acc, e -> acc.compose(e.create()) }
        val deckSize = 119315717514047.toBigInteger()
        return doShuffle(aggregate, times).apply(2020.toBigInteger()).mod(deckSize)
    }

    private fun doShuffle(f: LinearFunction, n: BigInteger): LinearFunction {
        return when {
            n == ZERO -> identity
            n.mod(TWO) == ZERO -> {
                doShuffle(LinearFunction(f.a.multiply(f.a).mod(n), f.a.multiply(f.b).add(f.b).mod(n)), n.divide(TWO))
            }
            else -> {
                val cd = doShuffle(LinearFunction(f.a, f.b), n.subtract(ONE))
                LinearFunction(f.a.multiply(cd.a).mod(n), f.a.multiply(cd.b).mod(n))
            }
        }
    }
}