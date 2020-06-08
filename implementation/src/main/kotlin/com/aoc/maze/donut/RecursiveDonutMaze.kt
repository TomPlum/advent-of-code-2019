package com.aoc.maze.donut

import com.aoc.log.AdventLogger
import com.aoc.maze.donut.portal.Portal
import java.util.*

class RecursiveDonutMaze(data: List<String>) : PlutonianMaze(data) {
    private var level = 0

    override fun findShortestPath(): Int {
        var steps = 0

        val unvisited = mutableSetOf(entrance)
        while (unvisited.isNotEmpty()) {
            //Get Any Portal Entrances
            val lastPortalEntrances = filterPoints(unvisited).filterValues { it.isPortalEntrance() }
            if (lastPortalEntrances.isNotEmpty()) {
                clearTraversedTiles()
            }

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
            val portalExitPositions = lastPortalEntrances.mapNotNull {
                val entrancePosition = it.key
                val portal = getPortalWithEntrance(entrancePosition)
                val portalEntrance = portal.getEntrance(entrancePosition)
                if (portalEntrance.isInner() || level > 0) {
                    portal.warpFrom(entrancePosition)
                } else {
                    null
                }
            }
            unvisited.addAll(portalExitPositions)

            //Update Visited Traversable Tiles & Used Portal Tiles -> Map
            (traversable + lastPortalEntrances.keys + portalExitPositions).forEach { pos -> addTile(pos, DonutTile('o')) }

            //Increment Steps Taken
            steps++

            //AdventLogger.debug("Step $steps")
            //AdventLogger.debug(this)
            //AdventLogger.debug("Current No. Consecutive Paths: ${unvisited.size}")

            //If we're at the exit and on the outermost level, return the step count
            if (adjacent.count { it.value.isExit() } == 1) {
                if (lastPortalEntrances.map { getPortalWithEntrance(it.key) }.map { it.level }.any { it == 0 }) {
                    return steps
                }
            }
        }
        return steps
    }
}