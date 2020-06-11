package com.aoc.map

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import com.aoc.math.Point3D
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException
import java.util.*

class AdventMap3DTest {
    @Test
    fun addTile() {
        val map = ExampleMap()
        val tile = ExampleTile(6)
        map.addExampleTile(Point3D(3, 4, 0), tile)
        assertThat(map.getExampleTile(Point3D(3, 4, 0))).isEqualTo(tile)
    }

    @Test
    fun addTileAtExistingPointShouldUpdate() {
        val map = ExampleMap()
        map.addExampleTile(Point3D(3, 4, 2), ExampleTile(6))
        map.addExampleTile(Point3D(3, 4, 2), ExampleTile(23))
        assertThat(map.getExampleTile(Point3D(3, 4, 2)).value).isEqualTo(23)
    }

    @Test
    @DisplayName("Given a Map with some tiles, when getting a tile that exists, then it should return that tile")
    fun getTilePositive() {
        val map = ExampleMap()
        map.addExampleTile(Point3D(3, 4, 12), ExampleTile(4))
        map.addExampleTile(Point3D(2, 12, 4), ExampleTile(1))
        assertThat(map.getExampleTile(Point3D(2, 12, 4)).toString()).isEqualTo("1")
    }

    @Test
    fun getTileThatDoesntExist() {
        val e = assertThrows<IllegalArgumentException> { ExampleMap().getExampleTile(Point3D(0, 0, 0)) }
        assertThat(e.message).isEqualTo("Map does not contain tile at (0, 0, 0)")
    }

    @Test
    fun hasRecordedPositive() {
        val map = ExampleMap()
        map.addExampleTile(Point3D(3, 4, 5), ExampleTile(4))
        assertThat(map.hasMapped(Point3D(3, 4, 5))).isTrue()
    }

    @Test
    fun hasRecordedNegative() {
        val map = ExampleMap()
        map.addExampleTile(Point3D(3, 4, 2), ExampleTile(4))
        assertThat(map.hasMapped(Point3D(5, 8, 0))).isFalse()
    }

    @Test
    fun filterTiles() {
        val map = ExampleMap()
        map.addExampleTile(Point3D(3, 4, 1), ExampleTile(4))
        val targetTile = ExampleTile(12)
        map.addExampleTile(Point3D(0, 0, 0), targetTile)
        assertThat(map.getTiles { it.isMyTestValue() }).isEqualTo(mapOf(Pair(Point3D(0, 0, 0), targetTile)))
    }

    @Test
    fun filterPoints() {
        val map = ExampleMap()
        for (z in 0..100) {
            for (y in 0..100) {
                for (x in 0..100) {
                    map.addExampleTile(Point3D(x, y, z), ExampleTile(y))
                }
            }
        }
        val tiles = map.getTilesAtPositions(setOf(Point3D(17, 4, 0), Point3D(56, 86, 1), Point3D(100, 100, 2), Point3D(25, 99, 3)))
        assertThat(tiles).isEqualTo(mapOf(Pair(Point3D(17,4,0), ExampleTile(4)), Pair(Point3D(56,86,1), ExampleTile(86)),
                Pair(Point3D(25,99,3), ExampleTile(99)), Pair(Point3D(100,100,2), ExampleTile(100))))
    }

    @Test
    fun filterPointsShouldIgnoreUnrecordedTiles() {
        val map = ExampleMap()
        for (z in 0..2) {
            for (y in 0..10) {
                for (x in 0..10) {
                    map.addExampleTile(Point3D(x, y, z), ExampleTile(y))
                }
            }
        }
        val tiles = map.getTilesAtPositions(setOf(Point3D(17, 4, 3), Point3D(1, 2, 0)))
        assertThat(tiles).isEqualTo(mapOf(Pair(Point3D(1,2,0), ExampleTile(2))))
    }

    private class ExampleTile(private val data: Int) : MapTile<Int>(data) {
        fun isMyTestValue() = data == 12

        override fun equals(other: Any?): Boolean {
            if (other !is ExampleTile) return false
            return data == other.data
        }

        override fun hashCode(): Int {
            return Objects.hashCode(data)
        }
    }

    private class ExampleMap : AdventMap3D<ExampleTile>() {
        fun addExampleTile(pos: Point3D, default: ExampleTile) = addTile(pos, default)
        fun getExampleTile(pos: Point3D) = getTile(pos)
        fun hasMapped(pos: Point3D) = hasRecorded(pos)
        fun getTilesAtPositions(positions: Set<Point3D>) = filterPoints(positions)
        fun getTiles(predicate: (ExampleTile) -> Boolean) = filterTiles(predicate)
    }
}