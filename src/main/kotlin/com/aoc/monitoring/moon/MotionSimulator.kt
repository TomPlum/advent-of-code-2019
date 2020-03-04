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

        println("\nAfter ${timeStep + 1} steps:")
        moons.forEach { println(it.toString()) }
    }

    fun determineTimeStepsUntilHistoryRepeats(): Int {
        var xPeriod = 0
        var yPeriod = 0
        var zPeriod = 0

        var timeStep = 0

        while (xPeriod == 0 || yPeriod == 0 || zPeriod == 0) {
            simulate(1)
            timeStep++

            //Finding when all moons have zero velocity and multiplying period by 2 should find the answer
           /* if (xPeriod == 0 && moons.all { it.velocity.x == 0 }) xPeriod = timeStep
            if (yPeriod == 0 && moons.all { it.velocity.y == 0 }) yPeriod = timeStep
            if (zPeriod == 0 && moons.all { it.velocity.z == 0 }) zPeriod = timeStep*/

            if (xPeriod == 0 && initialState.zip(moons).all { it.first.hasSamePositionVelocityX(it.second) }) xPeriod = timeStep
            if (yPeriod == 0 && initialState.zip(moons).all { it.first.hasSamePositionVelocityY(it.second) }) yPeriod = timeStep
            if (zPeriod == 0 && initialState.zip(moons).all { it.first.hasSamePositionVelocityZ(it.second) }) zPeriod = timeStep

           /* initialState.zip(moons).forEach {
                if (xPeriod == 0 && it.first.hasSamePositionVelocityX(it.second)) xPeriod = timeStep
                if (yPeriod == 0 && it.first.hasSamePositionVelocityY(it.second)) yPeriod = timeStep
                if (zPeriod == 0 && it.first.hasSamePositionVelocityZ(it.second)) zPeriod = timeStep
            }*/

        }

        print("$xPeriod, $yPeriod, $zPeriod")

        return Formulae.lcm(listOf(xPeriod, yPeriod, zPeriod)) * 2
    }

    fun calculateTotalSystemEnergy() = moons.toList().map { it.calculateTotalEnergy() }.sum()

}