package com.aoc.shuffle.deck

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isNotEqualTo
import assertk.assertions.isTrue
import com.aoc.shuffle.TestSpaceDeckFactory
import com.aoc.shuffle.card.SpaceCard
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*

class SpaceCardDeckTest {
    private val factory = TestSpaceDeckFactory()

    @Test
    fun getCard() {
        assertThat(SpaceCardDeckFactory().factoryOrder().getCard(5782)).isEqualTo(SpaceCard(5782))
    }

    @Test
    fun getCardInvalidIndex() {
        val e = assertThrows<IllegalArgumentException> { (SpaceCardDeckFactory().factoryOrder().getCard(83423412)) }
        assertThat(e.message).isEqualTo("The deck does not contain a card at position 83423412")
    }

    @Test
    fun equalityTestPositive() {
        assertThat(factory.with(15)).isEqualTo(factory.with(15))
    }

    @Test
    fun equalityTestNegative() {
        assertThat(factory.with(56)).isNotEqualTo(factory.with(15))
    }

    @Test
    fun isNotEmptyPositive() {
        assertThat(factory.with(10).isNotEmpty()).isTrue()
    }

    @Test
    fun isNotEmptyNegative() {
        assertThat(SpaceCardDeck(LinkedList()).isNotEmpty()).isFalse()
    }

    @Test
    fun getCardWithValue() {
        assertThat(factory.deckWithCards(3,4,5,6,7,8,9,0,1,2).getCardWithValue(4)).isEqualTo(1)
    }

    @Test
    fun equalityPositive() {
        assertThat(factory.deckWithCards(1,2,3,4,5)).isEqualTo(factory.deckWithCards(1,2,3,4,5))
    }

    @Test
    fun equalityNegative() {
        assertThat(factory.deckWithCards(1,2,3,4,5)).isNotEqualTo(factory.deckWithCards(1,2,3,4,5,6,7))
    }

    @Test
    fun toStringTest() {
        assertThat(factory.with(5).toString()).isEqualTo("[0, 1, 2, 3, 4]")
    }
}