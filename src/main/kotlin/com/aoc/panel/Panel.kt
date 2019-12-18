package com.aoc.panel

import com.aoc.value.Point
import kotlin.math.abs

class Panel constructor(private val firstWire: Wire, private val secondWire: Wire) {
    fun findIntersectionPointClosestToCentralPort(): Int? {
        val firstWirePath: List<Point> = getWireCoordinates(firstWire)
        val secondWirePath: List<Point> = getWireCoordinates(secondWire)

        val intersections: List<Point> = getIntersections(firstWirePath, secondWirePath)

        return intersections.map { calculateManhattanDistance(it.x, it.y) }.min()
    }

    private fun getIntersections(firstPath: List<Point>, secondPath: List<Point>): List<Point> {
        val intersections: MutableList<Point> = mutableListOf()
        firstPath.forEach { pointOne ->
            if (secondPath.contains(pointOne)) intersections.add(pointOne)
        }
        return intersections
    }

    private fun getWireCoordinates(wire: Wire): List<Point> {
        val path: MutableList<Point> = mutableListOf()
        var coordinate = Point()
        wire.segments.forEach { segment ->
            coordinate = updateOrdinate(segment, coordinate)
            path.add(Point(coordinate.x, coordinate.y))
        }
        return path
    }

    private fun updateOrdinate(segment: WireSegment, wireCoordinate: Point): Point {
        when (segment.direction) {
            "U" -> wireCoordinate.y += segment.length
            "R" -> wireCoordinate.x += segment.length
            "D" -> wireCoordinate.y -= segment.length
            "L" -> wireCoordinate.x -= segment.length
        }
        return wireCoordinate
    }

    private fun calculateManhattanDistance(x1: Int, y1: Int) = abs(x1 - 0) + abs(y1 - 0)
}