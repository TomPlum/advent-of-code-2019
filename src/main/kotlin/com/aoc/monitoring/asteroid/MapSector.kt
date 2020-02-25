package com.aoc.monitoring.asteroid

import kotlin.math.abs
import kotlin.math.atan2

data class MapSector(val contents: String, val x: Int, val y: Int) {

    /**
     * Calculates the positive clockwise angle between two [MapSector]
     */
    fun angleBetween(sector: MapSector): Double {
        val dy = (this.y - sector.y).toDouble()
        val dx = (this.x - sector.x).toDouble()
       /* val angle = (atan2(dy, dx) * 180) / Math.PI

        return when {
            angle in 0.0..90.0 -> abs(angle - 90)
            angle < 0 -> abs(angle) + 90
            else -> 450 - angle
        }*/
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