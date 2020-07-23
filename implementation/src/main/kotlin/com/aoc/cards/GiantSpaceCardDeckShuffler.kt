package com.aoc.cards

import com.aoc.cards.giant.GiantShufflingStrategy
import com.aoc.math.LinearFunction
import java.math.BigInteger
import java.math.BigInteger.*

class GiantSpaceCardDeckShuffler(private val deckSize: BigInteger, input: List<String>) {
    private val strategies = GiantShuffleInstructionParser(deckSize).parse(input)
    private val identity = LinearFunction(ONE, ZERO)

    fun shuffle(times: BigInteger): GiantSpaceCardDeck {
        val aggregate = strategies.map { it.create() }.fold(identity) { acc, f -> f.compose(acc) }
        val shuffleFunction = doShuffle(aggregate, times)
        return GiantSpaceCardDeck(shuffleFunction, deckSize)
    }

    /**
     * @param f The aggregate [LinearFunction] composed from all of the [GiantShufflingStrategy].
     * @param nShuffles The number of shuffles to be performed.
     */
    private fun doShuffle(f: LinearFunction, nShuffles: BigInteger): LinearFunction {
        return when {
            nShuffles == ZERO -> identity
            nShuffles.mod(TWO) == ZERO -> {
                doShuffle(LinearFunction(f.k.multiply(f.k).mod(deckSize), f.k.multiply(f.m).add(f.m).mod(deckSize)), nShuffles.divide(TWO))
            }
            else -> {
                val cd = doShuffle(LinearFunction(f.k, f.m), nShuffles.subtract(ONE))
                LinearFunction(f.k.multiply(cd.k).mod(deckSize), f.k.multiply(cd.m).add(f.m).mod(deckSize))
            }
        }
    }
}