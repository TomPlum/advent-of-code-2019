package com.aoc.shuffle.strategy.small

import com.aoc.shuffle.deck.SpaceCardDeck
import kotlin.math.abs

data class CuttingStrategy(private val quantity: Int) : ShufflingStrategy {
    override fun shuffle(deck: SpaceCardDeck): SpaceCardDeck {
        if (quantity > 0) {
            (0 until quantity).map { deck.takeFromTop() }.forEach { deck.addToBottom(it) }
        } else {
            (0 until abs(quantity)).map { deck.takeFromBottom() }.forEach { deck.addOnTop(it) }
        }
        return deck
    }
}