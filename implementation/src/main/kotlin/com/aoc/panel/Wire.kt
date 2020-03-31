package com.aoc.panel

import math.Point2D

class Wire constructor(input: String) {
    val segments: List<WireSegment> = input.split(",").map { WireSegment(it.substring(0, 1), it.substring(1).toInt()) }

    fun getPath(): List<Point2D> {
        val path: MutableList<Point2D> = mutableListOf()
        var coordinate = Point2D(0,0)
        segments.forEach { segment ->
            (0 until segment.length).forEach { _ ->
                coordinate = coordinate.shift(segment.direction)
                path.add(Point2D(coordinate.x, coordinate.y))
            }
        }
        return path
    }

}