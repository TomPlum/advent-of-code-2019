package com.aoc.maze.donut.portal

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import com.aoc.math.Point2D
import com.aoc.maze.donut.portal.WarpCode
import org.junit.jupiter.api.Test

class WarpCodeTest {
    @Test
    fun getPositions() {
        val warpCode = WarpCode('B', Point2D(10, 12), 'C', Point2D(12, 13))
        assertThat(warpCode.getPositions()).isEqualTo(setOf(Point2D(10,12), Point2D(12,13)))
    }

    @Test
    fun isEntrancePositive() {
        assertThat(WarpCode('A', Point2D(0, 0), 'A', Point2D(0, 1)).isEntrance()).isTrue()
    }

    @Test
    fun isEntranceNegative() {
        assertThat(WarpCode('B', Point2D(0, 0), 'A', Point2D(0, 1)).isEntrance()).isFalse()
    }

    @Test
    fun isExitPositive() {
        assertThat(WarpCode('Z', Point2D(0, 0), 'Z', Point2D(0, 1)).isExit()).isTrue()
    }

    @Test
    fun isExitNegative() {
        assertThat(WarpCode('G', Point2D(0, 0), 'Z', Point2D(0, 1)).isExit()).isFalse()
    }

    @Test
    fun isMatchingPositiveWhenCodesInSameOrder() {
        val first = WarpCode('H', Point2D(4, 5), 'I', Point2D(3, 5))
        val second = WarpCode('H', Point2D(12, 14), 'I', Point2D(12, 15))
        assertThat(first.isMatching(second)).isTrue()
    }

    @Test
    fun isMatchingPositiveWhenCodesInDifferentOrder() {
        val first = WarpCode('H', Point2D(4, 5), 'I', Point2D(3, 5))
        val second = WarpCode('I', Point2D(12, 14), 'H', Point2D(12, 15))
        assertThat(first.isMatching(second)).isTrue()
    }

    @Test
    fun isMatchingNegativeWhenCodesDiffer() {
        val first = WarpCode('H', Point2D(4, 5), 'I', Point2D(3, 5))
        val second = WarpCode('B', Point2D(12, 14), 'H', Point2D(12, 15))
        assertThat(first.isMatching(second)).isFalse()
    }

    @Test
    fun isMatchingNegativeWhenPositionsSame() {
        val first = WarpCode('H', Point2D(4, 5), 'I', Point2D(4, 6))
        val second = WarpCode('H', Point2D(4, 5), 'I', Point2D(4, 6))
        assertThat(first.isMatching(second)).isFalse()
    }

    @Test
    fun toStringTest() {
        assertThat(WarpCode('T', Point2D(12, 14), 'U', Point2D(13, 14)).toString()).isEqualTo("TU")
    }
}