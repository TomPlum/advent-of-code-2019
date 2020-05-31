package com.aoc.intcode.vacuum.scaffold

import com.aoc.map.MapTile

data class ScaffoldMapTile(override var value: Char) : MapTile<Char>(value) {

    fun isScaffold() = value == '#'

    fun isIntersection() = value == 'O'

    companion object {
        fun empty() = ScaffoldMapTile('.')
        fun intersection() = ScaffoldMapTile('O')
        fun unknown() = ScaffoldMapTile('!')
    }

    override fun toString() = super.toString()

}