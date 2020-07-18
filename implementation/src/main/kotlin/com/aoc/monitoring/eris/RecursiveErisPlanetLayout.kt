package com.aoc.monitoring.eris

import com.aoc.map.AdventMap3D
import com.aoc.math.Point2D
import com.aoc.math.Point3D

class RecursiveErisPlanetLayout(scanData: List<String>) : AdventMap3D<ErisScanTile>() {

    init {
        var x = 0
        var y = 0
        scanData.forEach { row ->
            row.forEach { tile ->
                addTile(Point3D(x, y, 0), ErisScanTile(tile))
                x++
            }
            x = 0
            y++
        }

        (1..150).forEach { z ->
            (0..4).forEach { y ->
                (0..4).forEach {x ->
                    addTile(Point3D(x, y, -z), ErisScanTile.empty())
                    addTile(Point3D(x, y, z), ErisScanTile.empty())
                }
            }
        }
    }

    fun getAdjacentPositions(sourcePos: Point3D): List<Point3D> {
       return sourcePos.planarAdjacentPoints().flatMap { pos ->
           when {
               pos.isOutsideGrid() -> pos.convertFromOutside().toList()
               pos.isCentreTile() -> sourcePos.convertFromCentre()
               else -> pos.toList()
           }
        }
    }

    fun getDyingBugs(): List<Point3D> = filterTiles { it.isBug() }.keys.filter { bug ->
        val adjacent = getAdjacentPositions(bug).toSet()
        return@filter filterPoints(adjacent).values.count { it.isBug() } != 1
    }

    fun getInfestedTiles(): List<Point3D> = filterTiles { it.isEmpty() }.keys.filter { space ->
        val adjacent = getAdjacentPositions(space).toSet()
        val adjacentBugs = filterPoints(adjacent).values.count { it.isBug() }
        return@filter adjacentBugs == 1 || adjacentBugs == 2
    }

    fun kill(bugPositions: List<Point3D>) = bugPositions.forEach { bug -> addTile(bug, ErisScanTile.empty()) }

    fun infest(emptyPositions: List<Point3D>) = emptyPositions.filter {
        !it.isCentreTile()
    }.forEach { space -> addTile(space, ErisScanTile.bug()) }

    fun getBugQuantity() = filterTiles { it.isBug() }.count()

    private fun centre() = Point2D(2,2)

    private fun Point3D.isCentreTile() = this.x == 2 && this.y == 2

    private fun Point3D.isOutsideGrid() = this.x < xMin() || this.x > xMax() || this.y < yMin() || this.y > yMax()

    private fun Point3D.toList() = listOf(this)

    private fun Point3D.convertFromOutside(): Point3D {
        return when {
            this.x < xMin() -> Point3D(1, 2, this.z - 1)
            this.x > xMax() -> Point3D(3, 2, this.z - 1)
            this.y < yMin() -> Point3D(2, 1, this.z - 1)
            this.y > yMax() -> Point3D(2, 3, this.z - 1)
            else -> throw IllegalStateException("A Point3D that was deemed outside the grid is invalid: $this")
        }
    }

    private fun Point3D.convertFromCentre(): List<Point3D> {
        return when {
            this.x < centre().x -> (yMin()..yMax()).map { Point3D(0, it, this.z + 1) }
            this.x > centre().x -> (yMin()..yMax()).map { Point3D(4, it, this.z + 1) }
            this.y < centre().y -> (xMin()..xMax()).map { Point3D(it, 0, this.z + 1) }
            this.y > centre().y -> (xMin()..xMax()).map { Point3D(it, 4, this.z + 1) }
            else -> throw IllegalStateException("A Point3D that was deemed in the centre grid is invalid. Source: $this")
        }
    }

    override fun xMin(): Int = 0
    override fun yMin(): Int = 0
    override fun xMax(): Int = 4
    override fun yMax(): Int = 4
}