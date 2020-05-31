package com.aoc.solutions

import com.aoc.intcode.droid.OxygenSystem
import com.aoc.intcode.droid.RepairDroid
import com.aoc.input.Day
import com.aoc.input.InputReader

fun main() {
    val input = InputReader().readInputAsSingleString(Day.from(15))
    val repairDroid = RepairDroid(input)
    println("\nSolution Part 1: ${repairDroid.locateShipsOxygenSystem().second} moves")
    println("Solution Part 2: ${OxygenSystem(repairDroid.downloadShipMappingData()).oxygenateShip()} minutes")
}