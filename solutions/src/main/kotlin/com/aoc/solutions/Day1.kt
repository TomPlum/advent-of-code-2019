package com.aoc.solutions

import com.aoc.Day
import com.aoc.fuel.calculator.FuelCalculator
import com.aoc.fuel.calculator.NaiveFuelCalculationStrategy
import com.aoc.fuel.calculator.RefinedFuelCalculationStrategy
import com.aoc.input.InputReader

fun main() {
    val input = InputReader.read<Int>(Day(1)).value

    val naiveCalculator = FuelCalculator(NaiveFuelCalculationStrategy())
    println("Solution (Part 1): ${naiveCalculator.calculateFuelRequirementsSum(input)} Fuel")

    val refinedCalculator = FuelCalculator(RefinedFuelCalculationStrategy())
    println("Solution (Part 2): ${refinedCalculator.calculateFuelRequirementsSum(input)} Fuel")
}
