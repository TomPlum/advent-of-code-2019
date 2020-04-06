package map

import math.Point2D

abstract class Map<T> {
    private val data = mutableMapOf<Point2D, T>()

    fun addTile(position: Point2D, tile: T) = data.put(position, tile)
    fun getTile(position: Point2D, default: T) = data.getOrDefault(position, default)

    fun hasRecorded(position: Point2D) = data.containsKey(position)
    fun hasTile(value: T) = data.containsValue(value)

    fun tileQuantity() = data.size

    fun filterTiles(predicate: (T) -> Boolean) = data.filterValues(predicate)

    override fun toString(): String {
        val xMin = data.keys.minBy { it.x }!!.x
        val yMax = data.keys.maxBy { it.y }!!.y

        val xMax = data.keys.maxBy { it.x }!!.x
        val yMin = data.keys.minBy { it.y }!!.y

        return (yMin..yMax).joinToString("\n") { y ->
            (xMin..xMax).joinToString(separator = " ") { x ->
                data.getOrDefault(Point2D(x, y), " ").toString()
            }
        }
    }
}