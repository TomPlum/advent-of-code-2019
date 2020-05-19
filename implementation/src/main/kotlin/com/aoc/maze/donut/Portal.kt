package com.aoc.maze.donut

import math.Point2D

data class Portal(private val warpCodes: Pair<WarpCode, WarpCode>, private val entrances: Pair<Point2D, Point2D>) {
    override fun toString() = "${warpCodes.first}${entrances.first}<->${entrances.second}"
}