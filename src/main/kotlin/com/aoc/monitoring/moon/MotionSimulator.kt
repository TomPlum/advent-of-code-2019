package com.aoc.monitoring.moon

class MotionSimulator(private val moons: Set<Moon>) {

    fun simulate(timeSteps: Int) = (0 until timeSteps).forEach { timeStep ->
        val uniquePairs = mutableListOf<Pair<Moon, Moon>>()
        moons.forEach { sourceMoon ->
            moons.filter { it != sourceMoon }.forEach { targetMoon ->
                if (!uniquePairs.contains(Pair(sourceMoon, targetMoon)) && !uniquePairs.contains(Pair(targetMoon, sourceMoon))) {
                    uniquePairs.add(Pair(sourceMoon, targetMoon))
                }
            }
        }

        uniquePairs.forEach {it.apply { it.first.applyGravity(it.second) }}

        moons.forEach { it.applyVelocity() }

        println("\nAfter ${timeStep + 1} steps:")
        moons.forEach { println(it.toString()) }
    }

    fun calculateTotalSystemEnergy() = moons.toList().map { it.calculateTotalEnergy() }.sum()

}