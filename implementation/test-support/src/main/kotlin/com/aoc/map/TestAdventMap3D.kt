package com.aoc.map

import com.aoc.math.Point3D

class TestAdventMap3D : AdventMap3D<TestMapTile>() {
    fun addExampleTile(pos: Point3D, default: TestMapTile) = addTile(pos, default)
    fun getExampleTile(pos: Point3D) = getTile(pos)
    fun hasMapped(pos: Point3D) = hasRecorded(pos)
    fun getTilesAtPositions(positions: Set<Point3D>) = filterPoints(positions)
    fun getTiles(predicate: (TestMapTile) -> Boolean) = filterTiles(predicate)
}