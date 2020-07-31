package com.aoc.intcode.droid.cryo

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.log.AdventLogger
import com.aoc.math.Direction.*
import com.aoc.math.Point2D
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class StarShipMapTest {
    @Nested
    inner class AddRoom {
       @Test
       fun addRoomEmptyMap() {
           val map = StarShipMap()
           map.addRoom(Point2D(0, 0), Room("Kitchen", listOf(UP), mutableListOf(Item("knife"))))
           assertThat(map.rooms()).isEqualTo(1)
       }
    }

    @Nested
    inner class Display {
        @Test
        fun singleRoom() {
            val map = StarShipMap()
            map.addRoom(Point2D(0, 0), Room("Kitchen", listOf(UP, RIGHT, LEFT), mutableListOf(Item("knife"))))
            AdventLogger.info(map.display())
            assertThat(map.display()).isEqualTo(
                    "┌────    ────┐\n" +
                    "│  Kitchen   │\n" +
                    "              \n" +
                    "│   knife    │\n" +
                    "└────────────┘"
            )
        }

        @Test
        fun twoRoomsVerticallyAdjacent() {
            val map = StarShipMap()
            map.addRoom(Point2D(0, 0), Room("Kitchen", listOf(UP, RIGHT, LEFT), mutableListOf(Item("knife"))))
            map.addRoom(Point2D(0, 1), Room("Hull", listOf(DOWN, RIGHT, LEFT), mutableListOf()))
            AdventLogger.info(map.display())
        }

        @Test
        fun twoRoomsHorizontallyAdjacent() {
            val map = StarShipMap()
            map.addRoom(Point2D(0, 0), Room("Kitchen", listOf(UP, RIGHT, LEFT), mutableListOf(Item("knife"))))
            map.addRoom(Point2D(1, 0), Room("Hull", listOf(DOWN, RIGHT, LEFT), mutableListOf()))
            AdventLogger.info(map.display())
        }
    }
}