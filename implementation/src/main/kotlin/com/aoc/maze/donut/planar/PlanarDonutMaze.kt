package com.aoc.maze.donut.planar

import com.aoc.log.AdventLogger
import com.aoc.map.AdventMap2D
import com.aoc.math.Point2D
import com.aoc.maze.donut.DonutTile
import com.aoc.maze.donut.planar.portal.Portal2D
import com.aoc.maze.donut.planar.portal.PortalEntrance2D
import com.aoc.maze.donut.planar.portal.WarpCode2D

/**
 * A map of the surface of Pluto.
 * The [PlanarDonutMaze] is a space-warping maze belonging to a long-list Pluto civilization.
 *
 * The entrance to the maze is reached via a [Portal2D] with the [WarpCode2D] AA.
 * The exit to the maze is through the [Portal2D] with [WarpCode2D] ZZ.
 * Every other portal in the maze has another matching one with the same [WarpCode2D].
 */
class PlanarDonutMaze(data: List<String>) : AdventMap2D<DonutTile>() {
    val entrance: Point2D
    val exit: Point2D
    val portals = mutableSetOf<Portal2D>()

    init {
        var x = 0
        var y = 0
        data.forEach { row ->
            row.forEach { column ->
                addTile(Point2D(x, y), DonutTile(column))
                x++
            }
            x = 0
            y++
        }

        //Get Portal Markers (Denoted By UpperCase Letters)
        val portalMarkers = filterTiles { it.isPortalMarker() }

        //Match Portal Markers -> Warp Codes
        val warpCodes = mutableSetOf<WarpCode2D>()
        portalMarkers.forEach { marker ->
            val matchingMarker = portalMarkers.filter { it.key.isAdjacentTo(marker.key) }.entries.first()
            if (warpCodes.count { it.posOne == matchingMarker.key || it.posTwo == matchingMarker.key } == 0) {
                warpCodes.add(WarpCode2D(marker.value.value, marker.key, matchingMarker.value.value, matchingMarker.key))
            }
        }

        //Match Warp Codes Together
        val warpCodePairs = mutableSetOf<Pair<WarpCode2D, WarpCode2D>>()
        warpCodes.forEach { source ->
            if (!source.isEntrance() && !source.isExit() && warpCodePairs.count { it.first == source || it.second == source } == 0) {
                val matchingPair = warpCodes.first { target -> source.isMatching(target) }
                warpCodePairs.add(Pair(source, matchingPair))
            }
        }

        //Mark Entrance
        val surroundingEntrance = warpCodes.first { it.isEntrance() }.getPositions().flatMap { it.adjacentPoints() }.toSet()
        val entrance = filterPoints(surroundingEntrance).filter { it.value.isTraversable() }.keys.first()
        addTile(entrance, DonutTile('e'))
        this.entrance = entrance

        //Mark Exit
        val surroundingExit = warpCodes.first { it.isExit() }.getPositions().flatMap { it.adjacentPoints() }.toSet()
        val exit = filterPoints(surroundingExit).filter { it.value.isTraversable() }.keys.first()
        addTile(exit, DonutTile('x'))
        this.exit = exit

        //Create Portals & Update Donut Maze
        warpCodePairs.forEach { (firstWarpCode, secondWarpCode) ->
            val surroundingFirst = firstWarpCode.getPositions().flatMap { it.adjacentPoints() }.toSet()
            val firstEntrance = filterPoints(surroundingFirst).filter { it.value.isTraversable() }.keys.first()
            addTile(firstEntrance, DonutTile('@'))

            val surroundingSecond = secondWarpCode.getPositions().flatMap { it.adjacentPoints() }.toSet()
            val secondEntrance = filterPoints(surroundingSecond).filter { it.value.isTraversable() }.keys.first()
            addTile(secondEntrance, DonutTile('@'))

            val firstPortalEntrance = PortalEntrance2D(firstWarpCode, firstEntrance)
            val secondPortalEntrance = PortalEntrance2D(secondWarpCode, secondEntrance)
            portals.add(Portal2D(Pair(firstPortalEntrance, secondPortalEntrance)))
        }

        AdventLogger.debug(this)
        AdventLogger.debug("Entrance: $entrance")
        AdventLogger.debug("Exit: $exit")
        AdventLogger.debug("Maze Contains ${portals.size} Portals: $portals")
    }

    /**
     * Finds the shortest path through the [PlanarDonutMaze] using a BFS (Breadth First Search) algorithm.
     * @return steps taken during the shortest path
     */
    fun findShortestPath(): Int {
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

    /**
     * Find the [Portal2D] that warps to or from the given [position].
     * @throws IllegalArgumentException if the [PlanarDonutMaze] does not contain a [Portal2D] with the given [position]
     */
    private fun getPortalWithEntrance(position: Point2D): Portal2D = portals.find { it.hasEntrance(position) }
            ?: throw IllegalArgumentException("Maze does not contain a portal with an entrance at $position")

}