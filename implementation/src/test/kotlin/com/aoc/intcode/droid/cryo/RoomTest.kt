package com.aoc.intcode.droid.cryo

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isNull
import assertk.assertions.isTrue
import com.aoc.math.Direction.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EmptySource
import org.junit.jupiter.params.provider.ValueSource

class RoomTest {
    @Nested
    inner class HasItems {
        @Test
        fun hasItems() {
            assertThat(Room("Kitchen", emptyList(), mutableListOf(Item("knife"))).hasItems()).isTrue()
        }

        @Test
        fun doesNotHaveItems() {
            assertThat(Room("Kitchen", emptyList(), mutableListOf()).hasItems()).isFalse()
        }
    }

    @Nested
    inner class TakeItem {
        @Test
        fun validItemName() {
            val room = Room("Kitchen", emptyList(), mutableListOf(Item("knife")))
            val item = room.takeItem(Item("knife"))
            assertThat(item).isEqualTo(Item("knife"))
        }

        @ParameterizedTest
        @EmptySource
        @ValueSource(strings = ["RollingPin", "rollingpin", "rolling-pin"])
        @DisplayName("Given the room has an item, when the item name does not match, then it should return null")
        fun invalidItemName(itemName: String) {
            val room = Room("Kitchen", emptyList(), mutableListOf(Item("rolling pin")))
            val item = room.takeItem(Item(itemName))
            assertThat(item).isNull()
        }
    }

    @Nested
    inner class ToString  {
        @Test
        @Disabled("Not sure if this is needed yet as delegated to StarShipMap")
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
}