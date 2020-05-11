package com.aoc.intcode.tractorbeam

import assertk.assertThat
import assertk.assertions.isEqualTo
import input.Day
import input.InputReader
import org.junit.jupiter.api.Test

class DroneSystemTest {
    @Test
    fun partOne() {
        val input = InputReader().readInputAsSingleString(Day.from(19))
        val pointsAffected = DroneSystem(input).scanAreaSurroundingEmitter(50)
        assertThat(pointsAffected).isEqualTo(181)
    }
}