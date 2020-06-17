package com.aoc.intcode.tractorbeam

import com.aoc.input.Day
import com.aoc.input.InputReader
import org.junit.jupiter.api.Test

class DroneSystemTest {
    @Test
    fun solutionPartTwo() {
        val input = InputReader().readInputAsSingleString(Day.from(19))
        val system = DroneSystem(input).scanAreaForSantasShip(100)
    }
}
