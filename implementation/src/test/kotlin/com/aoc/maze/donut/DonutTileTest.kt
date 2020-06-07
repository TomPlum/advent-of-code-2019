package com.aoc.maze.donut

import assertk.assertThat
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.NullAndEmptySource
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

    @Test
    fun isTraversablePositive() {
        assertThat(DonutTile('.').isTraversable()).isTrue()
    }

    @ParameterizedTest
    @ValueSource(chars = ['A', ' ', '@', ','])
    fun isTraversableNegative(value: Char) {
        assertThat(DonutTile(value).isTraversable()).isFalse()
    }

    @Test
    fun isPortalEntrancePositive() {
        assertThat(DonutTile('@').isPortalEntrance()).isTrue()
    }

    @ParameterizedTest
    @ValueSource(chars = ['A', 'a', '#', '.', ' '])
    fun isPortalEntranceNegative(value: Char) {
        assertThat(DonutTile(value).isPortalEntrance()).isFalse()
    }

    @Test
    fun isExit() {
        assertThat(DonutTile('x').isExit()).isTrue()
    }

    @ParameterizedTest
    @ValueSource(chars = ['A', 'a', '#', '.', ' ', 'I', 'O', 'E'])
    fun isExitNegative(value: Char) {
        assertThat(DonutTile(value).isExit()).isFalse()
    }

    @Test
    fun hasBeenTraversedPositive() {
        assertThat(DonutTile('o').hasBeenTraversed()).isTrue()
    }

    @ParameterizedTest
    @ValueSource(chars = ['O', '.', '#', ' ', 'A', 'a', '@'])
    fun hasBeenTraversedNegative(value: Char) {
        assertThat(DonutTile(value).hasBeenTraversed()).isFalse()
    }
}