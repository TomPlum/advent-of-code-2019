package com.aoc.monitoring.asteroid

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.input.Day
import com.aoc.input.InputReader
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class AsteroidMapTest {
    @Test
    @DisplayName("Given valid Asteroid Map input data, when constructing an Asteroid Map, then it should parse it correctly")
    fun initParsesMapDataCorrectly() {
        val input = InputReader().readInputAsString("/asteroid/example-1.txt").values
        val map = AsteroidMap(input)
        assertThat(map.getRow(0)).isEqualTo(".#..#")
        assertThat(map.getRow(1)).isEqualTo(".....")
        assertThat(map.getRow(2)).isEqualTo("#####")
        assertThat(map.getRow(3)).isEqualTo("....#")
        assertThat(map.getRow(4)).isEqualTo("...##")
    }

    @Test
    fun optimalMappingStationSectorExampleOne() {
        val map = AsteroidMap(InputReader().readInputAsString("/asteroid/example-1.txt").values)
        val optimalSector = map.getOptimalAsteroidMappingStationSector()
        assertThat(optimalSector).isEqualTo(Pair(MapSector("#", 3, 4), 8))
    }

    @Test
    fun optimalMappingStationSectorExampleTwo() {
        val map = AsteroidMap(InputReader().readInputAsString("/asteroid/example-2.txt").values)
        val optimalSector = map.getOptimalAsteroidMappingStationSector()
        assertThat(optimalSector).isEqualTo(Pair(MapSector("#", 5, 8), 33))
    }

    @Test
    fun optimalMappingStationSectorExampleThree() {
        val map = AsteroidMap(InputReader().readInputAsString("/asteroid/example-3.txt").values)
        val optimalSector = map.getOptimalAsteroidMappingStationSector()
        assertThat(optimalSector).isEqualTo(Pair(MapSector("#", 1, 2), 35))
    }

    @Test
    fun optimalMappingStationSectorExampleFour() {
        val map = AsteroidMap(InputReader().readInputAsString("/asteroid/example-4.txt").values)
        val optimalSector = map.getOptimalAsteroidMappingStationSector()
        assertThat(optimalSector).isEqualTo(Pair(MapSector("#", 6, 3), 41))
    }

    @Test
    fun optimalMappingStationSectorExampleFive() {
        val map = AsteroidMap(InputReader().readInputAsString("/asteroid/example-5.txt").values)
        val optimalSector = map.getOptimalAsteroidMappingStationSector()
        assertThat(optimalSector).isEqualTo(Pair(MapSector("#", 11, 13), 210))
    }

    @Test
    @DisplayName("Given Day 10 Part 1 puzzle input, when calculating the number of asteroids in the direct line of sight" +
            "of a monitoring station placed in the optimal position, then it should return 263 at (23, 29)")
    fun solutionPartOne() {
        val map = AsteroidMap(InputReader().readInputString(Day.from(10)).values)
        val optimalSector = map.getOptimalAsteroidMappingStationSector()
        assertThat(optimalSector).isEqualTo(Pair(MapSector("#", 23, 29), 263))
    }
}