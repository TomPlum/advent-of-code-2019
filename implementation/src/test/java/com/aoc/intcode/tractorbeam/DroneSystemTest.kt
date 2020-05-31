package com.aoc.intcode.tractorbeam

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.input.Day
import com.aoc.input.InputReader
import org.junit.jupiter.api.Test

class DroneSystemTest {
    @Test
    fun partOneSolution() {
        val input = InputReader().readInputAsSingleString(Day.from(19))
        val scan = DroneSystem(input).scanAreaSurroundingEmitter(50)
        assertThat(scan.getPointsAffectedByBeam()).isEqualTo(181)
    }

    @Test
    fun scanAreaGridSizeShouldCreateBeamScanWithEqualWidthAndHeight() {
        val input = InputReader().readInputAsSingleString(Day.from(19))
        val scan = DroneSystem(input).scanAreaSurroundingEmitter(50)
        assertThat(scan.getArea()).isEqualTo(2500)
    }

    @Test
    fun scanForSantasShipWithSize2() {
        val input = InputReader().readInputAsSingleString(Day.from(19))
        val result = DroneSystem(input).scanAreaForSantasShip(2)
        assertThat(result).isEqualTo(60013)
    }

    @Test
    fun scanForSantasShipWithSize3() {
        val input = InputReader().readInputAsSingleString(Day.from(19))
        val result = DroneSystem(input).scanAreaForSantasShip(3)
        assertThat(result).isEqualTo(100023)
    }

    @Test
    fun partTwoSolution() {
        val input = InputReader().readInputAsSingleString(Day.from(19))
        val result = DroneSystem(input).scanAreaForSantasShip(100)
        assertThat(result).isEqualTo(4240964)
    }
}