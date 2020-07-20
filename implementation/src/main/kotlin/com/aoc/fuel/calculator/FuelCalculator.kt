package com.aoc.fuel.calculator

import kotlin.math.floor

/**
 * Part of the launch preparations to save Santa requires the ship to be fueled.
 *
 * At the first Go / No Go poll, every Elf is Go until the Fuel Counter-Upper.
 * They haven't determined the amount of fuel required yet.
 *
 * //TODO: Split Part 1 & 2 into strategies. A Naive and a Refined.
 */
class FuelCalculator {
    /**
     * Fuel required to launch a given module is based on its [mass].
     * Specifically, to find the fuel required for a module, take its mass, divide by three, round down, and subtract 2.
     * @return fuel quantity
     */
    fun calculateModuleFuel(mass: Int) : Int = (floor(mass.toDouble() / 3) - 2).toInt()

    /**
     * Calculates the sum of the [fuel] requirements for all of the modules on the spacecraft.
     */
    fun calculateTotalFuelForModule(fuel: List<Int>) : Int = fuel.sumBy { calculateModuleFuel(it) }

    /**
     * Calculates the sum of the [fuel] requirements for all of the modules on the spacecraft when also taking into
     * account the mass of the added fuel.
     */
    fun calculateTotalAdditionalFuelForModule(fuel: List<Int>) : Int = fuel.sumBy { calculateAdditionalFuel(it) }

    /**
     * Fuel itself requires fuel just like a module - take its mass, divide by three, round down, and subtract 2.
     * However, that fuel also requires fuel, and that fuel requires fuel, and so on.
     * Any mass that would require negative fuel should instead be treated as if it requires zero fuel.
     * @return additional fuel quantity
     */
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