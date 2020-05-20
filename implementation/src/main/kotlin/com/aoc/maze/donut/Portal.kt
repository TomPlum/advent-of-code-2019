package com.aoc.maze.donut

import math.Point2D
import java.lang.IllegalArgumentException

//TODO: are 2 warp codes needed? A pair of warp-games IS a portal with one common warp code
data class Portal(private val warpCodes: Pair<WarpCode, WarpCode>, private val entrances: Pair<Point2D, Point2D>) {

    fun hasEntrance(pos: Point2D): Boolean = entrances.first == pos || entrances.second == pos

    fun warp(entrance: Point2D): Point2D {
        val points = entrances.toList()
        if (points.contains(entrance)) {
            return points.find { it != entrance }!!
        }
        throw IllegalArgumentException("$this does not warp to or from $entrance")
    }

    override fun toString() = "${warpCodes.first}${entrances.first}<->${entrances.second}"
}