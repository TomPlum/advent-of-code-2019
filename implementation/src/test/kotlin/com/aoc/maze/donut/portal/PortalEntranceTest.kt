package com.aoc.maze.donut.portal

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import com.aoc.math.Point2D
import org.junit.jupiter.api.Test

class PortalEntranceTest {
    @Test
    fun isOuterNearAxisY() {
        val warpCode = WarpCode('B', Point2D(0, 12), 'C', Point2D(1, 12))
        assertThat(PortalEntrance(warpCode, Point2D(2, 12), 25, 25).isOuter()).isTrue()
    }

    @Test
    fun isOuterNearAxisX() {
        val warpCode = WarpCode('B', Point2D(15, 0), 'C', Point2D(15, 1))
        assertThat(PortalEntrance(warpCode, Point2D(15, 2), 25, 25).isOuter()).isTrue()
    }

    @Test
    fun isOuterNearRightEdge() {
        val warpCode = WarpCode('B', Point2D(25, 5), 'C', Point2D(24, 5))
        assertThat(PortalEntrance(warpCode, Point2D(23, 5), 25, 25).isOuter()).isTrue()
    }

    @Test
    fun isOuterNearBottomEdge() {
        val warpCode = WarpCode('B', Point2D(12, 25), 'C', Point2D(12, 24))
        assertThat(PortalEntrance(warpCode, Point2D(12, 23), 25, 25).isOuter()).isTrue()
    }

    @Test
    fun isOuterFalse() {
        val warpCode = WarpCode('B', Point2D(15, 4), 'C', Point2D(15, 5))
        assertThat(PortalEntrance(warpCode, Point2D(15, 6), 25, 25).isOuter()).isFalse()
    }

    @Test
    fun toStringTest() {
        val warpCode = WarpCode('B', Point2D(15, 4), 'C', Point2D(15, 5))
        assertThat(PortalEntrance(warpCode, Point2D(15, 6), 25, 25).toString()).isEqualTo("(15, 6)")
    }
}