package com.aoc.maze.donut

import math.Point2D

data class Portal(private val warpCode: String, private val entrances: Pair<Point2D, Point2D>) {
    override fun toString() = "$warpCode${entrances.first}<->${entrances.second}"
}