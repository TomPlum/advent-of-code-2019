package com.aoc.maze.donut

import log.AdventLogger
import map.AdventMap
import math.Point2D

class DonutMaze(data: List<String>) : AdventMap<DonutTile>() {
    init {
        var x = 0
        var y = 0
        data.forEach { row ->
            row.forEach { column ->
                addTile(Point2D(x, y), DonutTile(column))
                x++
            }
            x = 0
            y++
        }

        /*filterTiles { it.isPortalMarker() }.forEach {
            it.
        }*/

        AdventLogger.debug(this)
    }

    fun getPortals(): List<Portal> = listOf()
}