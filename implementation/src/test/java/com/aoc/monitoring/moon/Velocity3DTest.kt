package com.aoc.monitoring.moon

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Velocity3DTest {
    @Test
    fun toStringTest() {
        val velocity = Velocity3D(4, 12, 0)
        val toString = velocity.toString()
        assertThat(toString).isEqualTo("vel=<x=4, y=12, z=0>")
    }
}