package com.aoc.intcode.vacuum

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
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
    fun isScaffoldNegative() {
        assertThat(ScaffoldMapTile('#').isScaffold()).isFalse()
    }

    @Test
    fun empty() {
        assertThat(ScaffoldMapTile.empty()).isEqualTo(ScaffoldMapTile('.'))
    }

    @Test
    fun intersection() {
        assertThat(ScaffoldMapTile.intersection()).isEqualTo(ScaffoldMapTile('O'))
    }
}