package com.aoc.cards.strategy

import com.aoc.cards.SpaceCardDeck
import com.aoc.cards.SpaceCardTable

data class IncrementStrategy(private val increment: Int) : ShufflingStrategy {
    override fun shuffle(deck: SpaceCardDeck): SpaceCardDeck {
        return SpaceCardTable(deck).deal(increment)
    }
}