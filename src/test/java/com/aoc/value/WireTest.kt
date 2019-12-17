package com.aoc.value

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class WireTest {
    @Test
    @DisplayName("Given a valid input String, when creating a new Wire, then it should parse the Wire Segments correctly")
    internal fun createWireFromInput() {
        val wire = Wire("L1,R2,D4")
        val segments = wire.segments
        assertThat(segments).isEqualTo(listOf(WireSegment("L", 1), WireSegment("R", 2), WireSegment("D", 4)))
    }
}