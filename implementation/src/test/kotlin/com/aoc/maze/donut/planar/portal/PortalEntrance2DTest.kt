package com.aoc.maze.donut.planar.portal

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.math.Point2D
import org.junit.jupiter.api.Test

class PortalEntrance2DTest {
    @Test
    fun toStringTest() {
        val warpCode = WarpCode2D('B', Point2D(15, 4), 'C', Point2D(15, 5))
        assertThat(PortalEntrance2D(warpCode, Point2D(15, 6)).toString()).isEqualTo("(15, 6)")
    }
}