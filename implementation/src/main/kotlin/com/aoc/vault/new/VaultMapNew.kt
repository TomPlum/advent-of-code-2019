package com.aoc.vault.new

import com.aoc.log.AdventLogger
import com.aoc.map.AdventMap2D
import com.aoc.math.Point2D
import com.aoc.vault.VaultMap
import com.aoc.vault.VaultTile
import java.util.*


class VaultMapNew(initialData: List<String>) : AdventMap2D<VaultTile>() {

    private val keys: Map<Point2D, VaultTile>
    private val graph: UnDirectedGraph<Char>
    private val doors = mutableSetOf<Char>()
    private val visited = mutableListOf<Point2D>()
    private var steps = 0L

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

        keys = filterTiles { it.isKey() || it.isEntrance() }
        graph = UnDirectedGraph(keys.values.map { it.value })

        keys.forEach {
            depthFirstSearch(it.value.value, it.toPair())
        }

        AdventLogger.info(this)
        AdventLogger.info(graph)
    }
    private val paths = mutableListOf<Long>()
    private val weights = Stack<Long>()
    private val collected = mutableSetOf<Char>()

    /**
     * Explores the [VaultMap] by finding the keys to unlock the doors until all keys have been collected.
     * The algorithm finds the order in which picking the keys up results in the lowest number of steps taken.
     * @return The number of steps taken in order to collect all keys.
     */
    fun collectKeys(): Int {
        val entrance = filterTiles { it.isEntrance() }.entries.first()
        solve(entrance.value.value)
        return paths.minOrNull()?.toInt() ?: throw IllegalStateException("No Path Found!")
    }


    fun solve(sourceKey: Char) {
        val accessibleKeys = graph.getAdjacentVertices(sourceKey)?.filter {
            collected.map { it.toUpperCase() }.containsAll(it.third) //Do we have the keys for all the target keys blocking doors?
        }?.filter {
            it.first != '@' //Ignore the entrance
        }?.filter {
            !collected.contains(it.first) //Ignore if we've already collected
        }

        if (weights.sum() <= paths.minOrNull() ?: Long.MAX_VALUE) {
            accessibleKeys?.forEach { (targetKey, weight, _) ->
                collected.add(targetKey)
                weights.push(weight)
                solve(targetKey)
            }
        }

        if (collected.size == this.keys.size - 1) {
            paths.add(weights.sum())
            AdventLogger.debug("Found Path: $collected (${weights.sum()})")
        }

        if (sourceKey != '@') {
            weights.pop()
            collected.remove(sourceKey)
        }
    }

    private fun depthFirstSearch(sourceKey: Char, tile: Pair<Point2D, VaultTile>) {
        val next = Stack<Pair<Point2D, VaultTile>>()
        visited.add(tile.first)
        steps++

        //Get Un-Visited Adjacent Points
        val adjacentPositions = tile.first.adjacentPoints().filter { it !in visited }

        val adjacentTiles = filterPoints(adjacentPositions).filter { !it.value.isWall() }

        //Record Doors
        adjacentTiles.filterValues { it.isDoor() }.forEach {
            doors.add(it.value.value)
        }

        //Add Entrance, Empty, Keys, Doors Up-Next
        adjacentTiles.forEach { next.push(it.toPair()) }

        if (next.isEmpty()) {
            doors.clear()
        }

        //Map Keys -> Graph w/Current Steps Weighting
        adjacentTiles.filterValues { it.isKey() }.forEach {
            graph.addEdge(sourceKey, it.value.value, steps, doors.map { it.toUpperCase() }.toSet())
        }

        while(next.isNotEmpty()) {
            depthFirstSearch(sourceKey, next.pop())
        }
        steps--

        if (steps == 0L) {
            visited.clear()
        }
    }

    private fun graphKey(tile: Map.Entry<Point2D, VaultTile>) {
        val next = mutableListOf(tile.key)
        val visited = mutableListOf(tile.key)
        var steps = 0L

        while(next.isNotEmpty()) {
            steps++

            //Get Un-Visited Adjacent Points (Then Mark Visited) - Performed First As They're Cleared Next
            val adjacentPositions = next.flatMap { it.adjacentPoints() }.filter { it !in visited }
            visited.addAll(adjacentPositions)

            //Get Un-Visited Adjacent Tiles (Then Clear Next)
            val adjacentTiles = filterPoints(adjacentPositions)
            next.clear()

            //Add Entrance, Empty, Keys, Doors Up-Next
            adjacentTiles.filter { !it.value.isWall() }.forEach { next.add(it.key) }

            //Map Keys -> Graph w/Current Steps Weighting
            adjacentTiles.filterValues { it.isKey() }.forEach {
                graph.addEdge(tile.value.value, it.value.value, steps, emptySet())
            }
        }
    }

}