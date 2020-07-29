package com.aoc.intcode.droid.cryo.command

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.math.Direction
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class MovementCommandTest {
    @Nested
    inner class GetDirection {
        @Test
        fun north() {
            assertThat(MovementCommand("north").getDirection()).isEqualTo(Direction.UP)
        }

        @Test
        fun east() {
            assertThat(MovementCommand("east").getDirection()).isEqualTo(Direction.RIGHT)
        }

        @Test
        fun south() {
            assertThat(MovementCommand("south").getDirection()).isEqualTo(Direction.DOWN)
        }

        @Test
        fun west() {
            assertThat(MovementCommand("west").getDirection()).isEqualTo(Direction.LEFT)
        }
    }
}