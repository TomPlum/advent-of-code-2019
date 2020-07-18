package com.aoc.map

import com.aoc.math.Point2D
import java.util.*
import kotlin.contracts.ExperimentalContracts

/**
 * This class is designed for inheritance.
 *
 * Lots of the days involve the concept of a 'Map' or a 'Maze' in which the shortest path must be found while
 * meeting day-specific criteria. A cartesian-style graph is internally maintained that maps tiles to [Point2D]
 * coordinates.
 *
 * //TODO: Restrict the type parameter to a type that extends MapTile? Not sure about enums
 *
 * @param T The type of 'tile' that will be mapped.
 * @see MapTile
 */
abstract class AdventMap2D<T> {
    /** The internal data representation, mapping the positions to the tiles */
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

    /**
     * @return true if the map has recorded a tile at the given [position]
     */
    protected fun hasRecorded(position: Point2D): Boolean = data.containsKey(position)

    /**
     * Checks if the map has a tile of the given type. Equality is left up to the type [T].
     * @return true if the map has recorded at least one tile with the given [value]
     */
    protected fun hasTile(value: T): Boolean = data.containsValue(value)

    /**
     * @return The number of tile currently recorded in the [AdventMap2D].
     */
    protected fun tileQuantity(): Int = data.size

    /**
     * Gets all the tiles at the given [positions]. If there is no recorded at tile at one of the given [positions],
     * then it is omitted from the response.
     * @return a [Map] of the given [positions] and their respective tiles.
     */
    protected fun filterPoints(positions: Set<Point2D>): Map<Point2D, T> = positions.filter(this::hasRecorded).associateWith(this::getTile)

    /**
     * Gets all the tiles that equate to true on the given [predicate].
     * Each implementation of [AdventMap2D] will have a tile of type [T]. This tile will provide the function
     * that will be evaluated in this predicate.
     * @return a [Map] of all tiles that match the given [predicate].
     */
    protected fun filterTiles(predicate: (T) -> Boolean): Map<Point2D, T> = data.filterValues(predicate)

    @ExperimentalContracts
    protected fun whereIs(predicate: (T) -> Boolean): Point2D? = data.filterValues(predicate).keys.firstOrNull()

    @ExperimentalContracts
    protected fun findTile(predicate: (T) -> Boolean): T? = data.filterValues(predicate).values.firstOrNull()

    /**
     * Gets all the tiles that are adjacent to the given [positions].
     * @see Point2D.adjacentPoints
     * @return a [Map] of adjacent [Point2D] and their respective tiles, [T].
     */
    protected fun adjacentTiles(positions: Set<Point2D>): Map<Point2D, T> {
        return positions.flatMap { it.adjacentPoints() }.associateWith(this::getTile)
    }

    @ExperimentalContracts
    protected fun adjacentTiles(position: Point2D, predicate: (T) -> Boolean) = adjacentTiles(position).filterValues(predicate)
    private fun adjacentTiles(position: Point2D): Map<Point2D, T> = data.filterKeys { it in position.adjacentPoints() }

    /**
     * Resets the map as if it is a new instance of [AdventMap2D]. All internally stored data is cleared.
     */
    fun reset() = data.clear()

    /**
     * @return The minimum x-ordinate currently recorded in the map.
     */
    protected fun xMin() = data.keys.minBy { it.x }!!.x

    /**
     * @return The minimum y-ordinate currently recorded in the map.
     */
    protected fun yMin() = data.keys.minBy { it.y }!!.y

    /**
     * @return The maximum x-ordinate currently recorded in the map.
     */
    protected fun xMax() = data.keys.maxBy { it.x }!!.x

    /**
     * @return The maximum y-ordinate currently recorded in the map.
     */
    protected fun yMax() = data.keys.maxBy { it.y }!!.y

    /**
     * Two [AdventMap2D]s are equal to one another if they have the same [data].
     */
    override fun equals(other: Any?): Boolean {
        if (other !is AdventMap2D<*>) return false
        return this.data == other.data
    }

    override fun hashCode(): Int {
        return data.hashCode()
    }

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