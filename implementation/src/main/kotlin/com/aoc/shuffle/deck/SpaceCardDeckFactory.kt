package com.aoc.shuffle.deck

import com.aoc.shuffle.card.SpaceCard
import java.util.*

/**
 * Produces a [SpaceCardDeck]
 */
open class SpaceCardDeckFactory {
    fun factoryOrder() = SpaceCardDeck((0 until 10007L).map(::SpaceCard).toCollection(LinkedList()))
}