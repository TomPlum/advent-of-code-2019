package com.aoc.monitoring.asteroid

import kotlin.math.atan2

data class MapSector(val contents: String, val x: Int, val y: Int) {

    /**
     * Calculates the positive clockwise angle between two [MapSector]
     */
    fun angleBetween(sector: MapSector): Double {
        val dy = (this.y - sector.y).toDouble()
        val dx = (this.x - sector.x).toDouble()
        return atan2(dy, dx) * (180 / Math.PI)
    }

    fun hasAsteroid(): Boolean {
        return when(contents) {
            "#" -> true
            "." -> false
            else -> throw IllegalArgumentException("Invalid Map Sector Contents '$contents'. Asteroid (#), Empty (.)")
        }
    }

}