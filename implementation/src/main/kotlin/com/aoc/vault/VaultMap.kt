package com.aoc.vault

import com.aoc.log.AdventLogger
import com.aoc.map.AdventMap2D
import com.aoc.math.Point2D

class VaultMap(initialData: List<String>) : AdventMap2D<VaultTile>() {

    private val totalKeyQuantity: Int
    private val graph: Key
    private val cache = VaultCache()
    private val paths: MutableMap<Key, Float> = mutableMapOf()

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

        totalKeyQuantity = filterTiles { it.isKey() || it.isEntrance() }.count()

        //Find Entrance
        val entranceTile = filterTiles { it.isEntrance() }.entries.first()

        //Convert Entrance -> Key (Root Node)
        graph = Key(entranceTile.value.value, entranceTile.key, listOf())

        AdventLogger.debug(this)
    }

    /**
     * Explores the [VaultMap] by finding the keys to unlock the doors until all keys have been collected.
     * The algorithm finds the order in which picking the keys up results in the lowest number of steps taken.
     * @return The number of steps taken in order to collect all keys.
     */
    fun collectKeys(): Int {
        //Create Key Graph
        graphKeyPaths(graph)

        val shortestPathWeight = paths.values.minOrNull() ?: 0F

        AdventLogger.info("Found ${paths.size} path(s).")
        AdventLogger.info("The shortest path was ${shortestPathWeight.toInt()} steps.")
        AdventLogger.info("${cache.entries()} key states were cached.")

        return shortestPathWeight.toInt()
    }

    private fun graphKeyPaths(sourceKey: Key) {
        val shortestPathWeight = paths.values.minOrNull() ?: Float.POSITIVE_INFINITY

        if (sourceKey.steps() < shortestPathWeight) {
            if (sourceKey.collectedKeysQuantity() < totalKeyQuantity) {
                val accessibleKeys = getUncollectedAccessibleKeysFrom(sourceKey)
                accessibleKeys.forEach { (key, weight) ->
                    val targetKey = cache.get(key)
                    sourceKey.linkTo(targetKey, weight)
                    AdventLogger.debug("Mapping $sourceKey -> $targetKey ($weight)")
                    graphKeyPaths(targetKey)
                }
            } else {
                val pathLength = sourceKey.steps()
                paths[sourceKey] = pathLength
                AdventLogger.debug("Found Path: ${sourceKey.pathString()} $pathLength steps")
            }
        }
    }

    private fun getUncollectedAccessibleKeysFrom(sourceKey: Key): Map<Key, Float> {
        val accessibleKeys = mutableMapOf<Key, Float>()
        val next = mutableListOf(sourceKey.pos)
        val visited = mutableListOf(sourceKey.pos)
        val collectedKeys = sourceKey.collectedKeys()
        val collectedKeyNames = collectedKeys.map { it.name }
        var steps = 0F

        while (next.isNotEmpty()) {
            steps++

            //Get Un-Visited Adjacent Points
            val adjacentPositions = next.flatMap { it.adjacentPoints() }.filter { it !in visited }
            visited.addAll(adjacentPositions)

            val adjacentTiles = filterPoints(adjacentPositions)
            next.clear()

            //Add Traversable Tiles & The Entrance (In-Case we backtrack over it)
            adjacentTiles.filterValues { it.isTraversable() || it.isEntrance() }.forEach { next.add(it.key) }

            //Add Doors (That We Have Keys For) Up-Next
            adjacentTiles.filterValues { it.isDoor() }.filterValues { tile ->
                collectedKeyNames.contains(tile.value.toLowerCase())
            }.forEach { next.add(it.key) }

            //If we've stepped on a tile with a key
            val keyTiles = adjacentTiles.filterValues { it.isKey() }

            //Add Keys We've Already Collected Up-Next
            keyTiles.filterValues { tile -> collectedKeyNames.contains(tile.value) }.forEach { next.add(it.key) }

            //Record Accessible Keys
            keyTiles.forEach { (pos, tile) ->
                accessibleKeys[Key(tile.value, pos, collectedKeys)] = steps
            }
        }

        //Map & Filter Keys if Not Collected
        return accessibleKeys.filterKeys { key -> !collectedKeyNames.contains(key.name) }
    }

}