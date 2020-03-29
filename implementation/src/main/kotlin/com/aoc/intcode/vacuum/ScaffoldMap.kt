package com.aoc.intcode.vacuum

import math.Point2D

class ScaffoldMap(initialData: List<Long>) {
    private val data = mutableMapOf<Point2D, ScaffoldMapTile>()

    init {
        var x = 0
        var y = 0
        initialData.forEach { datum ->
            if (datum == 10L) {
                x = 0
                y++
            }
            this.data[Point2D(x, y)] = ScaffoldMapTile(datum.toChar())
            x++
        }
    }

    fun update(input: Char, position: Point2D) = data.put(position, ScaffoldMapTile(input))

    override fun toString(): String {
        val xMin = data.keys.minBy { it.x }!!.x
        val yMax = data.keys.maxBy { it.y }!!.y

        val xMax = data.keys.maxBy { it.x }!!.x
        val yMin = data.keys.minBy { it.y }!!.y

        return (yMin..yMax).joinToString("\n") { y ->
            (xMin..xMax).joinToString(separator = " ") { x ->
                data.getOrDefault(Point2D(x, y), ScaffoldMapTile('!')).toString()
            }
        }
    }
}