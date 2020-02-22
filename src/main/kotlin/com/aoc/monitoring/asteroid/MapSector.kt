package com.aoc.monitoring.asteroid

import java.lang.IllegalArgumentException

class MapSector(private val contents: String) {
    fun hasAsteroid(): Boolean {
        return when(contents) {
            "#" -> true
            "." -> false
            else -> throw IllegalArgumentException("Invalid Map Sector Contents '$contents'. Asteroid (#), Empty (.)")
        }
    }

    override fun toString() = contents
}