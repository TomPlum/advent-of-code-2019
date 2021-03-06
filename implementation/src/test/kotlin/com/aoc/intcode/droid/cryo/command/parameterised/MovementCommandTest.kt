package com.aoc.intcode.droid.cryo.command.parameterised

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotEqualTo
import com.aoc.intcode.droid.cryo.command.parameterised.MovementCommand
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

    @Nested
    inner class Encode {
        @Test
        fun encode() {
            assertThat(MovementCommand("north").encode()).isEqualTo(listOf<Long>(110, 111, 114, 116, 104, 10))
        }
    }

    @Nested
    inner class Equality {
        @Test
        fun equal() {
            assertThat(MovementCommand("north")).isEqualTo(MovementCommand("north"))
        }

        @Test
        fun notEqualDifferentCasing() {
            assertThat(MovementCommand("North")).isNotEqualTo(MovementCommand("north"))
        }

        @Test
        fun notEqualDifferentDirection() {
            assertThat(MovementCommand("east")).isNotEqualTo(MovementCommand("north"))
        }
    }
}