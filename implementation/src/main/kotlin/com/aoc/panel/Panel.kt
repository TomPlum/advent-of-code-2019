package com.aoc.panel

import math.Point2D

class Panel constructor(private val firstWire: Wire, private val secondWire: Wire) {
    fun findIntersectionPointClosestToCentralPort(): Int? = firstWire.getPath()
            .intersect(secondWire.getPath()).toList()
            .map { it.distanceBetween(Point2D(0,0)) }
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

}