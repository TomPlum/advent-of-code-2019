package com.aoc.cards

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotEqualTo
import org.junit.jupiter.api.Test

class SpaceCardTest {
    @Test
    fun equalityTestPositive() {
        assertThat(SpaceCard(12)).isEqualTo(SpaceCard(12))
    }

    @Test
    fun equalityTestNegative() {
        assertThat(SpaceCard(12)).isNotEqualTo(SpaceCard(15))
    }
}