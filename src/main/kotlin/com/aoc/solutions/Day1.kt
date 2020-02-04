package com.aoc.solutions

import com.aoc.fuel.FuelCalculator
import com.aoc.input.Day
import com.aoc.input.InputReader

fun main() {
    val inputReader = InputReader()
    val fuelCalculator = FuelCalculator()
    val input = inputReader.readInputInteger(Day.from(1))
    println("Solution (Part 1): " + fuelCalculator.calculateTotalFuelForModule(input.values))
    println("Solution (Part 2): " + fuelCalculator.calculateTotalAdditionFuelForModule(input.values))
}
