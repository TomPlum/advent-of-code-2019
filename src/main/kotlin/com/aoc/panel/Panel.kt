package com.aoc.panel

import com.aoc.value.PanelCoordinate
import kotlin.math.abs
import kotlin.math.exp
import kotlin.math.sqrt

class Panel constructor(private val firstWire: Wire, private val secondWire: Wire) {
    fun findIntersectionPointClosestToCentralPort(): Int? {
        val firstWirePath: List<PanelCoordinate> = getWireCoordinates(firstWire)
        val secondWirePath: List<PanelCoordinate> = getWireCoordinates(secondWire)
        println(firstWirePath)
        println(secondWirePath)

        val intersections: List<PanelCoordinate> = getIntersections(firstWirePath, secondWirePath)

        return intersections.map { calculateManhattanDistance(it.x, it.y) }.min()
    }

    private fun getIntersections(firstPath: List<PanelCoordinate>, secondPath: List<PanelCoordinate>): List<PanelCoordinate> {
        val intersections: MutableList<PanelCoordinate> = mutableListOf()
        firstPath.forEach { pointOne ->
            if (secondPath.contains(pointOne)) intersections.add(pointOne)
        }
        return intersections
    }

    private fun getWireCoordinates(wire: Wire): List<PanelCoordinate> {
        val path: MutableList<PanelCoordinate> = mutableListOf()
        var coordinate = PanelCoordinate()
        wire.segments.forEach { segment ->
            (0 until segment.length).forEach { _ ->
                coordinate = updateOrdinate(segment, coordinate)
                path.add(PanelCoordinate(coordinate.x, coordinate.y))
            }
        }
        return path
    }

    private fun updateOrdinate(segment: WireSegment, wireCoordinate: PanelCoordinate): PanelCoordinate {
        when (segment.direction) {
            "U" -> wireCoordinate.y += 1
            "R" -> wireCoordinate.x += 1
            "D" -> wireCoordinate.y -= 1
            "L" -> wireCoordinate.x -= 1
        }
        return wireCoordinate
    }

    private fun calculateManhattanDistance(x1: Int, y1: Int) = abs(x1 - 0) + abs(y1 - 0)

    private fun calculateEuclideanDistance(x1: Int, y1: Int): Int = sqrt(exp((x1 - 0).toDouble()) + exp((y1 - 0).toDouble())).toInt()
}