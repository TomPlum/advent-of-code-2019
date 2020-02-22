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
        val input = InputReader().readInputAsString("/asteroid/example-one.txt").values
        val map = AsteroidMap(input)
        assertThat(map.getRow(0)).isEqualTo(".#..#")
        assertThat(map.getRow(1)).isEqualTo(".....")
        assertThat(map.getRow(2)).isEqualTo("#####")
        assertThat(map.getRow(3)).isEqualTo("....#")
        assertThat(map.getRow(4)).isEqualTo("...##")
    }

    @Test
    fun lineOfSight() {
        val input = InputReader().readInputAsString("/asteroid/example-one.txt").values
        val map = AsteroidMap(input)
        map.getAsteroidSightLine(1, 0, 4, 4)
    }
}