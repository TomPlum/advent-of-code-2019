package com.aoc.math

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class FormulaeTest {
    @Test
    fun lowestCommonMultiple() {
        assertThat(Formulae.lcm(listOf(2, 4, 6))).isEqualTo(12)
    }

    @Test
    fun lowestCommonMultipleLargerNumbers() {
        assertThat(Formulae.lcm(listOf(41, 27, 43))).isEqualTo(47601)
    }
}