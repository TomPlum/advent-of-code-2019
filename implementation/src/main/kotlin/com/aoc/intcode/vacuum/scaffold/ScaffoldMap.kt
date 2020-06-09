package com.aoc.intcode.vacuum.scaffold

import com.aoc.math.Point2D
import com.aoc.map.AdventMap2D

class ScaffoldMap(initialData: List<Long>) : AdventMap2D<ScaffoldMapTile>() {

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

    private fun getIntersections() = filterTiles { it.isIntersection() }.keys.toSet()

    private fun findIntersection(): Set<Point2D> = filterTiles { it.isScaffold() }
            .filter { datum ->
                datum.key.adjacentPoints().all { point ->
                    getTile(point, ScaffoldMapTile.empty()).isScaffold()
                }
            }.keys.toSet()

}