package map

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import math.Point2D
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class MapTest {
    @Test
    fun addTile() {
        val map = ExampleMap()
        val tile = ExampleTile(6)
        map.addTile(Point2D(3, 4), tile)
        assertThat(map.getTile(Point2D(3, 4), ExampleTile(0))).isEqualTo(tile)
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
        assertThat(map.tileQuantity()).isEqualTo(2)
    }

    @Test
    fun tileQuantityWhenEmpty() {
        assertThat(ExampleMap().tileQuantity()).isEqualTo(0)
    }

    @Test
    fun filterTiles() {
        val map = ExampleMap()
        map.addTile(Point2D(3, 4), ExampleTile(4))
        val targetTile = ExampleTile(12)
        map.addTile(Point2D(0, 0), targetTile)
        assertThat(map.filterTiles { it.isMyTestValue() }).isEqualTo(mapOf(Pair(Point2D(0, 0), targetTile)))
    }

    private class ExampleTile(private val data: Int) : MapTile<Int>(data) {
        fun isMyTestValue() = data == 12
    }

    private class ExampleMap : Map<ExampleTile>()
}