package com.aoc.monitoring.asteroid

import kotlin.math.abs
import kotlin.math.atan2

data class MapSector(val contents: String, val x: Int, val y: Int) {

    /**
     * Calculates the positive clockwise angle between two [MapSector] in degrees.
     * Angles are calculated from the sector's true north in the range of 0 =< angle < 360.
     */
    fun angleBetween(sector: MapSector): Double {
        val angle = atan2((y - sector.y).toDouble(), (x - sector.x).toDouble()) * (180 / Math.PI) - 90.00
        return if (angle < 0) { angle + 360.00 } else angle
    }

    /**
     * Calculates the Manhattan Distance between two [MapSector] as an integer
     */
    fun distanceBetween(sector: MapSector) = abs(this.x - sector.x) + abs(this.y - sector.y)

    fun hasAsteroid() = when (contents) {
        "#" -> true
        "." -> false
        else -> throw IllegalArgumentException("Invalid Map Sector Contents '$contents'. Asteroid (#), Empty (.)")
    }


}