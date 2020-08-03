package com.aoc.intcode.droid.cryo.map.glyphs

enum class EdgeGlyph(val glyph: String) {
    HORIZONTAL("─"),
    VERTICAL("│"),
    HORIZONTAL_DOOR("    "),
    VERTICAL_DOOR(" ");

    override fun toString(): String = glyph
}