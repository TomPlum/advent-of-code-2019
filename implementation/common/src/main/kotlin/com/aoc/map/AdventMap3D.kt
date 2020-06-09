package com.aoc.map

import com.aoc.math.Point3D
import kotlin.contracts.ExperimentalContracts

/**
 * This class is designed for inheritance.
 *
 * Lots of the days involve the concept of a 'Map' or a 'Maze' in which the shortest path must be found while
 * meeting day-specific criteria. A cartesian-style graph is internally maintained that maps tiles to [Point3D]
 * coordinates.
 *
 * //TODO: Restrict the type parameter to a type that extends MapTile? Not sure about enums
 *
 * @param T The type of 'tile' that will be mapped.
 * @see MapTile
 */
abstract class AdventMap3D<T> {
    /** The internal data representation, mapping the positions to the tiles */
    private val data = mutableMapOf<Point3D, T>()

    /**
     * Adds a new [tile] at the given [position].
     * If a tile already exists at the given position then it returned, otherwise null.
     */
    protected fun addTile(position: Point3D, tile: T): T? = data.put(position, tile)

    /**
     * Retrieves the tile at the given [position].
     * If there is no tile present then the [default] is returned.
     */
    protected fun getTile(position: Point3D, default: T): T = data.getOrDefault(position, default)

    /**
     * Retrieves the tile at the given [position].
     * @throws IllegalArgumentException if the map does not contain a tile at the given [position]
     */
    protected fun getTile(position: Point3D): T = data[position] ?: throw IllegalArgumentException("Map does not contain tile at $position")

    /**
     * @return true if the map has recorded a tile at the given [position]
     */
    protected fun hasRecorded(position: Point3D): Boolean = data.containsKey(position)

    /**
     * Checks if the map has a tile of the given type. Equality is left up to the type [T].
     * @return true if the map has recorded at least one tile with the given [value]
     */
    protected fun hasTile(value: T): Boolean = data.containsValue(value)

    /**
     * @return The number of tile currently recorded in the [AdventMap3D].
     */
    protected fun tileQuantity(): Int = data.size

    /**
     * Gets all the tiles at the given [positions]. If there is no recorded at tile at one of the given [positions],
     * then it is omitted from the response.
     * @return a [Map] of the given [positions] and their respective tiles.
     */
    protected fun filterPoints(positions: Set<Point3D>): Map<Point3D, T> = positions.filter(this::hasRecorded).associateWith(this::getTile)

    /**
     * Gets all the tiles that equate to true on the given [predicate].
     * Each implementation of [AdventMap3D] will have a tile of type [T]. This tile will provide the function
     * that will be evaluated in this predicate.
     * @return a [Map] of all tiles that match the given [predicate].
     */
    protected fun filterTiles(predicate: (T) -> Boolean): Map<Point3D, T> = data.filterValues(predicate)

    @ExperimentalContracts
    protected fun whereIs(predicate: (T) -> Boolean): Point3D? = data.filterValues(predicate).keys.firstOrNull()

    @ExperimentalContracts
    protected fun findTile(predicate: (T) -> Boolean): T? = data.filterValues(predicate).values.firstOrNull()

    /**
     * Gets all the tiles that are adjacent to the given [positions].
     * @see Point3D.planarAdjacentPoints
     * @return a [Map] of adjacent [Point3D] and their respective tiles, [T].
     */
    protected fun adjacentTiles(positions: Set<Point3D>): Map<Point3D, T> {
        return positions.flatMap { it.planarAdjacentPoints() }.associateWith(this::getTile)
    }

    @ExperimentalContracts
    protected fun adjacentTiles(position: Point3D, predicate: (T) -> Boolean) = adjacentTiles(position).filterValues(predicate)
    private fun adjacentTiles(position: Point3D): Map<Point3D, T> = data.filterKeys { it in position.planarAdjacentPoints() }

    protected fun duplicateTopLayer(n: Int) {
        val topLayer = data.entries
        val nIterator = (1..n).iterator()
        val toAdd = mutableMapOf<Point3D, T>()
        while (nIterator.hasNext()) {
            val it = topLayer.iterator()
            val z = nIterator.nextInt()
            while(it.hasNext()) {
                val next = it.next()
                val position = next.key
                toAdd[Point3D(position.x, position.y, z)] = next.value
            }
        }
        data.putAll(toAdd)
    }

    /**
     * Resets the map as if it is a new instance of [AdventMap3D]. All internally stored data is cleared.
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
     * @return The minimum z-ordinate currently recorded in the map.
     */
    protected fun zMin() = data.keys.minBy { it.z }!!.z

    /**
     * @return The maximum x-ordinate currently recorded in the map.
     */
    protected fun xMax() = data.keys.maxBy { it.x }!!.x

    /**
     * @return The maximum y-ordinate currently recorded in the map.
     */
    protected fun yMax() = data.keys.maxBy { it.y }!!.y

    /**
     * @return The maximum y-ordinate currently recorded in the map.
     */
    protected fun zMax() = data.keys.maxBy { it.z }!!.z

    /**
     * Creates a cartesian graph style visual representation of the [data]
     */
    override fun toString(): String {
        return (zMin()..zMax()).joinToString("\n") { z ->
            (yMin()..yMax()).joinToString("\n") { y ->
                (xMin()..xMax()).joinToString(" ") { x ->
                    data.getOrDefault(Point3D(x, y, z), " ").toString()
                }
            }.plus("\n")
        }
    }
}