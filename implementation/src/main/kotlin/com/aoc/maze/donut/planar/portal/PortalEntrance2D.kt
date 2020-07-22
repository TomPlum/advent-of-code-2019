package com.aoc.maze.donut.planar.portal

import com.aoc.math.Point2D
import com.aoc.maze.donut.planar.PlanarDonutMaze

/**
 * The entrance to a [Portal2D] in a [PlanarDonutMaze].
 *
 * @param warpCode The two tiles in the [PlanarDonutMaze] that represent the portals name. E.g 'FG'
 * @param position The tile which upon stepping on, warps you to the other portal entrance.
 */
class PortalEntrance2D(val warpCode: WarpCode2D, val position: Point2D) {
    override fun toString() = position.toString()
}