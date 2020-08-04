package com.aoc.intcode.droid.cryo.map.glyphs

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class CornerGlyphTest {
    @Test
    fun crossSection() {
        assertThat(CornerGlyph.CROSS_SECTION.toString()).isEqualTo("┼")
    }

    @Test
    fun tripleLeft() {
        assertThat(CornerGlyph.TRIPLE_LEFT.toString()).isEqualTo("┤")
    }

    @Test
    fun tripleRight() {
        assertThat(CornerGlyph.TRIPLE_RIGHT.toString()).isEqualTo("├")
    }

    @Test
    fun tripleDown() {
        assertThat(CornerGlyph.TRIPLE_DOWN.toString()).isEqualTo("┬")
    }

    @Test
    fun tripleUp() {
        assertThat(CornerGlyph.TRIPLE_UP.toString()).isEqualTo("┴")
    }

    @Test
    fun topLeftCorner() {
        assertThat(CornerGlyph.TOP_LEFT_CORNER.toString()).isEqualTo("┌")
    }

    @Test
    fun topRightCorner() {
        assertThat(CornerGlyph.TOP_RIGHT_CORNER.toString()).isEqualTo("┐")
    }

    @Test
    fun bottomLeftCorner() {
        assertThat(CornerGlyph.BOTTOM_LEFT_CORNER.toString()).isEqualTo("└")
    }

    @Test
    fun bottomRightCorner() {
        assertThat(CornerGlyph.BOTTOM_RIGHT_CORNER.toString()).isEqualTo("┘")
    }

}