package com.aoc.panel

import com.aoc.math.Point2D

data class Wire(private val input: String) {
    val segments: List<WireSegment> = input.split(",").map { WireSegment(it.substring(0, 1), it.substring(1).toInt()) }

    val path = mutableListOf<Point2D>()

    init {
        var coordinate = Point2D(0, 0)
        segments.forEach { segment ->
            (0 until segment.length).forEach { _ ->
                coordinate = coordinate.shift(segment.direction)
                path.add(Point2D(coordinate.x, coordinate.y))
            }
        }
    }

    fun stepsTo(position: Point2D): Int {
        val steps = path.indexOf(position)
        if (steps == -1) throw IllegalArgumentException("$this does not contains $position")
        return steps
    }

}