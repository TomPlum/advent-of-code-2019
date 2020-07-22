package com.aoc.fuel.calculator

import com.aoc.fuel.calculator.strategy.FuelCalculationStrategy

/**
 * Part of the launch preparations to save Santa requires the ship to be fueled.
 *
 * At the first Go / No Go poll, every Elf is Go until the Fuel Counter-Upper.
 * They haven't determined the amount of fuel required yet.
 *
 * @param strategy The strategy used to calculate the sum of the fuel requirements.
 */
class FuelCalculator(private val strategy: FuelCalculationStrategy) {

    /**
     * Calculates the sum of the fuel requirements for all of the [moduleMasses] on the spacecraft.
     */
    fun calculateFuelRequirementsSum(moduleMasses: List<Int>) = moduleMasses.sumBy { mass ->
        strategy.calculateRequirements(mass)
    }

}