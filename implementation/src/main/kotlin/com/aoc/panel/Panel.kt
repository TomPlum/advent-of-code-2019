package com.aoc.panel

import kotlin.math.abs

class Panel constructor(private val firstWire: Wire, private val secondWire: Wire) {
    fun findIntersectionPointClosestToCentralPort(): Int? = firstWire.getPath()
            .intersect(secondWire.getPath()).toList()
            .map { manhattanDistance(it.x, it.y) }
            .min()


    fun findShortestCombinedIntersectionPaths(): Int? {
        val intersectionDistances = mutableListOf<Int>()
        firstWire.getPath().forEachIndexed { i, w1 ->
            secondWire.getPath().forEachIndexed { j, w2 ->
                if (w1 == w2) intersectionDistances.add(i + j)
            }
        }

        return intersectionDistances.min()?.plus(2)
    }

    private fun manhattanDistance(x1: Int, y1: Int) = abs(x1) + abs(y1)
}