package com.aoc.intcode.droid.cryo

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.math.Direction.*
import org.junit.jupiter.api.Test

class RoomTest {
    @Test
    fun toStringTest() {
        val room = Room("Kitchen", listOf(UP, LEFT, RIGHT), mutableListOf(Item("knife")))
        assertThat(room.toString()).isEqualTo(
                "┌───────      ───────┐\n" +
                "│      Kitchen       │\n" +
                "│                    │\n" +
                "                      \n" +
                "                      \n" +
                "│    Escape Pod      │\n" +
                "│                    │\n" +
                "└────────────────────┘\n"
        )
    }
}