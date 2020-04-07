package map

import math.Point2D
import kotlin.collections.Map

abstract class Map<T> {
    private val data = mutableMapOf<Point2D, T>()

    fun addTile(position: Point2D, tile: T) = data.put(position, tile)
    fun getTile(position: Point2D, default: T): T = data.getOrDefault(position, default)

    fun hasRecorded(position: Point2D): Boolean = data.containsKey(position)
    fun hasTile(value: T): Boolean = data.containsValue(value)

    fun tileQuantity(): Int = data.size

    fun filterPoints(positions: Set<Point2D>): Map<Point2D, T> = data.filterKeys { it in positions }
    fun filterTiles(predicate: (T) -> Boolean): Map<Point2D, T> = data.filterValues(predicate)

    fun adjacentTiles(position: Point2D) = data.filterKeys { it in position.adjacentPoints() }

    fun adjacentTiles(position: Point2D, predicate: (T) -> Boolean) = adjacentTiles(position).filterValues(predicate)

    override fun toString(): String {
        val xMin = data.keys.minBy { it.x }!!.x
        val yMax = data.keys.maxBy { it.y }!!.y

        val xMax = data.keys.maxBy { it.x }!!.x
        val yMin = data.keys.minBy { it.y }!!.y

        val xAxisLabels = "  ${(xMin..xMax).joinToString(" ")}\n"

        return xAxisLabels + (yMin..yMax).joinToString("\n") { y ->
           "$y " + (xMin..xMax).joinToString(separator = " ") { x ->
                data.getOrDefault(Point2D(x, y), " ").toString()
            }
        }
    }
}