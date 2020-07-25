package com.aoc.shuffle.deck

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.shuffle.card.SpaceCard
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class SpaceCardDeckFactoryTest {
    @Nested
    inner class FactoryOrder {
        private val deck = SpaceCardDeckFactory().factoryOrder()

        @Test
        fun factoryOrderShouldStartAt0() {
            assertThat(deck.getCard(0)).isEqualTo(SpaceCard(0))
        }

        @Test
        fun factoryOrderShouldEndAt10006() {
            assertThat(deck.getCard(10006)).isEqualTo(SpaceCard(10006))
        }

        @Test
        fun factoryOrderShouldSetCardValuesToIndex() {
            assertThat(deck.getCard(534)).isEqualTo(SpaceCard(534))
        }
    }
}