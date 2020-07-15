package com.aoc.monitoring.eris

import com.aoc.map.MapTile

class ErisScanTile(private val glyph: Char) : MapTile<Char>(glyph) {
    fun isBug() = glyph == '#'

    fun isEmpty() = glyph == '.'
}