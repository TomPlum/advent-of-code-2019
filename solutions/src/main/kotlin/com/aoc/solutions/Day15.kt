package com.aoc.solutions

import com.aoc.input.Day
import com.aoc.input.InputReader
import com.aoc.intcode.droid.repair.OxygenSystem
import com.aoc.intcode.droid.repair.RepairDroid

fun main() {
    val input = InputReader.read<String>(Day.from(15)).asSingleString()
    val repairDroid = RepairDroid(input)
    println("\nSolution Part 1: ${repairDroid.locateShipsOxygenSystem().second} moves")
    println("Solution Part 2: ${OxygenSystem(repairDroid.downloadShipMappingData()).oxygenateShip()} minutes")
}