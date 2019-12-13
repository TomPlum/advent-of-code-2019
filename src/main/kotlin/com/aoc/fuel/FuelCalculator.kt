package com.aoc.fuel

class FuelCalculator {
    fun calculateModuleFuel(mass: Int) : Int = (kotlin.math.floor(mass.toDouble() / 3) - 2).toInt()

    fun calculateTotalFuelForModule(fuel: List<Int>) : Int = fuel.sum()
}