package com.aoc.orbit

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.input.Day
import com.aoc.input.InputReader
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class OrbitalMapTest {
    @Test
    @DisplayName("Given the example, when calculating the combined number of direct and indirect orbits, then it should return 42")
    fun example() {
        val input = listOf("COM)B", "B)C", "C)D", "D)E", "E)F", "B)G", "G)H", "D)I", "E)J", "J)K", "K)L")
        val map = OrbitalMap(input)
        assertThat(map.readOrbits()).isEqualTo(42)
    }

    @Test
    @DisplayName("Given Day 6 Input, when calculating the combined number of orbits, then it should be 314702")
    fun solutionPartOne() {
        val input = InputReader().readInputString(Day.from(6))
        val map = OrbitalMap(input.values)
        assertThat(map.readOrbits()).isEqualTo(314702)
    }
}