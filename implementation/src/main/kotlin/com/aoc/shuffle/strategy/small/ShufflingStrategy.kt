package com.aoc.shuffle.strategy.small

import com.aoc.shuffle.deck.SpaceCardDeck

interface ShufflingStrategy {
    fun shuffle(deck: SpaceCardDeck): SpaceCardDeck
}