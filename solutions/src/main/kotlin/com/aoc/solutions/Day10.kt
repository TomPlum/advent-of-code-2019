package com.aoc.solutions

import com.aoc.Day
import com.aoc.input.InputReader
import com.aoc.monitoring.asteroid.AsteroidMap

fun main() {
    val input = InputReader.read<String>(Day(10)).value

    val asteroidMap = AsteroidMap(input)
    println("Solution Part 1: ${asteroidMap.getOptimalAsteroidMappingStationSector().second} asteroids")
    println("Solution Part 2: ${asteroidMap.winBetWithElves()}")
}