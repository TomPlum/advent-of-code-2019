package com.aoc.shuffle.card

import com.aoc.shuffle.deck.SpaceCardDeck

/**
 * A single card that can be added to a [SpaceCardDeck].
 */
data class SpaceCard(val value: Long) {
    override fun toString() = value.toString()
}