package com.aoc.intcode.droid.repair

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import com.aoc.math.Point2D
import org.junit.jupiter.api.Test

class ShipFloorMapTest {
    @Test
    fun newMapShouldHaveDefaultDroidLocation() {
        assertThat(ShipFloorMap().tileType(Point2D(0, 0))).isEqualTo(ShipFloorTile.DROID)
    }

    @Test
    fun updateUnknownTileShouldAdd() {
        val emptyMap = ShipFloorMap()
        emptyMap.addShipTile(Point2D(0, -1), ShipFloorTile.TRAVERSABLE)
        assertThat(emptyMap.tileType(Point2D(0, -1))).isEqualTo(ShipFloorTile.TRAVERSABLE)
    }

    @Test
    fun updateKnownTileShouldReplace() {
        val emptyMap = ShipFloorMap()
        emptyMap.addShipTile(Point2D(0, -1), ShipFloorTile.TRAVERSABLE)
        emptyMap.addShipTile(Point2D(0, -1), ShipFloorTile.OXYGEN_SYSTEM)
        assertThat(emptyMap.tileType(Point2D(0, -1))).isEqualTo(ShipFloorTile.OXYGEN_SYSTEM)
    }

    @Test
    fun hasRecordedPositive() {
        assertThat(ShipFloorMap().hasRecordedShipTile(Point2D(0, 0))).isTrue()
    }

    @Test
    fun hasRecordedNegative() {
        assertThat(ShipFloorMap().hasRecordedShipTile(Point2D(12, -15))).isFalse()
    }

    @Test
    fun tileTypeWhenExists() {
        assertThat(ShipFloorMap().tileType(Point2D(0, 0))).isEqualTo(ShipFloorTile.DROID)
    }

    @Test
    fun tileTypeWhenDoesNotExist() {
        assertThat(ShipFloorMap().tileType(Point2D(8, 2))).isEqualTo(ShipFloorTile.UNKNOWN)
    }

    @Test
    fun isNotCompletelyOxygenatedWhenShipHasTraversableTiles() {
        val map = ShipFloorMap()
        map.addShipTile(Point2D(0,0), ShipFloorTile.TRAVERSABLE)
        assertThat(map.isNotCompletelyOxygenated()).isTrue()
    }

    @Test
    fun isNotCompletelyOxygenatedWhenShipHasOnlyOxygenatedTiles() {
        val map = ShipFloorMap()
        map.addShipTile(Point2D(0,0), ShipFloorTile.OXYGENATED)
        assertThat(map.isNotCompletelyOxygenated()).isFalse()
    }

    @Test
    fun toStringNewMap() {
        assertThat(ShipFloorMap().toString()).isEqualTo("D\n")
    }
}