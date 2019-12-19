package com.aoc.value

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.panel.Wire
import com.aoc.panel.WireSegment
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class WireTest {
    @Test
    @DisplayName("Given a valid input String, when creating a new Wire, then it should parse the Wire Segments correctly")
    fun createWireFromInput() {
        val wire = Wire("L1,R2,D4")
        assertThat(wire.segments).isEqualTo(listOf(WireSegment("L", 1), WireSegment("R", 2), WireSegment("D", 4)))
    }

    @Test
    @DisplayName("Given a valid input String that has lengths greater than 1 digit, when creating a new Wire, then it should parse the Wire Segments correctly")
    fun createWireFromInputWithBiggerLengths() {
        val wire = Wire("U123,D75634,L87")
        assertThat(wire.segments).isEqualTo(listOf(WireSegment("U", 123), WireSegment("D", 75634), WireSegment("L", 87)))
    }

    @Test
    fun getPath() {
        val wire = Wire("R2,U3,L1")
        val path = wire.getPath()
        assertThat(path.toString()).isEqualTo("[(1, 0), (2, 0), (2, 1), (2, 2), (2, 3), (1, 3)]")
    }
}