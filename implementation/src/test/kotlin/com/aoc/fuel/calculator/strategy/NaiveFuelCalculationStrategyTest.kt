package com.aoc.fuel.calculator.strategy

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class NaiveFuelCalculationStrategyTest {
    @Nested
    @DisplayName("Part 1 Examples")
    inner class ExamplesPartOne {
        @Test
        @DisplayName("Given a mass of 12, when calculating the mass for the module, then it should return 2")
        fun exampleOne() {
            assertThat(NaiveFuelCalculationStrategy().calculateRequirements(12)).isEqualTo(2)
        }

        @Test
        @DisplayName("Given a mass of 14, when calculating the mass for the module, then it should return 2")
        fun exampleTwo() {
            assertThat(NaiveFuelCalculationStrategy().calculateRequirements(14)).isEqualTo(2)
        }

        @Test
        @DisplayName("Given a mass of 1969, when calculating the mass for the module, then it should return 654")
        fun exampleThree() {
            assertThat(NaiveFuelCalculationStrategy().calculateRequirements(1969)).isEqualTo(654)
        }

        @Test
        @DisplayName("Given a mass of 100756, when calculating the mass for the module, then it should return 33583")
        fun exampleFour() {
            assertThat(NaiveFuelCalculationStrategy().calculateRequirements(100756)).isEqualTo(33583)
        }
    }
}