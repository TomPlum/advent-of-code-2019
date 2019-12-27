package com.aoc.orbit

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class OrbitalMapTest {
    @Test
    @DisplayName("Given the example, when calculating the combined number of direct and indirect orbits, then it should return 42")
    fun example() {
        val input = listOf("COM)B", "B)C", "C)D", "D)E", "E)F", "B)G", "G)H", "D)I", "E)J", "J)K", "K)L")
        val reader = OrbitalMap(input)
        assertThat(reader.readOrbits()).isEqualTo(42)
    }
}