package com.aoc.fuel.factory.components

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotEqualTo
import org.junit.jupiter.api.Test

class FuelTest {
    @Test
    fun toStringTest() {
        assertThat(Fuel(1).toString()).isEqualTo("1 FUEL")
    }

    @Test
    fun equalityTestPositive() {
        assertThat(Fuel(15)).isEqualTo(Fuel(15))
    }

    @Test
    fun equalityTestNegative() {
        assertThat(Fuel(4)).isNotEqualTo(Fuel(15))
    }
}