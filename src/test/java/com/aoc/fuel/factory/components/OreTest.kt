package com.aoc.fuel.factory.components

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotEqualTo
import com.aoc.fuel.factory.components.Ore
import org.junit.jupiter.api.Test

class OreTest {
    @Test
    fun toStringTest() {
        assertThat(Ore(10.0).toString()).isEqualTo("10 ORE")
    }

    @Test
    fun equalityTestPositive() {
        assertThat(Ore(15.0)).isEqualTo(Ore(15.0))
    }

    @Test
    fun equalityTestNegative() {
        assertThat(Ore(4.0)).isNotEqualTo(Ore(15.0))
    }
}