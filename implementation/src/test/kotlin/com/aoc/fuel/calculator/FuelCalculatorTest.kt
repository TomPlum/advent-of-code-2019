package com.aoc.fuel.calculator

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.Day
import com.aoc.input.InputReader
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class FuelCalculatorTest {
    @Nested
    @DisplayName("Part 1 Examples")
    inner class ExamplesPartOne {
        @Test
        @DisplayName("Given a mass of 12, when calculating the mass for the module, then it should return 2")
        fun exampleOne() {
            assertThat(FuelCalculator().calculateModuleFuel(12)).isEqualTo(2)
        }

        @Test
        @DisplayName("Given a mass of 14, when calculating the mass for the module, then it should return 2")
        fun exampleTwo() {
            assertThat(FuelCalculator().calculateModuleFuel(14)).isEqualTo(2)
        }

        @Test
        @DisplayName("Given a mass of 1969, when calculating the mass for the module, then it should return 654")
        fun exampleThree() {
            assertThat(FuelCalculator().calculateModuleFuel(1969)).isEqualTo(654)
        }

        @Test
        @DisplayName("Given a mass of 100756, when calculating the mass for the module, then it should return 33583")
        fun exampleFour() {
            assertThat(FuelCalculator().calculateModuleFuel(100756)).isEqualTo(33583)
        }
    }

    @Nested
    @DisplayName("Part 2 Examples")
    inner class ExamplesPartTwo {
        @Test
        @DisplayName("Given a module of mass 14, when calculating additional fuel, then it should only return 2")
        fun additionalFuelExampleOne() {
            assertThat(FuelCalculator().calculateAdditionalFuel(14)).isEqualTo(2)
        }

        @Test
        @DisplayName("Given a module of mass 1969, when calculating additional fuel, then it should only return 966")
        fun additionalFuelExampleTwo() {
            assertThat(FuelCalculator().calculateAdditionalFuel(1969)).isEqualTo(966)
        }

        @Test
        @DisplayName("Given a module of mass 100756, when calculating additional fuel, then it should only return 50346")
        fun additionalFuelExampleThree() {
            assertThat(FuelCalculator().calculateAdditionalFuel(100756)).isEqualTo(50346)
        }
    }

    @Nested
    @DisplayName("Day 1 - Solutions")
    inner class Solutions {
        private val input = InputReader.read<Int>(Day(1)).value

        @Test
        @DisplayName("Given the puzzle input, when calculating the total fuel for the module, then it should return 3184233")
        fun partOneSolution() {
            assertThat(FuelCalculator().calculateTotalFuelForModule(input)).isEqualTo(3184233)
        }

        @Test
        @DisplayName("Given then puzzle input, when calculating to the total additional fuel required, then it should return 4773483")
        fun partTwoSolution() {
            assertThat(FuelCalculator().calculateTotalAdditionalFuelForModule(input)).isEqualTo(4773483)
        }
    }
}