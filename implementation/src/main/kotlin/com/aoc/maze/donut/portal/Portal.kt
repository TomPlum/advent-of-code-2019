package com.aoc.maze.donut.portal

import com.aoc.math.Point2D
import java.lang.IllegalArgumentException
import com.aoc.maze.donut.PlutonianMaze

/**
 * A space-time folding warp-gate in a [PlutonianMaze].
 *
 * @param entrances The two tiles in the [PlutonianMaze] that the portal teleports between.
 */
data class Portal(val entrances: Pair<PortalEntrance, PortalEntrance>) {

    fun hasEntrance(pos: Point2D): Boolean = entrances.first.position == pos || entrances.second.position == pos

    fun warp(entrance: Point2D): Point2D {
        val points = entrances.toList().map { it.position }
        if (points.contains(entrance)) {
            return points.find { it != entrance }!!
        }
        throw IllegalArgumentException("$this does not warp to or from $entrance")
    }

    override fun toString() = "${entrances.first.warpCode}${entrances.first}<->${entrances.second}"
}