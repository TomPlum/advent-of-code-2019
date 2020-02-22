package com.aoc.monitoring.asteroid

import kotlin.math.atan2

class AsteroidMap(mapData: List<String>) {
    private var map: MutableList<List<MapSector>> = mutableListOf() //TODO: Change back to 2D Array

    init {
        mapData.map { row -> map.add(row.chunked(1).map { contents -> MapSector(contents) }) }
    }

    fun getRow(index: Int) = map[index].joinToString(separator = "")

    fun getAsteroidSightLine(x1: Int, y1: Int, x2: Int, y2: Int) {
        val dotProduct = x1 * x2 + y1 * y2
        val determinant = x1 * y2 - y1 * x2
        val angle = atan2(determinant.toDouble(), dotProduct.toDouble())
    }
}