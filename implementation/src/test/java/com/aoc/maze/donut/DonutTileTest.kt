package com.aoc.maze.donut

import assertk.assertThat
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DonutTileTest {
    @ParameterizedTest
    @ValueSource(chars = ['A', 'G', 'Z'])
    @DisplayName("Given the Donut Tile value is a single uppercase letter, when checking it it is a Portal Marker," +
    "then it should return true")
    fun isPortalMarkerPositive(value: Char) {
        assertThat(DonutTile(value).isPortalMarker()).isTrue()
    }

    @ParameterizedTest
    @ValueSource(chars = ['a', 'g', 'z', '1', '@'])
    fun isPortalMarkerNegative(value: Char) {
        assertThat(DonutTile(value).isPortalMarker()).isFalse()
    }
}