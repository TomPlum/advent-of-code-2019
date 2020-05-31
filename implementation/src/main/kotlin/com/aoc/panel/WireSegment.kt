package com.aoc.panel

import com.aoc.math.Direction

/**
 * @param directionalCode [U, R, D, L] -> [Direction.UP], [Direction.RIGHT], [Direction.DOWN], [Direction.LEFT]
 */
data class WireSegment(private val directionalCode: String, val length: Int) {

    val direction: Direction = when (directionalCode) {
        "U" -> Direction.UP
        "R" -> Direction.RIGHT
        "D" -> Direction.DOWN
        "L" -> Direction.LEFT
        else -> throw IllegalArgumentException("Invalid Directional Code ($directionalCode)")
    }

}