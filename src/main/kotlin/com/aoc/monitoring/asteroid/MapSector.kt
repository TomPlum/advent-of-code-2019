package com.aoc.monitoring.asteroid

import java.lang.IllegalArgumentException
import kotlin.math.abs
import kotlin.math.atan2

class MapSector(private val contents: String, val x: Int, val y: Int) {

    /**
     * Calculates the clockwise angle between two [MapSector]
     */
    fun angleBetween(sector: MapSector): Double {
        //val dotProduct = this.x * sector.x + this.y * sector.y //TODO Remove if not needed
        //val determinant = this.x * sector.y - this.y * sector.x
        val dy = (this.y - sector.y).toDouble()
        val dx = (this.x - sector.x).toDouble()
        val angle = atan2(dy, dx) * (180 / Math.PI)
        return if (angle < 0) abs(angle + 90) else angle
    }

    fun hasAsteroid(): Boolean {
        return when(contents) {
            "#" -> true
            "." -> false
            else -> throw IllegalArgumentException("Invalid Map Sector Contents '$contents'. Asteroid (#), Empty (.)")
        }
    }

    override fun toString() = contents
}