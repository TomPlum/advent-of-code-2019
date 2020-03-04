package com.aoc.intcode.arcade

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class TileIDTest {
    @Test
    fun fromValueEmpty() {
        assertThat(TileID.fromValue(0)).isEqualTo(TileID.EMPTY)
    }

    @Test
    fun fromValueWall() {
        assertThat(TileID.fromValue(1)).isEqualTo(TileID.WALL)
    }

    @Test
    fun fromValueBlock() {
        assertThat(TileID.fromValue(2)).isEqualTo(TileID.BLOCK)
    }

    @Test
    fun fromValueHorizontalPaddle() {
        assertThat(TileID.fromValue(3)).isEqualTo(TileID.HORIZONTAL_PADDLE)
    }

    @Test
    fun fromValueBall() {
        assertThat(TileID.fromValue(4)).isEqualTo(TileID.BALL)
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, 5, 10, 101])
    @DisplayName("Given an invalid value, when creating a Tile ID from value, then it should throw an exception")
    fun fromValueInvalid(value: Int) {
        val e = assertThrows<IllegalArgumentException> { TileID.fromValue(value) }
        assertThat(e.message).isEqualTo("Invalid Tile ID ($value)")
    }
}