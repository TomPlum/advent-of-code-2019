package com.aoc.fuel.factory.components

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotEqualTo
import org.junit.jupiter.api.Test

class OreTest {
    @Test
    fun toStringTest() {
        assertThat(Ore(10).toString()).isEqualTo("10 ORE")
    }

    @Test
    fun equalityTestPositive() {
        assertThat(Ore(15)).isEqualTo(Ore(15))
    }

    @Test
    fun equalityTestNegative() {
        assertThat(Ore(4)).isNotEqualTo(Ore(15))
    }
}