package com.aoc.maze.donut

import assertk.assertThat
import assertk.assertions.isEqualTo
import math.Point2D
import org.junit.jupiter.api.Test

class PortalTest {
    @Test
    fun toStringTest() {
        assertThat(Portal("FG", Pair(Point2D(4,12), Point2D(8,1))).toString()).isEqualTo("FG(4, 12)<->(8, 1)")
    }
}