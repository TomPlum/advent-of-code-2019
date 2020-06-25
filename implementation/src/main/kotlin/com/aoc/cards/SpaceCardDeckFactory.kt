package com.aoc.cards

import java.util.*

/**
 * Produces [SpaceCardDeck]
 */
class SpaceCardDeckFactory {

    companion object {
        fun factoryOrder() = SpaceCardDeck((0..10006).map(::SpaceCard).toCollection(LinkedList()))
        fun withCardQuantity(quantity: Int) = SpaceCardDeck((0 until quantity).map(::SpaceCard).toCollection(LinkedList()))
    }

}