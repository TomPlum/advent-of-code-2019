package com.aoc.intcode.droid.repair

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.Day
import com.aoc.input.InputReader
import com.aoc.math.Point2D
import org.junit.jupiter.api.Test

class RepairDroidTest {
    @Test
    fun partOneSolution() {
        val instructions = InputReader.read<String>(Day(15)).asSingleString()
        val distance = RepairDroid(instructions).locateShipsOxygenSystem()
        assertThat(distance).isEqualTo(Pair(Point2D(-12, 12), 212))
    }
}