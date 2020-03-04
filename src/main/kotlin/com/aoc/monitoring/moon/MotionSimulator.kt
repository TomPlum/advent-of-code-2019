package com.aoc.monitoring.moon

import com.aoc.math.Formulae

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

    fun simulate(timeSteps: Int) = (0 until timeSteps).forEach { timeStep ->

        uniqueMoonPairs.forEach { it.first.applyGravity(it.second) }

        moons.forEach { it.applyVelocity() }

        //println("\nAfter ${timeStep + 1} steps:")
        //moons.forEach { println(it.toString()) }
    }

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

        //print("$xPeriod, $yPeriod, $zPeriod")

        return Formulae.lcm(listOf(xPeriod, yPeriod, zPeriod))
    }

    fun calculateTotalSystemEnergy() = moons.toList().map { it.calculateTotalEnergy() }.sum()

}