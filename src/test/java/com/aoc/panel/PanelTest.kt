package com.aoc.panel

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class PanelTest {
    @Test
    @DisplayName("Given the wires from the basic example, when calculating the closest intersection point, then it should return 6")
    fun basicExample() {
        val panel = Panel(Wire("R8,U5,L5,D3"), Wire("U7,R6,D4,L4"))
        val distance = panel.findIntersectionPointClosestToCentralPort()
        assertThat(distance).isEqualTo(6)
    }

    @Test
    @DisplayName("Given the wires from example one, when calculating the closest intersection point, then it should return 159")
    fun exampleOne() {
        val firstWire = Wire("R75,D30,R83,U83,L12,D49,R71,U7,L72")
        val secondWire = Wire("U62,R66,U55,R34,D71,R55,D58,R83")

        val panel = Panel(firstWire, secondWire)

        assertThat(panel.findIntersectionPointClosestToCentralPort()).isEqualTo(159)
    }

    @Test
    @DisplayName("Given the wires from example two, when calculating the closest intersection point, then it should return 135")
    fun exampleTwo() {
        val firstWire = Wire("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51")
        val secondWire = Wire("U98,R91,D20,R16,D67,R40,U7,R15,U6,R7")

        val panel = Panel(firstWire, secondWire)

        assertThat(panel.findIntersectionPointClosestToCentralPort()).isEqualTo(135)
    }
}