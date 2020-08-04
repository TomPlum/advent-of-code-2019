package com.aoc.intcode.droid.cryo.map.glyphs

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class EdgeGlyphTest {
    @Test
    fun horizontal() {
        assertThat(EdgeGlyph.HORIZONTAL.toString()).isEqualTo("─")
    }

    @Test
    fun vertical() {
        assertThat(EdgeGlyph.VERTICAL.toString()).isEqualTo("│")
    }

    @Test
    fun horizontalDoor() {
        assertThat(EdgeGlyph.HORIZONTAL_DOOR.toString()).isEqualTo("    ")
    }

    @Test
    fun verticalDoor() {
        assertThat(EdgeGlyph.VERTICAL_DOOR.toString()).isEqualTo(" ")
    }

}