package com.aoc.panel

import kotlin.math.pow
import kotlin.math.sqrt

class PanelCoordinate constructor(var x: Int = 0, var y: Int = 0) {

    override fun equals(other: Any?): Boolean {
        if (other == null) return false

        if (other !is PanelCoordinate) return false

        return (x == other.x && y == other.y)
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        return result
    }

    override fun toString(): String {
        return "($x, $y)"
    }

    private fun calculateEuclideanDistance(x0: Int, y0: Int, x1: Int, y1: Int): Int = sqrt(((x1 - x0).toDouble()).pow(2) + (y1 - y0).toDouble().pow(2)).toInt()
}