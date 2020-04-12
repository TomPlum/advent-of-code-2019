package com.aoc.value

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.panel.Wire
import com.aoc.panel.WireSegment
import math.Point2D
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

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
        assertThat(Wire("R2,U3,L1").path.toString()).isEqualTo("[(1, 0), (2, 0), (2, 1), (2, 2), (2, 3), (1, 3)]")
    }

    @Test
    fun stepsTo() {
        assertThat(Wire("R75,D30,R83,U83,L12,D49,R71,U7,L72").stepsTo(Point2D(158, -12))).isEqualTo(205)
    }

    @Test
    fun stepsToInvalidPosition() {
        val e = assertThrows<IllegalArgumentException> { Wire("R2,U8,D9").stepsTo(Point2D(78, 1)) }
        assertThat(e.message).isEqualTo("Wire(input=R2,U8,D9) does not contains (78, 1)")
    }
}