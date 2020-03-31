package math

import kotlin.math.abs
import kotlin.math.atan2

data class Point2D(val x: Int, val y: Int) {

    /**
     * Returns the four points that are orthogonally adjacent.
     */
    fun adjacentPoints() = setOf(Point2D(x, y + 1), Point2D(x + 1, y), Point2D(x, y - 1), Point2D(x - 1, y))

    /**
     * Calculates the Manhattan Distance between two [Point2D]
     */
    fun distanceBetween(point: Point2D): Int = abs(this.x - point.x) + abs(this.y - point.y)

    /**
     * Calculates the positive clockwise angle between two [Point2D] in degrees.
     * Angles are calculated from the sector's true north in the range of 0 =< angle < 360.
     */
    fun angleBetween(point: Point2D): Double {
        val angle = atan2((y - point.y).toDouble(), (x - point.x).toDouble()) * (180 / Math.PI) - 90.00
        return if (angle < 0) { angle + 360.00 } else angle
    }

    override fun toString() = "($x, $y)"
}