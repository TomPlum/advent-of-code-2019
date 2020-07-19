package com.aoc.solutions

import com.aoc.input.Day
import com.aoc.input.InputReader
import com.aoc.intcode.tractorbeam.DroneSystem

fun main() {
    val input = InputReader.read<String>(Day.from(19)).asSingleString()
    val system = DroneSystem(input)
    val scan = system.scanAreaSurroundingEmitter(50)
    println("Solution Part 1: ${scan.getPointsAffectedByBeam()} points affected by the beam.")
    println("Solution Part 2: ${system.scanAreaForSantasShip(100)}")
}