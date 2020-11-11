package com.aoc.fuel.calculator.strategy

/**
 * The [NaiveFuelCalculationStrategy] is the first iteration of the [FuelCalculationStrategy] that was tested before
 * the Elf in charge of the rocket equation double checked it. It does not account for the weight of the fuel.
 * @see RefinedFuelCalculationStrategy
 */
class NaiveFuelCalculationStrategy : FuelCalculationStrategy() {
    /**
     * Calculates the sum of the fuel requirements for the [mass] of all of the modules on the spacecraft.
     */
    override fun calculateRequirements(mass: Int): Int = calculateModuleFuel(mass)
}