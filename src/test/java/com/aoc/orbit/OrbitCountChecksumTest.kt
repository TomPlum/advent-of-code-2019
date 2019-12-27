package com.aoc.orbit

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class OrbitCountChecksumTest {
    @ParameterizedTest
    @ValueSource(strings = ["COM)B", "B)C", "GQ2)WBM"])
    @DisplayName("Given a valid checksum value, when creating a new Orbital Count Checksum, then it should parse the input value correctly")
    fun constructorParsesChecksumValueCorrectly(checksum: String) {
        val orbitCountChecksum = OrbitCountChecksum(checksum)
        assertThat(orbitCountChecksum.value).isEqualTo(checksum)
    }

    @Test
    @DisplayName("Given a checksum with a single character orbital, when creating a new Orbital Count Checksum, it should return the correct orbital")
    fun getOrbitalSingleDigit() {
        val checksum = OrbitCountChecksum("COM)B")
        val orbital = checksum.getOrbital()
        assertThat(orbital).isEqualTo("B")
    }

    @Test
    @DisplayName("Given a checksum with a double character orbital, when creating a new Orbital Count Checksum, it should return the correct orbital")
    fun getOrbitalDoubleDigit() {
        val checksum = OrbitCountChecksum("COM)AS")
        val orbital = checksum.getOrbital()
        assertThat(orbital).isEqualTo("AS")
    }

    @Test
    @DisplayName("Given a checksum with a triple character orbital, when creating a new Orbital Count Checksum, it should return the correct orbital")
    fun getOrbitalTripleDigit() {
        val checksum = OrbitCountChecksum("COM)MP6")
        val orbital = checksum.getOrbital()
        assertThat(orbital).isEqualTo("MP6")
    }

    @Test
    @DisplayName("Given a checksum with a single character barycenter, when creating a new Orbital Count Checksum, it should return the correct barycenter")
    fun getBarycenterSingleDigit() {
        val checksum = OrbitCountChecksum("A)MP6")
        val barycenter = checksum.getBarycenter()
        assertThat(barycenter).isEqualTo("A")
    }

    @Test
    @DisplayName("Given a checksum with a double character barycenter, when creating a new Orbital Count Checksum, it should return the correct barycenter")
    fun getBarycenterDoubleDigit() {
        val checksum = OrbitCountChecksum("JH)MP6")
        val barycenter = checksum.getBarycenter()
        assertThat(barycenter).isEqualTo("JH")
    }

    @Test
    @DisplayName("Given a checksum with a triple character barycenter, when creating a new Orbital Count Checksum, it should return the correct barycenter")
    fun getBarycenterTripleDigit() {
        val checksum = OrbitCountChecksum("COM)MP6")
        val barycenter = checksum.getBarycenter()
        assertThat(barycenter).isEqualTo("COM")
    }
}