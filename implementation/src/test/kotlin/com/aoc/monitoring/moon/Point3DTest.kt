package com.aoc.monitoring.moon

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Point3DTest {
    @Test
    fun toStringTest() {
        val point = Point3D(4, 12, 0)
        val toString = point.toString()
        assertThat(toString).isEqualTo("pos=<x=4, y=12, z=0>")
    }
}