package com.aoc.intcode.tractorbeam

import map.AdventMap
import math.Point2D
import java.lang.IllegalArgumentException

class TractorBeamScan : AdventMap<DroneState>() {

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
                row.keys.forEach { pos ->
                    if (pointHasBeamColumn(pos, squareSize.toInt()) && pointHasBeamRow(pos, squareSize.toInt())) {
                        return ((pos.x * 10000) + pos.y).toLong()
                    }
                }
            }
        }
        throw IllegalArgumentException("Could not find beam square of size $squareSize")
    }

    private fun pointHasBeamColumn(topPosition: Point2D, height: Int): Boolean {
        val lastPointIsBeam = getTile(Point2D(topPosition.x, topPosition.y + height - 1)).isPropagating()
        val oneAfterIsNot = getTile(Point2D(topPosition.x, topPosition.y + height)).isStationary()
        return lastPointIsBeam && oneAfterIsNot
    }

    private fun pointHasBeamRow(topPosition: Point2D, width: Int): Boolean {
        val lastPointIsBeam = getTile(Point2D(topPosition.x + width - 1, topPosition.y)).isPropagating()
        val oneAfterIsNot = getTile(Point2D(topPosition.x + width, topPosition.y)).isStationary()
        return lastPointIsBeam && oneAfterIsNot
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