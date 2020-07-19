package com.aoc.maze.donut.recursive.portal

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import com.aoc.math.Point3D
import org.junit.jupiter.api.Test

class Portal3DTest {
    @Test
    fun hasEntrancePositive() {
        val first = PortalEntrance3D(WarpCode3D('F', Point3D(4, 11, 0), 'G', Point3D(4, 10, 0)), Point3D(4, 12, 0), 25, 25)
        val second = PortalEntrance3D(WarpCode3D('F', Point3D(8, 0, 0), 'G', Point3D(8, -1, 0)), Point3D(8, 1, 0), 25, 25)
        assertThat(Portal3D(Pair(first, second)).hasEntrance(Point3D(8,1, 0))).isTrue()
    }

    @Test
    fun hasEntranceNegative() {
        val first = PortalEntrance3D(WarpCode3D('F', Point3D(4, 11, 0), 'G', Point3D(4, 10, 0)), Point3D(4, 12, 0), 25, 25)
        val second = PortalEntrance3D(WarpCode3D('F', Point3D(8, 0, 0), 'G', Point3D(8, -1, 0)), Point3D(8, 1, 0), 25, 25)
        assertThat(Portal3D(Pair(first, second)).hasEntrance(Point3D(45,5,0))).isFalse()
    }

    @Test
    fun warpFromOuterShouldDecreaseOrdinateZ() {
        val outer = PortalEntrance3D(WarpCode3D('F', Point3D(0, 11, 3), 'G', Point3D(1, 11, 3)), Point3D(2, 11, 3), 25, 25)
        val inner = PortalEntrance3D(WarpCode3D('F', Point3D(10, 16, 3), 'G', Point3D(10, 17, 3)), Point3D(10, 18, 3), 25, 25)
        assertThat(Portal3D(Pair(inner, outer)).warpRecursivelyFrom(Point3D(2, 11, 3))).isEqualTo(Point3D(10, 18, 2))
    }

    @Test
    fun warpFromInnerShouldIncreaseOrdinateZ() {
        val outer = PortalEntrance3D(WarpCode3D('F', Point3D(0, 11, 3), 'G', Point3D(1, 11, 3)), Point3D(2, 11, 3), 25, 25)
        val inner = PortalEntrance3D(WarpCode3D('F', Point3D(10, 16, 3), 'G', Point3D(10, 17, 3)), Point3D(10, 18, 3), 25, 25)
        assertThat(Portal3D(Pair(inner, outer)).warpRecursivelyFrom(Point3D(10, 18, 3))).isEqualTo(Point3D(2, 11, 4))
    }

    @Test
    fun toStringTest() {
        val first = PortalEntrance3D(WarpCode3D('F', Point3D(4, 11, 0), 'G', Point3D(4, 10, 0)), Point3D(4, 12, 0), 25, 25)
        val second = PortalEntrance3D(WarpCode3D('F', Point3D(8, 0, 0), 'G', Point3D(8, -1, 0)), Point3D(8, 1, 0), 25, 25)
        assertThat(Portal3D(Pair(first, second)).toString()).isEqualTo("FG(4, 12, 0)<->(8, 1, 0)")
    }
}