package com.aoc.monitoring.moon

import math.Formulae

class MotionSimulator(private val moons: Set<Moon>) {
    private val initialState: Set<Moon> = moons.map { it.copy() }.toSet()
    private val uniqueMoonPairs: MutableList<Pair<Moon, Moon>> = mutableListOf()

    init {
        moons.forEach { sourceMoon ->
            moons.filter { it != sourceMoon }.forEach { targetMoon ->
                if (!uniqueMoonPairs.contains(Pair(sourceMoon, targetMoon)) && !uniqueMoonPairs.contains(Pair(targetMoon, sourceMoon))) {
                    uniqueMoonPairs.add(Pair(sourceMoon, targetMoon))
                }
            }
        }
    }

    /**
     * For the given number of [timeSteps] the gravity and velocity is applied to each of the [moons].
     * Gravity is applied only to the [uniqueMoonPairs], whereas Velocity is applied to each of the [moons].
     * @see [Moon.applyGravity]
     * @see [Moon.applyVelocity]
     */
    fun simulate(timeSteps: Int) = repeat( (0 until timeSteps).count() ) {
        uniqueMoonPairs.forEach { it.first.applyGravity(it.second) }
        moons.forEach { it.applyVelocity() }
    }


    /**
     * Calculates the number of time steps until all of the moons are aligned in the exact same [Point3D] positions
     * and [Velocity3D] values. This is the point in which history repeats itself. It is calculated by finding the
     * 'period' of each axis of all the moons. Each time a [Moon] has the same [Point3D] and [Velocity3D] axis
     * respectively as the initial state, it is recorded and the simulation is ran for one more time step.
     * Once all three axis periods have been found, the lowest common multiple of these values is equal to the time
     * step in which all four moons are in their initial state again.
     */
    fun determineTimeStepsUntilHistoryRepeats(): Long {
        var xPeriod = 0L
        var yPeriod = 0L
        var zPeriod = 0L

        var timeStep = 0L

        while (xPeriod == 0L || yPeriod == 0L || zPeriod == 0L) {
            simulate(1)
            timeStep++

            if (xPeriod == 0L && initialState.zip(moons).all { it.first.hasSamePositionVelocityX(it.second) }) xPeriod = timeStep

            if (yPeriod == 0L && initialState.zip(moons).all { it.first.hasSamePositionVelocityY(it.second) }) yPeriod = timeStep

            if (zPeriod == 0L && initialState.zip(moons).all { it.first.hasSamePositionVelocityZ(it.second) }) zPeriod = timeStep

        }

        return Formulae.lcm(listOf(xPeriod, yPeriod, zPeriod))
    }

    /**
     * Calculates the total energy of the system in its current state.
     * @see [Moon.calculateTotalEnergy]
     */
    fun calculateTotalSystemEnergy() = moons.toList().map { it.calculateTotalEnergy() }.sum()

}