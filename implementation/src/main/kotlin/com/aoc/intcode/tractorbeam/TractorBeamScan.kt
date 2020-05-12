package com.aoc.intcode.tractorbeam

import map.AdventMap
import math.Point2D
import java.lang.IllegalArgumentException

class TractorBeamScan() : AdventMap<DroneState>() {

    companion object {
        fun fromData(data: List<String>): TractorBeamScan {
            val scan = TractorBeamScan()
            data.forEachIndexed { y, line ->
                line.forEachIndexed { x, char ->
                    scan.addTile(Point2D(x, y), DroneState(char.toString()))
                }
            }
            return scan
        }
    }

    fun findSquareClosestToEmitter(squareSize: Long): Long {
        (0 .. yMax()).forEach { y ->
            val rowCoordinates = mutableSetOf<Point2D>()
            (0 .. xMax()).forEach { x ->
                rowCoordinates.add(Point2D(x, y))
            }

            val row = filterPoints(rowCoordinates).filterValues { it.isPropagating() }

            val beamWidth = row.count()

            if (beamWidth >= squareSize) {
                row.keys.forEachIndexed { i, pos ->
                    if (pointHasBeamColumn(pos, squareSize.toInt()) && pointHasBeamRow(pos, squareSize.toInt())) {
                        return ((pos.x * 10000) + pos.y).toLong()
                    }
                }
            }
        }
        throw IllegalArgumentException("Could not find beam square of size $squareSize")
    }

    private fun pointHasBeamColumn(topPosition: Point2D, quantity: Int): Boolean {
        val columnCoordinates = mutableSetOf<Point2D>()
        (1 until quantity).forEach { y -> columnCoordinates.add(Point2D(topPosition.x, topPosition.y + y)) }
        return filterPoints(columnCoordinates).values.all { it.isPropagating() }
    }

    private fun pointHasBeamRow(topPosition: Point2D, quantity: Int): Boolean {
        val rowCoordinates = mutableSetOf<Point2D>()
        (1 until quantity).forEach { x -> rowCoordinates.add(Point2D(topPosition.x + x, topPosition.y)) }
        return filterPoints(rowCoordinates).values.all { it.isPropagating() }
    }

    /**
     * @return The number of points directly effected by the Tractor Beam
     */
    fun getPointsAffectedByBeam(): Int = filterTiles { it.isPropagating() }.count()

    /**
     * @return The total area of the scan, including every point scanned by the [DroneSystem]
     */
    fun getArea() = tileQuantity()
}