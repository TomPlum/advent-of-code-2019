package com.aoc.shuffle.shuffler

import com.aoc.shuffle.deck.SpaceCardDeck
import com.aoc.shuffle.strategy.small.ShufflingStrategy

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