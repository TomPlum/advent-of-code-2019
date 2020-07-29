package com.aoc.intcode.droid.cryo

import com.aoc.map.AdventMap2D
import com.aoc.math.Point2D

class StarShipMap : AdventMap2D<Room>() {
    fun addRoom(position: Point2D, room: Room) {
        addTile(position, room)
    }

    fun rooms() = tileQuantity()

    fun print(): String = ""
}