package com.aoc.monitoring.eris

import com.aoc.map.MapTile

class ErisScanTile(private val glyph: Char) : MapTile<Char>(glyph) {

    companion object {
        fun empty() = ErisScanTile('.')

        fun bug() = ErisScanTile('#')
    }

    fun isBug() = glyph == '#'

    fun isEmpty() = glyph == '.'

}