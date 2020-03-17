package com.aoc.intcode.droid

import input.Day
import input.InputReader
import org.junit.jupiter.api.Test

class RepairDroidTest {
    @Test
    fun test() {
        val instructions = InputReader().readInputAsSingleString(Day.from(15))
        RepairDroid(instructions).locateOxygenSystem()
    }
}