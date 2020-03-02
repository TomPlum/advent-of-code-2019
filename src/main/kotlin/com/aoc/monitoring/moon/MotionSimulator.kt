package com.aoc.monitoring.moon

import kotlin.math.abs

/**
 * KE = Kinetic Energy
 * GPE = Gravitational Potential Energy
 */
class MotionSimulator(private val moons: Set<Moon>) {

    fun simulate(timeSteps: Int) {}

    /**
     * The total energy of a given moon is equals to its GPE multiplied by its KE.
     * A moons GPE is calculated by the sum of its absolute positional values.
     * A moons KE is calculated by the sum of the absolute velocity values.
     *
     * @see Point3D
     * @return The total energy of the system calculated by the cumulative sum of the products of KE and GPE.
     */
    fun calculateTotalSystemEnergy() = moons.toList().map {
        val position = it.position
        val velocity = it.velocity
        val potential = abs(position.x) + abs(position.y) + abs(position.z)
        val kinetic = abs(velocity.x) + abs(velocity.y) + abs(velocity.z)
        return@map potential * kinetic
    }.sum()

}