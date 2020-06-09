package com.aoc.maze.donut.portal

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import com.aoc.math.Point3D
import org.junit.jupiter.api.Test

class WarpCodeTest {
    @Test
    fun getPositions() {
        val warpCode = WarpCode('B', Point3D(10, 12, 0), 'C', Point3D(12, 13, 0))
        assertThat(warpCode.getPositions()).isEqualTo(setOf(Point3D(10,12,0), Point3D(12,13,0)))
    }

    @Test
    fun isEntrancePositive() {
        assertThat(WarpCode('A', Point3D(0, 0, 0), 'A', Point3D(0, 1, 0)).isEntrance()).isTrue()
    }

    @Test
    fun isEntranceNegative() {
        assertThat(WarpCode('B', Point3D(0, 0, 0), 'A', Point3D(0, 1, 0)).isEntrance()).isFalse()
    }

    @Test
    fun isExitPositive() {
        assertThat(WarpCode('Z', Point3D(0, 0, 0), 'Z', Point3D(0, 1, 0)).isExit()).isTrue()
    }

    @Test
    fun isExitNegative() {
        assertThat(WarpCode('G', Point3D(0, 0, 0), 'Z', Point3D(0, 1, 0)).isExit()).isFalse()
    }

    @Test
    fun isMatchingPositiveWhenCodesInSameOrder() {
        val first = WarpCode('H', Point3D(4, 5, 0), 'I', Point3D(3, 5, 0))
        val second = WarpCode('H', Point3D(12, 14, 0), 'I', Point3D(12, 15, 0))
        assertThat(first.isMatching(second)).isTrue()
    }

    @Test
    fun isMatchingPositiveWhenCodesInDifferentOrder() {
        val first = WarpCode('H', Point3D(4, 5, 0), 'I', Point3D(3, 5, 0))
        val second = WarpCode('I', Point3D(12, 14, 0), 'H', Point3D(12, 15, 0))
        assertThat(first.isMatching(second)).isTrue()
    }

    @Test
    fun isMatchingNegativeWhenCodesDiffer() {
        val first = WarpCode('H', Point3D(4, 5, 0), 'I', Point3D(3, 5, 0))
        val second = WarpCode('B', Point3D(12, 14, 0), 'H', Point3D(12, 15, 0))
        assertThat(first.isMatching(second)).isFalse()
    }

    @Test
    fun isMatchingNegativeWhenPositionsSame() {
        val first = WarpCode('H', Point3D(4, 5, 0), 'I', Point3D(4, 6, 0))
        val second = WarpCode('H', Point3D(4, 5, 0), 'I', Point3D(4, 6, 0))
        assertThat(first.isMatching(second)).isFalse()
    }

    @Test
    fun toStringTest() {
        assertThat(WarpCode('T', Point3D(12, 14, 0), 'U', Point3D(13, 14, 0)).toString()).isEqualTo("TU")
    }
}