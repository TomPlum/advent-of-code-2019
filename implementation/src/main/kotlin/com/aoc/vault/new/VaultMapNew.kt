package com.aoc.vault.new

import com.aoc.log.AdventLogger
import com.aoc.map.AdventMap2D
import com.aoc.math.Point2D
import com.aoc.vault.VaultTile


class VaultMapNew(initialData: List<String>) : AdventMap2D<VaultTile>() {

    private val keys: Map<Point2D, VaultTile>
    private val graph: UnDirectedGraph<Char>
    private val doors = mutableSetOf<Char>()
    private val visited = mutableListOf<Point2D>()
    private val next = mutableMapOf<Point2D, VaultTile>()
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
            depthFirstSearch(it.value.value, it)
        }

        AdventLogger.info(this)
        AdventLogger.info(graph)
    }

    fun collectKeys() {
        //Do we need to do a DFS instead and pre-compute the passed doors for our graph?
    }

    fun depthFirstSearch(sourceKey: Char, tile: Map.Entry<Point2D, VaultTile>) {
        visited.add(tile.key)
        next.clear()

        //Get Un-Visited Adjacent Points
        val adjacentPositions = tile.key.adjacentPoints().filter { it !in visited }

        val adjacentTiles = filterPoints(adjacentPositions).filter { !it.value.isWall() }

        //Record Doors
        adjacentTiles.filterValues { it.isDoor() }.forEach {
            doors.add(it.value.value)
        }

        //Map Keys -> Graph w/Current Steps Weighting
        adjacentTiles.filterValues { it.isKey() }.forEach {
            graph.addEdge(sourceKey, it.value.value, steps, doors)
        }

        //Add Entrance, Empty, Keys, Doors Up-Next
        next.putAll(adjacentTiles)

        if (next.isEmpty()) {
            doors.clear()
            steps--
        } else {
            steps++
        }

        next.forEach {
            depthFirstSearch(sourceKey, it)
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