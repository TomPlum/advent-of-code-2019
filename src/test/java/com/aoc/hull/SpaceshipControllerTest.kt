package com.aoc.hull

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.input.Day
import com.aoc.input.InputReader
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class SpaceshipControllerTest {
    @Test
    @DisplayName("Given Day 11 Part 1 Puzzle Input, when starting on a BLACK panel, then it should paint 1564 panels at least once")
    fun dayElevenPartOneSolution() {
        val input = InputReader().readInputAsSingleString(Day.from(11))
        val paintedHull = SpaceshipController().deployEmergencyHullPaintingRobot(input, HullPaint.BLACK)
        assertThat(paintedHull.getTimesPanelsPainted()).isEqualTo(1564)
    }
}