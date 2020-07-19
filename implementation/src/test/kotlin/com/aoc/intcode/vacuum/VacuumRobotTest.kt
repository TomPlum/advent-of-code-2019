package com.aoc.intcode.vacuum

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.input.Day
import com.aoc.input.InputReader
import org.junit.jupiter.api.Test

class VacuumRobotTest {
    @Test
    fun solutionPartOne() {
        val instructions = InputReader.read<String>(Day(17)).asSingleString()
        val robot = VacuumRobot(instructions)
        assertThat(robot.scanShipsExteriorScaffolding().calculateAlignmentParameterSum()).isEqualTo(7404)
    }

    @Test
    fun solutionPartTwo() {
        val instructions = InputReader.read<String>(Day(17)).asSingleString()
        val robot = VacuumRobot(instructions)
        assertThat(robot.notifyRobotsAboutSolarFlare().quantity).isEqualTo(929045L)
    }
}