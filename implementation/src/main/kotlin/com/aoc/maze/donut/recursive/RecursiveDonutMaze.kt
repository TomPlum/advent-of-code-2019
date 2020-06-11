package com.aoc.maze.donut.recursive

import com.aoc.log.AdventLogger
import com.aoc.map.AdventMap3D
import com.aoc.math.Point3D
import com.aoc.maze.donut.DonutTile
import com.aoc.maze.donut.recursive.portal.Portal3D
import com.aoc.maze.donut.recursive.portal.PortalEntrance3D
import com.aoc.maze.donut.recursive.portal.WarpCode3D

class RecursiveDonutMaze(mapData: List<String>) : AdventMap3D<DonutTile>() {
    private val portals = mutableSetOf<Portal3D>()
    private val entrance: Point3D
    private val exit: Point3D

    private val portalsPerLevel: Int

    init {
        var x = 0
        var y = 0

        //TODO: Can we not loop here for the Z-Dimesion and avoid the duplicate() function and portal logic?
        mapData.forEach { row ->
            row.forEach { column ->
                addTile(Point3D(x, y, 0), DonutTile(column))
                x++
            }
            x = 0
            y++
        }

        //Get Portal Markers (Denoted By UpperCase Letters)
        val portalMarkers = filterTiles { it.isPortalMarker() }

        //Match Portal Markers -> Warp Codes
        val warpCodes = mutableSetOf<WarpCode3D>()
        portalMarkers.forEach { marker ->
            val matchingMarker = portalMarkers.filter { it.key.isPlanarAdjacentTo(marker.key) }.entries.first()
            if (warpCodes.count { it.posOne == matchingMarker.key || it.posTwo == matchingMarker.key } == 0) {
                warpCodes.add(WarpCode3D(marker.value.value, marker.key, matchingMarker.value.value, matchingMarker.key))
            }
        }

        //Match Warp Codes Together
        val warpCodePairs = mutableSetOf<Pair<WarpCode3D, WarpCode3D>>()
        warpCodes.forEach { source ->
            if (!source.isEntrance() && !source.isExit() && warpCodePairs.count { it.first == source || it.second == source } == 0) {
                val matchingPair = warpCodes.first { target -> source.isMatching(target) }
                warpCodePairs.add(Pair(source, matchingPair))
            }
        }

        //Mark Entrance
        val surroundingEntrance = warpCodes.first { it.isEntrance() }.getPositions().flatMap { it.planarAdjacentPoints() }.toSet()
        val entrance = filterPoints(surroundingEntrance).filter { it.value.isTraversable() }.keys.first()
        addTile(entrance, DonutTile('e'))
        this.entrance = entrance

        //Mark Exit
        val surroundingExit = warpCodes.first { it.isExit() }.getPositions().flatMap { it.planarAdjacentPoints() }.toSet()
        val exit = filterPoints(surroundingExit).filter { it.value.isTraversable() }.keys.first()
        addTile(exit, DonutTile('x'))
        this.exit = exit

        //Copy Layers (Z-Dimension)
        duplicateTopLayer(warpCodePairs.size)

        val xMax = xMax()
        val yMax = yMax()
        //Create Portals & Update Donut Maze
        warpCodePairs.forEach { (firstWarpCode, secondWarpCode) ->
            (0..warpCodePairs.size).forEach { z ->
                val surroundingFirst = firstWarpCode.getPositions().flatMap { it.planarAdjacentPoints() }
                        .map { Point3D(it.x, it.y, z) }.toSet()
                val firstEntrance = filterPoints(surroundingFirst).filter { it.value.isTraversable() }.keys.first()
                addTile(Point3D(firstEntrance.x, firstEntrance.y, z), DonutTile('@'))

                val surroundingSecond = secondWarpCode.getPositions().flatMap { it.planarAdjacentPoints() }
                        .map { Point3D(it.x, it.y, z) }.toSet()
                val secondEntrance = filterPoints(surroundingSecond).filter { it.value.isTraversable() }.keys.first()
                addTile(Point3D(secondEntrance.x, secondEntrance.y, z), DonutTile('@'))

                val firstPortalEntrance = PortalEntrance3D(firstWarpCode, firstEntrance, xMax, yMax)
                val secondPortalEntrance = PortalEntrance3D(secondWarpCode, secondEntrance, xMax, yMax)
                portals.add(Portal3D(Pair(firstPortalEntrance, secondPortalEntrance)))
            }
        }

        portalsPerLevel = portals.filter { it.entrances.first.position.z == 0 }.count() //TODO: Just same as warpcode pairs size?

        AdventLogger.debug(this.toStringTopLevel())
        AdventLogger.debug("Entrance: $entrance")
        AdventLogger.debug("Exit: $exit")
        AdventLogger.debug("Maze Contains ${portals.size} Portals: $portals")
    }

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

    /**
     * Find the [Portal3D] that warps to or from the given [position].
     * @throws IllegalArgumentException if the [RecursiveDonutMaze] does not contain a [Portal3D] with the given [position]
     */
    private fun getPortalWithEntrance(position: Point3D): Portal3D = portals.find { it.hasEntrance(position) }
            ?: throw IllegalArgumentException("Maze does not contain a portal with an entrance at $position")

}