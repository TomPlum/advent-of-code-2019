package com.aoc.intcode.tractorbeam

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class DroneStateTest {
    @Test
    fun stationaryToString() {
        assertThat(DroneState.STATIONARY.toString()).isEqualTo(".")
    }

    @Test
    fun propagatingToString() {
        assertThat(DroneState.PROPAGATING.toString()).isEqualTo("#")
    }
}