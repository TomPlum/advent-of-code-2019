package com.aoc.panel

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.math.Direction
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EmptySource
import org.junit.jupiter.params.provider.ValueSource

class WireSegmentTest {
    @Test
    fun up() {
        assertThat(WireSegment("U", 4).direction).isEqualTo(Direction.UP)
    }

    @Test
    fun right() {
        assertThat(WireSegment("R", 4).direction).isEqualTo(Direction.RIGHT)
    }

    @Test
    fun down() {
        assertThat(WireSegment("D", 4).direction).isEqualTo(Direction.DOWN)
    }

    @Test
    fun left() {
        assertThat(WireSegment("L", 4).direction).isEqualTo(Direction.LEFT)
    }

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = ["A", "-", "UD"])
    fun invalidDirectionalCode(code: String) {
        val e = assertThrows<IllegalArgumentException> { WireSegment(code, 4).direction }
        assertThat(e.message).isEqualTo("Invalid Directional Code: $code")
    }
}