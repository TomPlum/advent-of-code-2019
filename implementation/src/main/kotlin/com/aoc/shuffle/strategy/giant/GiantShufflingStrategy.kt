package com.aoc.shuffle.strategy.giant

import com.aoc.math.LinearFunction
import java.math.BigInteger
import com.aoc.shuffle.deck.GiantSpaceCardDeck
import com.aoc.shuffle.parser.GiantShuffleInstructionParser

/**
 * A technique for shuffling a [GiantSpaceCardDeck].
 * Produced by [GiantShuffleInstructionParser].
 */
interface GiantShufflingStrategy {
    fun create(): LinearFunction
    val a: BigInteger
    val b: BigInteger
}