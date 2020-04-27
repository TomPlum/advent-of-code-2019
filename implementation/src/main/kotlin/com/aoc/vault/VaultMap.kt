package com.aoc.vault

import log.AdventLogger
import map.Map
import math.Point2D

class VaultMap(initialData: List<String>) : Map<VaultTile>() {

    private var currentPosition: Point2D
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
        //Find Entrance
        val entranceTile = filterTiles { it.isEntrance() }.entries.first()

        //Turn Entrance -> Key (Root Node)
        val root = Key(entranceTile.value.value, entranceTile.key, setOf())

        graphKeyPaths(setOf(root))
        val allChildren = root.getAllChildren()
        val sum = allChildren.flatMap { it.keys.values }.sum()
        return 0;
    }

    private fun graphKeyPaths(foundKeys: Set<Key>) {
        foundKeys.forEach { sourceKey ->
            if (sourceKey.collectedKeys.count() < totalKeyQuantity) {
                val accessibleKeys = getUncollectedAccessibleKeysFrom(sourceKey)
                accessibleKeys.forEach { targetKey ->
                    val weight = getShortestPathSteps(sourceKey, targetKey)
                    sourceKey.mapTo(targetKey, weight)
                }
                //keys.addAll(accessibleKeys)
                graphKeyPaths(accessibleKeys)
            }
        }
    }

    private fun getUncollectedAccessibleKeysFrom(sourceKey: Key): Set<Key> {
        val keyTiles = mutableSetOf<Pair<Point2D, VaultTile>>()
        val nextPositions = mutableSetOf(sourceKey.pos)
        val visited = mutableSetOf(sourceKey.pos)
        val collectedKeys = sourceKey.collectedKeys + sourceKey
        
        while (nextPositions.isNotEmpty()) {
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

            //Add Keys
            keyTiles.addAll(adjacentTiles.filterValues { it.isKey() }.map { it.toPair() } )
        }

        //Map & Filter Keys if Not Collected
        return keyTiles.map { Key(it.second.value, it.first, collectedKeys) }.filter { foundKey ->
            collectedKeys.count { key -> key.name.equals(foundKey.name, true) } == 0
        }.toSet()
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