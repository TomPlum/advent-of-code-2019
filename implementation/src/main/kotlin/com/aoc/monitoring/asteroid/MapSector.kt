package com.aoc.monitoring.asteroid

import math.Point2D
import kotlin.math.abs
import kotlin.math.atan2

data class MapSector(val contents: String, val position: Point2D) { //TODO: Replace with Point2D

    /**
     * Calculates the positive clockwise angle between two [MapSector] in degrees.
     */
    fun angleBetween(sector: MapSector): Double = this.position.angleBetween(sector.position)

    /**
     * Calculates the Manhattan Distance between two [MapSector] as an integer
     */
    fun distanceBetween(sector: MapSector): Int = sector.position.distanceBetween(this.position)

    fun hasAsteroid(): Boolean = when (contents) {
        "#" -> true
        "." -> false
        else -> throw IllegalArgumentException("Invalid Map Sector Contents '$contents'. Asteroid (#), Empty (.)")
    }


}