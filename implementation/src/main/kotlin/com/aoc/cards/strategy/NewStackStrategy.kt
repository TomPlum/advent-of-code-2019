package com.aoc.cards.strategy

import com.aoc.cards.SpaceCardDeck

class NewStackStrategy : ShufflingStrategy {
    override fun shuffle(deck: SpaceCardDeck): SpaceCardDeck {
        deck.reverseCards()
        return deck
    }
}