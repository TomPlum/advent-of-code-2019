package com.aoc.shuffle.card

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotEqualTo
import com.aoc.shuffle.card.SpaceCard
import org.junit.jupiter.api.Test

class SpaceCardTest {
    @Test
    fun getValue() {
        assertThat(SpaceCard(12).value).isEqualTo(12)
    }

    @Test
    fun equalityTestPositive() {
        assertThat(SpaceCard(12)).isEqualTo(SpaceCard(12))
    }

    @Test
    fun equalityTestNegative() {
        assertThat(SpaceCard(12)).isNotEqualTo(SpaceCard(15))
    }

    @Test
    fun toStringTest() {
        assertThat(SpaceCard(57).toString()).isEqualTo("57")
    }
}