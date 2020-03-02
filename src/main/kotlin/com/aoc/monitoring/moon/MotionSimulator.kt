package com.aoc.monitoring.moon

class MotionSimulator(private val moons: Set<Moon>) {

    fun simulate(timeSteps: Int) = (0..timeSteps).forEach { timeStep ->
        println("\nAfter $timeStep steps:")
        moons.forEach { println(it.toString()) }

        moons.forEach { sourceMoon ->
            moons.filter { it != sourceMoon }.forEach { targetMoon -> sourceMoon.applyGravity(targetMoon) }
        }

        moons.forEach { it.applyVelocity() }
    }

    fun calculateTotalSystemEnergy() = moons.toList().map { it.calculateTotalEnergy() }.sum()

}