package com.aoc.fuel.calculator

import kotlin.math.floor

class FuelCalculator {
    fun calculateModuleFuel(mass: Int) : Int = (floor(mass.toDouble() / 3) - 2).toInt()

    fun calculateTotalFuelForModule(fuel: List<Int>) : Int = fuel.map { e -> calculateModuleFuel(e) }.sum()

    fun calculateTotalAdditionFuelForModule(fuel: List<Int>) : Int = fuel.map {e -> calculateAdditionalFuel(e) }.sum()

    fun calculateAdditionalFuel(mass: Int) : Int {
        var currentMass = mass
        var additionalFuel = 0

        while (calculateModuleFuel(currentMass) > 0) {
            val moduleFuel = calculateModuleFuel(currentMass)
            additionalFuel += moduleFuel
            currentMass = moduleFuel
        }

        return additionalFuel
    }
}