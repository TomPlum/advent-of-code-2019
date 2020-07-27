package com.aoc.shuffle.strategy.small

import com.aoc.shuffle.card.SpaceCard
import com.aoc.shuffle.deck.SpaceCardDeck
import java.util.*

/**
 * To deal with increment [n], start by clearing enough space on your table to lay out all of the cards individually in
 * a long line. Deal the top card into the leftmost position. Then, move [n] positions to the right and deal the next
 * card there. If you would move into a position past the end of the space on your table, wrap around and keep counting
 * from the leftmost card again. Continue this process until you run out of cards.
 *
 * @param n The value to increment by
 */
data class IncrementStrategy(private val n: Int) : ShufflingStrategy {

    /**
     * Performs a single shuffle of the given [deck].
     * @return The shuffled [SpaceCardDeck].
     */
    override fun shuffle(deck: SpaceCardDeck): SpaceCardDeck {
        val space = arrayOfNulls<SpaceCard>(deck.size())
        val incrementer = Incrementer(deck)
        while (deck.isNotEmpty()) {
            space[incrementer.next()] = deck.takeFromTop()
        }
        return SpaceCardDeck(space.filterNotNull().toCollection(LinkedList()))
    }

    /**
     * Maintains the indices of the [SpaceCardDeck] according to the [IncrementStrategy] behaviour.
     */
    private inner class Incrementer(deck: SpaceCardDeck) {
        private val upperLimit = deck.size() - 1
        private var timesCalled = 0
        private var last = 0

        /**
         * @return The index in which the next card from top of the deck is to be moved to.
         */
        fun next(): Int {
            var next = 0

            if (timesCalled == 0) {
                timesCalled++
                return next
            }

            if ((last + n) <= upperLimit) {
                next = last + n
                last = next
            } else {
                val greaterThan = last + n
                val diff = greaterThan - upperLimit
                next = diff - 1
                last = next
            }

            timesCalled++
            return next
        }
    }
}