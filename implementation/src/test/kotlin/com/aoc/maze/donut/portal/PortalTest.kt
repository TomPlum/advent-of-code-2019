package com.aoc.maze.donut.portal

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import com.aoc.math.Point2D
import com.aoc.maze.donut.portal.Portal
import com.aoc.maze.donut.portal.WarpCode
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
        assertThat(Portal(Pair(first, second)).warp(Point2D(4,12))).isEqualTo(Point2D(8,1))
    }

    @Test
    fun warpWithInvalidEntrance() {
        val first = PortalEntrance(WarpCode('F', Point2D(4, 11), 'G', Point2D(4, 10)), Point2D(4, 12))
        val second = PortalEntrance(WarpCode('F', Point2D(8, 0), 'G', Point2D(8, -1)), Point2D(8, 1))
        val e = assertThrows<IllegalArgumentException> { Portal(Pair(first, second)).warp(Point2D(67, 34)) }
        assertThat(e.message).isEqualTo("FG(4, 12)<->(8, 1) does not warp to or from (67, 34)")
    }

    @Test
    fun toStringTest() {
        val first = PortalEntrance(WarpCode('F', Point2D(4, 11), 'G', Point2D(4, 10)), Point2D(4, 12))
        val second = PortalEntrance(WarpCode('F', Point2D(8, 0), 'G', Point2D(8, -1)), Point2D(8, 1))
        assertThat(Portal(Pair(first, second)).toString()).isEqualTo("FG(4, 12)<->(8, 1)")
    }
}