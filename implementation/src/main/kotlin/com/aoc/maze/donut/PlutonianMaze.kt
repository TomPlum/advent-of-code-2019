package com.aoc.maze.donut

import com.aoc.log.AdventLogger
import com.aoc.map.AdventMap
import com.aoc.math.Point2D
import com.aoc.maze.donut.portal.Portal
import com.aoc.maze.donut.portal.WarpCode

abstract class PlutonianMaze(data: List<String>) : AdventMap<DonutTile>() {
    val portals = mutableSetOf<Portal>()
    val entrance: Point2D
    val exit: Point2D

    abstract fun findShortestPath(): Int

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
        val warpCodes = mutableSetOf<WarpCode>()
        portalMarkers.forEach { marker ->
            val matchingMarker = portalMarkers.filter { it.key.isAdjacentTo(marker.key) }.entries.first()
            if (warpCodes.count { it.posOne == matchingMarker.key || it.posTwo == matchingMarker.key } == 0) {
                warpCodes.add(WarpCode(marker.value.value, marker.key, matchingMarker.value.value, matchingMarker.key))
            }
        }

        //Match Warp Codes Together
        val warpCodePairs = mutableSetOf<Pair<WarpCode, WarpCode>>()
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

            portals.add(Portal(Pair(firstWarpCode, secondWarpCode), Pair(firstEntrance, secondEntrance)))
        }

        AdventLogger.debug(this)
        AdventLogger.debug("Entrance: $entrance")
        AdventLogger.debug("Exit: $exit")
        AdventLogger.debug("Maze Contains ${portals.size} Portals: $portals")
    }

    /**
     * Find the [Portal] that warps to or from the given [position].
     * @throws IllegalArgumentException if the [DonutMaze] does not contain a [Portal] with the given [position]
     */
    protected fun getPortalWithEntrance(position: Point2D): Portal = portals.find { it.hasEntrance(position) }
            ?: throw IllegalArgumentException("Maze does not contain a portal with an entrance at $position")
}