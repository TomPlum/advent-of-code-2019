package com.aoc.vault

import map.Map
import math.Point2D

class VaultMap(initialData: List<String>) : Map<VaultTile>() {

    private val keysCollected = mutableSetOf<VaultTile>()
    private val startingPosition: Point2D

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
        startingPosition = filterTiles { it.isEntrance() }.keys.first()
    }

    /**
     * Explores the [VaultMap] by finding the keys to unlock the doors until all keys have been collected.
     * The algorithm finds the order in which picking the keys up results in the lowest number of steps taken.
     * @return The number of steps taken in order to collect all keys.
     */
    fun collectKeys(): Int {
        val accessibleKeys = accessibleKeys()
        return 0
    }

    private fun accessibleKeys(): Int {
        var keys = 0
        val nextPositions = mutableSetOf(startingPosition)
        val scannedTiles = mutableSetOf(startingPosition)
        while (nextPositions.isNotEmpty()) {
            val adjacentPositions = nextPositions.flatMap { it.adjacentPoints() }.filter { it !in scannedTiles }.toSet()
            scannedTiles.addAll(adjacentPositions)
            val tiles = filterPoints(adjacentPositions)
            nextPositions.clear()
            tiles.filterValues { it.isTraversable() || it.isKey() }.forEach { nextPositions.add(it.key) }
            keys += tiles.filterValues { it.isKey() }.count()
        }
        return keys
    }

    private fun getShortestPathToDoor() {

    }

}