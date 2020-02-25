package com.aoc.monitoring.asteroid

import assertk.assertThat
import assertk.assertions.isEqualTo
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
}