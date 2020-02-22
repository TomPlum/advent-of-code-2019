package com.aoc.monitoring.asteroid

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EmptySource
import org.junit.jupiter.params.provider.ValueSource

class MapSectorTest {
    @Test
    fun hasAsteroid() {
        val mapSector = MapSector("#")
        val hasAsteroid = mapSector.hasAsteroid()
        assertThat(hasAsteroid).isTrue()
    }

    @Test
    fun isEmpty() {
        val mapSector = MapSector(".")
        val hasAsteroid = mapSector.hasAsteroid()
        assertThat(hasAsteroid).isFalse()
    }

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = ["##", "..", "A", "1"])
    fun invalidContentsValue(contents: String) {
        val e = assertThrows<IllegalArgumentException> { MapSector(contents).hasAsteroid() }
        assertThat(e.message).isEqualTo("Invalid Map Sector Contents '$contents'. Asteroid (#), Empty (.)")
    }

    @Test
    fun toStringTestAsteroid() {
        val mapSector = MapSector("#")
        val toString = mapSector.toString()
        assertThat(toString).isEqualTo("#")
    }

    @Test
    fun toStringTestEmpty() {
        val mapSector = MapSector(".")
        val toString = mapSector.toString()
        assertThat(toString).isEqualTo(".")
    }
}