package com.aoc.panel

import kotlin.math.abs

class Panel constructor(private val firstWire: Wire, private val secondWire: Wire) {
    fun findIntersectionPointClosestToCentralPort(): Int? {
        return firstWire.getPath().intersect(secondWire.getPath()).toList().map { manhattanDistance(it.x, it.y) }.min()
    }

    fun findShortestCombinedIntersectionPaths(): Int = 0

    private fun manhattanDistance(x1: Int, y1: Int) = abs(x1) + abs(y1)
}