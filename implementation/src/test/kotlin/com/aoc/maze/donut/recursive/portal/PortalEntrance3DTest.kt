package com.aoc.maze.donut.recursive.portal

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import com.aoc.math.Point3D
import org.junit.jupiter.api.Test

class PortalEntrance3DTest {
    @Test
    fun isOuterNearAxisY() {
        val warpCode = WarpCode3D('B', Point3D(0, 12, 0), 'C', Point3D(1, 12, 0))
        assertThat(PortalEntrance3D(warpCode, Point3D(2, 12, 0), 25, 25).isOuter()).isTrue()
    }

    @Test
    fun isOuterNearAxisX() {
        val warpCode = WarpCode3D('B', Point3D(15, 0, 0), 'C', Point3D(15, 1, 0))
        assertThat(PortalEntrance3D(warpCode, Point3D(15, 2, 0), 25, 25).isOuter()).isTrue()
    }

    @Test
    fun isOuterNearRightEdge() {
        val warpCode = WarpCode3D('B', Point3D(25, 5, 0), 'C', Point3D(24, 5, 0))
        assertThat(PortalEntrance3D(warpCode, Point3D(23, 5, 0), 25, 25).isOuter()).isTrue()
    }

    @Test
    fun isOuterNearBottomEdge() {
        val warpCode = WarpCode3D('B', Point3D(12, 25, 0), 'C', Point3D(12, 24, 0))
        assertThat(PortalEntrance3D(warpCode, Point3D(12, 23, 0), 25, 25).isOuter()).isTrue()
    }

    @Test
    fun isOuterFalse() {
        val warpCode = WarpCode3D('B', Point3D(15, 4, 0), 'C', Point3D(15, 5, 0))
        assertThat(PortalEntrance3D(warpCode, Point3D(15, 6, 0), 25, 25).isOuter()).isFalse()
    }

    @Test
    fun toStringTest() {
        val warpCode = WarpCode3D('B', Point3D(15, 4, 0), 'C', Point3D(15, 5, 0))
        assertThat(PortalEntrance3D(warpCode, Point3D(15, 6, 0), 25, 25).toString()).isEqualTo("(15, 6, 0)")
    }
}