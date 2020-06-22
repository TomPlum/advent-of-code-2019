package com.aoc.orbit

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isNull
import assertk.assertions.isTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EmptySource
import org.junit.jupiter.params.provider.ValueSource

class BodyTest {
    @Test
    @DisplayName("Given a valid barycenter, when setting another body to orbit it, then the barycenter should have 1 orbiting body")
    fun orbitedBy() {
        val barycenter = Body("A")
        barycenter.setOrbitingBody(Body("B"))
        assertThat(barycenter.getOrbitingBodies().size).isEqualTo(1)
    }

    @Test
    @DisplayName("Given a valid barycenter, when setting another body to orbit it, then the barycenter should have the correct orbiting body")
    fun orbitedBySetsCorrectBody() {
        val barycenter = Body("A")
        barycenter.setOrbitingBody(Body("B"))
        assertThat(barycenter.getOrbitingBodies()[0].name).isEqualTo("B")
    }

    @Test
    @DisplayName("Given Body A and Body B, when Body B orbits Body A, then Body A should be the parent of Body B")
    fun twoBodiesHaveParentSetCorrectly() {
        val barycenter = Body("A")
        val orbitingBody = Body("B")
        barycenter.setOrbitingBody(orbitingBody)
        assertThat(barycenter.getParent()).isNull()
        assertThat(orbitingBody.getParent()).isEqualTo(barycenter)
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

        assertThat(barycenter.getParent()).isNull()
        assertThat(b.getParent()).isEqualTo(barycenter)
        assertThat(c.getParent()).isEqualTo(barycenter)
        assertThat(d.getParent()).isEqualTo(barycenter)
    }

    @Test
    @DisplayName("Given a body with the name COM, when checking if it is the center of mass, then it should return true")
    fun isMassCenter() {
        val body = Body("COM")
        val isMassCenter = body.isMassCenter()
        assertThat(isMassCenter).isTrue()
    }

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = ["BYA", "LKA", "CAM", "C0M", "MOC", "OIA"])
    @DisplayName("Given a body with the name that is NOT COM, when checking if it is the center of mass, then it should return false")
    fun isNotMassCenter(name: String) {
        val body = Body(name)
        val isMassCenter = body.isMassCenter()
        assertThat(isMassCenter).isFalse()
    }

    @Test
    @DisplayName("Given a body with the name YOU, when checking if it is you, then it should return true")
    fun isYou() {
        val body = Body("YOU")
        val isYou = body.isYou()
        assertThat(isYou).isTrue()
    }

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = ["BYA", "LKA", "YUO", "Y0U", "MOC", "OIA"])
    @DisplayName("Given a body with the name that is NOT YOU, when checking if it is you, then it should return false")
    fun isNotYou(name: String) {
        val body = Body(name)
        val isYou = body.isYou()
        assertThat(isYou).isFalse()
    }

    @Test
    @DisplayName("Given a body with the name SAN, when checking if it is santa, then it should return true")
    fun isSanta() {
        val body = Body("SAN")
        val isSanta = body.isSanta()
        assertThat(isSanta).isTrue()
    }

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = ["BYA", "LKA", "SNA", "NAS", "MOC", "OIA"])
    @DisplayName("Given a body with the name that is NOT SAN, when checking if it is santa, then it should return false")
    fun isNotSanta(name: String) {
        val body = Body(name)
        val isSanta = body.isSanta()
        assertThat(isSanta).isFalse()
    }

}