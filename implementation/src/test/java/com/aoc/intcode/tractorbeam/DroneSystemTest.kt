package com.aoc.intcode.tractorbeam

import input.Day
import input.InputReader
import org.junit.jupiter.api.Test

class DroneSystemTest {
    @Test
    fun partOne() {
        val input = InputReader().readInputAsSingleString(Day.from(19))
        DroneSystem(input).scanAreaSurroundingEmitter(50)
    }
}