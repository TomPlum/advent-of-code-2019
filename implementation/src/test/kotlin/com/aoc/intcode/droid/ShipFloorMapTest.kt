package com.aoc.intcode.droid

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import math.Point2D
import org.junit.jupiter.api.Test

class ShipFloorMapTest {
    @Test
    fun newMapShouldHaveDefaultDroidLocation() {
        assertThat(ShipFloorMap().getTile(Point2D(0, 0), ShipFloorTile.UNKNOWN)).isEqualTo(ShipFloorTile.DROID)
    }

    @Test
    fun updateUnknownTileShouldAdd() {
        val emptyMap = ShipFloorMap()
        emptyMap.addTile(Point2D(0, -1), ShipFloorTile.TRAVERSABLE)
        assertThat(emptyMap.getTile(Point2D(0, -1), ShipFloorTile.UNKNOWN)).isEqualTo(ShipFloorTile.TRAVERSABLE)
    }

    @Test
    fun updateKnownTileShouldReplace() {
        val emptyMap = ShipFloorMap()
        emptyMap.addTile(Point2D(0, -1), ShipFloorTile.TRAVERSABLE)
        emptyMap.addTile(Point2D(0, -1), ShipFloorTile.OXYGEN_SYSTEM)
        assertThat(emptyMap.getTile(Point2D(0, -1), ShipFloorTile.UNKNOWN)).isEqualTo(ShipFloorTile.OXYGEN_SYSTEM)
    }

    @Test
    fun hasRecordedPositive() {
        assertThat(ShipFloorMap().hasRecorded(Point2D(0, 0))).isTrue()
    }

    @Test
    fun hasRecordedNegative() {
        assertThat(ShipFloorMap().hasRecorded(Point2D(12, -15))).isFalse()
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
    fun toStringNewMap() {
        assertThat(ShipFloorMap().toString()).isEqualTo("D\n")
    }
}