package com.aoc.monitoring.eris

import assertk.assertThat
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class ErisScanTileTest {
    @Test
    fun isBugPositive() {
        assertThat(ErisScanTile('#').isBug()).isTrue()
    }

    @ParameterizedTest
    @ValueSource(chars = ['.', 'a', 'T', '1'])
    fun isBugNegative(glyph: Char) {
        assertThat(ErisScanTile(glyph).isBug()).isFalse()
    }
    
    @Test
    fun isEmptyPositive() {
        assertThat(ErisScanTile('.').isEmpty()).isTrue()
    }

    @ParameterizedTest
    @ValueSource(chars = ['#', 'a', 'T', '1'])
    fun isEmptyNegative(glyph: Char) {
        assertThat(ErisScanTile(glyph).isEmpty()).isFalse()
    }
}