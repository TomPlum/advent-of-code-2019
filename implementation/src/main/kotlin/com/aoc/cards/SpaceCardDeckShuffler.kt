package com.aoc.cards

import com.aoc.cards.strategy.ShufflingStrategy

/**
 * Shuffles a [SpaceCardDeck] using the provided [ShufflingStrategy].
 */
class SpaceCardDeckShuffler(private val instructions: List<ShufflingStrategy>) {
    fun shuffle(deck: SpaceCardDeck, times: Long): SpaceCardDeck {
        var shuffled = deck
        instructions.forEach{ shuffled = it.shuffle(shuffled) } //TODO: Function approach?
        return shuffled
    }

    fun shuffle(deck: SpaceCardDeck): SpaceCardDeck {
        var shuffled = deck
        instructions.forEach{ shuffled = it.shuffle(shuffled) }
        return shuffled
    }
}