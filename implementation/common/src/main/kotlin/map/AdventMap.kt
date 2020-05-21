package map

import math.Point2D
import java.lang.IllegalArgumentException

abstract class AdventMap<T> {
    private val data = mutableMapOf<Point2D, T>()

    /**
     * Adds a new [tile] at the given [position].
     * If a tile already exists at the given position then it returned, otherwise null.
     */
    protected fun addTile(position: Point2D, tile: T): T? = data.put(position, tile)

    /**
     * Retrieves the tile at the given [position].
     * If there is no tile present then the [default] is returned.
     */
    protected fun getTile(position: Point2D, default: T): T = data.getOrDefault(position, default)

    /**
     * Retrieves the tile at the given [position].
     * @throws IllegalArgumentException if the map does not contain a tile at the given [position]
     */
    protected fun getTile(position: Point2D): T = data[position] ?: throw IllegalArgumentException("Map does not contain tile at $position")

    fun hasRecorded(position: Point2D): Boolean = data.containsKey(position)
    fun hasTile(value: T): Boolean = data.containsValue(value)

    protected fun tileQuantity(): Int = data.size

    fun filterPoints(positions: Set<Point2D>): Map<Point2D, T> = data.filterKeys { it in positions }
    fun filterTiles(predicate: (T) -> Boolean): Map<Point2D, T> = data.filterValues(predicate)
    fun whereIs(predicate: (T) -> Boolean): Point2D? = data.filterValues(predicate).keys.firstOrNull()
    fun findTile(predicate: (T) -> Boolean): T? = data.filterValues(predicate).values.firstOrNull()

    fun adjacentTiles(position: Point2D): Map<Point2D, T> = data.filterKeys { it in position.adjacentPoints() }
    fun adjacentTiles(positions: Set<Point2D>): Map<Point2D, T> = data.filterKeys { k -> k in positions.flatMap { it.adjacentPoints() } }
    fun adjacentTiles(position: Point2D, predicate: (T) -> Boolean) = adjacentTiles(position).filterValues(predicate)

    fun xMin() = data.keys.minBy { it.x }!!.x
    fun yMin() = data.keys.minBy { it.y }!!.y

    fun xMax() = data.keys.maxBy { it.x }!!.x
    fun yMax() = data.keys.maxBy { it.y }!!.y

    /**
     * Creates a cartesian graph style visual representation of the [data]
     */
    override fun toString(): String {
        return (yMin()..yMax()).joinToString("\n") { y ->
            (xMin()..xMax()).joinToString(" ") { x ->
                data.getOrDefault(Point2D(x, y), " ").toString()
            }
        }.plus("\n")
    }
}