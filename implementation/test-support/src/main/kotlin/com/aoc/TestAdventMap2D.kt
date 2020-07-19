package com.aoc

import com.aoc.map.AdventMap2D
import com.aoc.math.Point2D

class TestAdventMap2D : AdventMap2D<TestMapTile>() {
    fun getTileQuantity() = tileQuantity()
    fun addExampleTile(pos: Point2D, default: TestMapTile) = addTile(pos, default)
    fun getExampleTile(pos: Point2D) = getTile(pos)
    fun getExampleTile(pos: Point2D, default: TestMapTile) = getTile(pos, default)
    fun hasMapped(pos: Point2D) = hasRecorded(pos)
    fun hasExampleTile(tile: TestMapTile) = hasTile(tile)
    fun getTilesAtPositions(positions: Set<Point2D>) = filterPoints(positions)
    fun getTiles(predicate: (TestMapTile) -> Boolean) = filterTiles(predicate)
}