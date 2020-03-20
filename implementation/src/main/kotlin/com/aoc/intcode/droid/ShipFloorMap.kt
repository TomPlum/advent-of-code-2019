package com.aoc.intcode.droid

import math.Point2D

class ShipFloorMap {
    val data: MutableMap<Point2D, ShipFloorTile> = mutableMapOf()

    init {
        updateTile(Point2D(0, 0), ShipFloorTile.DROID)
    }

    fun updateTile(position: Point2D, type: ShipFloorTile) {
        data[position] = type
    }

    fun hasRecorded(position: Point2D) = data.containsKey(position)

    fun tileType(position: Point2D) = data.getOrDefault(position, ShipFloorTile.UNKNOWN)

    override fun toString(): String {
        val xMin = data.keys.minBy { it.x }!!.x
        val yMax = data.keys.maxBy { it.y }!!.y

        val xMax = data.keys.maxBy { it.x }!!.x
        val yMin = data.keys.minBy { it.y }!!.y

        return (yMin..yMax).joinToString("\n") { y ->
            (xMin..xMax).joinToString(separator = " ") { x ->
                data.getOrDefault(Point2D(x, y), ShipFloorTile.UNKNOWN).toString()
            }
        }
    }
}