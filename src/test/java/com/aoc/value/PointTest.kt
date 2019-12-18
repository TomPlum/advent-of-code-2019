package com.aoc.value

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class PointTest {
    @ParameterizedTest
    @CsvSource(value = ["1,1,1,1", "2,1,1,2", "-3,-3,-3,-3", "-1,-8,-8,-1"])
    @DisplayName("Given two Points that have the same cartesian coordinate, when checking their equality, they should be equal")
    fun equalityTest(x0: Int, x1: Int, y0: Int, y1: Int) {
        val pointOne = Point(x0, y0)
        val pointTwo = Point(y0, y1)
        assertThat(pointOne).isEqualTo(pointTwo)
    }
}