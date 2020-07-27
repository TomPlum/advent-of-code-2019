package com.aoc.shuffle.strategy.small

import com.aoc.shuffle.deck.SpaceCardDeck

/**
 * To deal into new stack, create a new stack of cards by dealing the top card of the deck onto the top of the new
 * stack repeatedly until you run out of cards.
 *
 * Finally, pick up the new stack you've just created and use it as the deck for the next technique.
 */
class NewStackStrategy : ShufflingStrategy {
    /**
     * Performs a single shuffle of the given [deck].
     * @return The shuffled [SpaceCardDeck].
     */
    override fun shuffle(deck: SpaceCardDeck): SpaceCardDeck {
        deck.reverseCards()
        return deck
    }
}