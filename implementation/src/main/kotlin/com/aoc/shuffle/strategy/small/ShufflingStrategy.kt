package com.aoc.shuffle.strategy.small

import com.aoc.shuffle.deck.SpaceCardDeck
import com.aoc.shuffle.parser.SmallShuffleInstructionParser

/**
 * A technique for shuffling a [SpaceCardDeck].
 * Produced by [SmallShuffleInstructionParser].
 */
interface ShufflingStrategy {
    fun shuffle(deck: SpaceCardDeck): SpaceCardDeck
}