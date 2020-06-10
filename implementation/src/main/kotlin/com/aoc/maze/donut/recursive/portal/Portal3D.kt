package com.aoc.maze.donut.recursive.portal

import com.aoc.log.AdventLogger
import com.aoc.math.Point3D
import java.lang.IllegalArgumentException
import com.aoc.maze.donut.recursive.RecursiveDonutMaze
import com.aoc.maze.donut.DonutTile

/**
 * A space-time folding warp-gate in a [RecursiveDonutMaze].
 *
 * @param entrances The two [DonutTile] in the [RecursiveDonutMaze] that the portal teleports between.
 */
data class Portal3D(val entrances: Pair<PortalEntrance3D, PortalEntrance3D>) {

    /**
     * @return true if the portal warps either to or from the given [pos].
     */
    fun hasEntrance(pos: Point3D): Boolean = entrances.first.position == pos || entrances.second.position == pos

    /**
     * @return The entrance with with the given [position].
     * @throws IllegalArgumentException if the portal does not have an entrance with the given [position].
     */
    fun getEntrance(position: Point3D) = entrances.toList()
                .find { it.position == position } ?: throw IllegalArgumentException("$this does not warp to or from $position")

    fun warpRecursivelyFrom(entrancePosition: Point3D): Point3D {
        val entrances = entrances.toList()
        val portalEntrancePositions = entrances.map { it.position }

        if (portalEntrancePositions.contains(entrancePosition)) {
            val source = entrances.find { it.position == entrancePosition }!!
            val target = entrances.find { it.position != entrancePosition }!!
            AdventLogger.debug("Warping from $entrancePosition -> $target through ${target.warpCode}")
            val targetPos = target.position
            when {
                source.isInner() -> return Point3D(targetPos.x, targetPos.y, targetPos.z + 1)
                source.isOuter() -> return Point3D(targetPos.x, targetPos.y, targetPos.z - 1)
            }
        }

        throw IllegalArgumentException("$this does not warp to or from $entrancePosition")
    }

    override fun toString() = "${entrances.first.warpCode}${entrances.first}<->${entrances.second}"
}