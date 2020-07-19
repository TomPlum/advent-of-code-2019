package com.aoc.solutions

import com.aoc.fuel.calculator.FuelCalculator
import com.aoc.Day
import com.aoc.input.InputReader

fun main() {
    val input = InputReader.read<Int>(Day(1))
    println("Solution (Part 1): ${FuelCalculator().calculateTotalFuelForModule(input.value)} Fuel")
    println("Solution (Part 2): ${FuelCalculator().calculateTotalAdditionFuelForModule(input.value)} Fuel")
}
