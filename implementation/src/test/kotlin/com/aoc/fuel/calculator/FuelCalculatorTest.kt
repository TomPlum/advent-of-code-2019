package com.aoc.fuel.calculator

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.Day
import com.aoc.fuel.calculator.strategy.NaiveFuelCalculationStrategy
import com.aoc.fuel.calculator.strategy.RefinedFuelCalculationStrategy
import com.aoc.input.InputReader
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class FuelCalculatorTest {
    private val input = InputReader.read<Int>(Day(1)).value

    @Test
    @DisplayName("Given the puzzle input, when calculating the total fuel for the module, then it should return 3184233")
    fun partOneSolution() {
        val calculator = FuelCalculator(NaiveFuelCalculationStrategy())
        val requirementsSum = calculator.calculateFuelRequirementsSum(input)
        assertThat(requirementsSum).isEqualTo(3184233)
    }

    @Test
    @DisplayName("Given then puzzle input, when calculating to the total additional fuel required, then it should return 4773483")
    fun partTwoSolution() {
        val calculator = FuelCalculator(RefinedFuelCalculationStrategy())
        val requirementsSum = calculator.calculateFuelRequirementsSum(input)
        assertThat(requirementsSum).isEqualTo(4773483)
    }
}