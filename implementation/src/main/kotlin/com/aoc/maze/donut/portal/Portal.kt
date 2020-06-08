package com.aoc.maze.donut.portal

import com.aoc.log.AdventLogger
import com.aoc.math.Point2D
import java.lang.IllegalArgumentException
import com.aoc.maze.donut.PlutonianMaze

/**
 * A space-time folding warp-gate in a [PlutonianMaze].
 *
 * @param entrances The two tiles in the [PlutonianMaze] that the portal teleports between.
 */
data class Portal(val entrances: Pair<PortalEntrance, PortalEntrance>) {

    var level = 0

    /**
     * @return true if the portal warps either to or from the given [pos].
     */
    fun hasEntrance(pos: Point2D): Boolean = entrances.first.position == pos || entrances.second.position == pos

    /**
     * @return The entrance with with the given [position].
     * @throws IllegalArgumentException if the portal does not have an entrance with the given [position].
     */
    fun getEntrance(position: Point2D) = entrances.toList()
                .find { it.position == position } ?: throw IllegalArgumentException("$this does not warp to or from $position")


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
            when {
                other.isOuter() -> level++
                else -> level--
            }
            AdventLogger.debug("Warping from $entrancePosition -> $other through ${other.warpCode}")
            return other.position
        }
        throw IllegalArgumentException("$this does not warp to or from $entrancePosition")
    }

    override fun toString() = "${entrances.first.warpCode}${entrances.first}<->${entrances.second}"
}