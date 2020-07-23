package com.aoc.cards

import com.aoc.cards.strategy.ShufflingStrategy
import java.math.BigInteger

/**
 * Shuffles a [SpaceCardDeck] using the provided [ShufflingStrategy].
 */
class SpaceCardDeckShuffler(private val instructions: List<ShufflingStrategy>) {

    fun shuffle(deck: SpaceCardDeck): SpaceCardDeck {
        var shuffled = deck
        instructions.forEach{ shuffled = it.shuffle(shuffled) } //TODO: Functional approach?
        return shuffled
    }

}