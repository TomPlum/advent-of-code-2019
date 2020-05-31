package com.aoc.panel

import com.aoc.math.Point2D

class Panel(private val firstWire: Wire, private val secondWire: Wire) {

    private val intersections = firstWire.path.intersect(secondWire.path)

    /**
     * Calculates the distance between the central port and the closest intersection.
     * @see Point2D.distanceBetween
     */
    fun findIntersectionPointClosestToCentralPort() = intersections.map { it.distanceBetween(Point2D(0, 0)) }.min()

    /**
     * The circuit is timing-sensitive and so the signal delay needs to be minimised.
     * Calculates the shortest combined number of steps to reach an intersection between [firstWire] and [secondWire]
     * @return steps taken
     * @throws IllegalStateException if there are no intersections found between the two wires
     */
    fun findShortestCombinedIntersectionPaths(): Int {
        return intersections.map {
            firstWire.stepsTo(it) + secondWire.stepsTo(it) + 2
        }.min() ?: throw IllegalStateException("No Intersections Found")
    }
}