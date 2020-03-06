package com.aoc.intcode.arcade

import com.aoc.math.Point2D

class Tile(var id: TileID, val position: Point2D) {
    override fun toString() = id.displayIcon
}