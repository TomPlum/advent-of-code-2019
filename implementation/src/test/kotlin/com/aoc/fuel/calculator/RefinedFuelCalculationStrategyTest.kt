package com.aoc.fuel.calculator

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class RefinedFuelCalculationStrategyTest {
    @Nested
    @DisplayName("Part 2 Examples")
    inner class ExamplesPartTwo {
        @Test
        @DisplayName("Given a module of mass 14, when calculating additional fuel, then it should only return 2")
        fun additionalFuelExampleOne() {
            assertThat(RefinedFuelCalculationStrategy().calculateRequirements(14)).isEqualTo(2)
        }

        @Test
        @DisplayName("Given a module of mass 1969, when calculating additional fuel, then it should only return 966")
        fun additionalFuelExampleTwo() {
            assertThat(RefinedFuelCalculationStrategy().calculateRequirements(1969)).isEqualTo(966)
        }

        @Test
        @DisplayName("Given a module of mass 100756, when calculating additional fuel, then it should only return 50346")
        fun additionalFuelExampleThree() {
            assertThat(RefinedFuelCalculationStrategy().calculateRequirements(100756)).isEqualTo(50346)
        }
    }
}