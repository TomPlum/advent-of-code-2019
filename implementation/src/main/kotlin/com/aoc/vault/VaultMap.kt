package com.aoc.vault

import map.Map
import math.Point2D
import kotlin.IllegalStateException

class VaultMap(initialData: List<String>) : Map<VaultTile>() {

    private var currentPosition: Point2D
    private var stepsTaken = 0

    init {
        //TODO: Wasn't this same thing done somewhere else? Can you move it to the common Map<T> class?
        var x = 0
        var y = 0
        initialData.forEach { row ->
            row.forEach { column ->
                addTile(Point2D(x, y), VaultTile(column))
                x++
            }
            x = 0
            y++
        }

        println(this)
        currentPosition = filterTiles { it.isEntrance() }.keys.first()
    }

    /**
     * Explores the [VaultMap] by finding the keys to unlock the doors until all keys have been collected.
     * The algorithm finds the order in which picking the keys up results in the lowest number of steps taken.
     * @return The number of steps taken in order to collect all keys.
     */
    fun collectKeys(): Int {
        while (keysRemain()) {
            //Get Key
            val (pos, tile) = accessibleKeys().minBy { getShortestPathSteps(it.first) }!!
            stepsTaken += getShortestPathSteps(pos)
            updateCurrentPosition(pos)

            //Unlock Door
            val doorLocation = findTile { it.value == tile.value.toUpperCase() } ?: return stepsTaken
            stepsTaken += getShortestPathSteps(doorLocation)
            updateCurrentPosition(doorLocation)
        }

        return stepsTaken
    }

    private fun accessibleKeys(): MutableSet<Pair<Point2D, VaultTile>> {
        val keys = mutableSetOf<Pair<Point2D, VaultTile>>()
        val nextPositions = mutableSetOf(currentPosition)
        val scannedTiles = mutableSetOf(currentPosition)
        while (nextPositions.isNotEmpty()) {
            val adjacentPositions = nextPositions.flatMap { it.adjacentPoints() }.filter { it !in scannedTiles }.toSet()
            scannedTiles.addAll(adjacentPositions)

            val adjacentTiles = filterPoints(adjacentPositions)
            nextPositions.clear()
            adjacentTiles.filterValues { it.isTraversable() || it.isKey() }.forEach { nextPositions.add(it.key) }
            keys.addAll(adjacentTiles.filterValues { it.isKey() }.map { it.toPair() } )
        }
        return keys
    }

    private fun getShortestPathSteps(destination: Point2D): Int {
        val nextPositions = mutableSetOf(currentPosition)
        val scannedTiles = mutableSetOf(currentPosition)
        var steps = 0
        while (nextPositions.isNotEmpty()) {
            steps++
            val adjacentPositions = nextPositions.flatMap { it.adjacentPoints() }.filter { it !in scannedTiles }.toSet()
            scannedTiles.addAll(adjacentPositions)

            val adjacentTiles = filterPoints(adjacentPositions)
            if (adjacentTiles.containsKey(destination)) return steps

            nextPositions.clear()
            adjacentTiles.filterValues { it.isTraversable() || it.isKey() }.forEach { nextPositions.add(it.key) }
        }
        throw IllegalStateException("Error finding the shortest path between $currentPosition and $destination")
    }

    private fun getShortestPathToDoor() {

    }

    private fun keysRemain() = filterTiles { it.isKey() }.count() > 0

    private fun updateCurrentPosition(newPosition: Point2D) {
        addTile(currentPosition, VaultTile('.'))
        addTile(newPosition, VaultTile('@'))
        currentPosition = newPosition
        println(this)
    }

}