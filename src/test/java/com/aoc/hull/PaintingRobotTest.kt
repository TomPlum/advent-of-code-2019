package com.aoc.hull

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.input.Day
import com.aoc.input.InputReader
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class PaintingRobotTest {
    @Test
    @DisplayName("Given a new Painting Robot, when getting the current direction, then it should be UP")
    fun robotShouldStartFacingUp() {
        val robot = PaintingRobot("99")
        assertThat(robot.direction).isEqualTo(Direction.UP)
    }

    @Test
    fun dayElevenPartOneSolution() {
        val input = InputReader().readInputAsSingleString(Day.from(11))
        val robot = PaintingRobot(input)
        assertThat(robot.paint()).isEqualTo(999)
    }
}