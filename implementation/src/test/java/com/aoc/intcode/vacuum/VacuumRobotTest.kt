package com.aoc.intcode.vacuum

import input.Day
import input.InputReader
import org.junit.jupiter.api.Test

class VacuumRobotTest {
    @Test
    fun explore() {
        val instructions = InputReader().readInputAsSingleString(Day.from(17))
        val robot = VacuumRobot(instructions)
        robot.exploreShipsExteriorScaffolding()
    }

    @Test
    fun partTwo() {
        val instructions = InputReader().readInputAsSingleString(Day.from(17))
        val robot = VacuumRobot(instructions)
        robot.forceWakeUp()

    }
}