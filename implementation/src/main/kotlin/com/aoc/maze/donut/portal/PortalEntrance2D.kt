package com.aoc.maze.donut.portal
import com.aoc.math.Point2D

/**
 * The entrance to a [Portal2D] in a [PlutonianMaze].
 *
 * @param warpCode The two tiles in the [PlutonianMaze] that represent the portals name. E.g 'FG'
 * @param position The tile which upon stepping on, warps you to the other portal entrance.
 */
class PortalEntrance2D(val warpCode: WarpCode2D, val position: Point2D) {

    /**
     * @return True if the portal is on the outside of the [PlutonianMaze], false if is on in the inside.
     */
    fun isOuter() = position.distanceFromAxisY() == 2 || position.distanceFromAxisX() == 2

    override fun toString() = position.toString()
}