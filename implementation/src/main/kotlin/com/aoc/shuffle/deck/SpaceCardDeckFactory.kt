package com.aoc.shuffle.deck

import com.aoc.shuffle.card.SpaceCard
import java.util.*

/**
 * Produces [SpaceCardDeck]
 */
open class SpaceCardDeckFactory {

    companion object {
        fun default() = factoryOrder(10007)
        fun factoryOrder(quantity: Long) = SpaceCardDeck((0 until quantity).map(::SpaceCard).toCollection(LinkedList()))
    }

}