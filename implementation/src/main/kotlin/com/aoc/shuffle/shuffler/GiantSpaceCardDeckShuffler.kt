package com.aoc.shuffle.shuffler

import com.aoc.shuffle.strategy.giant.GiantShufflingStrategy
import com.aoc.math.LinearFunction
import com.aoc.shuffle.deck.GiantSpaceCardDeck
import com.aoc.shuffle.parser.GiantShuffleInstructionParser
import java.math.BigInteger
import java.math.BigInteger.*

/**
 * @param s The number of cards in the [GiantSpaceCardDeck]
 */
class GiantSpaceCardDeckShuffler(private val s: BigInteger, input: List<String>) {
    private val strategies = GiantShuffleInstructionParser(s).parse(input)
    private val identity = LinearFunction(ONE, ZERO)

    fun shuffle(times: BigInteger): GiantSpaceCardDeck {
        val aggregate = strategies.map { it.create() }.fold(identity) { acc, f -> f.compose(acc) }
        val shuffleFunction = doShuffle(aggregate, times)
        return GiantSpaceCardDeck(shuffleFunction, s)
    }

    /**
     * @param f The aggregate [LinearFunction] shuffle function composed from all of the [GiantShufflingStrategy].
     * @param n The number of shuffles to be performed.
     * @return The shuffled state of the deck represented as a [LinearFunction]
     */
    private fun doShuffle(f: LinearFunction, n: BigInteger): LinearFunction = when {
        n == ZERO -> identity
        n.mod(TWO) == ZERO -> {
            doShuffle(LinearFunction(f.a.multiply(f.a).mod(s), f.a.multiply(f.b).add(f.b).mod(s)), n.divide(TWO))
        }
        else -> {
            val cd = doShuffle(LinearFunction(f.a, f.b), n.subtract(ONE))
            LinearFunction(f.a.multiply(cd.a).mod(s), f.a.multiply(cd.b).add(f.b).mod(s))
        }
    }
}