package com.aoc.intcode.droid

import assertk.assertThat
import assertk.assertions.isEqualTo
import input.Day
import input.InputReader
import org.junit.jupiter.api.Test

class OxygenSystemTest {
    @Test
    fun partTwoSolution() {
        val input = InputReader().readInputAsSingleString(Day.from(15))
        val droid = RepairDroid(input)
        droid.locateShipsOxygenSystem()
        val shipsBlueprints = droid.downloadShipMappingData()
        val minutes = OxygenSystem(shipsBlueprints).oxygenateShip()
        assertThat(minutes).isEqualTo(358)
    }

    @Test
    fun example() {
        val input = InputReader().readInputAsSingleString("/droid/example-droid-input.txt")
        val droid = RepairDroid(input)
        droid.locateShipsOxygenSystem()
        val shipsBlueprints = droid.downloadShipMappingData()
        val minutes = OxygenSystem(shipsBlueprints).oxygenateShip()
        assertThat(minutes).isEqualTo(326)
    }
}