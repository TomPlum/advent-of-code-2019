package com.aoc.cards

import com.aoc.cards.strategy.ShufflingStrategy

/**
 * Shuffles a [SpaceCardDeck] using the provided [ShufflingStrategy].
 */
class SpaceCardDeckShuffler(private var deck: SpaceCardDeck) {
    fun shuffle(instructions: List<ShufflingStrategy>): SpaceCardDeck {
        instructions.forEach{ deck = it.shuffle(deck) }
        return deck
    }
}