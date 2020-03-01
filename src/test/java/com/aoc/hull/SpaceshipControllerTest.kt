package com.aoc.hull

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.input.Day
import com.aoc.input.InputReader
import org.junit.jupiter.api.Test

class SpaceshipControllerTest {
    @Test
    fun dayElevenPartOneSolution() {
        val input = InputReader().readInputAsSingleString(Day.from(11))
        val paintedHull = SpaceshipController().deployEmergencyHullPaintingRobot(input)
        assertThat(paintedHull.getTimesPanelsPainted()).isEqualTo(1564)
    }
}