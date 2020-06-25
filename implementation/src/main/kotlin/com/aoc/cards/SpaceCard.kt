package com.aoc.cards

/**
 * A single card that can be added to a [SpaceCardDeck].
 */
data class SpaceCard(val value: Int) {
    override fun toString() = value.toString()
}