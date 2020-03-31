package com.aoc.intcode.droid

import map.Map
import math.Point2D

class ShipFloorMap : Map<ShipFloorTile>() {

    init {
        addTile(Point2D(0, 0), ShipFloorTile.DROID)
    }

    fun tileType(position: Point2D) = getTile(position, ShipFloorTile.UNKNOWN)

}