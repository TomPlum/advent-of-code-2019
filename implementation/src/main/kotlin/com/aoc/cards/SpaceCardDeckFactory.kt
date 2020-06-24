package com.aoc.cards

import java.util.*

class SpaceCardDeckFactory {

    companion object {
        fun factoryOrder() = SpaceCardDeck((0..10006).map(::SpaceCard).toCollection(Stack()))
        fun withCardQuantity(quantity: Int) = SpaceCardDeck((0 until quantity).map(::SpaceCard).toCollection(Stack()))
    }

}