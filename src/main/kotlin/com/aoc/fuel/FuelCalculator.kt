package com.aoc.fuel

import kotlin.math.floor;

class FuelCalculator {
    fun calculateModuleFuel(mass: Int) : Int = (floor(mass.toDouble() / 3) - 2).toInt()

    fun calculateTotalFuelForModule(fuel: List<Int>) : Int = fuel.map { e -> calculateModuleFuel(e) }.sum()
}