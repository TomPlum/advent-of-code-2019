package com.aoc.maze.donut

import assertk.assertThat
import assertk.assertions.isEqualTo
import math.Point2D
import org.junit.jupiter.api.Test

class PortalTest {
    @Test
    fun toStringTest() {
        val warpCodes = Pair(WarpCode('F', Point2D(4,11), 'G', Point2D(4,10)), WarpCode('F', Point2D(8,0), 'G', Point2D(8,-1)))
        val entrances = Pair(Point2D(4, 12), Point2D(8, 1))
        assertThat(Portal(warpCodes, entrances).toString()).isEqualTo("FG(4, 12)<->(8, 1)")
    }
}