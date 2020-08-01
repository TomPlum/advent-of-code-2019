package com.aoc.intcode.droid.cryo.map

import assertk.assertThat
import assertk.assertions.*
import com.aoc.intcode.droid.cryo.droid.Item
import com.aoc.intcode.droid.cryo.map.Room
import com.aoc.math.Direction.*
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
            val room = Room("Kitchen", "Everything's freeze-dried.", emptyList(), mutableListOf(Item("knife")))
            val hasItems = room.hasItems()
            assertThat(hasItems).isTrue()
        }

        @Test
        fun doesNotHaveItems() {
            val room = Room("Kitchen", "Everything's freeze-dried.", emptyList(), mutableListOf())
            val hasItems = room.hasItems()
            assertThat(hasItems).isFalse()
        }
    }

    @Nested
    inner class HasDoorLeadingTo {
        @Test
        fun hasTheDoor() {
            val room = Room("Kitchen", "Everything's freeze-dried.", mutableListOf(UP), mutableListOf())
            val hasTheDoor = room.hasDoorLeading(UP)
            assertThat(hasTheDoor).isTrue()
        }

        @Test
        fun doesNotHaveTheDoor() {
            val room = Room("Kitchen", "Everything's freeze-dried.", mutableListOf(UP), mutableListOf())
            val hasTheDoor = room.hasDoorLeading(RIGHT)
            assertThat(hasTheDoor).isFalse()
        }
    }

    @Nested
    inner class TakeItem {
        @Test
        fun validItemName() {
            val room = Room("Kitchen", "Everything's freeze-dried.", emptyList(), mutableListOf(Item("knife")))
            val item = room.takeItem(Item("knife"))
            assertThat(item).isEqualTo(Item("knife"))
        }

        @ParameterizedTest
        @EmptySource
        @ValueSource(strings = ["RollingPin", "rollingpin", "rolling-pin"])
        @DisplayName("Given the room has an item, when the item name does not match, then it should return null")
        fun invalidItemName(itemName: String) {
            val room = Room("Kitchen", "Everything's freeze-dried.", emptyList(), mutableListOf(Item("rolling pin")))
            val item = room.takeItem(Item(itemName))
            assertThat(item).isNull()
        }
    }

    @Nested
    inner class EmptyRoom {
        @Test
        fun name() {
            assertThat(Room.empty().name).isEqualTo("N/A")
        }

        @Test
        fun description() {
            assertThat(Room.empty().description).isEqualTo("An un-explored room")
        }

        @Test
        fun doors() {
            assertThat(Room.empty().doors).containsOnly(UP)
        }

        @Test
        fun items() {
            assertThat(Room.empty().items).isEmpty()
        }
    }

    @Nested
    inner class ToString {
        @Test
        fun noItems() {
            val room = Room("Kitchen", "Everything's freeze-dried.", listOf(UP, LEFT, RIGHT), mutableListOf())
            assertThat(room.toString()).isEqualTo("-Kitchen\nEverything's freeze-dried.\n")
        }

        @Test
        fun oneItem() {
            val room = Room("Kitchen", "Everything's freeze-dried.", listOf(UP, LEFT, RIGHT), mutableListOf(Item("knife")))
            assertThat(room.toString()).isEqualTo("-Kitchen\nEverything's freeze-dried.\nUpon entering the room you find: Knife")
        }

        @Test
        fun multipleItems() {
            val room = Room("Kitchen", "Everything's freeze-dried.", listOf(UP, LEFT, RIGHT), mutableListOf(Item("knife"), Item("board")))
            assertThat(room.toString()).isEqualTo("-Kitchen\nEverything's freeze-dried.\nUpon entering the room you find: Knife, Board")
        }
    }

    @Nested
    inner class Equality {
        @Test
        fun equal() {
            val first = Room("Kitchen", "Everything's freeze-dried.", listOf(UP, LEFT, RIGHT), mutableListOf())
            val second = Room("Kitchen", "Everything's freeze-dried.", listOf(UP, LEFT, RIGHT), mutableListOf())
            assertThat(first).isEqualTo(second)
        }

        @Test
        fun notEqualDifferentNames() {
            val first = Room("Kitchen", "Everything's freeze-dried.", listOf(UP, LEFT, RIGHT), mutableListOf())
            val second = Room("Not Kitchen", "Everything's freeze-dried.", listOf(UP, LEFT, RIGHT), mutableListOf())
            assertThat(first).isNotEqualTo(second)
        }

        @Test
        fun notEqualDifferentDescriptions() {
            val first = Room("Kitchen", "Everything's freeze-dried.", listOf(UP, LEFT, RIGHT), mutableListOf())
            val second = Room("Kitchen", "Everything's fire-wet.", listOf(UP, LEFT, RIGHT), mutableListOf())
            assertThat(first).isNotEqualTo(second)
        }

        @Test
        fun notEqualDifferentDoors() {
            val first = Room("Kitchen", "Everything's freeze-dried.", listOf(UP, LEFT, RIGHT), mutableListOf())
            val second = Room("Kitchen", "Everything's freeze-dried.", listOf(UP), mutableListOf())
            assertThat(first).isNotEqualTo(second)
        }

        @Test
        fun notEqualDifferentItems() {
            val first = Room("Kitchen", "Everything's freeze-dried.", listOf(UP, LEFT, RIGHT), mutableListOf())
            val second = Room("Kitchen", "Everything's freeze-dried.", listOf(UP, LEFT, RIGHT), mutableListOf(Item("knife")))
            assertThat(first).isNotEqualTo(second)
        }
    }
}