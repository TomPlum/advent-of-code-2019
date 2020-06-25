package com.aoc.cards

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isNotEqualTo
import assertk.assertions.isTrue
import com.aoc.cards.TestSpaceDeckFactory.Companion.deckWithCards
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException
import java.util.*

class SpaceCardDeckTest {
    @Test
    fun getCard() {
        assertThat(SpaceCardDeckFactory.factoryOrder().getCard(5782)).isEqualTo(SpaceCard(5782))
    }

    @Test
    fun getCardInvalidIndex() {
        val e = assertThrows<IllegalArgumentException> { (SpaceCardDeckFactory.factoryOrder().getCard(83423412)) }
        assertThat(e.message).isEqualTo("The deck does not contain a card at position 83423412")
    }

    @Test
    fun equalityTestPositive() {
        assertThat(SpaceCardDeckFactory.withCardQuantity(15)).isEqualTo(SpaceCardDeckFactory.withCardQuantity(15))
    }

    @Test
    fun equalityTestNegative() {
        assertThat(SpaceCardDeckFactory.withCardQuantity(56)).isNotEqualTo(SpaceCardDeckFactory.withCardQuantity(15))
    }

    @Test
    fun isNotEmptyPositive() {
        assertThat(SpaceCardDeckFactory.withCardQuantity(10).isNotEmpty()).isTrue()
    }

    @Test
    fun isNotEmptyNegative() {
        assertThat(SpaceCardDeck(LinkedList()).isNotEmpty()).isFalse()
    }

    @Test
    fun getCardWithValue() {
        assertThat(deckWithCards(3,4,5,6,7,8,9,0,1,2).getCardWithValue(4)).isEqualTo(1)
    }

    @Test
    fun toStringTest() {
        assertThat(SpaceCardDeckFactory.withCardQuantity(5).toString()).isEqualTo("[0, 1, 2, 3, 4]")
    }
}