package com.aoc.intcode.tractorbeam

import log.AdventLogger
import map.AdventMap
import math.Point2D

class TractorBeamScan : AdventMap<DroneState>() {

    private var lastBlockMaxY: Int? = null
    private var lastRowBeamStartX: Int = 0
    private var lastRowBeamEndX: Int? = null

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

    fun findSquareClosestToEmitter(squareSize: Int): Long {
        val yMin = (lastBlockMaxY ?: squareSize) - squareSize
        val yMax = if (lastBlockMaxY == null) squareSize else lastBlockMaxY!! + squareSize

        (yMin .. yMax).forEach { y ->
            val rowCoordinates = mutableSetOf<Point2D>()

            val xMax = (lastRowBeamEndX ?: squareSize) + 5
            (lastRowBeamStartX ..xMax).forEach { x ->
                rowCoordinates.add(Point2D(x, y))
            }

            val row = filterPoints(rowCoordinates).filterValues { it.isPropagating() }
            lastRowBeamStartX = row.minBy { it.key.x }?.key?.x ?: lastRowBeamStartX
            lastRowBeamEndX = row.maxBy { it.key.x }?.key?.x ?: lastRowBeamEndX

            val beamWidth = row.count()

            if (beamWidth >= squareSize) {
                row.keys.forEach { pos ->
                    if (pointHasBeamColumn(pos, squareSize) && pointHasBeamRow(pos, squareSize)) {
                        AdventLogger.debug("Found Ship @ $pos")
                        return ((pos.x * 10000) + pos.y).toLong()
                    }
                }
            }
        }
        if (lastBlockMaxY == null) lastBlockMaxY = squareSize else lastBlockMaxY = lastBlockMaxY!! + squareSize
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