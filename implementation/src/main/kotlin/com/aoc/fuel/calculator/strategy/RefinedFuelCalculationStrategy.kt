package com.aoc.fuel.calculator.strategy

import com.aoc.fuel.calculator.strategy.FuelCalculationStrategy

/**
 * During the second Go / No Go poll, the Elf in charge of the Rocket Equation Double-Checker stops the launch sequence.
 * Apparently, you forgot to include additional fuel for the fuel you just added.
 * The [RefinedFuelCalculationStrategy] accounts for the weight of the fuel itself.
 */
class RefinedFuelCalculationStrategy : FuelCalculationStrategy() {
    /**
     * Fuel itself requires fuel just like a module - take its [mass], divide by three, round down, and subtract 2.
     * However, that fuel also requires fuel, and that fuel requires fuel, and so on.
     * Any [mass] that would require negative fuel should instead be treated as if it requires zero fuel.
     * @return additional fuel quantity
     */
    override fun calculateRequirements(mass: Int): Int {
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