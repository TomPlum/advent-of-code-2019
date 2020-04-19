package com.aoc.vault

import log.AdventLogger
import map.Map
import math.Point2D

class VaultMap(initialData: List<String>) : Map<VaultTile>() {

    private var currentPosition: Point2D
    private var stepsTaken = 0F
    private val keys = mutableSetOf<Key>()
    private val totalKeyQuantity: Int

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
        totalKeyQuantity = filterTiles { it.isKey() }.count()

        AdventLogger.debug("Starting Position: $currentPosition")
        AdventLogger.debug(this)
    }

    /**
     * Explores the [VaultMap] by finding the keys to unlock the doors until all keys have been collected.
     * The algorithm finds the order in which picking the keys up results in the lowest number of steps taken.
     * @return The number of steps taken in order to collect all keys.
     */
 /*   fun collectKeys(): Float {
        while (keysRemain()) {
            //Get Key
            val (pos, tile) = accessibleTiles { it.isKey() }.minBy { getShortestPathSteps(it.first) }!!
            AdventLogger.debug("Collecting Key: ${tile.value}")
            stepsTaken += getShortestPathSteps(pos)
            updateCurrentPosition(pos)

            //Unlock Door
            val accessibleDoors = accessibleTiles { it.isDoor() }
            val doorLocation = findTile { it.value == tile.value.toUpperCase() } ?: return stepsTaken
            AdventLogger.debug("Unlocking Door: ${tile.value.toUpperCase()}")
            stepsTaken += getShortestPathSteps(doorLocation)
            updateCurrentPosition(doorLocation)
        }

        return stepsTaken
    }
*/
    fun collectKeys2(): Int {
        //Graph Key Paths

        //Add Starting Position
        filterTiles { it.isEntrance() }.forEach { keys.add(Key(it.value.value, it.key, mutableSetOf())) }

        graphKeyPaths(keys.filter { it.name == '@' }.toSet())
        val allChildren = keys.find { it.name == '@' }!!.getAllChildren()
        return 0;
    }

    private fun graphKeyPaths(foundKeys: Set<Key>) {
        foundKeys.forEach { sourceKey ->
            if (sourceKey.collectedKeys.count() < totalKeyQuantity) {
                val accessibleKeys = getUncollectedAccessibleKeys(sourceKey.pos)
                accessibleKeys.forEach { targetKey ->
                    val weight = getShortestPathSteps(sourceKey.pos, targetKey.pos)
                    keys.find { it == sourceKey }!!.mapTo(targetKey, weight)
                }
                keys.addAll(accessibleKeys)
                graphKeyPaths(accessibleKeys)
            }
        }
    }

    private fun getUncollectedAccessibleKeys(source: Point2D): Set<Key> {
        val keyTiles = mutableSetOf<Pair<Point2D, VaultTile>>()
        val nextPositions = mutableSetOf(source)
        val visited = mutableSetOf(source)
        while (nextPositions.isNotEmpty()) {
            //Get Un-Visited Adjacent Points
            val adjacentPositions = nextPositions.flatMap { it.adjacentPoints() }.filter { it !in visited }.toSet()
            visited.addAll(adjacentPositions)

            val adjacentTiles = filterPoints(adjacentPositions)
            nextPositions.clear()

            //Add Traversable & Key Tiles Up-Next
            adjacentTiles.filterValues { it.isTraversable() || it.isKey() || it.isEntrance() }.forEach { nextPositions.add(it.key) }

            //Add Doors (That We Have Keys For) Up-Next
            adjacentTiles.filterValues { it.isDoor() }.filter { door ->
                keys.count { key -> key.name.equals(door.value.value, true) } == 1
            }.forEach { nextPositions.add(it.key) }

            keyTiles.addAll(adjacentTiles.filterValues { it.isKey() }.map { it.toPair() } )
        }
        return keyTiles.map { Key(it.second.value, it.first, keys.toSet()) }.filter { !keys.contains(it) }.toSet()
    }

    /**
     * Finds the shortest path between the [source] and the [destination].
     * @return The length of the path in steps taken. If un-reachable, returns [Float.POSITIVE_INFINITY]
     */
    private fun getShortestPathSteps(source: Point2D, destination: Point2D): Float {
        var steps = 0F
        val nextPositions = mutableSetOf(source)
        val visited = mutableSetOf(source)
        //val blockingDoors = mutableSetOf<VaultTile>()
        while (nextPositions.isNotEmpty()) {
            steps++

            //Get Un-Visited Adjacent Points
            val adjacentPositions = nextPositions.flatMap { it.adjacentPoints() }.filter { it !in visited }.toSet()
            visited.addAll(adjacentPositions)

            val adjacentTiles = filterPoints(adjacentPositions)
            if (adjacentTiles.containsKey(destination)) return steps

            nextPositions.clear()

            //Add Traversable & Key Tiles Up-Next
            adjacentTiles.filterValues { it.isTraversable() || it.isKey() || it.isEntrance() }.forEach { nextPositions.add(it.key) }

            //Add Doors (That We Have Keys For) Up-Next
            adjacentTiles.filterValues { it.isDoor() }.filter { door ->
                keys.count { key -> key.name.equals(door.value.value, true) } == 1
            }.forEach { nextPositions.add(it.key) }

            //adjacentTiles.filterValues { it.isDoor() }.forEach { blockingDoors.add(it.value) }
        }
        //AdventLogger.debug("The path between $currentPosition -> $destination is blocked by ${blockingDoors.joinToString { it.value.toString() }}")
        return Float.POSITIVE_INFINITY
    }

    private fun keysRemain() = filterTiles { it.isKey() }.count() > 0

    private fun updateCurrentPosition(newPosition: Point2D) {
        addTile(currentPosition, VaultTile('.'))
        addTile(newPosition, VaultTile('@'))
        currentPosition = newPosition
        AdventLogger.debug(this)
    }

}