package com.aoc.intcode.droid.spring.register.read

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class DistanceCodeTest {
    @Test
    fun toStringTest() {
        assertThat(DistanceCode.A.toString()).isEqualTo("A")
    }
}