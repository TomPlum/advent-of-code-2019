package com.aoc.cards

import java.util.*

/**
 * Produces [SpaceCardDeck]
 */
class SpaceCardDeckFactory {

    companion object {
        fun default() = factoryOrder(10007)
        fun factoryOrder(quantity: Long) = SpaceCardDeck((0 until quantity).map(::SpaceCard).toCollection(LinkedList()))
    }

}