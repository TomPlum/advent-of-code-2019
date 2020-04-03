package com.aoc.intcode.vacuum

import assertk.assertThat
import assertk.assertions.isEqualTo
import input.Day
import input.InputReader
import org.junit.jupiter.api.Test

class VacuumRobotTest {
    @Test
    fun solutionPartOne() {
        val instructions = InputReader().readInputAsSingleString(Day.from(17))
        val robot = VacuumRobot(instructions)
        assertThat(robot.exploreShipsExteriorScaffolding().calculateAlignmentParameterSum()).isEqualTo(7404)
    }

    @Test
    fun solutionPartTwo() {
        val instructions = InputReader().readInputAsSingleString(Day.from(17))
        val robot = VacuumRobot(instructions)
        assertThat(robot.getDustCollectionReport()).isEqualTo(929045L)
    }
}