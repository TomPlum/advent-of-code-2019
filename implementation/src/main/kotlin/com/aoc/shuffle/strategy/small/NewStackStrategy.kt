package com.aoc.shuffle.strategy.small

import com.aoc.shuffle.deck.SpaceCardDeck

class NewStackStrategy : ShufflingStrategy {
    override fun shuffle(deck: SpaceCardDeck): SpaceCardDeck {
        deck.reverseCards()
        return deck
    }
}