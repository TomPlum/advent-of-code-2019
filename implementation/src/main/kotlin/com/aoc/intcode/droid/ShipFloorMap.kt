package com.aoc.intcode.droid

import map.AdventMap
import math.Point2D

class ShipFloorMap : AdventMap<ShipFloorTile>() {

    init {
        addTile(Point2D(0, 0), ShipFloorTile.DROID)
    }

    fun tileType(position: Point2D) = getTile(position, ShipFloorTile.UNKNOWN)

}