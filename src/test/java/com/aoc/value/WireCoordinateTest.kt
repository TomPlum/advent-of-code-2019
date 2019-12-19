package com.aoc.value

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotEqualTo
import com.aoc.panel.WireCoordinate
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class WireCoordinateTest {
    @ParameterizedTest
    @CsvSource(value = ["1,1,1,1", "2,2,2,2", "-1,-1,-1,-1"])
    @DisplayName("Given two Points that have the same cartesian coordinate, when checking their equality, they should be equal")
    fun equalityTest(x0: Int, x1: Int, y0: Int, y1: Int) {
        val pointOne = WireCoordinate(x0, y0)
        val pointTwo = WireCoordinate(y0, y1)
        assertThat(pointOne).isEqualTo(pointTwo)
    }

    @ParameterizedTest
    @CsvSource(value = ["1,1,0,1", "1,1,1,-1", "0,2,2,0"])
    @DisplayName("Given two Points that have the same cartesian coordinate, when checking their equality, they should be equal")
    fun inequalityTest(x0: Int, x1: Int, y0: Int, y1: Int) {
        val pointOne = WireCoordinate(x0, y0)
        val pointTwo = WireCoordinate(y0, y1)
        assertThat(pointOne).isNotEqualTo(pointTwo)
    }

}