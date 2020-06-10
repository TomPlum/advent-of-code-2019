package com.aoc.maze.donut.recursive.portal

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import com.aoc.math.Point3D
import org.junit.jupiter.api.Test

class WarpCode3DTest {
    @Test
    fun getPositions() {
        val warpCode = WarpCode3D('B', Point3D(10, 12, 0), 'C', Point3D(12, 13, 0))
        assertThat(warpCode.getPositions()).isEqualTo(setOf(Point3D(10,12,0), Point3D(12,13,0)))
    }

    @Test
    fun isEntrancePositive() {
        assertThat(WarpCode3D('A', Point3D(0, 0, 0), 'A', Point3D(0, 1, 0)).isEntrance()).isTrue()
    }

    @Test
    fun isEntranceNegative() {
        assertThat(WarpCode3D('B', Point3D(0, 0, 0), 'A', Point3D(0, 1, 0)).isEntrance()).isFalse()
    }

    @Test
    fun isExitPositive() {
        assertThat(WarpCode3D('Z', Point3D(0, 0, 0), 'Z', Point3D(0, 1, 0)).isExit()).isTrue()
    }

    @Test
    fun isExitNegative() {
        assertThat(WarpCode3D('G', Point3D(0, 0, 0), 'Z', Point3D(0, 1, 0)).isExit()).isFalse()
    }

    @Test
    fun isMatchingPositiveWhenCodesInSameOrder() {
        val first = WarpCode3D('H', Point3D(4, 5, 0), 'I', Point3D(3, 5, 0))
        val second = WarpCode3D('H', Point3D(12, 14, 0), 'I', Point3D(12, 15, 0))
        assertThat(first.isMatching(second)).isTrue()
    }

    @Test
    fun isMatchingPositiveWhenCodesInDifferentOrder() {
        val first = WarpCode3D('H', Point3D(4, 5, 0), 'I', Point3D(3, 5, 0))
        val second = WarpCode3D('I', Point3D(12, 14, 0), 'H', Point3D(12, 15, 0))
        assertThat(first.isMatching(second)).isTrue()
    }

    @Test
    fun isMatchingNegativeWhenCodesDiffer() {
        val first = WarpCode3D('H', Point3D(4, 5, 0), 'I', Point3D(3, 5, 0))
        val second = WarpCode3D('B', Point3D(12, 14, 0), 'H', Point3D(12, 15, 0))
        assertThat(first.isMatching(second)).isFalse()
    }

    @Test
    fun isMatchingNegativeWhenPositionsSame() {
        val first = WarpCode3D('H', Point3D(4, 5, 0), 'I', Point3D(4, 6, 0))
        val second = WarpCode3D('H', Point3D(4, 5, 0), 'I', Point3D(4, 6, 0))
        assertThat(first.isMatching(second)).isFalse()
    }

    @Test
    fun toStringTest() {
        assertThat(WarpCode3D('T', Point3D(12, 14, 0), 'U', Point3D(13, 14, 0)).toString()).isEqualTo("TU")
    }
}