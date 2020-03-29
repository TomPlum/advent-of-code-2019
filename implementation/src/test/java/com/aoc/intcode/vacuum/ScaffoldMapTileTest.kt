package com.aoc.intcode.vacuum

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import input.Day
import input.InputReader
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class ScaffoldMapTileTest {
    @Test
    fun isScaffoldPositive() {
        assertThat(ScaffoldMapTile('#').isScaffold()).isTrue()
    }

    @ParameterizedTest
    @ValueSource(chars = ['.', '^', 'A', '~'])
    fun isScaffoldNegative(value: Char) {
        assertThat(ScaffoldMapTile(value).isScaffold()).isFalse()
    }

    @Test
    fun isIntersectionPositive() {
        assertThat(ScaffoldMapTile('O').isIntersection()).isTrue()
    }

    @ParameterizedTest
    @ValueSource(chars = ['.', '^', 'A', '~', '#'])
    fun isIntersectionNegative(value: Char) {
        assertThat(ScaffoldMapTile(value).isIntersection()).isFalse()
    }

    @Test
    fun empty() {
        assertThat(ScaffoldMapTile.empty()).isEqualTo(ScaffoldMapTile('.'))
    }

    @Test
    fun intersection() {
        assertThat(ScaffoldMapTile.intersection()).isEqualTo(ScaffoldMapTile('O'))
    }

    @Test
    fun solutionPartOne() {
        val instruction = InputReader().readInputAsSingleString(Day.from(17))
        val scaffoldMap = VacuumRobot(instruction).exploreShipsExteriorScaffolding()
        assertThat(scaffoldMap.calculateAlignmentParameterSum()).isEqualTo(7404)
    }
}