package com.aoc.monitoring.eris

import com.aoc.map.AdventMap2D
import com.aoc.math.Point2D
import kotlin.math.pow

data class ErisPlanetLayout(private val scanData: List<String>) : AdventMap2D<ErisScanTile>() {
    init {
        var x = 0
        var y = 0
        scanData.forEach { row ->
            row.forEach { tile ->
                addTile(Point2D(x, y), ErisScanTile(tile))
                x++
            }
            x=0
            y++
        }
    }

    /**
     * A bug dies (becoming an empty space) unless there is exactly one bug adjacent to it.
     * @return The [Point2D]s of the bugs in the current layout that will die after one minute passes.
     */
    fun getDyingBugs(): List<Point2D> = filterTiles { it.isBug() }.keys.filter { bug ->
        val adjacent = bug.adjacentPoints().toSet()
        return@filter filterPoints(adjacent).values.count { it.isBug() } != 1
    }

    /**
     * An empty space becomes infested with a bug if exactly one or two bugs are adjacent to it.
     * @return The [Point2D]s of the empty tiles in the current layout that will become infested after one minute passes.
     */
    fun getInfestedTiles(): List<Point2D> = filterTiles { it.isEmpty() }.keys.filter { space ->
        val adjacent = space.adjacentPoints().toSet()
        val adjacentBugs = filterPoints(adjacent).values.count { it.isBug() }
        return@filter adjacentBugs == 1 || adjacentBugs == 2
    }

    fun kill(bugPositions: List<Point2D>) = bugPositions.forEach { bug -> addTile(bug, ErisScanTile.empty()) }

    fun infest(emptyPositions: List<Point2D>) = emptyPositions.forEach { space -> addTile(space, ErisScanTile.bug()) }

    /**
     * To calculate the biodiversity rating for a layout, consider each tile left-to-right in the top row,
     * then left-to-right in the second row, and so on. Each of these tiles is worth biodiversity points equal to
     * increasing powers of two: 1, 2, 4, 8, 16, 32, and so on.
     * @return The cumulative biodiversity rating for all the bugs in the layout.
     */
    fun getBioDiversityRating(): Long = filterTiles { it.isBug() }.keys
            .sumBy { 2.0.pow( ( (xMax() + 1) * it.y) + it.x).toInt() }.toLong()

    override fun toString(): String = super.toString()
    override fun equals(other: Any?): Boolean = super.equals(other)
    override fun hashCode(): Int = scanData.hashCode()

}