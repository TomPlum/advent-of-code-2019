package com.aoc.intcode.droid

import assertk.assertThat
import assertk.assertions.isEqualTo
import input.Day
import input.InputReader
import org.junit.jupiter.api.Test

class RepairDroidTest {
    @Test
    fun partOneSolution() {
        val instructions = InputReader().readInputAsSingleString(Day.from(15))
        val distance = RepairDroid(instructions).locateShipsOxygenSystem()
        assertThat(distance).isEqualTo(212)
    }
}