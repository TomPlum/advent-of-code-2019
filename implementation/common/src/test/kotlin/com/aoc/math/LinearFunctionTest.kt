package com.aoc.math

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotEqualTo
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class LinearFunctionTest {
    @Nested
    inner class Apply {
        @Test
        fun positiveIntegers() {
            assertThat(LinearFunction(2.big(), 4.big()).apply(5.big())).isEqualTo(14.big())
        }

        @Test
        fun withNegativeIntegers() {
            assertThat(LinearFunction((-6).big(), 4.big()).apply((-2).big())).isEqualTo(16.big())
        }
    }

    @Nested
    inner class Compose {
        @Test
        fun compose() {
            val f = LinearFunction(2.big(), 4.big())
            val k = LinearFunction((-6).big(), 4.big())
            assertThat(f.compose(k)).isEqualTo(LinearFunction((-12).big(), 12.big()))
        }
    }

    @Nested
    inner class Equality {
        @Test
        fun equal() {
            val first = LinearFunction(12.big(), 26.big())
            val second = LinearFunction(12.big(), 26.big())
            assertThat(first).isEqualTo(second)
        }

        @Test
        fun notEqualDifferentFirstTerm() {
            val first = LinearFunction(15.big(), 26.big())
            val second = LinearFunction(12.big(), 26.big())
            assertThat(first).isNotEqualTo(second)
        }

        @Test
        fun notEqualDifferentSecondTerm() {
            val first = LinearFunction(12.big(), 26.big())
            val second = LinearFunction(12.big(), 16.big())
            assertThat(first).isNotEqualTo(second)
        }
    }

    @Test
    fun toStringTest() {
        assertThat(LinearFunction((-6).big(), 4.big()).toString()).isEqualTo("LinearFunction(a=-6, b=4)")
    }

    private fun Int.big() = this.toBigInteger()
}