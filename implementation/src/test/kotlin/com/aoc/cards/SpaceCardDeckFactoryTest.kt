package com.aoc.cards

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class SpaceCardDeckFactoryTest {
    @Nested
    inner class FactoryOrder {
        private val deck = SpaceCardDeckFactory.factoryOrder()

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

    @Nested
    inner class CustomStackSize {
        private val deck = SpaceCardDeckFactory.withCardQuantity(10)

        @Test
        fun tenCardDeckSize() {
            assertThat(deck.size()).isEqualTo(10)
        }

        @Test
        fun tenCardDeckShouldStartAt0() {
            assertThat(deck.getCard(0)).isEqualTo(SpaceCard(0))
        }

        @Test
        fun tenCardDeckShouldEndAt9() {
            assertThat(deck.getCard(9)).isEqualTo(SpaceCard(9))
        }
    }
}