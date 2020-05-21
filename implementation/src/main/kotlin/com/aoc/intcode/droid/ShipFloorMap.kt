package com.aoc.intcode.droid

import map.AdventMap
import math.Point2D

class ShipFloorMap : AdventMap<ShipFloorTile>() {

    init {
        addTile(Point2D(0, 0), ShipFloorTile.DROID)
    }

    fun tileType(position: Point2D) = getTile(position, ShipFloorTile.UNKNOWN)

    fun oxygenateShipAt(position: Point2D) = addTile(position, ShipFloorTile.OXYGENATED)

    fun addShipTile(position: Point2D, tile: ShipFloorTile) = addTile(position, tile)

    fun isNotCompletelyOxygenated() = hasTile(ShipFloorTile.TRAVERSABLE)

    fun hasRecordedShipTile(position: Point2D) = hasRecorded(position)
}