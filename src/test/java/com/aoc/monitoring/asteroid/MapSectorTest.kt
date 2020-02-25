package com.aoc.monitoring.asteroid

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.EmptySource
import org.junit.jupiter.params.provider.ValueSource

class MapSectorTest {
    @Test
    fun hasAsteroid() {
        val mapSector = MapSector("#", 0, 0)
        val hasAsteroid = mapSector.hasAsteroid()
        assertThat(hasAsteroid).isTrue()
    }

    @Test
    fun isEmpty() {
        val mapSector = MapSector(".", 0, 0)
        val hasAsteroid = mapSector.hasAsteroid()
        assertThat(hasAsteroid).isFalse()
    }

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = ["##", "..", "A", "1"])
    fun invalidContentsValue(contents: String) {
        val e = assertThrows<IllegalArgumentException> { MapSector(contents, 0, 0).hasAsteroid() }
        assertThat(e.message).isEqualTo("Invalid Map Sector Contents '$contents'. Asteroid (#), Empty (.)")
    }

    @ParameterizedTest
    @CsvSource(value = ["0,0,1,1", "0,0, 2,2", "3,3,5,5"], delimiter = ',')
    @DisplayName("Coordinates that are diagonal should be -135deg when target is bottom right")
    fun angleBetweenWhenSectorsAreExactlyDiagonalBottomRight(x1: Int, y1: Int, x2: Int, y2: Int) {
        assertThat(MapSector("#", x1, y1).angleBetween(MapSector("#", x2, y2))).isEqualTo(-135.0)
    }

    @ParameterizedTest
    @CsvSource(value = ["5,0,0,5", "4,4,3,5"], delimiter = ',')
    @DisplayName("Coordinates that are diagonal should be -45deg when target is top right")
    fun angleBetweenWhenSectorsAreExactlyDiagonalTopRight(x1: Int, y1: Int, x2: Int, y2: Int) {
        assertThat(MapSector("#", x1, y1).angleBetween(MapSector("#", x2, y2))).isEqualTo(-45.0)
    }

    @ParameterizedTest
    @CsvSource(value = ["4,5,5,5", "5,5,6,5"], delimiter = ',')
    @DisplayName("Coordinates that have the same y-ordinate should be horizontal at 180deg")
    fun angleBetweenWhenSectorsAreOnTheSameRow(x1: Int, y1: Int, x2: Int, y2: Int) {
        assertThat(MapSector("#", x1, y1).angleBetween(MapSector("#", x2, y2))).isEqualTo(180.0)
    }

    @ParameterizedTest
    @CsvSource(value = ["3,3,3,2", "3,3,3,0", "0,5,0,2", "12,15,12,0"], delimiter = ',')
    @DisplayName("Coordinates that have the same x-ordinate and a lesser y-ordinate should be vertical at 90deg")
    fun angleBetweenWhenTargetSectorIsVerticallyAbove(x1: Int, y1: Int, x2: Int, y2: Int) {
        assertThat(MapSector("#", x1, y1).angleBetween(MapSector("#", x2, y2))).isEqualTo(90.0)
    }

    @ParameterizedTest
    @CsvSource(value = ["3,3,3,7", "3,3,3,21", "0,5,0,10", "12,15,12,16"], delimiter = ',')
    @DisplayName("Coordinates that have the same x-ordinate and a greater y-ordinate should be vertical at -90deg")
    fun angleBetweenWhenTargetSectorIsVerticallyBelow(x1: Int, y1: Int, x2: Int, y2: Int) {
        assertThat(MapSector("#", x1, y1).angleBetween(MapSector("#", x2, y2))).isEqualTo(-90.0)
    }
}