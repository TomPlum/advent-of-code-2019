package com.aoc.fuel.calculator

class NaiveFuelCalculationStrategy : FuelCalculationStrategy() {
    /**
     * Calculates the sum of the fuel requirements for the [mass] of all of the modules on the spacecraft.
     */
    override fun calculateRequirements(mass: Int): Int = calculateModuleFuel(mass)
}