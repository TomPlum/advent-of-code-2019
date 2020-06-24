package com.aoc.cards

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isNotEqualTo
import assertk.assertions.isTrue
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
    fun dealIntoNewStack() {
        val deck = SpaceCardDeckFactory.withCardQuantity(10)
        val newStack = deck.dealIntoNewStack()
        assertThat(newStack).isEqualTo(deckWithCards(9,8,7,6,5,4,3,2,1,0))
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
    fun exampleDeckSizeTenCutThree() {
        assertThat(SpaceCardDeckFactory.withCardQuantity(10).cut(3)).isEqualTo(deckWithCards(3,4,5,6,7,8,9,0,1,2))
    }

    @Test
    fun exampleTwoDeckSizeTenCutNegativeFour() {
        assertThat(SpaceCardDeckFactory.withCardQuantity(10).cut(-4)).isEqualTo(deckWithCards(6,7,8,9,0,1,2,3,4,5))
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

    private fun deckWithCards(vararg values: Int): SpaceCardDeck {
        val deck = LinkedList<SpaceCard>()
        deck.addAll(values.map(::SpaceCard).toList())
        return SpaceCardDeck(deck)
    }
}