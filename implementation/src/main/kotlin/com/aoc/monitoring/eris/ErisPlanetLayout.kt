package com.aoc.monitoring.eris

import com.aoc.log.AdventLogger
import com.aoc.map.AdventMap2D
import com.aoc.math.Point2D

class ErisPlanetLayout(scanData: List<String>) : AdventMap2D<ErisScanTile>() {
    init {
        var x = 0
        var y = 0
        scanData.forEach { row ->
            row.forEach { tile ->
                addTile(Point2D(x, y), ErisScanTile(tile))
                x++
            }
            x=0
            y++
        }
        AdventLogger.debug(this)
    }

    fun getBioDiversityRating(): Long = 0
}