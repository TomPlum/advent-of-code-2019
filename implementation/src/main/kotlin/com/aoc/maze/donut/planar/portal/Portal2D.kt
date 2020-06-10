package com.aoc.maze.donut.planar.portal

import com.aoc.log.AdventLogger
import com.aoc.math.Point2D
import com.aoc.maze.donut.DonutTile
import com.aoc.maze.donut.planar.PlanarDonutMaze

/**
 * A space-time folding warp-gate in a [PlanarDonutMaze].
 *
 * @param entrances The two [DonutTile] in the [PlanarDonutMaze] that the portal teleports between.
 */
data class Portal2D(val entrances: Pair<PortalEntrance2D, PortalEntrance2D>) {

    /**
     * @return true if the portal warps either to or from the given [pos].
     */
    fun hasEntrance(pos: Point2D): Boolean = entrances.first.position == pos || entrances.second.position == pos

    /**
     * Warps through space-time to the other side of the portal.
     *
     * @param entrancePosition The coordinate of the portal entrance
     * @return The coordinate of the other side of the portal after travelling through it.
     */
    fun warpFrom(entrancePosition: Point2D): Point2D {
        val portalEntrancePositions = entrances.toList().map { it.position }
        if (portalEntrancePositions.contains(entrancePosition)) {
            val other = entrances.toList().find { it.position != entrancePosition }!!
            AdventLogger.debug("Warping from $entrancePosition -> $other through ${other.warpCode}")
            return other.position
        }
        throw IllegalArgumentException("$this does not warp to or from $entrancePosition")
    }

    override fun toString() = "${entrances.first.warpCode}${entrances.first}<->${entrances.second}"
}