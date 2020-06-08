package com.aoc.math

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Point3DTest {
    @Test
    fun toStringTest() {
        assertThat(Point3D(4, 2, 11).toString()).isEqualTo("(4, 2, 11)")
    }
}