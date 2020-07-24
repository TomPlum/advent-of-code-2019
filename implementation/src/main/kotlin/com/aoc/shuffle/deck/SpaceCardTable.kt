package com.aoc.shuffle.deck

import com.aoc.shuffle.card.SpaceCard
import java.util.*

class SpaceCardTable(private val deck: SpaceCardDeck) {

    private val space = arrayOfNulls<SpaceCard>(deck.size())

    fun deal(n: Int): SpaceCardDeck {
        val incrementer = Incrementer(n)
        while (deck.isNotEmpty()) {
            space[incrementer.next()] = deck.takeFromTop()
        }
        return SpaceCardDeck(space.filterNotNull().toCollection(LinkedList()))
    }

    private inner class Incrementer(private val n: Int) {
        private val upperLimit = deck.size() - 1
        private var timesCalled = 0
        private var last = 0

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