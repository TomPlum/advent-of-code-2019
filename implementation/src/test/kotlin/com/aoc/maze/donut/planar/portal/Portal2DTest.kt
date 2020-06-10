package com.aoc.maze.donut.planar.portal

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import com.aoc.math.Point2D
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class Portal2DTest {
    @Test
    fun hasEntrancePositive() {
        val first = PortalEntrance2D(WarpCode2D('F', Point2D(4, 11), 'G', Point2D(4, 10)), Point2D(4, 12))
        val second = PortalEntrance2D(WarpCode2D('F', Point2D(8, 0), 'G', Point2D(8, -1)), Point2D(8, 1))
        assertThat(Portal2D(Pair(first, second)).hasEntrance(Point2D(8,1))).isTrue()
    }

    @Test
    fun hasEntranceNegative() {
        val first = PortalEntrance2D(WarpCode2D('F', Point2D(4, 11), 'G', Point2D(4, 10)), Point2D(4, 12))
        val second = PortalEntrance2D(WarpCode2D('F', Point2D(8, 0), 'G', Point2D(8, -1)), Point2D(8, 1))
        assertThat(Portal2D(Pair(first, second)).hasEntrance(Point2D(45,5))).isFalse()
    }

    @Test
    fun warpWithValidEntrance() {
        val first = PortalEntrance2D(WarpCode2D('F', Point2D(4, 11), 'G', Point2D(4, 10)), Point2D(4, 12))
        val second = PortalEntrance2D(WarpCode2D('F', Point2D(8, 0), 'G', Point2D(8, -1)), Point2D(8, 1))
        assertThat(Portal2D(Pair(first, second)).warpFrom(Point2D(4,12))).isEqualTo(Point2D(8,1))
    }

    @Test
    fun warpWithInvalidEntrance() {
        val first = PortalEntrance2D(WarpCode2D('F', Point2D(4, 11), 'G', Point2D(4, 10)), Point2D(4, 12))
        val second = PortalEntrance2D(WarpCode2D('F', Point2D(8, 0), 'G', Point2D(8, -1)), Point2D(8, 1))
        val e = assertThrows<IllegalArgumentException> { Portal2D(Pair(first, second)).warpFrom(Point2D(67, 34)) }
        assertThat(e.message).isEqualTo("FG(4, 12)<->(8, 1) does not warp to or from (67, 34)")
    }
    
    @Test
    fun toStringTest() {
        val first = PortalEntrance2D(WarpCode2D('F', Point2D(4, 11), 'G', Point2D(4, 10)), Point2D(4, 12))
        val second = PortalEntrance2D(WarpCode2D('F', Point2D(8, 0), 'G', Point2D(8, -1)), Point2D(8, 1))
        assertThat(Portal2D(Pair(first, second)).toString()).isEqualTo("FG(4, 12)<->(8, 1)")
    }
}