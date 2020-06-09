package com.aoc.math

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class Point3DTest {
    @Nested
    inner class PlanarAdjacentTo {
        @Test
        fun isPlanarAdjacentToTargetOnRight() {
            assertThat(Point3D(5,6,0).isPlanarAdjacentTo(Point3D(6,6,0))).isTrue()
        }

        @Test
        fun isPlanarAdjacentToTargetOnBottomRight() {
            assertThat(Point3D(5,6,0).isPlanarAdjacentTo(Point3D(6,5,0))).isTrue()
        }

        @Test
        fun isPlanarAdjacentToTargetOnBottom() {
            assertThat(Point3D(5,6,0).isPlanarAdjacentTo(Point3D(5,5,0))).isTrue()
        }

        @Test
        fun isPlanarAdjacentToTargetOnBottomLeft() {
            assertThat(Point3D(5,6,0).isPlanarAdjacentTo(Point3D(4,5,0))).isTrue()
        }

        @Test
        fun isPlanarAdjacentToTargetOnLeft() {
            assertThat(Point3D(5,6,0).isPlanarAdjacentTo(Point3D(4,6,0))).isTrue()
        }

        @Test
        fun isPlanarAdjacentToTargetTopLeft() {
            assertThat(Point3D(5,6,0).isPlanarAdjacentTo(Point3D(4,7,0))).isTrue()
        }

        @Test
        fun isPlanarAdjacentToTargetTop() {
            assertThat(Point3D(5,6,0).isPlanarAdjacentTo(Point3D(5,7,0))).isTrue()
        }

        @Test
        fun isNotAdjacent() {
            assertThat(Point3D(5,6,0).isPlanarAdjacentTo(Point3D(4,4,0))).isFalse()
        }

        @Test
        fun samePointsAreNotAdjacent() {
            assertThat(Point3D(5,6,0).isPlanarAdjacentTo(Point3D(5,6,0))).isFalse()
        }
    }

    @Nested
    inner class OrthogonallyPlanarAdjacent {
        @Test
        fun adjacentPoints() {
            assertThat(Point3D(0,0,0).planarAdjacentPoints()).isEqualTo(listOf(Point3D(0,1,0), Point3D(1,0,0), Point3D(0,-1,0), Point3D(-1,0,0)))
        }
    }

    @Test
    fun toStringTest() {
        assertThat(Point3D(4, 2, 11).toString()).isEqualTo("(4, 2, 11)")
    }
}