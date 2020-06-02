package com.aoc.maze.donut.portal

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import com.aoc.math.Point2D
import org.junit.jupiter.api.Test

class PortalEntranceTest {
    @Test
    fun isOuterTrue() {
        val warpCode = WarpCode('B', Point2D(0, 12), 'C', Point2D(1, 12))
        assertThat(PortalEntrance(warpCode, Point2D(2, 12)).isOuter()).isTrue()
    }

    @Test
    fun isOuterFalse() {
        val warpCode = WarpCode('B', Point2D(15, 4), 'C', Point2D(15, 5))
        assertThat(PortalEntrance(warpCode, Point2D(15, 6)).isOuter()).isFalse()
    }

    @Test
    fun toStringTest() {
        val warpCode = WarpCode('B', Point2D(15, 4), 'C', Point2D(15, 5))
        assertThat(PortalEntrance(warpCode, Point2D(15, 6)).toString()).isEqualTo("(15, 6)")
    }
}