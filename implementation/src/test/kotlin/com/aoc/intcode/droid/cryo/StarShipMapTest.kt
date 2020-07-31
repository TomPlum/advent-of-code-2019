package com.aoc.intcode.droid.cryo

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import com.aoc.math.Direction.*
import com.aoc.math.Point2D
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class StarShipMapTest {
    @Nested
    inner class AddRoom {
       @Test
       fun addRoomEmptyMap() {
           val map = StarShipMap()
           map.addRoom(Point2D(0, 0), Room("Kitchen", "desc", listOf(UP), mutableListOf(Item("knife"))))
           assertThat(map.rooms()).isEqualTo(1)
       }
    }

    @Nested
    inner class GetRoom {
        @Test
        fun getExistingRoom() {
            val map = StarShipMap()
            map.addRoom(Point2D(0, 0), Room("Kitchen", "desc", listOf(UP), mutableListOf(Item("knife"))))
            assertThat(map.getRoom(Point2D(0, 0))).isEqualTo(Room("Kitchen", "desc", listOf(UP), mutableListOf(Item("knife"))))
        }

        @Test
        fun getRoomEmptyMap() {
            val e = assertThrows<IllegalArgumentException> { StarShipMap().getRoom(Point2D(0, 0)) }
            assertThat(e.message).isEqualTo("Map does not contain tile at (0, 0)")
        }
    }

    @Nested
    inner class HasRoomAt {
        @Test
        fun hasRoom() {
            val map = StarShipMap()
            map.addRoom(Point2D(0, 0), Room("Kitchen", "desc", listOf(UP), mutableListOf(Item("knife"))))
            assertThat(map.hasRoomAt(Point2D(0, 0))).isTrue()
        }

        @Test
        fun doesNotHaveRoom() {
            val map = StarShipMap()
            map.addRoom(Point2D(0, 0), Room("Kitchen", "desc", listOf(UP), mutableListOf(Item("knife"))))
            assertThat(map.hasRoomAt(Point2D(5, 0))).isFalse()
        }
    }

    @Nested
    inner class Display {
        @Test
        fun singleRoom() {
            val map = StarShipMap()
            map.addRoom(Point2D(0, 1), Room("Kitchen", "desc", listOf(UP, RIGHT, LEFT), mutableListOf(Item("knife"))))
            assertThat(map.display()).isEqualTo(
                    "┌────    ────┐\n" +
                    "│  Kitchen   │\n" +
                    "              \n" +
                    "│   knife    │\n" +
                    "└────────────┘"
            )
        }

        @Test
        fun startingRoomShouldDisplayDroidIcon() {
            val map = StarShipMap()
            map.addRoom(Point2D(0, 0), Room("Kitchen", "desc", listOf(UP, RIGHT, LEFT), mutableListOf(Item("knife"))))
            assertThat(map.display()).isEqualTo(
                    "┌────    ────┐\n" +
                    "│  Kitchen   │\n" +
                    "     /\\      \n" +
                    "│   knife    │\n" +
                    "└────────────┘"
            )
        }

        @Test
        fun twoRoomsVerticallyAdjacent() {
            val map = StarShipMap()
            map.addRoom(Point2D(0, 0), Room("Kitchen", "desc", listOf(UP, RIGHT, LEFT), mutableListOf(Item("knife"))))
            map.addRoom(Point2D(0, 1), Room("Hull", "desc", listOf(DOWN, RIGHT, LEFT), mutableListOf()))
            assertThat(map.display()).isEqualTo(
                    "┌────────────┐\n" +
                    "│    Hull    │\n" +
                    "              \n" +
                    "│            │\n" +
                    "├────    ────┤\n" +
                    "│  Kitchen   │\n" +
                    "      /\\     \n" +
                    "│   knife    │\n" +
                    "└────────────┘"
            )
        }

        @Test
        fun twoRoomsHorizontallyAdjacent() {
            val map = StarShipMap()
            map.addRoom(Point2D(0, 0), Room("Kitchen", "desc", listOf(UP, RIGHT, LEFT), mutableListOf(Item("knife"))))
            map.addRoom(Point2D(1, 0), Room("Hull", "desc", listOf(DOWN, RIGHT, LEFT), mutableListOf()))
            assertThat(map.display()).isEqualTo(
                    "┌────    ────┬┬────────────┐\n" +
                    "│  Kitchen   ││    Hull    │\n" +
                    "      /\\                   \n" +
                    "│   knife    ││            │\n" +
                    "└────────────┴┴────    ────┘\n"
            )
        }
    }
}