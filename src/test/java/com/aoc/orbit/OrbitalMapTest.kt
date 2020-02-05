package com.aoc.orbit

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class OrbitalMapTest {
    @Test
    @Disabled("Until continue with Day 6")
    @DisplayName("Given the example, when calculating the combined number of direct and indirect orbits, then it should return 42")
    fun example() {
        val input = listOf("COM)B", "B)C", "C)D", "D)E", "E)F", "B)G", "G)H", "D)I", "E)J", "J)K", "K)L")
        val map = OrbitalMap(input)
        assertThat(map.readOrbits()).isEqualTo(42)
    }

    @Test
    @Disabled("No longer needed?")
    @DisplayName("Given a barycenter and a single orbiting body, when checking if the orbiting body orbits the center, then it should return true")
    fun isOrbitedByDirectly() {
        val barycenter = Body("A")
        val orbitingBody = Body("B")
        barycenter.setOrbitingBody(orbitingBody)
       // assertThat(barycenter.isOrbitedBy(orbitingBody)).isTrue()
    }

    @Test
    @Disabled("No longer needed?")
    @DisplayName("Given a barycenter with a chain of orbiting bodies, when checking if the outermost body orbits the center, then it should return true")
    fun isOrbitedByInDirectly() {
        val barycenter = Body("A")
        val orbitingBody = Body("B")
        val outermostOrbitingBody = Body("C")
        barycenter.setOrbitingBody(orbitingBody)
        orbitingBody.setOrbitingBody(outermostOrbitingBody)
       // assertThat(barycenter.isOrbitedBy(outermostOrbitingBody)).isTrue()
    }
}