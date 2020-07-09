package com.aoc.cards.giant

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class GiantNewStackStrategyTest {
    @Test
    fun giantNewStack() {
        assertThat(GiantNewStackStrategy(119315717514047.toBigInteger()).shuffle(2020)).isEqualTo(119315717512026)
    }
}