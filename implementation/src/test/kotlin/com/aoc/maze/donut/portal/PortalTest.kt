package com.aoc.maze.donut.portal

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import com.aoc.math.Point2D
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

class PortalTest {
    @Test
    fun hasEntrancePositive() {
        val first = PortalEntrance(WarpCode('F', Point2D(4, 11), 'G', Point2D(4, 10)), Point2D(4, 12))
        val second = PortalEntrance(WarpCode('F', Point2D(8, 0), 'G', Point2D(8, -1)), Point2D(8, 1))
        assertThat(Portal(Pair(first, second)).hasEntrance(Point2D(8,1))).isTrue()
    }

    @Test
    fun hasEntranceNegative() {
        val first = PortalEntrance(WarpCode('F', Point2D(4, 11), 'G', Point2D(4, 10)), Point2D(4, 12))
        val second = PortalEntrance(WarpCode('F', Point2D(8, 0), 'G', Point2D(8, -1)), Point2D(8, 1))
        assertThat(Portal(Pair(first, second)).hasEntrance(Point2D(45,5))).isFalse()
    }

    @Test
    fun warpWithValidEntrance() {
        val first = PortalEntrance(WarpCode('F', Point2D(4, 11), 'G', Point2D(4, 10)), Point2D(4, 12))
        val second = PortalEntrance(WarpCode('F', Point2D(8, 0), 'G', Point2D(8, -1)), Point2D(8, 1))
        assertThat(Portal(Pair(first, second)).warpFrom(Point2D(4,12))).isEqualTo(Point2D(8,1))
    }

    @Test
    fun warpWithInvalidEntrance() {
        val first = PortalEntrance(WarpCode('F', Point2D(4, 11), 'G', Point2D(4, 10)), Point2D(4, 12))
        val second = PortalEntrance(WarpCode('F', Point2D(8, 0), 'G', Point2D(8, -1)), Point2D(8, 1))
        val e = assertThrows<IllegalArgumentException> { Portal(Pair(first, second)).warpFrom(Point2D(67, 34)) }
        assertThat(e.message).isEqualTo("FG(4, 12)<->(8, 1) does not warp to or from (67, 34)")
    }

    @Test
    fun warpFromOuterShouldDecreaseLevel() {
        val outer = PortalEntrance(WarpCode('F', Point2D(0, 11), 'G', Point2D(1, 11)), Point2D(2, 11))
        val inner = PortalEntrance(WarpCode('F', Point2D(10, 16), 'G', Point2D(10, 17)), Point2D(10, 18))
        val portal = Portal(Pair(inner, outer))
        portal.level = 5
        portal.warpFrom(Point2D(2, 11))
        assertThat(portal.level).isEqualTo(4)
    }

    @Test
    fun warpFromInnerShouldIncreaseLevel() {
        val outer = PortalEntrance(WarpCode('F', Point2D(0, 11), 'G', Point2D(1, 11)), Point2D(2, 11))
        val inner = PortalEntrance(WarpCode('F', Point2D(10, 16), 'G', Point2D(10, 17)), Point2D(10, 18))
        val portal = Portal(Pair(inner, outer))
        portal.warpFrom(Point2D(10, 18))
        assertThat(portal.level).isEqualTo(1)
    }

    @Test
    fun toStringTest() {
        val first = PortalEntrance(WarpCode('F', Point2D(4, 11), 'G', Point2D(4, 10)), Point2D(4, 12))
        val second = PortalEntrance(WarpCode('F', Point2D(8, 0), 'G', Point2D(8, -1)), Point2D(8, 1))
        assertThat(Portal(Pair(first, second)).toString()).isEqualTo("FG(4, 12)<->(8, 1)")
    }
}