package map

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import math.Point2D
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.util.*

class AdventMapTest {
    @Test
    fun addTile() {
        val map = ExampleMap()
        val tile = ExampleTile(6)
        map.addTile(Point2D(3, 4), tile)
        assertThat(map.getTile(Point2D(3, 4), ExampleTile(0))).isEqualTo(tile)
    }

    @Test
    fun addTileAtExistingPointShouldUpdate() {
        val map = ExampleMap()
        map.addTile(Point2D(3, 4), ExampleTile(6))
        map.addTile(Point2D(3, 4), ExampleTile(23))
        assertThat(map.getTile(Point2D(3, 4)).value).isEqualTo(23)
    }

    @Test
    @DisplayName("Given a Map with some tiles, when getting a tile that exists, then it should return that tile")
    fun getTilePositive() {
        val map = ExampleMap()
        map.addTile(Point2D(3, 4), ExampleTile(4))
        map.addTile(Point2D(2, 12), ExampleTile(1))
        assertThat(map.getTile(Point2D(2, 12), ExampleTile(0)).toString()).isEqualTo("1")
    }

    @Test
    @DisplayName("Given a Map with some tiles, when getting a tile that doesn't exist, then it should return the default")
    fun getTileNegative() {
        val map = ExampleMap()
        map.addTile(Point2D(0, 5), ExampleTile(1))
        assertThat(map.getTile(Point2D(2, 12), ExampleTile(0)).toString()).isEqualTo("0")
    }


    @Test
    fun hasRecordedPositive() {
        val map = ExampleMap()
        map.addTile(Point2D(3, 4), ExampleTile(4))
        assertThat(map.hasRecorded(Point2D(3, 4))).isTrue()
    }

    @Test
    fun hasRecordedNegative() {
        val map = ExampleMap()
        map.addTile(Point2D(3, 4), ExampleTile(4))
        assertThat(map.hasRecorded(Point2D(5, 8))).isFalse()
    }

    @Test
    fun hasTilePositive() {
        val map = ExampleMap()
        val tile = ExampleTile(4)
        map.addTile(Point2D(3, 4), tile)
        assertThat(map.hasTile(tile)).isTrue()
    }

    @Test
    fun hasTileNegative() {
        val map = ExampleMap()
        map.addTile(Point2D(3, 4), ExampleTile(4))
        assertThat(map.hasTile(ExampleTile(1))).isFalse()
    }

    @Test
    fun tileQuantityWhenPositive() {
        val map = ExampleMap()
        map.addTile(Point2D(3, 4), ExampleTile(4))
        map.addTile(Point2D(0, 0), ExampleTile(12))
        assertThat(map.getTileQuantity()).isEqualTo(2)
    }

    @Test
    fun tileQuantityWhenEmpty() {
        assertThat(ExampleMap().getTileQuantity()).isEqualTo(0)
    }

    @Test
    fun filterTiles() {
        val map = ExampleMap()
        map.addTile(Point2D(3, 4), ExampleTile(4))
        val targetTile = ExampleTile(12)
        map.addTile(Point2D(0, 0), targetTile)
        assertThat(map.filterTiles { it.isMyTestValue() }).isEqualTo(mapOf(Pair(Point2D(0, 0), targetTile)))
    }

    @Test
    fun filterPoints() {
        val map = ExampleMap()
        for (y in 0..100) {
            for (x in 0..100) {
                map.addTile(Point2D(x, y), ExampleTile(y))
            }
        }
        val tiles = map.filterPoints(setOf(Point2D(17, 4), Point2D(56, 86), Point2D(100, 100), Point2D(25, 99)))
        assertThat(tiles).isEqualTo(mapOf(Pair(Point2D(17,4), ExampleTile(4)), Pair(Point2D(56,86), ExampleTile(86)),
                Pair(Point2D(25,99), ExampleTile(99)), Pair(Point2D(100,100), ExampleTile(100))))
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

    private class ExampleMap : AdventMap<ExampleTile>() {
        fun getTileQuantity() = tileQuantity()
    }
}