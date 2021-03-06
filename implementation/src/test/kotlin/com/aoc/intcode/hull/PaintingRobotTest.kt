package com.aoc.intcode.hull

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.math.Direction
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class PaintingRobotTest {
    @Test
    @DisplayName("Given a new Painting Robot, when getting the current direction, then it should be UP")
    fun robotShouldStartFacingUp() {
        val robot = PaintingRobot("99")
        assertThat(robot.direction).isEqualTo(Direction.UP)
    }
}