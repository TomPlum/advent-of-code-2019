package com.aoc.maze.donut.planar.portal

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import com.aoc.math.Point2D
import org.junit.jupiter.api.Test

class WarpCode2DTest {
    @Test
    fun getPositions() {
        val warpCode = WarpCode2D('B', Point2D(10, 12), 'C', Point2D(12, 13))
        assertThat(warpCode.getPositions()).isEqualTo(setOf(Point2D(10,12), Point2D(12,13)))
    }

    @Test
    fun isEntrancePositive() {
        assertThat(WarpCode2D('A', Point2D(0, 0), 'A', Point2D(0, 1)).isEntrance()).isTrue()
    }

    @Test
    fun isEntranceNegative() {
        assertThat(WarpCode2D('B', Point2D(0, 0), 'A', Point2D(0, 1)).isEntrance()).isFalse()
    }

    @Test
    fun isExitPositive() {
        assertThat(WarpCode2D('Z', Point2D(0, 0), 'Z', Point2D(0, 1)).isExit()).isTrue()
    }

    @Test
    fun isExitNegative() {
        assertThat(WarpCode2D('G', Point2D(0, 0), 'Z', Point2D(0, 1)).isExit()).isFalse()
    }

    @Test
    fun isMatchingPositiveWhenCodesInSameOrder() {
        val first = WarpCode2D('H', Point2D(4, 5), 'I', Point2D(3, 5))
        val second = WarpCode2D('H', Point2D(12, 14), 'I', Point2D(12, 15))
        assertThat(first.isMatching(second)).isTrue()
    }

    @Test
    fun isMatchingPositiveWhenCodesInDifferentOrder() {
        val first = WarpCode2D('H', Point2D(4, 5), 'I', Point2D(3, 5))
        val second = WarpCode2D('I', Point2D(12, 14), 'H', Point2D(12, 15))
        assertThat(first.isMatching(second)).isTrue()
    }

    @Test
    fun isMatchingNegativeWhenCodesDiffer() {
        val first = WarpCode2D('H', Point2D(4, 5), 'I', Point2D(3, 5))
        val second = WarpCode2D('B', Point2D(12, 14), 'H', Point2D(12, 15))
        assertThat(first.isMatching(second)).isFalse()
    }

    @Test
    fun isMatchingNegativeWhenPositionsSame() {
        val first = WarpCode2D('H', Point2D(4, 5), 'I', Point2D(4, 6))
        val second = WarpCode2D('H', Point2D(4, 5), 'I', Point2D(4, 6))
        assertThat(first.isMatching(second)).isFalse()
    }

    @Test
    fun toStringTest() {
        assertThat(WarpCode2D('T', Point2D(12, 14), 'U', Point2D(13, 14)).toString()).isEqualTo("TU")
    }
}