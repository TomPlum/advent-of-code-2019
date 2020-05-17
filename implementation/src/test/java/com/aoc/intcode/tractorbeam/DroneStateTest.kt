package com.aoc.intcode.tractorbeam

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EmptySource
import org.junit.jupiter.params.provider.NullAndEmptySource
import org.junit.jupiter.params.provider.ValueSource

class DroneStateTest {
    @Test
    fun stationaryToString() {
        assertThat(DroneState.stationary().toString()).isEqualTo(".")
    }

    @Test
    fun propagatingToString() {
        assertThat(DroneState.propagating().toString()).isEqualTo("#")
    }

    @Test
    fun shipAreaToString() {
        assertThat(DroneState.shipArea().toString()).isEqualTo("O")
    }

    @Test
    fun isStationaryPositive() {
        assertThat(DroneState(".").isStationary()).isTrue()
    }

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = ["#", "O"])
    fun isStationaryNegative(value: String) {
        assertThat(DroneState(value).isStationary()).isFalse()
    }

    @Test
    fun isPropagatingPositive() {
        assertThat(DroneState("#").isPropagating()).isTrue()
    }

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = [".", "O"])
    fun isPropagatingNegative(value: String) {
        assertThat(DroneState(value).isPropagating()).isFalse()
    }

    @Test
    fun isSantaShipAreaPositive() {
        assertThat(DroneState("O").isSantaShipArea()).isTrue()
    }

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = [".", "#"])
    fun isSantaShipAreaNegative(value: String) {
        assertThat(DroneState(value).isSantaShipArea()).isFalse()
    }
}