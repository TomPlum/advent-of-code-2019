package com.aoc.intcode.droid.cryo

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.math.Direction
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
    inner class Print {
        @Test
        fun singleRoom() {
            val map = StarShipMap()
            map.addRoom(Point2D(0, 0), Room("Kitchen", listOf(UP, RIGHT, LEFT), mutableListOf(Item("knife"))))
            assertThat(map.print()).isEqualTo("")
        }
    }
}