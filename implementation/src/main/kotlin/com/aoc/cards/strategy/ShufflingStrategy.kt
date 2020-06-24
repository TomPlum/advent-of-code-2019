package com.aoc.cards.strategy

import com.aoc.cards.SpaceCardDeck

interface ShufflingStrategy {
    fun shuffle(deck: SpaceCardDeck): SpaceCardDeck
}