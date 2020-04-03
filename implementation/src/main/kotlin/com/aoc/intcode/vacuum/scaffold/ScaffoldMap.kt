package com.aoc.intcode.vacuum.scaffold

import math.Point2D
import map.Map

class ScaffoldMap(initialData: List<Long>) : Map<ScaffoldMapTile>() {

    init {
        var x = 0
        var y = 0
        initialData.forEach { datum ->
            if (datum == 10L) {
                x = 0
                y++
            } else {
                addTile(Point2D(x, y), ScaffoldMapTile(datum.toChar()))
                x++
            }
        }
        findIntersection().forEach { position -> addTile(position, ScaffoldMapTile.intersection()) }
    }

    fun calculateAlignmentParameterSum() = getIntersections().sumBy { it.x * it.y }

    private fun getIntersections() = getData().filter { it.value.isIntersection() }.keys.toSet()

    private fun findIntersection(): Set<Point2D> = getData()
            .filterValues { it.isScaffold() }
            .filter { datum ->
                datum.key.adjacentPoints().all { point ->
                    getData().getOrDefault(point, ScaffoldMapTile.empty()).isScaffold()
                }
            }.keys.toSet()

}