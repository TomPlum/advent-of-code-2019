package com.aoc.orbit

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import assertk.assertions.isTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class BodyTest {
    @Test
    @DisplayName("Given a valid barycenter, when setting another body to orbit it, then the barycenter should have 1 orbiting body")
    fun orbitedBy() {
        val barycenter = Body("A")
        barycenter.setOrbitingBody(Body("B"))
        assertThat(barycenter.orbitingBodies.size).isEqualTo(1)
    }

    @Test
    @DisplayName("Given a valid barycenter, when setting another body to orbit it, then the barycenter should have the correct orbiting body")
    fun orbitedBySetsCorrectBody() {
        val barycenter = Body("A")
        barycenter.setOrbitingBody(Body("B"))
        assertThat(barycenter.orbitingBodies[0].name).isEqualTo("B")
    }

    @Test
    @DisplayName("Given Body A and Body B, when Body B orbits Body A, then Body A should be the parent of Body B")
    fun twoBodiesHaveParentSetCorrectly() {
        val barycenter = Body("A")
        val orbitingBody = Body("B")
        barycenter.setOrbitingBody(orbitingBody)
        assertThat(barycenter.parent).isNull()
        assertThat(orbitingBody.parent).isEqualTo(barycenter)
    }

    @Test
    @DisplayName("Given a barycenter with multiple orbiting bodies, when checking the orbiting bodies parents, they should all be set to the barycenter")
    fun barycenterHasMultipleOrbitingBodiesShouldSetParentCorrectly() {
        val barycenter = Body("A")
        val b = Body("B")
        barycenter.setOrbitingBody(b)
        val c = Body("C")
        barycenter.setOrbitingBody(c)
        val d = Body("D")
        barycenter.setOrbitingBody(d)

        assertThat(barycenter.parent).isNull()
        assertThat(b.parent).isEqualTo(barycenter)
        assertThat(c.parent).isEqualTo(barycenter)
        assertThat(d.parent).isEqualTo(barycenter)
    }

}