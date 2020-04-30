package com.aoc.vault

import log.AdventLogger
import map.Map
import math.Point2D

class VaultMap(initialData: List<String>) : Map<VaultTile>() {

    private var currentPosition: Point2D
    private val totalKeyQuantity: Int
    private val root: Key

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

        currentPosition = filterTiles { it.isEntrance() }.keys.first()
        totalKeyQuantity = filterTiles { it.isKey() || it.isEntrance() }.count()

        //Find Entrance
        val entranceTile = filterTiles { it.isEntrance() }.entries.first()

        //Turn Entrance -> Key (Root Node)
        root = Key(entranceTile.value.value, entranceTile.key, setOf())

        AdventLogger.debug("Entrance Position: $currentPosition")
        AdventLogger.debug(this)
    }

    /**
     * Explores the [VaultMap] by finding the keys to unlock the doors until all keys have been collected.
     * The algorithm finds the order in which picking the keys up results in the lowest number of steps taken.
     * @return The number of steps taken in order to collect all keys.
     */
    fun collectKeys(): Int {
        //Thread.sleep(7000)

        //Create Key Graph
        graphKeyPaths(setOf(root))

        val paths = getCompletePaths()
        val shortestPathSteps = paths.map { shortestPath(it.collectedKeys.toList()) }.min()

        AdventLogger.info("\nFound ${paths.size} paths.")
        AdventLogger.info("The shortest path was ${shortestPathSteps!!.toInt()} steps.")

        return shortestPathSteps.toInt()
    }

    private fun getCompletePaths() = root.getAllChildren().filter { it.collectedKeysQuantity() == totalKeyQuantity }

    //TODO: Make this functional
    private fun shortestPath(keys: List<Key>): Float {
        var cumulativeWeight = 0F
        keys.forEachIndexed { i, key ->
            cumulativeWeight += if (key.linkedKeys.size == 1) {
                key.linkedKeys.values.first()
            } else {
                key.getLinkedKeyWeight(keys[i + 1].name)
            }
        }
        return cumulativeWeight
    }

    private fun graphKeyPaths(foundKeys: Set<Key>) {
        foundKeys.forEach { sourceKey ->
            if (sourceKey.collectedKeysQuantity() == totalKeyQuantity) {
                AdventLogger.debug("Found Path: ${sourceKey.pathString()} - ${shortestPath(sourceKey.collectedKeys.toList())} ")
            }
            if (sourceKey.collectedKeysQuantity() < totalKeyQuantity) {
                //If we've already graphed the key, grab it from the graph
                /*if (root.getAllChildren().contains(sourceKey)) {
                    val existing = root.getAllChildren().filter { it.name == sourceKey.name }
                    val withMostKeys = existing.maxBy { it.linkedKeys.count() }
                }*/
                val accessibleKeys = getUncollectedAccessibleKeysFrom(sourceKey)
                accessibleKeys.forEach { entry ->
                    AdventLogger.debug("Mapping $sourceKey -> ${entry.key} (${entry.value})")
                    sourceKey.mapTo(entry.key, entry.value)
                }
                graphKeyPaths(accessibleKeys.keys)
            }
        }
    }

    private fun getUncollectedAccessibleKeysFrom(sourceKey: Key): MutableMap<Key, Float> {
        val keyTiles = mutableSetOf<Triple<Point2D, VaultTile, Float>>()
        val nextPositions = mutableSetOf(sourceKey.pos)
        val visited = mutableSetOf(sourceKey.pos)
        val collectedKeys = sourceKey.collectedKeys + sourceKey
        var steps = 0F

        while (nextPositions.isNotEmpty()) {
            steps++

            //Get Un-Visited Adjacent Points
            val adjacentPositions = nextPositions.flatMap { it.adjacentPoints() }.filter { it !in visited }.toSet()
            visited.addAll(adjacentPositions)

            val adjacentTiles = filterPoints(adjacentPositions)
            nextPositions.clear()

            //Add Traversable Tiles & The Entrance (In-Case we backtrack over it)
            adjacentTiles.filterValues { it.isTraversable() || it.isEntrance() }.forEach { nextPositions.add(it.key) }

            //Add Doors (That We Have Keys For) Up-Next
            adjacentTiles.filterValues { it.isDoor() }.filter { door ->
                collectedKeys.count { key -> key.name.equals(door.value.value, true) } == 1
            }.forEach { nextPositions.add(it.key) }

            //Add Keys We've Already Collected
            adjacentTiles.filterValues { it.isKey() }.filterValues {
                collectedKeys.count { key -> key.name.equals(it.value, true) } == 1 //TODO: Key class equals override to compare value (data class?)
            }.forEach { nextPositions.add(it.key) }

            //Record Accessible Keys
            keyTiles.addAll(adjacentTiles.filterValues { it.isKey() }.map { Triple(it.key, it.value, steps) })
        }

        //Map & Filter Keys if Not Collected
        return keyTiles.associate { Key(it.second.value, it.first, collectedKeys) to it.third }
                .filter { entry -> collectedKeys.count { key -> key.name.equals(entry.key.name, true) } == 0 }
                .toMutableMap()

        /*  return keyTiles.map { Key(it.second.value, it.first, collectedKeys) }.filter { foundKey ->
              collectedKeys.count { key -> key.name.equals(foundKey.name, true) } == 0
          }.map {  }*/
    }

    /**
     * Finds the shortest path between the [source] and the [destination].
     * @return The length of the path in steps taken. If un-reachable, returns [Float.POSITIVE_INFINITY]
     */
    private fun getShortestPathSteps(source: Key, destination: Key): Float {
        var steps = 0F
        val nextPositions = mutableSetOf(source.pos)
        val visited = mutableSetOf(source.pos)
        val collectedKeys = source.collectedKeys + source

        while (nextPositions.isNotEmpty()) {
            steps++

            //Get Un-Visited Adjacent Points
            val adjacentPositions = nextPositions.flatMap { it.adjacentPoints() }.filter { it !in visited }.toSet()
            visited.addAll(adjacentPositions)

            val adjacentTiles = filterPoints(adjacentPositions)
            if (adjacentTiles.containsKey(destination.pos)) return steps

            nextPositions.clear()

            //Add Traversable & Key Tiles Up-Next
            adjacentTiles.filterValues { it.isTraversable() || it.isKey() || it.isEntrance() }.forEach { nextPositions.add(it.key) }

            //Add Doors (That We Have Keys For) Up-Next
            adjacentTiles.filterValues { it.isDoor() }.filter { door ->
                collectedKeys.count { key -> key.name.equals(door.value.value, true) } == 1
            }.forEach { nextPositions.add(it.key) }
        }
        return Float.POSITIVE_INFINITY
    }

}