package com.aoc.intcode.droid.cryo.map.glyphs

enum class CornerGlyph(val glyph: String) {
    CROSS_SECTION("┼"),
    TRIPLE_LEFT("┤"),
    TRIPLE_RIGHT("├"),
    TRIPLE_DOWN("┬"),
    TRIPLE_UP("┴"),
    TOP_LEFT_CORNER("┌"),
    TOP_RIGHT_CORNER("┐"),
    BOTTOM_LEFT_CORNER("└"),
    BOTTOM_RIGHT_CORNER("┘");

    override fun toString(): String = glyph
}