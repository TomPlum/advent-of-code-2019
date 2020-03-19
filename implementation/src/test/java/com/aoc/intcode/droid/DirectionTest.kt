package com.aoc.intcode.droid

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class DirectionTest {
    @Test
    fun reverseNorth() {
        assertThat(Direction.NORTH.reverse()).isEqualTo(Direction.SOUTH)
    }

    @Test
    fun reverseEast() {
        assertThat(Direction.EAST.reverse()).isEqualTo(Direction.WEST)
    }

    @Test
    fun reverseSouth() {
        assertThat(Direction.SOUTH.reverse()).isEqualTo(Direction.NORTH)
    }

    @Test
    fun reverseWest() {
        assertThat(Direction.WEST.reverse()).isEqualTo(Direction.EAST)
    }
}