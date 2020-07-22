package com.aoc.fuel.calculator.strategy

import kotlin.math.floor

abstract class FuelCalculationStrategy {
    /**
     * The strategy contract.
     * @param mass The mass of the module whose fuel requirements are being calculated.
     * @return The quantity of fuel required for the given module [mass].
     */
    abstract fun calculateRequirements(mass: Int): Int

    /**
     * Fuel required to launch a given module is based on its [mass].
     * Specifically, to find the fuel required for a module, take its mass, divide by three, round down, and subtract 2.
     * @return fuel quantity
     */
    protected fun calculateModuleFuel(mass: Int) : Int = (floor(mass.toDouble() / 3) - 2).toInt()
}