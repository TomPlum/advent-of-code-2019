package com.aoc.shuffle.strategy.small

import com.aoc.shuffle.deck.SpaceCardDeck
import com.aoc.shuffle.deck.SpaceCardTable

data class IncrementStrategy(private val increment: Int) : ShufflingStrategy {
    override fun shuffle(deck: SpaceCardDeck): SpaceCardDeck {
        return SpaceCardTable(deck).deal(increment)
    }
}