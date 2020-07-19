package com.aoc.intcode.droid.repair

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.TestInputReader
import com.aoc.input.Day
import com.aoc.input.InputReader
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
        val input = TestInputReader().readInputAsSingleString("/droid/example-droid-input.txt")
        val droid = RepairDroid(input)
        droid.locateShipsOxygenSystem()
        val shipsBlueprints = droid.downloadShipMappingData()
        val minutes = OxygenSystem(shipsBlueprints).oxygenateShip()
        assertThat(minutes).isEqualTo(326)
    }
}