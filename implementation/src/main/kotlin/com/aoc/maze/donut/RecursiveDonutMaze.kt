package com.aoc.maze.donut

import com.aoc.log.AdventLogger

class RecursiveDonutMaze(data: List<String>) : PlutonianMaze(data) {
    private val portalsPerLevel = portals.filter { it.entrances.first.position.z == 0 }.count()

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
            val portalExitPositions = lastPortalEntrances.mapNotNull {
                val entrancePosition = it.key
                val portal = getPortalWithEntrance(entrancePosition)
                val portalEntrance = portal.getEntrance(entrancePosition)
                val currentLevel = portalEntrance.position.z
                if ((portalEntrance.isInner() && currentLevel < portalsPerLevel) || (portalEntrance.isOuter() && currentLevel > 0)) {
                    portal.warpRecursivelyFrom(entrancePosition)
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
            val exit = adjacent.filter { it.value.isExit() }
            if (exit.isNotEmpty() && exit.filterValues { it.isExit() }.keys.any { it.z == 0 }) {
                return steps
            }
        }
        return steps
    }
}