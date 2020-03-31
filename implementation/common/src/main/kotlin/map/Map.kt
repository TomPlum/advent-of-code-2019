package map

import math.Point2D

abstract class Map<T> {
    private val data = mutableMapOf<Point2D, T>()

    fun addTile(position: Point2D, tile: T) = data.put(position, tile)

    fun hasRecorded(position: Point2D) = data.containsKey(position)

    fun getTile(position: Point2D, default: T) = data.getOrDefault(position, default)

    fun getData() = data //TODO: Not necessary due to Kotlin properties.. don't want to fully expose

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