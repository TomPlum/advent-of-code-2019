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
            } else {
                this.data[Point2D(x, y)] = ScaffoldMapTile(datum.toChar())
                x++
            }
        }
        findIntersection().forEach { position -> update(position, ScaffoldMapTile.intersection()) }
    }

    fun calculateAlignmentParameterSum() = getIntersections().sumBy { it.x * it.y }

    private fun getIntersections() = data.filter { it.value.isIntersection() }.keys.toSet()

    private fun findIntersection(): Set<Point2D> = data
            .filterValues { it.isScaffold() }
            .filter { datum -> datum.key.adjacentPoints().all { point -> data.getOrDefault(point, ScaffoldMapTile.empty()).isScaffold() } }
            .keys.toSet()

    private fun update(position: Point2D, type: ScaffoldMapTile) = data.put(position, type)

    override fun toString(): String {
        val xMin = data.keys.minBy { it.x }!!.x
        val yMax = data.keys.maxBy { it.y }!!.y

        val xMax = data.keys.maxBy { it.x }!!.x
        val yMin = data.keys.minBy { it.y }!!.y

        return (yMin..yMax).joinToString("\n") { y ->
            (xMin..xMax).joinToString(separator = " ") { x ->
                data.getOrDefault(Point2D(x, y), ScaffoldMapTile.unknown()).toString()
            }
        }
    }
}