package com.aoc.intcode.droid

import assertk.assertThat
import assertk.assertions.isEqualTo
import input.Day
import input.InputReader
import math.Point2D
import org.junit.jupiter.api.Test

class RepairDroidTest {
    @Test
    fun partOneSolution() {
        val instructions = InputReader().readInputAsSingleString(Day.from(15))
        val distance = RepairDroid(instructions).locateShipsOxygenSystem()
        assertThat(distance).isEqualTo(Pair(Point2D(-12, 12), 212))
    }
}