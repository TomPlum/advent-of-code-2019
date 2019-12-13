package com.aoc.solutions

import com.aoc.common.InputReader
import com.aoc.fuel.FuelCalculator
import com.aoc.value.Day

fun main() {
    val inputReader = InputReader()
    val fuelCalculator = FuelCalculator()
    val input = inputReader.readInputInteger(Day.from(1))
    println("Solution: " + fuelCalculator.calculateTotalFuelForModule(input.values))
}
