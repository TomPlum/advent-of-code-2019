package com.aoc.vault

import com.aoc.log.AdventLogger
import com.aoc.map.AdventMap2D
import com.aoc.math.Point2D

class VaultMap(initialData: List<String>) : AdventMap2D<VaultTile>() {

    private val totalKeyQuantity: Int
    private val root: Key
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
        root = Key(entranceTile.value.value, entranceTile.key, listOf())

        AdventLogger.debug(this)
    }

    /**
     * Explores the [VaultMap] by finding the keys to unlock the doors until all keys have been collected.
     * The algorithm finds the order in which picking the keys up results in the lowest number of steps taken.
     * @return The number of steps taken in order to collect all keys.
     */
    fun collectKeys(): Int {
        //Create Key Graph
        graphKeyPaths(listOf(root))

        val shortestPathWeight = paths.values.min()!!

        AdventLogger.info("\nFound ${paths.size} path(s).")
        AdventLogger.info("The shortest path was ${shortestPathWeight.toInt()} steps.")

        return shortestPathWeight.toInt()
    }

    private fun graphKeyPaths(foundKeys: List<Key>) {
        val shortestPathWeight = paths.values.min() ?: Float.POSITIVE_INFINITY

        foundKeys.filter { it.steps() < shortestPathWeight }.forEach { sourceKey ->
            if (sourceKey.collectedKeysQuantity() < totalKeyQuantity) {
                val accessibleKeys = getUncollectedAccessibleKeysFrom(sourceKey)
                accessibleKeys.forEach { (key, weight) ->
                    val targetKey = cache.get(key) ?: key
                    sourceKey.linkTo(targetKey, weight)
                    cache.add(sourceKey) //TODO: use getOrPut instead
                    AdventLogger.debug("Mapping $sourceKey -> $targetKey ($weight)")
                }
                graphKeyPaths(accessibleKeys.keys.toList())
            } else {
                val pathLength = sourceKey.steps()
                paths[sourceKey] = pathLength
                AdventLogger.debug("Found Path: ${sourceKey.pathString()} $pathLength steps")
            }
        }
    }

    private fun getUncollectedAccessibleKeysFrom(sourceKey: Key): Map<Key, Float> {
        val accessibleKeys = mutableMapOf<Key, Float>()
        val nextPositions = mutableListOf(sourceKey.pos)
        val visited = mutableListOf(sourceKey.pos)
        val collectedKeys = sourceKey.collectedKeys()
        var steps = 0F

        while (nextPositions.isNotEmpty()) {
            steps++

            //Get Un-Visited Adjacent Points
            val adjacentPositions = nextPositions.flatMap { it.adjacentPoints() }.filter { it !in visited }
            visited.addAll(adjacentPositions)

            val adjacentTiles = filterPoints(adjacentPositions)
            nextPositions.clear()

            //Add Traversable Tiles & The Entrance (In-Case we backtrack over it)
            adjacentTiles.filterValues { it.isTraversable() || it.isEntrance() }.forEach { nextPositions.add(it.key) }

            //Add Doors (That We Have Keys For) Up-Next
            adjacentTiles.filterValues { it.isDoor() }.filter { door ->
                collectedKeys.count { key -> key.name.equals(door.value.value, true) } == 1
            }.forEach { nextPositions.add(it.key) }

            //Add Keys We've Already Collected Up-Next
            adjacentTiles.filterValues { it.isKey() }.filterValues {
                collectedKeys.count { key -> key.name.equals(it.value, true) } == 1 //TODO: Key class equals override to compare value (data class?)
            }.forEach { nextPositions.add(it.key) }

            //Record Accessible Keys
            adjacentTiles.filterValues { it.isKey() }.forEach { (pos, tile) ->
                accessibleKeys[Key(tile.value, pos, collectedKeys)] = steps
            }
        }

        //Map & Filter Keys if Not Collected
        return accessibleKeys.filterKeys { key -> !collectedKeys.map { it.name }.contains(key.name) }
    }

}