package com.aoc.maze.donut

import com.aoc.maze.donut.portal.Portal
import com.aoc.maze.donut.portal.WarpCode

/**
 * A map of the surface of Pluto.
 * The [DonutMaze] is a space-warping maze belonging to a long-list Pluto civilization.
 *
 * The entrance to the maze is reached via a [Portal] with the [WarpCode] AA.
 * The exit to the maze is through the [Portal] with [WarpCode] ZZ.
 * Every other portal in the maze has another matching one with the same [WarpCode].
 */
class DonutMaze(data: List<String>) : PlutonianMaze(data) {

    /**
     * Finds the shortest path through the [DonutMaze] using a BFS (Breadth First Search) algorithm.
     * @return steps taken during the shortest path
     */
    override fun findShortestPath(): Int {
        var steps = 0

        val unvisited = mutableSetOf(entrance)
        while (unvisited.isNotEmpty()) {
            //Get Any Portal Entrances
            val lastPortalEntrances = filterPoints(unvisited).filterValues { it.isPortalEntrance() }

            //Get Adjacent Tiles
            val adjacent = adjacentTiles(unvisited)

            //Clear Last
            unvisited.clear()

            //Add Traversable Tiles Up-Next
            val traversable = adjacent.filterValues { it.isTraversable() }.keys
            unvisited.addAll(traversable)

            //If At Portal Entrance, Add It Up-Next
            val portalEntrances = adjacent.filterValues { it.isPortalEntrance() }.keys
            unvisited.addAll(portalEntrances)

            //If Stepped Into Portal, Added Its Exit Up-Next
            val portalExitPositions = lastPortalEntrances.map {
                val entrancePosition = it.key
                val portal = getPortalWithEntrance(entrancePosition)
                portal.warpFrom(entrancePosition)
            }
            unvisited.addAll(portalExitPositions)

            //Update Visited Traversable Tiles & Used Portal Tiles -> Map
            (traversable + lastPortalEntrances.keys + portalExitPositions).forEach { pos -> addTile(pos, DonutTile('o')) }

            //Increment Steps Taken
            steps++

            //AdventLogger.debug("Step $steps")
            //AdventLogger.debug(this)

            //If We're At The Exit, Return Step Count
            if (adjacent.count { it.value.isExit() } == 1) return steps
        }
        return steps
    }
}