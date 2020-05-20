package com.aoc.maze.donut

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import math.Point2D
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

class PortalTest {
    @Test
    fun hasEntrancePositive() {
        val warpCodes = Pair(WarpCode('F', Point2D(4,11), 'G', Point2D(4,10)), WarpCode('F', Point2D(8,0), 'G', Point2D(8,-1)))
        val entrances = Pair(Point2D(4, 12), Point2D(8, 1))
        assertThat(Portal(warpCodes, entrances).hasEntrance(Point2D(8,1))).isTrue()
    }

    @Test
    fun hasEntranceNegative() {
        val warpCodes = Pair(WarpCode('F', Point2D(4,11), 'G', Point2D(4,10)), WarpCode('F', Point2D(8,0), 'G', Point2D(8,-1)))
        val entrances = Pair(Point2D(4, 12), Point2D(8, 1))
        assertThat(Portal(warpCodes, entrances).hasEntrance(Point2D(45,5))).isFalse()
    }

    @Test
    fun warpWithValidEntrance() {
        val warpCodes = Pair(WarpCode('F', Point2D(4,11), 'G', Point2D(4,10)), WarpCode('F', Point2D(8,0), 'G', Point2D(8,-1)))
        val entrances = Pair(Point2D(4, 12), Point2D(8, 1))
        assertThat(Portal(warpCodes, entrances).warp(Point2D(4,12))).isEqualTo(Point2D(8,1))
    }

    @Test
    fun warpWithInvalidEntrance() {
        val warpCodes = Pair(WarpCode('F', Point2D(4,11), 'G', Point2D(4,10)), WarpCode('F', Point2D(8,0), 'G', Point2D(8,-1)))
        val entrances = Pair(Point2D(4, 12), Point2D(8, 1))
        val e = assertThrows<IllegalArgumentException> { Portal(warpCodes, entrances).warp(Point2D(67, 34)) }
        assertThat(e.message).isEqualTo("FG(4, 12)<->(8, 1) does not warp to or from (67, 34)")
    }

    @Test
    fun toStringTest() {
        val warpCodes = Pair(WarpCode('F', Point2D(4,11), 'G', Point2D(4,10)), WarpCode('F', Point2D(8,0), 'G', Point2D(8,-1)))
        val entrances = Pair(Point2D(4, 12), Point2D(8, 1))
        assertThat(Portal(warpCodes, entrances).toString()).isEqualTo("FG(4, 12)<->(8, 1)")
    }
}