package com.aoc.intcode.vacuum

import map.MapTile

data class ScaffoldMapTile(override var value: Char) : MapTile<Char>(value) {

    fun isScaffold() = value == '#'

    fun isIntersection() = value == 'O'

    companion object {
        fun empty() = ScaffoldMapTile('.')
        fun intersection() = ScaffoldMapTile('O')
        fun unknown() = ScaffoldMapTile('!')
    }

}