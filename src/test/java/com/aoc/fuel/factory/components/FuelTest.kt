package com.aoc.fuel.factory.components

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotEqualTo
import com.aoc.fuel.factory.components.Fuel
import org.junit.jupiter.api.Test

class FuelTest {
    @Test
    fun toStringTest() {
        assertThat(Fuel(1.0).toString()).isEqualTo("1 FUEL")
    }

    @Test
    fun equalityTestPositive() {
        assertThat(Fuel(15.0)).isEqualTo(Fuel(15.0))
    }

    @Test
    fun equalityTestNegative() {
        assertThat(Fuel(4.0)).isNotEqualTo(Fuel(15.0))
    }
}