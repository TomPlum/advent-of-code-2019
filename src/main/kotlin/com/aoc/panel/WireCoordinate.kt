package com.aoc.panel


data class WireCoordinate constructor(var x: Int = 0, var y: Int = 0) {
    override fun toString() = "($x, $y)"
}