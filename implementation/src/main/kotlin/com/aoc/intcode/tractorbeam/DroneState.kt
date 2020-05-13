package com.aoc.intcode.tractorbeam

import map.MapTile

class DroneState(private val glyph: String) : MapTile<String>(glyph) {
    companion object {
        fun stationary() = DroneState(".")
        fun propagating() = DroneState("#")
        fun shipArea() = DroneState("O")
        fun unknown() = DroneState(" ")
    }

    fun isPropagating() = glyph == "#"

    fun isSantaShipArea() = glyph == "O"

    fun isStationary() = glyph == "."
}