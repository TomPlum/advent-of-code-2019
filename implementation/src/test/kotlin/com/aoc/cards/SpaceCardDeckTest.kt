package com.aoc.cards

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotEqualTo
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

    private fun deckWithCards(vararg values: Int): SpaceCardDeck {
        val stack = Stack<SpaceCard>()
        stack.addAll(values.map(::SpaceCard).toList())
        return SpaceCardDeck(stack)
    }
}