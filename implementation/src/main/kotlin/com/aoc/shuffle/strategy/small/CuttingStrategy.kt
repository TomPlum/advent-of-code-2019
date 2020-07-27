package com.aoc.shuffle.strategy.small

import com.aoc.shuffle.deck.SpaceCardDeck
import kotlin.math.abs

/**
 * To cut [n] cards, take the top N cards off the top of the deck and move them as a single unit to the bottom of
 * the deck, retaining their order.
 */
data class CuttingStrategy(private val n: Int) : ShufflingStrategy {
    /**
     * Performs a single shuffle of the given [deck].
     * @return The shuffled [SpaceCardDeck].
     */
    override fun shuffle(deck: SpaceCardDeck): SpaceCardDeck {
        when {
            n > 0 -> (0 until n).map { deck.takeFromTop() }.forEach { deck.addToBottom(it) }
            else -> (0 until abs(n)).map { deck.takeFromBottom() }.forEach { deck.addOnTop(it) }
        }
        return deck
    }
}