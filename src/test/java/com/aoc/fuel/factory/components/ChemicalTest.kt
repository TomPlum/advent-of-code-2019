package com.aoc.fuel.factory.components

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotEqualTo
import com.aoc.fuel.factory.components.Chemical
import org.junit.jupiter.api.Test

class ChemicalTest {
    @Test
    fun toStringTest() {
        assertThat(Chemical("A", 7).toString()).isEqualTo("7 A")
    }

    @Test
    fun toStringTestPositive() {
        assertThat(Chemical("A", 6)).isEqualTo(Chemical("A", 6))
    }

    @Test
    fun toStringTestNegativeDifferentNames() {
        assertThat(Chemical("B", 6)).isNotEqualTo(Chemical("A", 6))
    }

    @Test
    fun toStringTestNegativeDifferentQuantities() {
        assertThat(Chemical("A", 12)).isNotEqualTo(Chemical("A", 6))
    }

    @Test
    fun toStringTestNegativeDifferentNamesAndQuantities() {
        assertThat(Chemical("B", 12)).isNotEqualTo(Chemical("A", 6))
    }
}