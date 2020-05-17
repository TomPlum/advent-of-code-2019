package com.aoc.tractorbeam

import log.AdventLogger
import map.AdventMap
import math.Point2D

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
        (yMin() .. yMax()).forEach { y ->
            val rowCoordinates = mutableSetOf<Point2D>()
            (xMin() .. xMax()).forEach { x ->
                rowCoordinates.add(Point2D(x, y))
            }

            val row = filterPoints(rowCoordinates).filterValues { it.isPropagating() }

            val beamWidth = row.count()

            if (beamWidth >= squareSize) {
                row.keys.forEach { pos ->
                    if (pointHasBeamColumn(pos, squareSize.toInt()) && pointHasBeamRow(pos, squareSize.toInt())) {
                        AdventLogger.debug("Found Ship @ $pos")
                        return ((pos.x * 10000) + pos.y).toLong()
                    }
                }
            }
        }
        throw ShipNotFound(squareSize)
    }

    private fun pointHasBeamColumn(topPosition: Point2D, height: Int): Boolean {
        val default = DroneState.stationary()
        return getTile(Point2D(topPosition.x, topPosition.y + height - 1), default).isPropagating()
    }

    private fun pointHasBeamRow(topPosition: Point2D, width: Int): Boolean {
        val default = DroneState.stationary()
        return getTile(Point2D(topPosition.x + width - 1, topPosition.y), default).isPropagating()
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